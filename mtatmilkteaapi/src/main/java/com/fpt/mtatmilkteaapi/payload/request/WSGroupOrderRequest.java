package com.fpt.mtatmilkteaapi.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WSGroupOrderRequest {

    private String username;
    private String type;
    private String orderID;

}
