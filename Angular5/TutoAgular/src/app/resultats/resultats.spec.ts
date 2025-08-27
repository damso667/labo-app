import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Resultats } from './resultats';

describe('Resultats', () => {
  let component: Resultats;
  let fixture: ComponentFixture<Resultats>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Resultats]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Resultats);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
