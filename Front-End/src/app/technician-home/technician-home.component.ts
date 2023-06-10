import {Component, OnInit} from '@angular/core';
import {TicketService} from "../TicketService";
import {AuthService} from "../AuthService";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import {Technicien} from "../admin-home/tichnicien.model";
import {Ticket} from "../admin-home/ticket.model";


@Component({
  selector: 'app-technician-home',
  templateUrl: './technician-home.component.html',
  styleUrls: ['./technician-home.component.css']
})
export class TechnicianHomeComponent implements OnInit{
  technicienId: number = 0;
  interventions: any[] = [];
  tickets:any;

  numTicket: number = 0;

  duration:number = 0;

  technicien : any;

  constructor(private ticketService: TicketService,private authService:AuthService, private http : HttpClient, private router:Router) { }

  ngOnInit() {
    this.authService.technicienId$.subscribe(id => {
      this.technicienId = id;
      this.loadTickets();
    });

    this.http.get<Technicien>('http://localhost:8080/api/techniciens/'+this.technicienId).subscribe(techniciens => {
      this.technicien = techniciens;
    });
  }

  private loadTickets() {
    this.ticketService.getTicketsByTechnicienId(this.technicienId).subscribe(data => {
      // Save the full intervention data
      this.interventions = data;
      // And extract the tickets for display
      this.tickets = data.map((intervention: any) => intervention.ticket);
    });
  }

  closeTicket() {
    const maintenant = new Date();

    // Format the date using 'Date' methods
    const annee = maintenant.getFullYear();
    const mois = this.padNumber(maintenant.getMonth() + 1);
    const jour = this.padNumber(maintenant.getDate());
    const heures = this.padNumber(maintenant.getHours());
    const minutes = this.padNumber(maintenant.getMinutes());
    const secondes = this.padNumber(maintenant.getSeconds());

    // Build the date in the desired format
    const dateActuelle = `${annee}-${mois}-${jour}T${heures}:${minutes}:${secondes}`;

    const url = "http://localhost:8080/api/interventions/close/"+ this.numTicket;
    const body = {
      duration: this.duration,
      closeDate: dateActuelle
    };

    this.http.put(url, body).subscribe(() => {
        // Find the ticket in the local list
        const ticket = this.tickets.find((t:any) => t.numTicket === this.numTicket);

        // Find the intervention in the local list
        const intervention = this.interventions.find(i => i.ticket.numTicket === this.numTicket);

        // Update the intervention duration locally
        if (intervention) {
          intervention.dureeIntervention = this.duration;
        }

        // Update the ticket intervention duration locally as well
        if (ticket) {
          ticket.interventionDuration = this.duration;
        }

        // Reload the tickets list to get any other updates
        this.loadTickets();
      },
      error => {
        console.error('Error:', error);
      });

  }

  private padNumber(nombre: number): string {
    return nombre < 10 ? '0' + nombre : '' + nombre;
  }

  navigateTo(path: string) {
    this.router.navigate([`/${path}`]);
  }
}
