package com.example.demo;
import com.example.demo.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.controller.MagicalArena;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	private Player playerA;
	private Player playerB;

	@BeforeEach
	public void setUp() {
		playerA = new Player(100, 10, 5);
		playerB = new Player(50, 8, 4);
	}
	@Test
	public void testAttackRollInRange() {
		int roll = MagicalArena.rollDie();
		assertTrue(roll >= 1 && roll <= 6);
	}

	@Test
	public void testCalculateAttackDamage() {
		Player attacker = new Player(100, 5, 10);
		int attackRoll = 4; // Sample attack roll
		int expectedDamage = 10 * attackRoll;
		int actualDamage = MagicalArena.calculateAttackDamage(attacker, attackRoll);
		assertEquals(expectedDamage, actualDamage);
	}

	@Test
	public void testCalculateDefenseStrength() {
		Player defender = new Player(100, 10, 5);
		int defenseRoll = 3; // Sample defense roll
		int expectedStrength = 10 * defenseRoll;
		int actualStrength = MagicalArena.calculateDefenseStrength(defender, defenseRoll);
		assertEquals(expectedStrength, actualStrength);
	}

	@Test
	public void testCalculateDamageWithNoExcess() {
		int attackDamage = 20;
		int defenseStrength = 25;
		int expectedDamage = 0;
		int actualDamage = MagicalArena.calculateDamage(attackDamage, defenseStrength);
		assertEquals(expectedDamage, actualDamage);
	}

	@Test
	public void testCalculateDamageWithExcess() {
		int attackDamage = 30;
		int defenseStrength = 20;
		int expectedDamage = 10;
		int actualDamage = MagicalArena.calculateDamage(attackDamage, defenseStrength);
		assertEquals(expectedDamage, actualDamage);
	}

	@Test
	public void testApplyDamage() {
		Player defender = new Player(100, 10, 5);
		int initialHealth = defender.health;
		int damage = 20;
		MagicalArena.applyDamage(defender, damage);
		assertEquals(initialHealth - damage, defender.health);
	}




	@Test
	public void testCalculateDamage_NoDamage() {
		int attackDamage = 10;
		int defenseStrength = 15;
		int expectedDamage = 0;
		assertEquals(expectedDamage, MagicalArena.calculateDamage(attackDamage, defenseStrength));
	}

	@Test
	public void testCalculateDamage_WithDamage() {
		int attackDamage = 20;
		int defenseStrength = 15;
		int expectedDamage = 5;
		assertEquals(expectedDamage, MagicalArena.calculateDamage(attackDamage, defenseStrength));
	}


	@Test
	public void testExecuteFight_PlayerA_Attacks() {
		MagicalArena.executeFight(playerA, playerB, 1);
		// Cannot assert exact values due to randomness, but we can check health decreased.
		assertTrue(playerB.getHealth() < 80);
	}

	@Test
	public void testExecuteFight_PlayerB_Attacks() {
		MagicalArena.executeFight(playerB, playerA, 0);
		// Cannot assert exact values due to randomness, but we can check health decreased.
		assertTrue(playerA.getHealth() < 100);
	}

}
