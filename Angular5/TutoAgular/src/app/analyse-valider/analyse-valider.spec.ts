import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyseValider } from './analyse-valider';

describe('AnalyseValider', () => {
  let component: AnalyseValider;
  let fixture: ComponentFixture<AnalyseValider>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnalyseValider]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalyseValider);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
