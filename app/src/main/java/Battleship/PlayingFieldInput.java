package Battleship;

import java.io.IOException;
import java.util.Scanner;

public class PlayingFieldInput {
	public static String [][] playingField(Scanner scan) {
				
		String [][] playerField = new String[10][10];
		for (int i = 0; i<playerField.length; i++) {
			for (int j = 0; j<playerField[i].length; j++) {
				playerField[i][j] = "🟦";
			}
		}
		
		while (true) {
			System.out.println("Enter the four-deck ship coordinates (format: x,y;x,y;x,y;x,y)");
			String ship = scan.nextLine();
			String[] fourDeck = ship.split(";");
			
			if (!HelpFunctions.generalCheckOfTheShip(fourDeck, 4, playerField)) {
				continue;
			}
			
			if (HelpFunctions.fieldCellFilling(fourDeck, playerField)) {
				break;
			} 
		}
		
		for (int i = 0; i < 2; i++) {
			while (true) {
				System.out.println("Enter the three-deck ship coordinates (format: x,y;x,y;x,y)");
				String ship = scan.nextLine();
				String[] threeDeck1 = ship.split(";");
			
				if (!HelpFunctions.generalCheckOfTheShip(threeDeck1, 3, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(threeDeck1, playerField)) {
					break;
				} 
			}
		}
	
		
		for (int i = 0; i < 3; i++) {
			while (true) {	
				System.out.println("Enter the double-deck ship coordinates (format: x,y;xy)");
				String ship = scan.nextLine();
				String[] doubleDeck1 = ship.split(";");

				if (!HelpFunctions.generalCheckOfTheShip(doubleDeck1, 2, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(doubleDeck1, playerField)) {
					break;
				} 
			}
		}
		
		for (int i = 0; i < 4; i++) {
			while (true) {	
				System.out.println("Enter the single-deck ship coordinates (format: x,y)");
				String ship = scan.nextLine();
				String[] singleDeck = {ship};

				if (!HelpFunctions.generalCheckOfTheShip(singleDeck, 1, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(singleDeck, playerField)) {
					break;
				} 
			}
		}

		HelpFunctions.areolaFilling(playerField);
		HelpFunctions.showMap(playerField);
		
		return playerField;
		
	}

}
