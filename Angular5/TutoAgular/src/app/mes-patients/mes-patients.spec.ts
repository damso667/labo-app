import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesPatients } from './mes-patients';

describe('MesPatients', () => {
  let component: MesPatients;
  let fixture: ComponentFixture<MesPatients>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesPatients]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MesPatients);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
