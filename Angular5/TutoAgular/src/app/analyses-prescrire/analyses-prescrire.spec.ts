import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysesPrescrire } from './analyses-prescrire';

describe('AnalysesPrescrire', () => {
  let component: AnalysesPrescrire;
  let fixture: ComponentFixture<AnalysesPrescrire>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalysesPrescrire]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalysesPrescrire);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
