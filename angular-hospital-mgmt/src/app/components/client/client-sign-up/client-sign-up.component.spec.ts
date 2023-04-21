import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientSignUpComponent } from './client-sign-up.component';

describe('ClientSignUpComponent', () => {
  let component: ClientSignUpComponent;
  let fixture: ComponentFixture<ClientSignUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientSignUpComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientSignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
