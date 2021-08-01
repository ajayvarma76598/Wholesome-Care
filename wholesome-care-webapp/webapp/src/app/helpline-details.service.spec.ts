import { TestBed } from '@angular/core/testing';

import { HelplineDetailsService } from './helpline-details.service';

describe('HelplineDetailsService', () => {
  let service: HelplineDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HelplineDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
