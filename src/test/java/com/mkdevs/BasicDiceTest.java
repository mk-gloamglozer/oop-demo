package com.mkdevs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BasicDiceTest {
	
	@Test
	public void testRollReturnsNumberBetweenOneAndMax(){
		Dice dice = BasicDice.build(1, "name");
		for (int i = 0; i < 100; i++) {
			int rolled = dice.roll();
			assertThat(rolled).isEqualTo(1);
		}

		dice = BasicDice.build(2, "name");
		int twocount = 0;
		for (int i = 0; i < 100; i++) {
			int rolled = dice.roll();
			if(rolled ==2) {
				twocount++;
			}
			assertThat(rolled).isLessThan(3);
		}
		assertThat(twocount).isNotEqualTo(0);

	}
	
	@Test 
	public void testRepeatRollReturnsSumOfRolls() {
		
		Dice dice = BasicDice.build(1, "name");
		assertThat(dice.roll(5)).isEqualTo(5);
	}
	
	@Test
	public void whenSidesLessThanOne_thenBuildThrowsIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> BasicDice.build(0, "name"));
	}
}
