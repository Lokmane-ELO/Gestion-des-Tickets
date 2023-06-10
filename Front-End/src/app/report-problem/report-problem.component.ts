import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-report-problem',
  templateUrl: './report-problem.component.html',
  styleUrls: ['./report-problem.component.css']
})
export class ReportProblemComponent {
  description: string = "";
  priority: string = "";
  dateActuelle: string = "";
  constructor(private http: HttpClient, private router: Router) { }

  onSubmit() {
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

    const body = {
      description: this.description,
      priorite: this.priority,
      dateCreation: this.dateActuelle,
      etatTicket : "en cours"
      // add any other necessary fields
    };

    this.http.post('http://localhost:8080/api/tickets', body).subscribe(
      response => {
        // The request was successful, navigate to some page
        this.router.navigate(['/some-page']);
        alert("Ticket Creer!")
      },
      error => {
        // The request failed, show an error message
        alert('Failed to create ticket!');
      }
    );
  }

  // Méthode utilitaire pour formater les nombres avec des zéros à gauche si nécessaire
  private padNumber(nombre: number): string {
    return nombre < 10 ? '0' + nombre : '' + nombre;
  }
}
