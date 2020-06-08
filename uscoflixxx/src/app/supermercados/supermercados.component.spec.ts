import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SupermercadosComponent } from './supermercados.component';

describe('SupermercadosComponent', () => {
  let component: SupermercadosComponent;
  let fixture: ComponentFixture<SupermercadosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SupermercadosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupermercadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
