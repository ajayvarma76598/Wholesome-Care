import { TestBed } from '@angular/core/testing';

import { RegisterCommonService } from './register-common.service';

describe('SoulmateCommonService', () => {
  let service: RegisterCommonService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisterCommonService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
