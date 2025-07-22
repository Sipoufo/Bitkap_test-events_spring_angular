import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupWidget } from './popup-widget';

describe('PopupWidget', () => {
  let component: PopupWidget;
  let fixture: ComponentFixture<PopupWidget>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PopupWidget]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopupWidget);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
