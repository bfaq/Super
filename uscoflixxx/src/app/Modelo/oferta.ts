import { TokenizeOptions } from '@angular/compiler/src/ml_parser/lexer';
import { Tipo } from './Tipo';
import { Supermercado } from './Supermercado';
export class Oferta {
  id?: number;
  nombreProducto: string;
  tipo: Tipo;
  imagen: string;
  descripcion: string;
  vigencia: string;
  precio: string;
  supermercado: Supermercado;
}
