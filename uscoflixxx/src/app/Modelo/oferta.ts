import { TokenizeOptions } from '@angular/compiler/src/ml_parser/lexer';

export class Oferta {
  id?: number;
  nombreProducto: string;
  tipo: string;
  imagen: string;
  descripcion: string;
  vigencia: string;
  precio: string;
  supermercado: number;
}
