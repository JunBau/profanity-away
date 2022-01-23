package com.junbau.profanityaway;

import com.junbau.profanityaway.controller.ProfanityController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ProfanityAwayApplicationTests {

	@Autowired
	private ProfanityController profanityController;

	@Test
	void contextLoads() throws Exception {
		assertThat(profanityController).isNotNull();
	}

}
