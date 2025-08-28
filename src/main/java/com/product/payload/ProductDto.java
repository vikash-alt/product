package com.product.payload;

import lombok.Data;

@Data
public class ProductDto {
    private Integer productId;
    private String productName;
    private String productCode;
    private String productType;
}
