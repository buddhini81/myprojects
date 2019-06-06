import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs/index";
import { ApiResponse } from '../model/api.response';
import { ContactUpdate } from '../model/contact.update';
import { Contact } from '../model/contact.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  baseUrl: string = environment.baseUrl;
  headers1 : any;

  constructor(private http: HttpClient) { 
    this.headers1 = new Headers();
    this.headers1.append('Access-Control-Allow-Origin', '*');
    this.headers1.append('Access-Control-Allow-Methods', 'GET, POST, DELETE, PUT');
  }

  getAllContacts() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl+'/getAll',{
      headers: this.headers1
    });
  }

  getAllSorted() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl+'/getAllSorted',{
      headers: this.headers1
    });
  }

  saveContacts() : Observable<ApiResponse> {
    return this.http.post<ApiResponse>(this.baseUrl+'/save',{
      headers: this.headers1
    });
  }

  updateContact(update: ContactUpdate) : Observable<ApiResponse> {
    return this.http.put<ApiResponse>(this.baseUrl+'/update',update,{
      headers: this.headers1
    })
  }

  deleteAllContacts() : Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl+'/deleteAll',{
      headers: this.headers1
    });
  }

  deleteContact(id: String) : Observable<ApiResponse> {
    return this.http.delete<ApiResponse>(this.baseUrl+'/delete/' + id,{
      headers: this.headers1
    });
  }

  exportContacts() : Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl+'/export',{
      headers: this.headers1
    });
  }

}
