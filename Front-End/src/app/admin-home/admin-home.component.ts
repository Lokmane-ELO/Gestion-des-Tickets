import { Component } from '@angular/core';
import { Ticket } from "./ticket.model";
import { Technicien } from "./tichnicien.model";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {forkJoin} from "rxjs";


interface TechnicienDetail extends Technicien {
  openTicketCount?: number;
  closedTicketCount?: number;
}

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
  tickets: Ticket[] = [];
  techniciens: TechnicienDetail[] = [];
  ticketToAssign: number = 0;
  technicienToAssign: number = 0;

  dateActuelle: string = "";

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {

      this.http.get<Ticket[]>('http://localhost:8080/api/tickets').subscribe(tickets => {
        this.tickets = tickets;

        const interventionObservables = tickets.map(ticket =>
          this.http.get<any>(`http://localhost:8080/api/interventions/${ticket.numTicket}`));

        forkJoin(interventionObservables).subscribe(interventions => {
          for (let i = 0; i < interventions.length; i++) {
            this.tickets[i].interventionDuration = interventions[i].dureeIntervention;
          }
        });
      });

    this.http.get<Technicien[]>('http://localhost:8080/api/techniciens').subscribe(techniciens => {
      this.techniciens = techniciens;

      for (const technicien of this.techniciens) {
        this.http.get<number>(`http://localhost:8080/api/techniciens/${technicien.codeUser}/tickets/open/count`).subscribe(count => {
          technicien.openTicketCount = count;
        });

        this.http.get<number>(`http://localhost:8080/api/techniciens/${technicien.codeUser}/tickets/closed/count`).subscribe(count => {
          technicien.closedTicketCount = count;
        });
      }
    });
  }

  assignTicket() {
    const maintenant = new Date();

    // Formatez la date en utilisant les méthodes de 'Date'
    const annee = maintenant.getFullYear();
    const mois = this.padNumber(maintenant.getMonth() + 1);
    const jour = this.padNumber(maintenant.getDate());
    const heures = this.padNumber(maintenant.getHours());
    const minutes = this.padNumber(maintenant.getMinutes());
    const secondes = this.padNumber(maintenant.getSeconds());

    this.dateActuelle = `${annee}-${mois}-${jour}T${heures}:${minutes}:${secondes}`;

    this.http.post(`http://localhost:8080/api/tickets/${this.ticketToAssign}/assign/${this.technicienToAssign}`,
      {interventionDate : this.dateActuelle})
      .subscribe(response => {
        console.log(response);
        alert('Ticket affecter ! ')
      }, error => {
        console.log(error);
        alert('Failed to assign ticket!');
      });
  }
  loadInterventionDurations() {
    for (const ticket of this.tickets) {
      this.http.get<any>(`http://localhost:8080/api/interventions/${ticket.numTicket}`).subscribe(intervention => {
        ticket.interventionDuration = intervention.dureeIntervention;
      });
    }
  }
  private padNumber(nombre: number): string {
    return nombre < 10 ? '0' + nombre : '' + nombre;
  }

  navigateTo(path: string) {
    this.router.navigate([`/${path}`]);
  }
}
