import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainBar } from './main-bar';

describe('MainBar', () => {
  let component: MainBar;
  let fixture: ComponentFixture<MainBar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainBar],
    }).compileComponents();

    fixture = TestBed.createComponent(MainBar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
