import { Contact } from './contact.model';

export class ContactView{
    group : string;
    contacts : Contact[];
  
    constructor() {
    }

    setGroup(group:string) {
      this.group = group;
    }

    setContacts(contacts:Contact[]) {
        this.contacts = contacts;
    }
}