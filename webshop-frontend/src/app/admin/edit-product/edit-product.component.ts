import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product.service';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from 'src/app/models/category.model';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit {
  products: Product[] = [];


  product!: Product;
  editProductForm!: FormGroup;
  categories: Category[] = [];

  constructor(private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private productService: ProductService,
    private categoryService: CategoryService) { }

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get("productId");
    if (productId) {
      this.getProductsFromDb(productId);
    }
    this.getCategoriesFromDb();
  }

  private getProductsFromDb(productId: string) {
    this.productService.getProductsFromDb().subscribe(response => {
      this.products = response;
      const productFound = this.products.find(element => Number(element.id) === Number(productId));
      if (productFound) {
        this.product = productFound;
         this.initEditForm();
      }
    });
  }

  private initEditForm() {
    this.editProductForm = new FormGroup({
     // id: new FormControl(this.product.id),
      name: new FormControl(this.product.name),
      price: new FormControl(this.product.price),
      image: new FormControl(this.product.image),
      category: new FormControl(this.product.category),
      description: new FormControl(this.product.description),
      active: new FormControl(this.product.active)
    })
  }

  private getCategoriesFromDb() {
    this.categoryService.getCategoriesFromDb().subscribe(categoriesFromDb => {
      this.categories = categoriesFromDb;
    });
  }


  onSubmit() {
//const queueNumber = this.products.indexOf(this.product);
//this.products[queueNumber] = this.editProductForm.value;
    // Edit product
    //this.product = this.editProductForm.value;
    const val = this.editProductForm.value;
    const newProduct = new Product(this.product.id, val.name, val.url, val.price,
      val.description, val.active, this.product.stock, new Category("", val.category));
    this.productService.updateProduct(newProduct).subscribe(() => {
      //this.products = res;
      this.router.navigateByUrl("/admin/halda")
    });

  }

}
