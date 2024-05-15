package fr.fms.ContactSpringMvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ContactSpringMvcApplicationTests {

	@Test
	void contextLoads() {
		int value1 = 1;
		int value2 = 2;
		assertNotEquals(value1, value2);
	}

}
