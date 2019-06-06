import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavBarContactsComponent } from './nav-bar-contacts.component';

describe('NavBarContactsComponent', () => {
  let component: NavBarContactsComponent;
  let fixture: ComponentFixture<NavBarContactsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavBarContactsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavBarContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
