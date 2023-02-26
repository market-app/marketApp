package br.com.marketapp.domain;

import br.com.marketapp.enumerates.ProductType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private LocalDate expirationDate;
    private ProductType type;
    private Long calories;
    private String brand;
}