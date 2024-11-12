package com.ms1.productservicemicroservices.services;

import com.ms1.productservicemicroservices.dtos.FakeStoreProductsDto;
import com.ms1.productservicemicroservices.models.Category;
import com.ms1.productservicemicroservices.models.Products;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate; //make config class so obj will be create once

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Products getSingleProduct(Long productId) {
        throw new ArithmeticException();

        //call fakestore to fetch the products with given id=make http call
//       FakeStoreProductsDto fakeStoreProductsDto=restTemplate.getForObject("https://fakestoreapi.com/products/" + productId,
//               FakeStoreProductsDto.class
//       );
//       return convertFakeStoreProductToProducts(fakeStoreProductsDto);
    }


    @Override
    public List<Products> getAllProducts() {
        FakeStoreProductsDto[] fakeStoreProductsDtos= //using array for type erasing
               restTemplate.getForObject  (
               "https://fakestoreapi.com/products",
               FakeStoreProductsDto[].class
       );
        //convert ListofFakeStoreDto into List of Products
        List<Products> products = new ArrayList<>();
        for (FakeStoreProductsDto fakeStoreProductsDto : fakeStoreProductsDtos) {
            products.add(convertFakeStoreProductToProducts(fakeStoreProductsDto));
        }
        return products;
    }


    //patch->http://localhost:8080/products/1
    @PatchMapping("/{id}")
    public Products updateProduct(@PathVariable("id") Long id, @RequestBody Products product){
        //patch-partial update
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductsDto.class);
        HttpMessageConverterExtractor<FakeStoreProductsDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductsDto.class, restTemplate.getMessageConverters());

        FakeStoreProductsDto response= restTemplate.execute("https://fakestoreapi.com/products/"+ id,
                HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductToProducts(response);
    }


    @PutMapping("/{id}")
    public Products replaceProduct(@PathVariable("id") Long productId, @RequestBody Products product){
        return null;
    }




    @Override
    public void deleteProduct(Long id) {
    }

    private Products convertFakeStoreProductToProducts(FakeStoreProductsDto fakeStoreProductsDto) {
        //convert fakestoreproductdto into product
        Products products=new Products();
        products.setId(fakeStoreProductsDto.getId());
        products.setTitle(fakeStoreProductsDto.getTitle());
        products.setPrice(fakeStoreProductsDto.getPrice());

        Category category=new Category();//as category is passed as class in products so first create obj of category and set it
        category.setDescription(fakeStoreProductsDto.getDescription());
        products.setCategory(category);


        return products;
    }
}
