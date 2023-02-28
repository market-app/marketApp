package br.com.marketapp.domain;

import br.com.marketapp.enumerates.ProductType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    @Column(name = "product_name")
    private String name;
    private String price;
    @Column(name = "product_type")
    private ProductType type;
    private Long calories;
    private String brand;
    private Long weight;
    private Long quantity;
    @Column(name = "product_description")
    private String description;
}