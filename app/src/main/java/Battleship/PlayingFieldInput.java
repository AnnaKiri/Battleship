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
			System.out.println("Введи координаты четырехпалубного корабля (формат: x,y;x,y;x,y;x,y)");
			String ship = scan.nextLine();
			String[] fourDeck = ship.split(";"); // 4 элемента
			
			if (!HelpFunctions.generalCheckOfTheShip(fourDeck, 4, playerField)) {
				continue;
			}
			
			if (HelpFunctions.fieldCellFilling(fourDeck, playerField)) {
				break;
			} 
		}
		
		for (int i = 0; i < 2; i++) {
			while (true) {
				System.out.println("Введи координаты трехпалубного корабля (формат: x,y;x,y;x,y)");
				String ship = scan.nextLine();
				String[] threeDeck1 = ship.split(";"); // 3 элемента
			
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
				System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
				String ship = scan.nextLine();
				String[] doubleDeck1 = ship.split(";"); // 2 элемента

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
				System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
				String ship = scan.nextLine();
				String[] singleDeck = {ship}; // 1 элемент

				if (!HelpFunctions.generalCheckOfTheShip(singleDeck, 1, playerField)) {
					continue;
				}
				
				if (HelpFunctions.fieldCellFilling(singleDeck, playerField)) {
					break;
				} 
			}
		}

		
		HelpFunctions.showMap(playerField);
		
		return playerField;
		
	}

}
