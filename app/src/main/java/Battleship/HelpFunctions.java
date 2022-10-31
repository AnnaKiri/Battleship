package Battleship;

import java.io.IOException;
import java.util.Scanner;

public class HelpFunctions {
	
	public static void showMap(String [][] array) {
		
		System.out.print("   ");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		for (int i = 0; i<array.length; i++) {
			System.out.print(getCharForNumber(i+1) + " ");
			for (int j = 0; j<array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static String getCharForNumber(int i) {
	    return i > 0 && i < 27 ? String.valueOf((char)(i + 96)) : null;
	}
	
	public static int getNumberForChar(char i) {
		int value = (int) i;
		if (value >= 97 && value <= 106) {
			return value - 97;
		} else {
			return -1;
		}
	}
	
	public static boolean shipsAvailability (String [][] array) {
		for (int i = 0; i<array.length; i++) {
			for (int j = 0; j<array[i].length; j++) {
				if (array [i][j].equals("🚢")) {
					 return true;
				} 
			}
		}
		
		return false;
	}
	
	public static void clearScreen() {   
	    try {
	      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    } catch (InterruptedException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}
	
	public static boolean coordinateCheck(int x, int y) {
		if ((x < 0 || x > 9) || (y < 0 || y > 9)){
			return false;
		}
		
		return true;
	}
	
	public static boolean shipLengthCheck(String [] array, int n) {
		if (array.length != n) {
			System.out.println("Incorrect input of ship coordinates. Try again!");
			return false;
		}
		return true;
	}
	
	public static boolean coordinateValidation(String [] array) {  // Checking that coordinates of x,y format and int type
		for (int i = 0; i < array.length; i++) {

			if (array[i].length() != 2) {
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
			
			int x;
			int y;
			try {
				x = getNumberForChar(array[i].charAt(0));				
				y = Integer.parseInt(String.valueOf(array[i].charAt(1)));					
			} catch (NumberFormatException e) {
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
			
			if (!HelpFunctions.coordinateCheck(x, y)) {
				System.out.println("Coordinates outside the playing field. Try again!");
				return false;
			}
		}
		return true;
	}
	
	public static boolean coordinateEqualityCheck(String [] array) {   // checking that the coordinates are not equal to each other
		for(int i = 0; i<array.length; i++) {
			for(int j = 0; j<array.length; j++) {
				if ((i != j) && (array[i].equals(array[j]))) {
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				} 				// else can be omitted because there is no further code
			}
		}
		return true;
	}
	
	public static boolean coordinateLinearityCheck(String [] array) {
		boolean isHorizontal = true;
		if ( array.length > 1) {
			int x0 = getNumberForChar(array[0].charAt(0));				
			int y0 = Integer.parseInt(String.valueOf(array[0].charAt(1)));
			
			int x1 = getNumberForChar(array[1].charAt(0));				
			int y1 = Integer.parseInt(String.valueOf(array[1].charAt(1)));
			
			if (x0 != x1 && y0 != y1) {													// diagonal coordinate check
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
			isHorizontal = x0 == x1;
		}
		
		for (int i = 1; i < array.length -1; i++) {
			
			int x3 = getNumberForChar(array[i].charAt(0));
			int y3 = Integer.parseInt(String.valueOf(array[i].charAt(1)));
																				
			int x4 = getNumberForChar(array[i + 1].charAt(0));
			int y4 = Integer.parseInt(String.valueOf(array[i + 1].charAt(1)));
			
			if (isHorizontal) {
				if (x3 != x4 || Math.abs(y4-y3) != 1) { 							// Horizontal check
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				}
			} else {
				if (y3 != y4 || Math.abs(x4-x3) != 1) { 							// Vertical check
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				}
			}
			
			if (Math.abs(x4-x3) > 1 || Math.abs(y4-y3) > 1) {
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
		}
		return true;	
	}
	
	public static boolean shipAreolaCheck(String [] array, String [][] array1) {
		for (int i = 0; i < array.length; i++) {
			
			int x = getNumberForChar(array[i].charAt(0));
			int y = Integer.parseInt(String.valueOf(array[i].charAt(1)));

			for (int newX = x-1; newX <= x+1; newX++) {
				for (int newY = y-1; newY <= y+1; newY++) {
					if (coordinateCheck(newX, newY) && (array1[newX][newY].equals("🚢"))) {
						System.out.println("The areola of this ship cross(intersects) with another ship. Try again!");
						return false;
					} 
				}
			}
		}
		
		return true;
	}
	
	public static void areolaFilling(String [][] playerField) {
		for (int i = 0; i < playerField.length; i++) {
			for (int j = 0; j < playerField[i].length; j++) {
				if (playerField[i][j].equals("🚢")) {
					for (int newX = i-1; newX <= i+1; newX++) {
						for (int newY = j-1; newY <= j+1; newY++) {
							if (coordinateCheck(newX, newY) && (playerField[newX][newY].equals("🟦"))) {
								playerField[newX][newY] = "🟨";
							}
						}
					}
				}
			}
		}
	}
	
	public static boolean fieldCellFilling(String [] fourDeck, String [][] playerField) {
		for (int i = 0; i < fourDeck.length; i++) { // filling the field cell by a ship
			int x = getNumberForChar(fourDeck[i].charAt(0));
			int y = Integer.parseInt(String.valueOf(fourDeck[i].charAt(1)));
			playerField[x][y] = "🚢";
		}
		return true;
	}
	
	public static boolean generalCheckOfTheShip(String [] fourDeck, int n, String [][] playerField) {
		
		boolean allIsOk = true;
		
		allIsOk = allIsOk && HelpFunctions.shipLengthCheck(fourDeck, n);  // Second operand (method) of && will not be call. Because first operand are already false. because false will never become true
		allIsOk = allIsOk && HelpFunctions.coordinateValidation(fourDeck);
		allIsOk = allIsOk && HelpFunctions.coordinateEqualityCheck(fourDeck);
		allIsOk = allIsOk && HelpFunctions.coordinateLinearityCheck(fourDeck);
		allIsOk = allIsOk && HelpFunctions.shipAreolaCheck(fourDeck, playerField);
		
		return allIsOk;
	}
	
	public static int hit(String [][] playerFieldForGame, String [][] playerFieldForCheck, int currentPlayer, int x, int y) {
		int player = currentPlayer;
		if (playerFieldForGame[x][y].equals("🚢")) {
			playerFieldForGame[x][y] = "🟥";
			playerFieldForCheck[x][y] = "🟥";
			HelpFunctions.clearScreen();
			System.out.println("Hit!");
		} else if (playerFieldForGame[x][y].equals("🟥")){
			HelpFunctions.clearScreen();
			System.out.println("Re - hit!");
			if (currentPlayer == 2) {
				player = 1;
			} else {
				player = 2;
			}
		} else {
			playerFieldForGame[x][y] = "⬜";
			playerFieldForCheck[x][y] = "⬜";
			HelpFunctions.clearScreen();
			System.out.println("Miss!");
			if (currentPlayer == 2) {
				player = 1;
			} else {
				player = 2;
			}
		}
		HelpFunctions.showMap(playerFieldForCheck);
		return player;
	}
}
