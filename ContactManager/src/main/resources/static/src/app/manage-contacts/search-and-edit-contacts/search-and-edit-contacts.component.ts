import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import 'datatables.net-dt';
import 'datatables.net-bs4';
import 'datatables.net-select-bs4';
import { DownloadFileService } from 'src/app/common-services/download-file.service';
import { ContactService } from '../contact.service';
import { Contact } from 'src/app/model/contact.model';
import { HttpResponse } from '@angular/common/http';
import { ContactUpdate } from 'src/app/model/contact.update';
import { saveAs } from 'file-saver';



@Component({
  selector: 'app-search-and-edit-contacts',
  templateUrl: './search-and-edit-contacts.component.html',
  styleUrls: ['./search-and-edit-contacts.component.css']
})
export class SearchAndEditContactsComponent implements OnInit {

  tableData: Contact[];
  tableWidget: any;
  deleteIndexes: any[];
  selectedContact: any;
  selectedIndex: number;
  contactToChange: string;
  message:string;
  btnClass:string;
 


  constructor(private downloadService: DownloadFileService, private contactService: ContactService) {
  }

  ngOnInit() {

    this.contactService.getAllContacts()
      .subscribe(data => {
        if (data.status == 200) {
          this.tableData = data.result;
          this.populateDataTable(this.tableData);
        } else {
          this.message = data.message;
          this.btnClass = "btn btn-danger";
          jQuery('#statusModal').modal('show');
        }
      }, err => {
        this.message = "Failed to load app!";
        this.btnClass = "btn btn-dark";
        jQuery('#statusModal').modal('show');
      });

  }

  populateDataTable(tblData: Contact[]) {
    let contactsTable: any = $('#contacts-table');
    this.tableWidget = contactsTable.DataTable({
      data: this.tableData,
      columns: [
        {
          title: 'Select',
          data: null,
          defaultContent: '',
          className: 'select-checkbox',
          width: '5%'
        },
        { title: 'Email', data: "email", width: '95%' }
      ],
      select: {
        style: 'single',
        info: false
      },
      pagingType: "full_numbers"
    });
  }

  deleteSelectedRow() {
    if (this.tableWidget != undefined) {
      this.selectedContact = this.tableWidget.rows('.selected').data()[0];
      this.selectedIndex = this.tableData.indexOf(this.selectedContact);
      console.log(this.selectedContact.id);

      this.contactService.deleteContact(this.selectedContact.id).subscribe(data => {
        this.message = data.message;
        if (data.status === 200) {
          this.tableData.splice(this.selectedIndex, 1);
          this.tableWidget.rows('.selected')
            .remove()
            .draw();

          console.log(JSON.stringify(this.tableData));
          this.btnClass = "btn btn-success";
        } else {
          this.btnClass = "btn btn-danger"; 
        }
        jQuery('#statusModal').modal('show');
      } ,err => {
        this.message = "Failed to load app!";
        this.btnClass = "btn btn-dark";
        jQuery('#statusModal').modal('show');
      });
    }
  }

  deleteAllRows() {
    if (this.tableWidget != undefined) {

      this.contactService.deleteAllContacts().subscribe(data => {
        this.message = data.message;
        if (data.status === 200) {
          this.tableWidget.rows()
          .remove()
          .draw();
          
          this.btnClass = "btn btn-success";
        } else {
          this.btnClass = "btn btn-danger";
        }
        jQuery('#statusModal').modal('show');
      }, err => {
        this.message = "Failed to load app!";
        this.btnClass = "btn btn-dark";
        jQuery('#statusModal').modal('show');
      });
    }
  }

  changeSelectedRow() {
    if (this.tableWidget != undefined) {
      this.selectedContact = this.tableWidget.rows('.selected').data()[0];
      this.selectedIndex = this.tableData.indexOf(this.selectedContact);
      console.log(this.selectedIndex);
      this.contactToChange = this.selectedContact.email;
    }
  }

  exportContacts() {
    if (this.tableWidget != undefined) {
      this.contactService.exportContacts()
        .subscribe(data => {
          this.message = data.message;
          if(data.status == 200) {
            this.btnClass = "btn btn-success";
          } else {
            this.btnClass = "btn btn-danger";
          }
          jQuery('#statusDownloadModal').modal('show');
        }, err => {
          this.message = "Failed to load app!";
          this.btnClass = "btn btn-dark";
          jQuery('#statusDownloadModal').modal('show');
        });
    }
  }

  download() {
    this.downloadService.downloadFileFromStorage().subscribe(data => {
    if (data instanceof HttpResponse) {
        if(data.status === 200) {
          const fileName ='exported-contacts.xlsx';
          saveAs(data.body, fileName);
        } else {
          this.message = data.statusText + " - Download was not succesful!";
          this.btnClass = 'btn btn-danger';
        }
      }
    } ,err => {
      this.message = "Failed to load app!";
      this.btnClass = "btn btn-dark";
      jQuery('#statusModal').modal('show');
    });

  }

  onSaveModal() {
    if (this.tableWidget != undefined) {

      let updateObj : ContactUpdate;
      updateObj = new ContactUpdate(this.selectedContact.id,this.selectedContact.email,this.contactToChange);

      console.log('%%%%% ' + updateObj.newVal + '%%%%%')
      this.contactService.updateContact(updateObj).subscribe(data => {
        this.message = data.message;
        if (data.status === 200) {
          this.tableData[this.selectedIndex].email = this.contactToChange;
          this.tableWidget.cell(this.selectedIndex, 1).data(this.contactToChange);
          console.log(JSON.stringify(this.tableData));

          this.btnClass = "btn btn-success";
        } else {
          this.btnClass = "btn btn-danger";
        }
        jQuery('#statusModal').modal('show');
      }, err => {
        this.message = "Failed to load app!";
        this.btnClass = "btn btn-dark";
        jQuery('#statusModal').modal('show');
      });
    }
  }

  isInvalid() {

    if ((this.tableWidget != undefined) && (this.tableWidget.rows('.selected').data()[0] == undefined)) {
      return true;
    } else {
      return false;
    }
  }


}
