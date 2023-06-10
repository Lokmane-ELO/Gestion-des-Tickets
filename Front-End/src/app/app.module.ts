import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { ReportProblemComponent } from './report-problem/report-problem.component';
import { TechnicianAuthComponent } from './technician-auth/technician-auth.component';
import { AdminAuthComponent } from './admin-auth/admin-auth.component';
import { TechnicianHomeComponent } from './technician-home/technician-home.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReportProblemComponent,
    TechnicianAuthComponent,
    AdminAuthComponent,
    TechnicianHomeComponent,
    AdminHomeComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
