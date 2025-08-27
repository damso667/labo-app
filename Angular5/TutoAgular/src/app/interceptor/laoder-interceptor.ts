import { inject, Injectable } from '@angular/core';
import {
  HttpInterceptor, HttpRequest, HttpHandler, HttpEvent,
  HttpInterceptorFn
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';
import { LoaderService } from '../services/loader-service';



const API_BASES = [
  'http://localhost:8080',
  '/api' // si tu appelles en relatif
];

function isApiUrl(url: string) {
  return API_BASES.some(base => url.startsWith(base) || url.includes('localhost:8080'));

}

export const loaderInterceptor: HttpInterceptorFn = (req, next) => {
  const loader = inject(LoaderService);
  if(req.url.includes("/login")){
    return next(req);
  }
  // ajoute les cookies de session pour ton backend
  const request = isApiUrl(req.url) ? req.clone({ withCredentials: true }) : req;

  loader.show();
  return next(request).pipe(finalize(() => loader.hide()));



}
