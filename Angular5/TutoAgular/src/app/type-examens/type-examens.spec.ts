import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeExamens } from './type-examens';

describe('TypeExamens', () => {
  let component: TypeExamens;
  let fixture: ComponentFixture<TypeExamens>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TypeExamens]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TypeExamens);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
