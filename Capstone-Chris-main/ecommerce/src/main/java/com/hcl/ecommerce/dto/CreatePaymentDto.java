package com.hcl.ecommerce.dto;

//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import com.hcl.ecommerce.entity.Address;
import com.hcl.ecommerce.entity.CreditCard;
import com.hcl.ecommerce.entity.User;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class CreatePaymentDto {
	@NotNull
    private User user;

    @NotNull
    private Address address;
    
    @NotNull
    private CreditCard card;

}
