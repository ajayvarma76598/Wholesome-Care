import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSlotsComponent } from './edit-slots.component';

describe('EditSlotsComponent', () => {
  let component: EditSlotsComponent;
  let fixture: ComponentFixture<EditSlotsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditSlotsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSlotsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
