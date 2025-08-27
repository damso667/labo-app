import { TestBed } from '@angular/core/testing';

import { TypeExamen } from './type-examen';

describe('TypeExamen', () => {
  let service: TypeExamen;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeExamen);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
