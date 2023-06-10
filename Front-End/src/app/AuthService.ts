import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private technicienId = new BehaviorSubject<number>(0);
  public readonly technicienId$ = this.technicienId.asObservable();

  private adminId = new BehaviorSubject<number>(0);
  public readonly adminId$ = this.adminId.asObservable();

  setTechnicienId(id: number) {
    this.technicienId.next(id);
  }

  setAdminId(id: number) {
    this.adminId.next(id);
  }
}
