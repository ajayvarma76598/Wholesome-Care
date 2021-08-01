import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MentorProfileHeadComponent } from './mentor-profile-head.component';

describe('MentorProfileHeadComponent', () => {
  let component: MentorProfileHeadComponent;
  let fixture: ComponentFixture<MentorProfileHeadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MentorProfileHeadComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MentorProfileHeadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
