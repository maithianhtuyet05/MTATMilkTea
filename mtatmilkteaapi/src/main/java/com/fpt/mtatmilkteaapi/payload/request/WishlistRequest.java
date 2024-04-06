package com.fpt.mtatmilkteaapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WishlistRequest {
    private long userId;
    private String productId;
}
