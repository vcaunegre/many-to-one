package com.example.manytoone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.manytoone.controller.OwnerController;
import com.example.manytoone.controller.PropertyController;

@SpringBootTest
class ManyToOneDemoApplicationTests {

	@Autowired
	private OwnerController ownerController;

	@Autowired
	private PropertyController propertyController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(ownerController).isNotNull();
		Assertions.assertThat(propertyController).isNotNull();

	}

}
