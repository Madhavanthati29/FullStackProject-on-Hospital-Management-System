import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorAppointmnetListComponent } from './doctor-appointmnet-list.component';

describe('DoctorAppointmnetListComponent', () => {
  let component: DoctorAppointmnetListComponent;
  let fixture: ComponentFixture<DoctorAppointmnetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorAppointmnetListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorAppointmnetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
