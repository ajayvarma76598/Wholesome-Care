import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SucessMessageComponent } from './sucess-message.component';

describe('SucessMessageComponent', () => {
  let component: SucessMessageComponent;
  let fixture: ComponentFixture<SucessMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SucessMessageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SucessMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
