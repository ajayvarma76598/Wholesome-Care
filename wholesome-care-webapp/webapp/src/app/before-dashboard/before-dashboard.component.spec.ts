import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BeforeDashboardComponent } from './before-dashboard.component';

describe('BeforeDashboardComponent', () => {
  let component: BeforeDashboardComponent;
  let fixture: ComponentFixture<BeforeDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BeforeDashboardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BeforeDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
