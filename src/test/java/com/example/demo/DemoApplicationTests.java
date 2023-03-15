package com.example.demo;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class DemoApplicationTests {

	Calculator calculator = new Calculator();

	@Test
	void itShouldAddTwoNumber() {
		// Given
		int number1 = 20, number2 = 10;

		// When
		int results = calculator.add(number1, number2);

		// Then
		int expected = 30;
		assertThat(results).isEqualTo(expected);
	}

	class Calculator{

		int add(int num1, int num2){
			return num1 + num2;
		}

		int subtract(int num1, int num2){
			return num1 - num2;
		}
	}

}
