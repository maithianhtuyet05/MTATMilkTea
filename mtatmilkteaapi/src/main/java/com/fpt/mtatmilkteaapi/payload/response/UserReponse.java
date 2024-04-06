package com.fpt.mtatmilkteaapi.payload.response;

import com.fpt.mtatmilkteaapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReponse {

    private User user;
    private WishlistResponse wishlistResponse;

}
