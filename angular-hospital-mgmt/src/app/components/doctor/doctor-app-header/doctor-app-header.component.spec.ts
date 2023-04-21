import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorAppHeaderComponent } from './doctor-app-header.component';

describe('DoctorAppHeaderComponent', () => {
  let component: DoctorAppHeaderComponent;
  let fixture: ComponentFixture<DoctorAppHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorAppHeaderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorAppHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
