package com.fpt.mtatmilkteaapi.payload.response;

import com.fpt.mtatmilkteaapi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private List<Product> product;
    private String newProductId;
}
