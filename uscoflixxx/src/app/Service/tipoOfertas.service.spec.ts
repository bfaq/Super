import { TestBed } from '@angular/core/testing';

import { TipoOfertasService } from './tipoOfertas.service';

describe('TipoOfertasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TipoOfertasService = TestBed.get(TipoOfertasService);
    expect(service).toBeTruthy();
  });
});
