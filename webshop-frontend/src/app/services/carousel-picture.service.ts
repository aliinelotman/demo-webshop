import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CarouselPicture } from '../models/carousel-picture.model';

@Injectable({
  providedIn: 'root'
})
export class CarouselPictureService {
  private dbUrl = "http://localhost:8080/carouselPicture";

  constructor(private http: HttpClient) { }

  getCarouselPictures() {
    return this.http.get<CarouselPicture[]>(this.dbUrl);
  }

  addCarouselPicture(carouselPicture: CarouselPicture) {
    return this.http.post<CarouselPicture[]>(this.dbUrl, carouselPicture);
  }

  //kodune 2.04
  deleteCarouselPicture(carouselPictureId: any){

    console.log(carouselPictureId);
    return this.http.delete<void>(this.dbUrl + "/" + carouselPictureId);
  }
}


// PascalCase - klassi nimed
// camelCase - muutujad,funktsioonid
// kebab-case - failinimed, API otspunktid
// snake_case - pildid
