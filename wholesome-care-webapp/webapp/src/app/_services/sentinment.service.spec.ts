import { TestBed } from '@angular/core/testing';

import { SentinmentService } from './sentinment.service';

describe('SentinmentService', () => {
  let service: SentinmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SentinmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
