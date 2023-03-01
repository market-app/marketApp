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
    private String name;
    private String price;
    private ProductType type;
    private Long calories;
    private String brand;
    private Long weight;
    private Long quantity;
    private String description;
}