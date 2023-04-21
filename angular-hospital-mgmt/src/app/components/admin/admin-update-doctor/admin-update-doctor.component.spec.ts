import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUpdateDoctorComponent } from './admin-update-doctor.component';

describe('AdminUpdateDoctorComponent', () => {
  let component: AdminUpdateDoctorComponent;
  let fixture: ComponentFixture<AdminUpdateDoctorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminUpdateDoctorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUpdateDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
