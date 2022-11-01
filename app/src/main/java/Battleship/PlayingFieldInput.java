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
		
		PlayingFieldInput.addShip(Ship.FOUR_DECK, playerField, scan);
		
		for (int i = 0; i < COUNT_THREE_DECK_SHIPS; i++) {
			PlayingFieldInput.addShip(Ship.THREE_DECK, playerField, scan);
		}
		
		for (int i = 0; i < COUNT_DOUBLE_DECK_SHIPS; i++) {
			PlayingFieldInput.addShip(Ship.DOUBLE_DECK, playerField, scan);
		}
		
		for (int i = 0; i < COUNT_SINGLE_DECK_SHIPS; i++) {
			PlayingFieldInput.addShip(Ship.SINGLE_DECK, playerField, scan);
		}
		
		return playerField;
	}
	
	private static void addShip(Ship ship, String [][] playerField, Scanner scan) {
		String shipName;
		String coordinates;
		int countFieldCell;
		
		switch (ship) {
		case FOUR_DECK:
			shipName = "four-deck";
			coordinates = "a0;b1;c2;d3";
			countFieldCell = 4;
			break;
		case THREE_DECK:
			shipName = "three-deck";
			coordinates = "a0;b1;c2";
			countFieldCell = 3;
			break;
		case DOUBLE_DECK:
			shipName = "double-deck";
			coordinates = "a0;b1";
			countFieldCell = 2;
			break;
		case SINGLE_DECK:
			shipName = "single-deck";
			coordinates = "a0";
			countFieldCell = 1;
			break;
		default:
			System.out.println("Incorrect input of ship size. Try again!");
			return;
		}
		
		while (true) {	
			System.out.println("Enter the " + shipName + " ship coordinates (format: " + coordinates + ")");
			final String shipCoordinates = scan.nextLine();
			final String[] shipCoordSeparated = shipCoordinates.split(";");

			if (!HelpFunctions.generalCheckOfTheShip(shipCoordSeparated, countFieldCell, playerField)) {
				continue;
			}
			
			if (HelpFunctions.fieldCellFilling(shipCoordSeparated, playerField)) {
				HelpFunctions.clearScreen();
				HelpFunctions.areolaFilling(playerField, "🚢");
				HelpFunctions.showMap(playerField);
				break;
			} 
		}
		
	}

}
