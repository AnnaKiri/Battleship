/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Battleship;

import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Let's start placing ships on the Player1 field. Another player, don't look!");
		String [][] player1FieldForGame = PlayingFieldInput.playingField(scan);
		System.out.println("All ships placed! Press Enter to continue");
		scan.nextLine();
		HelpFunctions.clearScreen();
		
		System.out.println("Let's start placing ships on the Player2 field. Another player, don't look!");
		String [][] player2FieldForGame = PlayingFieldInput.playingField(scan);
		System.out.println("All ships placed! Press Enter to continue");
		scan.nextLine();
		HelpFunctions.clearScreen();
		
		Random rand = new Random();
		int currentPlayer = (int) (rand.nextInt(2)+1);  // integers 1 and 2
		if (currentPlayer == 1) {
			System.out.println("Player1 starts");
		} else {
			System.out.println("Player2 starts");
		}
		
		String [][] player1FieldForCheсk = new String[PlayingFieldInput.FIELD_SIZE][PlayingFieldInput.FIELD_SIZE]; // checking field of player1 for player2
		for(int i = 0; i<player1FieldForCheсk.length; i++) {
			for(int j = 0; j<player1FieldForCheсk[i].length; j++) {
				player1FieldForCheсk[i][j] = "🟦";
			}
		}
		String [][] player2FieldForCheck = new String[PlayingFieldInput.FIELD_SIZE][PlayingFieldInput.FIELD_SIZE]; // checking field of player2 for player1
		for(int i = 0; i<player2FieldForCheck.length; i++) {
			for(int j = 0; j<player2FieldForCheck[i].length; j++) {
				player2FieldForCheck[i][j] = "🟦";
			}
		}
				
		while (true) {
			if (currentPlayer == 1) {
				System.out.println("Player1 move");
				HelpFunctions.showMap(player2FieldForCheck);
			} else {
				System.out.println("Player2 move");
				HelpFunctions.showMap(player1FieldForCheсk);
			}
			
			System.out.println("Enter strike coordinates (format: a0)");
			final String hitCoordinates = scan.nextLine();
			HelpFunctions.clearScreen();
			final String[] tempHitCoordinates = {hitCoordinates};
			if (!HelpFunctions.coordinateValidation(tempHitCoordinates)) {
				continue;
			}
			
			HelpFunctions.clearScreen();
			
			final int x = HelpFunctions.getNumberForChar(hitCoordinates.charAt(0));
			final int y = Integer.parseInt(String.valueOf(hitCoordinates.charAt(1)));

			if (currentPlayer == 1) {
				currentPlayer = HelpFunctions.hit(player2FieldForGame, player2FieldForCheck, currentPlayer, x, y);
			} else {
				currentPlayer = HelpFunctions.hit(player1FieldForGame, player1FieldForCheсk, currentPlayer, x, y);
			}
			
			System.out.println("Press Enter to continue");
			scan.nextLine();
			HelpFunctions.clearScreen();
		
			if (!HelpFunctions.shipsAvailability(player2FieldForGame)) {
				System.out.println("Player1 wins! 🥳  🎇");
				break;
			}
			
			if (!HelpFunctions.shipsAvailability(player1FieldForGame)) {
				System.out.println("Player2 wins! 🥳  🎇");
				break;
			} 		
		}
	}
}
