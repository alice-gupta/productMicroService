package com.ms1.productservicemicroservices.services;

import com.ms1.productservicemicroservices.models.Products;

import java.util.List;

public interface ProductService {
    Products getSingleProduct(Long id);
    List<Products> getAllProducts();

    Products updateProduct(Long id, Products products);
    Products replaceProduct(Long id, Products products);

    void deleteProduct(Long id);
}
