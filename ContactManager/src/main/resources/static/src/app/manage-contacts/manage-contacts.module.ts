import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavBarContactsComponent } from './nav-bar-contacts/nav-bar-contacts.component';
import { SearchAndEditContactsComponent } from './search-and-edit-contacts/search-and-edit-contacts.component';
import { UploadContactsComponent } from './upload-contacts/upload-contacts.component';
import { ViewContactsComponent } from './view-contacts/view-contacts.component';
import { RouterModule } from '@angular/router';
import { SearchFilterPipe } from '../search-filter.pipe';
import { FormsModule } from '@angular/forms';
import { ContactService } from './contact.service';





@NgModule({
  declarations: [
    NavBarContactsComponent,
    SearchAndEditContactsComponent,
    UploadContactsComponent,
    ViewContactsComponent,
    SearchFilterPipe
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  exports: [
    NavBarContactsComponent
  ],
  providers: []
})
export class ManageContactsModule { }