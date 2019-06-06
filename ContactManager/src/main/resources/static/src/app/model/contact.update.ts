import { Contact } from './contact.model';

export class ContactUpdate {
    id : number;
    email : string;
    newVal : string;
  
    constructor(id:number,email:string,newVal:string) {
      this.id = id;
      this.email = email;
      this.newVal = newVal;
    }
}