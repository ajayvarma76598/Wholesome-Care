import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomizedPlanComponent } from './customized-plan.component';

describe('CustomizedPlanComponent', () => {
  let component: CustomizedPlanComponent;
  let fixture: ComponentFixture<CustomizedPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomizedPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomizedPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
