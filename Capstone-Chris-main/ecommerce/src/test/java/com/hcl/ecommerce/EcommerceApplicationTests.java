package com.hcl.ecommerce;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class EcommerceApplicationTests {
	
	@Value("${stripe.key.secret}")
	String stripeKey;
	
	@Test
	void contextLoads() {
		
		assertNotNull(stripeKey);
	}

}
