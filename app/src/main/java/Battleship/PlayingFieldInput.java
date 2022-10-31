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
			System.out.println("Enter the four-deck ship coordinates (format: a0;b1;c3;d4)");
			String shipCoordinates = scan.nextLine();
			String[] fourDeckCoord = shipCoordinates.split(";");
			
			if (!HelpFunctions.generalCheckOfTheShip(fourDeckCoord, 4, playerField)) {
				continue;
			}
			
			if (HelpFunctions.fieldCellFilling(fourDeckCoord, playerField)) {
				break;
			} 
		}
		
		for (int i = 0; i < 2; i++) {
			while (true) {
				System.out.println("Enter the three-deck ship coordinates (format: a0;b1;c3)");
				String shipCoordinates = scan.nextLine();
				String[] threeDeckCoord = shipCoordinates.split(";");
			
				if (!HelpFunctions.generalCheckOfTheShip(threeDeckCoord, 3, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(threeDeckCoord, playerField)) {
					break;
				} 
			}
		}
	
		
		for (int i = 0; i < 3; i++) {
			while (true) {	
				System.out.println("Enter the double-deck ship coordinates (format: a0;b1)");
				String shipCoordinates = scan.nextLine();
				String[] doubleDeckCoord = shipCoordinates.split(";");

				if (!HelpFunctions.generalCheckOfTheShip(doubleDeckCoord, 2, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(doubleDeckCoord, playerField)) {
					break;
				} 
			}
		}
		
		for (int i = 0; i < 4; i++) {
			while (true) {	
				System.out.println("Enter the single-deck ship coordinates (format: a0)");
				String shipCoordinates = scan.nextLine();
				String[] singleDeckCoord = {shipCoordinates};

				if (!HelpFunctions.generalCheckOfTheShip(singleDeckCoord, 1, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(singleDeckCoord, playerField)) {
					break;
				} 
			}
		} 

		HelpFunctions.areolaFilling(playerField);
		HelpFunctions.showMap(playerField);
		
		return playerField;
		
	}

}
