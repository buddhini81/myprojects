import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchAndEditContactsComponent } from './search-and-edit-contacts.component';

describe('SearchAndEditContactsComponent', () => {
  let component: SearchAndEditContactsComponent;
  let fixture: ComponentFixture<SearchAndEditContactsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchAndEditContactsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchAndEditContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
