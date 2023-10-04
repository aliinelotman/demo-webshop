import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  dbUrl: string = "http://localhost:8080/product";
  cartChanged = new BehaviorSubject(true);

  constructor(private http: HttpClient) { }

  getProductsFromDb() {
    return this.http.get<any>(this.dbUrl).pipe(
      map(response => {
        return response.content;
      })
    );
  }

  addProductToDb(newProduct: Product) {
    return this.http.post(this.dbUrl, newProduct);
  }

  // updateProductsInDb(updatedProducts: Product[]) {
  //   return this.http.put(this.dbUrl, updatedProducts);
  // }

  // TODO: Delete product from backend
   //Kodune 02.04
  deleteProduct(id: number) {
    return this.http.delete(this.dbUrl + "/" + id);
  }

  // TODO: Get one product from backend
  // public Observable<Product> getOneProducr(int id) {}
  // Observable - tema külge on võimalik Subscribe-da
  getOneProduct(id: number): Observable<Product> {
    return this.http.get<Product>(this.dbUrl + "/" + id);
  }

  // Edit one product from backend
  updateProduct(updatedProduct: Product): Observable<Product[]> {
    return this.http.put<Product[]>("http://localhost:8080/product", updatedProduct);
  }



    getAdminProductsFromDb(): Observable<Product[]> {
      const headers = {
        headers: new HttpHeaders({
          Authorization:
            'Bearer  ' + sessionStorage.getItem("token"),
        }),
      };

      return this.http.get<Product[]>(
        'http://localhost:8080/admin-products',
        headers
      );
    }



  addStock(product: Product): Observable<Product[]> {
    console.log("am i here?", product);
    return this.http.patch<Product[]>("http://localhost:8080/add-stock/" + product.id, {});

  }

  decreaseStock(product: Product): Observable<Product[]> {
    console.log("am i here?", product);
    return this.http.patch<Product[]>("http://localhost:8080/decrease-stock/" + product.id, {});
  }
}
