import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AdminAuthComponent } from './admin-auth/admin-auth.component';
import { TechnicianAuthComponent} from "./technician-auth/technician-auth.component";
import { AdminHomeComponent} from "./admin-home/admin-home.component";
import { TechnicianHomeComponent} from "./technician-home/technician-home.component";
import  { ReportProblemComponent} from "./report-problem/report-problem.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'admin-auth', component: AdminAuthComponent },
  { path: 'technician-auth', component: TechnicianAuthComponent },
  { path: 'admin-home', component: AdminHomeComponent },
  { path: 'technician-home', component: TechnicianHomeComponent},
  { path: 'report', component: ReportProblemComponent}

  // Ajoutez d'autres routes ici
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

