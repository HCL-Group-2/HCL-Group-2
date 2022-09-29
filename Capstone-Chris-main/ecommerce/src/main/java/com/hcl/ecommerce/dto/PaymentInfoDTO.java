package com.hcl.ecommerce.dto;

import lombok.Data;

@Data
public class PaymentInfoDTO {
	private int amount;
	private String currency;
	private String receiptEmail;
}
