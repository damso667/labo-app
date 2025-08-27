import { TestBed } from '@angular/core/testing';

import { Technicien } from './technicien';

describe('Technicien', () => {
  let service: Technicien;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Technicien);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
