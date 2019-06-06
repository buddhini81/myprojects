import { Component, OnInit } from '@angular/core';
import { UploadFileService } from 'src/app/common-services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-upload-contacts',
  templateUrl: './upload-contacts.component.html',
  styleUrls: ['./upload-contacts.component.css']
})
export class UploadContactsComponent implements OnInit {

  selectedFiles: FileList;
  currentFileUpload: File;
  uploadComplete: boolean;
  message:string;
  btnClass:string;

  constructor(private uploadService: UploadFileService, private contactService: ContactService) { }

  ngOnInit() {
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    console.log(event.target.files);
  }

  upload() {
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
    if (event instanceof HttpResponse) {
        if(event.status === 200) {
          this.uploadComplete = true;
          this.message = event.statusText + " - Uploaded Successfully!";
          this.btnClass = 'btn btn-success';
        } else {
          this.message = event.statusText + " - Uploaded was not succesful!";
          this.btnClass = 'btn btn-danger';
        }

        jQuery('#statusModal').modal('show');
      }
    } ,err => {
      this.message = "Failed to load app!";
      this.btnClass = "btn btn-dark";
      jQuery('#statusModal').modal('show');
    });
 
    this.selectedFiles = undefined;
  }

  save() {
    this.contactService.saveContacts().subscribe(resp => {
      this.message = resp.message;
      if (resp.status === 200) {
        this.btnClass = 'btn btn-success';
      } else {
        this.btnClass = 'btn btn-danger';
      }
      jQuery('#statusModal').modal('show');
    },err => {
      this.message = "Failed to load app!";
      this.btnClass = "btn btn-dark";
      jQuery('#statusModal').modal('show');
    });
  }

}
