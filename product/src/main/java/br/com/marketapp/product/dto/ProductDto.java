package br.com.marketapp.product.dto;

import br.com.marketapp.product.domain.Product;
import br.com.marketapp.product.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String price;

    public Product toEntity(){
        return ProductMapper.INSTANCE.toEntity(this);
    }

}
