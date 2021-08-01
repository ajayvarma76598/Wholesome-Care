import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FollowNotificationComponent } from './follow-notification.component';

describe('FollowNotificationComponent', () => {
  let component: FollowNotificationComponent;
  let fixture: ComponentFixture<FollowNotificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FollowNotificationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FollowNotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
