package com.ms1.productservicemicroservices.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products extends BaseModel{
    private String title;
    private double price;
    private Category category;

}
