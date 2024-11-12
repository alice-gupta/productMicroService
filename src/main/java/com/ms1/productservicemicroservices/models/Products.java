package com.ms1.productservicemicroservices.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products extends BaseModel {
    private String title;
    private String description;
    private double price;
    @ManyToOne
    private Category category;

}
