import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Reactifs } from './reactifs';

describe('Reactifs', () => {
  let component: Reactifs;
  let fixture: ComponentFixture<Reactifs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Reactifs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Reactifs);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
