import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpEvent, HttpRequest, HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DownloadFileService {

  public baseUrl: string = environment.baseUrl+'/download';

  constructor(private http: HttpClient) { }

  public downloadFileFromStorage() {
    const req = new HttpRequest('GET', this.baseUrl, {
      responseType: 'blob'
    });
 
    return this.http.request(req);
  }
}
