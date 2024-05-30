package com.example.demo.controller;

import com.example.demo.entity.Player;

import java.util.Random;

public class MagicalArena {
    private static final Random random = new Random();

    public static int rollDie() {
        return random.nextInt(6) + 1; // Simulates a 6-sided die roll
    }

    public static int calculateAttackDamage(Player attacker, int attackRoll) {
        return attacker.getAttack() * attackRoll;
    }

    public static int calculateDefenseStrength(Player defender, int defenseRoll) {
        return defender.getStrength() * defenseRoll;
    }

    public static int calculateDamage(int attackDamage, int defenseStrength) {
        return Math.max(0, attackDamage - defenseStrength);
    }

    public static void applyDamage(Player defender, int damage) {
        defender.health -= damage;
    }

    private static void displayRoundInfo(Player attacker, Player defender, int attackRoll, int defenseRoll, int attackDamage, int defenseStrength, int damage, int turn) {
        if (turn == 1) {
            System.out.println("Player A attacks with a roll of " + attackRoll + ", Player B defends with a roll of " + defenseRoll);
            System.out.println("Player A inflicts " + attackDamage + " damage, Player B defends " + defenseStrength + " damage");
            System.out.println("Player B's health is reduced by " + damage + " to " + defender.getHealth() + "\n");
        } else {
            System.out.println("Player B attacks with a roll of " + attackRoll + ", Player A defends with a roll of " + defenseRoll);
            System.out.println("Player B inflicts " + attackDamage + " damage, Player A defends " + defenseStrength + " damage");
            System.out.println("Player A's health is reduced by " + damage + " to " + defender.getHealth() + "\n");
        }
    }

    public static void executeFight(Player attacker, Player defender, int turn) {
        int attackRoll = rollDie();
        int defenseRoll = rollDie();

        int attackDamage = calculateAttackDamage(attacker, attackRoll);
        int defenseStrength = calculateDefenseStrength(defender, defenseRoll);

        int damageInflicted = calculateDamage(attackDamage, defenseStrength);
        applyDamage(defender, damageInflicted);

        displayRoundInfo(attacker, defender, attackRoll, defenseRoll, attackDamage, defenseStrength, damageInflicted, turn);
    }


}
