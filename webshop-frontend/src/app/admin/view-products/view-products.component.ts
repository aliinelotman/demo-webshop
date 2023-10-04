import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-view-products',
  templateUrl: './view-products.component.html',
  styleUrls: ['./view-products.component.css']
})
export class ViewProductsComponent implements OnInit {
  descriptionWordCount = 5;
  products: Product[] = [];
  originalProducts: Product[] = [];
  searchedProduct: string = "";

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.productService.getAdminProductsFromDb().subscribe(response => {
        this.products = response;
        this.originalProducts = response;
    });
  }

  onFilterProducts() {
    this.products = this.originalProducts.filter(element =>
        element.name.toLowerCase().indexOf(this.searchedProduct.toLowerCase()) >= 0 ||
        element.description.toLowerCase().indexOf(this.searchedProduct.toLowerCase()) >= 0 ||
        element.id.toString().indexOf(this.searchedProduct.toLowerCase()) >= 0
        );
  }

  onChangeProductActive(product: Product) {
    product.active = !product.active;
    // TODO: Edit one product from backend
  }

  //Kodune 02.04
  onDeleteProduct(product: Product){
    let id = product.id;
    this.productService.deleteProduct(id).subscribe((res)=>
    {console.log(res)})

  }

  onIncreaseStock(product: Product){
    let updStock = product.stock;
    updStock = updStock + 1;
    product.stock=updStock;
    console.log(updStock);
    this.productService.addStock(product).subscribe();

  }

  onDecreaseStock(product: Product){
    let updStock = product.stock;
    updStock = updStock - 1;
    product.stock=updStock;
    console.log(updStock);
    this.productService.decreaseStock(product).subscribe();
  }

onReload(){
  location.reload();
}
}
