import { TestBed } from '@angular/core/testing';

import { Secretaire } from './secretaire';

describe('Secretaire', () => {
  let service: Secretaire;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Secretaire);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
