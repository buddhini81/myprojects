import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ManageContactsModule } from './manage-contacts/manage-contacts.module';
import { RouterModule } from '@angular/router';
import { UploadContactsComponent } from './manage-contacts/upload-contacts/upload-contacts.component';
import { ViewContactsComponent } from './manage-contacts/view-contacts/view-contacts.component';
import { SearchAndEditContactsComponent } from './manage-contacts/search-and-edit-contacts/search-and-edit-contacts.component';
import { ContactService } from './manage-contacts/contact.service';
import { NavBarContactsComponent } from './manage-contacts/nav-bar-contacts/nav-bar-contacts.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoadingScreenComponent } from './common-component/loading-screen/loading-screen.component';
import { LoadingScreenInterceptor } from './common-component/loading-screen/loading.interceptor';




@NgModule({
  declarations: [
    AppComponent,
    LoadingScreenComponent
  ],
  imports: [
    BrowserModule,
    ManageContactsModule,
    AppRoutingModule,
    RouterModule.forRoot([
      {path:'upload',component: UploadContactsComponent},
      {path:'view',component: ViewContactsComponent},
      {path:'search-edit',component: SearchAndEditContactsComponent}
    ]),
    HttpClientModule
  ],
  providers: [ContactService,  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoadingScreenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
