import { Component, OnInit } from '@angular/core';
import { ContactService } from '../contact.service';
import { ContactView } from 'src/app/model/contact.view';



@Component({
  selector: 'app-view-contacts',
  templateUrl: './view-contacts.component.html',
  styleUrls: ['./view-contacts.component.css']
})
export class ViewContactsComponent implements OnInit {

  
  contacts : any[];
  contactToChange : string;
  changeIndex : number;
  search: string;
  contactV : ContactView[];

  constructor(private contactService : ContactService) { 
    this.contactV = [];
  }

  ngOnInit() {
    this.contactService.getAllSorted()
    .subscribe(data => {
      this.contactV = data.result
      console.log(data.result);
    });
  }

  onRemove(contact) {
     let index = this.contacts.indexOf(contact);
     this.contacts.splice(index,1);
  }

  onChange(contact, idx) {
    this.contactToChange = contact.name;
    this.changeIndex = idx;
    console.log("from onChange " + this.changeIndex);
  }

  onSaveModal() {
    console.log("from onSaveModal " + this.changeIndex);
    this.contacts[this.changeIndex].name = this.contactToChange;
  }

}
