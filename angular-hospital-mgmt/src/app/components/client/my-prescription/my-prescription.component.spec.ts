import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPrescriptionComponent } from './my-prescription.component';

describe('MyPrescriptionComponent', () => {
  let component: MyPrescriptionComponent;
  let fixture: ComponentFixture<MyPrescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyPrescriptionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyPrescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
