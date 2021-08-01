import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NegativeComponent } from './negative.component';

describe('NegativeComponent', () => {
  let component: NegativeComponent;
  let fixture: ComponentFixture<NegativeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NegativeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NegativeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
