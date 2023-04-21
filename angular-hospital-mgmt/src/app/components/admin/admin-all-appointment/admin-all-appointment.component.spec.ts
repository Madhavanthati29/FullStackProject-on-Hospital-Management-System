import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAllAppointmentComponent } from './admin-all-appointment.component';

describe('AdminAllAppointmentComponent', () => {
  let component: AdminAllAppointmentComponent;
  let fixture: ComponentFixture<AdminAllAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAllAppointmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAllAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
