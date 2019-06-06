import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';


@Component({
  selector: 'app-nav-bar-contacts',
  templateUrl: './nav-bar-contacts.component.html',
  styleUrls: ['./nav-bar-contacts.component.css']
})
export class NavBarContactsComponent implements OnInit {

  backgroundColor = environment.navBarContactsBackgroundColor;
  
  constructor() { }

  ngOnInit() {
  }

}
