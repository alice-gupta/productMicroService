package com.ms1.productservicemicroservices.controllers;

import com.ms1.productservicemicroservices.models.Products;
import com.ms1.productservicemicroservices.services.FakeStoreProductService;
import com.ms1.productservicemicroservices.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        ResponseEntity<Products> responseEntity=new ResponseEntity<>(
                productService.getSingleProduct(id),
               HttpStatus.OK
        );
        return responseEntity;

//        ResponseEntity<Products> responseEntity = null;
//        try {
//            Products products=productService.getSingleProduct(id);
//            responseEntity=new ResponseEntity<>(
//                    products,
//                    HttpStatus.OK
//           );
//        }
//        catch (Exception e) {
//            responseEntity=new ResponseEntity<>(
//                    HttpStatus.NOT_FOUND
//
//            );
//       }
//       return responseEntity;
    }

    @GetMapping("/")
    public List<Products> getAllProducts(){
        return productService.getAllProducts();

    }

    @PatchMapping("/{id}")
    public Products updateProduct(@PathVariable("id")Long id,@RequestBody Products products){
        return productService.updateProduct(id,products);
    }

    @PutMapping ("/{id}")
    public Products replaceProduct(@PathVariable("id")Long id,@RequestBody Products products){
        return null;
    }






}
