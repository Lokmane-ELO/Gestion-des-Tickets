import  { Component } from '@angular/core';
import {Router} from "@angular/router";
import { HttpClient } from '@angular/common/http';
import {AuthService} from "../AuthService";

@Component({
  selector: 'app-admin-auth',
  templateUrl: './admin-auth.component.html',
  styleUrls: ['./admin-auth.component.css']
})
export class AdminAuthComponent {
  nom_prenom: string = "";
  password: string = "";

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) { }

  onSubmit() {
    const body = {
      nom_prenom: this.nom_prenom,
      password: this.password
    };

    this.http.post('http://localhost:8080/api/admins/login', body).subscribe(
      response => {
        this.http.get(`http://localhost:8080/api/users/codeUser?nom=${this.nom_prenom.split('_')[0]}&prenom=${this.nom_prenom.split('_')[1]}`).subscribe(
          codeUserResponse => {
            this.authService.setAdminId(codeUserResponse as number);
            this.router.navigate(['/admin-home']);
          },
          error => {
            console.error("Error fetching codeUser", error);
          }
        );
      },
      error => {
        console.log(error)
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
