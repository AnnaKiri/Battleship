package Battleship;

import java.util.Scanner;

public class PlayingFieldInput {
	
	static final int FIELD_SIZE = 10;
	static final int COUNT_THREE_DECK_SHIPS = 2;
	static final int COUNT_DOUBLE_DECK_SHIPS = 3;
	static final int COUNT_SINGLE_DECK_SHIPS = 4;
	
	public static String [][] playingField(Scanner scan) {
				
		String [][] playerField = new String[FIELD_SIZE][FIELD_SIZE];
		for (int i = 0; i<playerField.length; i++) {
			for (int j = 0; j<playerField[i].length; j++) {
				playerField[i][j] = "🟦";
			}
		}
		
		while (true) {
			System.out.println("Enter the four-deck ship coordinates (format: a0;b1;c3;d4)");
			final String shipCoordinates = scan.nextLine();
			final String[] fourDeckCoord = shipCoordinates.split(";");
			
			if (!HelpFunctions.generalCheckOfTheShip(fourDeckCoord, 4, playerField)) {
				continue;
			}
			
			if (HelpFunctions.fieldCellFilling(fourDeckCoord, playerField)) {
				HelpFunctions.clearScreen();
				HelpFunctions.areolaFilling(playerField, "🚢");
				HelpFunctions.showMap(playerField);
				break;
			} 
		}
		
		for (int i = 0; i < COUNT_THREE_DECK_SHIPS; i++) {
			while (true) {
				System.out.println("Enter the three-deck ship coordinates (format: a0;b1;c3)");
				final String shipCoordinates = scan.nextLine();
				final String[] threeDeckCoord = shipCoordinates.split(";");
			
				if (!HelpFunctions.generalCheckOfTheShip(threeDeckCoord, 3, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(threeDeckCoord, playerField)) {
					HelpFunctions.clearScreen();
					HelpFunctions.areolaFilling(playerField, "🚢");
					HelpFunctions.showMap(playerField);
					break;
				} 
			}
		}
	
		
		for (int i = 0; i < COUNT_DOUBLE_DECK_SHIPS; i++) {
			while (true) {	
				System.out.println("Enter the double-deck ship coordinates (format: a0;b1)");
				final String shipCoordinates = scan.nextLine();
				final String[] doubleDeckCoord = shipCoordinates.split(";");

				if (!HelpFunctions.generalCheckOfTheShip(doubleDeckCoord, 2, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(doubleDeckCoord, playerField)) {
					HelpFunctions.clearScreen();
					HelpFunctions.areolaFilling(playerField, "🚢");
					HelpFunctions.showMap(playerField);
					break;
				} 
			}
		}
		
		for (int i = 0; i < COUNT_SINGLE_DECK_SHIPS; i++) {
			while (true) {	
				System.out.println("Enter the single-deck ship coordinates (format: a0)");
				final String shipCoordinates = scan.nextLine();
				final String[] singleDeckCoord = {shipCoordinates};

				if (!HelpFunctions.generalCheckOfTheShip(singleDeckCoord, 1, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(singleDeckCoord, playerField)) {
					HelpFunctions.clearScreen();
					HelpFunctions.areolaFilling(playerField, "🚢");
					HelpFunctions.showMap(playerField);
					break;
				} 
			}
		} 
		
		return playerField;
	}

}
