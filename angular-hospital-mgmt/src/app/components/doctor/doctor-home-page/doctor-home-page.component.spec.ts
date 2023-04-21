import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctorHomePageComponent } from './doctor-home-page.component';

describe('DoctorHomePageComponent', () => {
  let component: DoctorHomePageComponent;
  let fixture: ComponentFixture<DoctorHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoctorHomePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoctorHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
