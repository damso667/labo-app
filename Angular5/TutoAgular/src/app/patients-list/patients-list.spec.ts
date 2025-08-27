import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientsList } from './patients-list';

describe('PatientsList', () => {
  let component: PatientsList;
  let fixture: ComponentFixture<PatientsList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientsList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientsList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
