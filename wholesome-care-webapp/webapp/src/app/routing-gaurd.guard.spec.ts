import { TestBed } from '@angular/core/testing';

import { RoutingGaurdGuard } from './routing-gaurd.guard';

describe('RoutingGaurdGuard', () => {
  let guard: RoutingGaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RoutingGaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
