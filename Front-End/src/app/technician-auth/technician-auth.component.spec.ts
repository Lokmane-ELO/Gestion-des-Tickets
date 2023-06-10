import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicianAuthComponent } from './technician-auth.component';

describe('TechnicianAuthComponent', () => {
  let component: TechnicianAuthComponent;
  let fixture: ComponentFixture<TechnicianAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TechnicianAuthComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TechnicianAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
