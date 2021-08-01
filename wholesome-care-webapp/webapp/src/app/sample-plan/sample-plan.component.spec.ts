import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SamplePlanComponent } from './sample-plan.component';

describe('SamplePlanComponent', () => {
  let component: SamplePlanComponent;
  let fixture: ComponentFixture<SamplePlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SamplePlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SamplePlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
