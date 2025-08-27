import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalysesAFaire } from './analyses-a-faire';

describe('AnalysesAFaire', () => {
  let component: AnalysesAFaire;
  let fixture: ComponentFixture<AnalysesAFaire>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalysesAFaire]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalysesAFaire);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
