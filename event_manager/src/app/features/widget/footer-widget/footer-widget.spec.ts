import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterWidget } from './footer-widget';

describe('FooterWidget', () => {
  let component: FooterWidget;
  let fixture: ComponentFixture<FooterWidget>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FooterWidget]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterWidget);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
