import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class TicketService {

  private baseURL = 'http://localhost:8080/api/interventions/technicien';
  dateActuelle: string = "";

  constructor(private http: HttpClient) { }

  getTicketsByTechnicienId(technicienId: number): Observable<any> {
    return this.http.get(`${this.baseURL}/${technicienId}`);
  }

  closeTicket(ticketId: number, duration: number): Observable<any> {
    const maintenant = new Date();

    // Formatez la date en utilisant les méthodes de 'Date'
    const annee = maintenant.getFullYear();
    const mois = this.padNumber(maintenant.getMonth() + 1);
    const jour = this.padNumber(maintenant.getDate());
    const heures = this.padNumber(maintenant.getHours());
    const minutes = this.padNumber(maintenant.getMinutes());
    const secondes = this.padNumber(maintenant.getSeconds());

    // Construisez la date au format souhaité
    this.dateActuelle = `${annee}-${mois}-${jour}T${heures}:${minutes}:${secondes}`;

    const url = `http://localhost:8080/api/interventions/close/${ticketId}`;
    const body = {
      duration: duration,
      closeDate: this.dateActuelle
    };
    console.log(body);
    return this.http.put(url, body);
  }






  private padNumber(nombre: number): string {
    return nombre < 10 ? '0' + nombre : '' + nombre;
  }
}
