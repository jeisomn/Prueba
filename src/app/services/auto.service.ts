import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environments } from '../environments/environments';
import { Auto } from '../pages/modelo/Auto';



@Injectable({
  providedIn: 'root'
})
export class AutoService {

  constructor(private http: HttpClient) { }

  getClientes(){
    let url = environments.WS_PATH + "/Autos/lista"
    return this.http.get<any>(url)
  }

  saveCliente(auto: Auto){
    let url = environments.WS_PATH + "/Autos"
    return this.http.post<any>(url, auto)
  }

  deleteCliente(codigo: number) {
    let url = environments.WS_PATH + '/Autos?id=' + codigo;
    console.log(url);
    return this.http.delete<any>(url)
  }

  actualizarCliente(auto: Auto){
    let url = environments.WS_PATH + "/Autos"; // Ajusta la URL según tu API
    return this.http.put<any>(url, auto);
  }

}
