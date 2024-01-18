import { Component } from '@angular/core';

import { AutoService } from 'src/app/services/auto.service';
import { Auto } from '../modelo/Auto';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent {
  autos: any

  aut: Auto = new Auto()



  constructor(private autoService: AutoService){

  }

  ngOnInit(): void {
      this.autos = this.autoService.getClientes()
  }

  guardar(){
    if(this.aut.codigo){
    this.autoService.actualizarCliente(this.aut).subscribe(data => {
      console.log(data);
      if (data.codigo !== 0) {
        this.ngOnInit();
      } else {
        alert("Error al actualizar " + data.mensaje);
      }
    });
  }else{
    this.autoService.saveCliente(this.aut).subscribe(data => {
      console.log(data)
      if(data.codigo !== 0)
        this.ngOnInit()
      else
        alert("Error al insertar " + data.mensaje)
    });
  }
  }

  actualizar(){
    
  }

  eliminar(codigo: number){
      this.autoService.deleteCliente(codigo).subscribe(data => {
        console.log(data);
    });
    this.ngOnInit();
  }
}
