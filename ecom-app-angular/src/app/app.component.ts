import {Component, OnInit} from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import {KeycloakProfile} from "keycloak-js";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'ecom-app-angular';
  public profile !: KeycloakProfile;


  ngOnInit() {
    if(this.keycloakService.isLoggedIn()){
      this.keycloakService.loadUserProfile().then(profile=>{
        this.profile=profile;
      })
    }
  }

  constructor(public keycloakService:KeycloakService) {
  }

  async handleLogin() {
    await this.keycloakService.login({
      redirectUri: window.location.origin
    });
  }

   handleLogout() {
    this.keycloakService.logout();

  }
}
