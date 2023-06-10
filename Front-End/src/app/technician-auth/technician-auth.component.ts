import { Component } from '@angular/core';
import {Router} from "@angular/router";
import { HttpClient } from '@angular/common/http';
import {AuthService} from "../AuthService";


@Component({
  selector: 'app-technician-auth',
  templateUrl: './technician-auth.component.html',
  styleUrls: ['./technician-auth.component.css']
})
export class TechnicianAuthComponent {
  codeUser: number = 0;

  nom_prenom: string = "";
  password: string = "";


  constructor(private http: HttpClient, private router: Router, private authService:AuthService) { }

  onSubmit() {
    const body = {
      nom_prenom: this.nom_prenom,
      password: this.password
    };



    this.http.post('http://localhost:8080/api/techniciens/login', body).subscribe(
      response => {

        this.http.get(`http://localhost:8080/api/users/codeUser?nom=${this.nom_prenom.split('_')[0]}&prenom=${this.nom_prenom.split('_')[1]}`).subscribe(
          codeUserResponse => {
            this.authService.setTechnicienId(codeUserResponse as number);
            this.router.navigate(['/technician-home']);
          },
          error => {
            console.error("Error fetching codeUser", error);
          }
        );
      },
      error => {
        console.log(error)
        // The authentication failed, display an error message
        if (error.status === 401 || error.status === 403) {
          alert('Login failed!');
        } else {
          console.log(body.nom_prenom);
          console.log(body.password);
          alert('An unexpected error occurred.');
        }
      }
    );

  }
}
