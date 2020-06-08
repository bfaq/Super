import { TestBed } from '@angular/core/testing';

import { BasicAuthHtppInterceptService } from './basic-auth-htpp-intercept.service';

describe('BasicAuthHtppInterceptService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BasicAuthHtppInterceptService = TestBed.get(BasicAuthHtppInterceptService);
    expect(service).toBeTruthy();
  });
});
