import { Component, inject, OnInit, signal } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';


import { Loader } from './loader/loader';
import { Navbar } from './navbar/navbar';
import { NgIf } from '@angular/common';
import { Background } from './background/background';
import { Auth } from './services/auth';



@Component({
  selector: 'app-root',
  imports: [RouterOutlet,Loader,Navbar,NgIf,Background ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  private router = inject(Router);
  showNavbar = false;


  constructor(private rouer: Router,private auth: Auth) {
    this.router.events.subscribe(() => {
      const url = this.router.url || '';
      // navbar cachée sur login & home
      this.showNavbar = !(url.startsWith('/login') || url.startsWith('/home') || url.startsWith('/dashbord'));


    });
  }

    ngOnInit(){
   this.auth.checkSession();
 }



}
