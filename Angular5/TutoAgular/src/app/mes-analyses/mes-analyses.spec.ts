import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesAnalyses } from './mes-analyses';

describe('MesAnalyses', () => {
  let component: MesAnalyses;
  let fixture: ComponentFixture<MesAnalyses>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesAnalyses]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MesAnalyses);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
