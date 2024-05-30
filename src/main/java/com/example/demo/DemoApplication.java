package com.example.demo;

import com.example.demo.entity.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.demo.controller.MagicalArena.executeFight;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		Player playerA = new Player(100, 5, 10);
		Player playerB = new Player(100, 10, 5);

		while (playerA.getHealth() > 0 && playerB.getHealth() > 0) {
			if (playerA.getHealth() < playerB.getHealth()) {
				executeFight(playerA, playerB, 1);
				if (playerB.health <= 0) {
					System.out.println("Player A wins!");
					break;
				}
				executeFight(playerB, playerA, 0);
				if (playerA.health <= 0) {
					System.out.println("Player B wins!");
					break;
				}
			} else {
				executeFight(playerB, playerA, 0);
				if (playerA.health <= 0) {
					System.out.println("Player B wins!");
					break;
				}
				executeFight(playerA, playerB, 1);
				if (playerB.health <= 0) {
					System.out.println("Player A wins!");
					break;
				}
			}
		}
	}

}
