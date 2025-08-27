import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Prelevements } from './prelevements';

describe('Prelevements', () => {
  let component: Prelevements;
  let fixture: ComponentFixture<Prelevements>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Prelevements]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Prelevements);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
