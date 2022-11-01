package Battleship;

import java.io.IOException;

public class HelpFunctions {
	
	static final int A_INDEX_IN_THE_ALPHABET = 1;
	static final int J_INDEX_IN_THE_ALPHABET = 10;
	static final int A_INDEX_IN_THE_ASCII = 97;
	static final int J_INDEX_IN_THE_ASCII = 106;	
	static final int DIMENSIONS_COORDINATES = 2;
	
	public static void showMap(String [][] playerField) {
		
		System.out.print("   ");
		for (int i = 0; i < PlayingFieldInput.FIELD_SIZE; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();

		for (int i = 0; i<playerField.length; i++) {
			System.out.print(getCharForNumber(i+1) + " ");
			for (int j = 0; j<playerField[i].length; j++) {
				System.out.print(playerField[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static String getCharForNumber(final int indexLetter) {
	    return indexLetter >= A_INDEX_IN_THE_ALPHABET && indexLetter <= J_INDEX_IN_THE_ALPHABET ? String.valueOf((char)(indexLetter + (A_INDEX_IN_THE_ASCII - 1))) : null;  // if the requested index of the letter in the alphabet is between 1 and 9 then return the ASCII character, else return "null"
	}
	
	public static int getNumberForChar(final char letter) {  // this method gives the index of the letter in the alphabet
		final int index = (int) letter;					// index is an ASCII index
		if (index >= A_INDEX_IN_THE_ASCII && index <= J_INDEX_IN_THE_ASCII) {
			return index - A_INDEX_IN_THE_ASCII;
		} else {
			return -1;
		}
	}
	
	public static boolean shipsAvailability(String [][] playerField) {
		for (int i = 0; i<playerField.length; i++) {
			for (int j = 0; j<playerField[i].length; j++) {
				if (playerField [i][j].equals("🚢")) {
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
	
	public static boolean coordinateCheck(final int x, final int y) {
		if ((x < 0 || x > (PlayingFieldInput.FIELD_SIZE - 1)) || (y < 0 || y > (PlayingFieldInput.FIELD_SIZE - 1))) {
			return false;
		}
		
		return true;
	}
	
	public static boolean shipLengthCheck(String [] shipCoordinates, final int shipLength) {
		if (shipCoordinates.length != shipLength) {
			System.out.println("Incorrect input of ship coordinates. Try again!");
			return false;
		}
		return true;
	}
	
	public static boolean coordinateValidation(String [] shipCoordinates) {  // Checking that coordinates of x,y format and int type
		for (int i = 0; i < shipCoordinates.length; i++) {

			if (shipCoordinates[i].length() != DIMENSIONS_COORDINATES) {
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
			
			int x;
			int y;
			try {
				x = getNumberForChar(shipCoordinates[i].charAt(0));				
				y = Integer.parseInt(String.valueOf(shipCoordinates[i].charAt(1)));					
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
	
	public static boolean coordinateEqualityCheck(String [] shipCoordinates) {   // checking that the coordinates are not equal to each other
		for(int i = 0; i<shipCoordinates.length; i++) {
			for(int j = 0; j<shipCoordinates.length; j++) {
				if ((i != j) && (shipCoordinates[i].equals(shipCoordinates[j]))) {
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				} 				// else can be omitted because there is no further code
			}
		}
		
		return true;
	}
	
	public static boolean coordinateLinearityCheck(String [] shipCoordinates) {
		boolean isHorizontal = true;
		if (shipCoordinates.length > 1) {
			final int x0 = getNumberForChar(shipCoordinates[0].charAt(0));				
			final int y0 = Integer.parseInt(String.valueOf(shipCoordinates[0].charAt(1)));
			
			final int x1 = getNumberForChar(shipCoordinates[1].charAt(0));				
			final int y1 = Integer.parseInt(String.valueOf(shipCoordinates[1].charAt(1)));
			
			if (x0 != x1 && y0 != y1) {													// diagonal coordinate check
				System.out.println("Incorrect input of ship coordinates. Try again!");
				return false;
			}
			isHorizontal = x0 == x1;
		}
		
		for (int i = 1; i < shipCoordinates.length - 1; i++) {
			
			final int x3 = getNumberForChar(shipCoordinates[i].charAt(0));
			final int y3 = Integer.parseInt(String.valueOf(shipCoordinates[i].charAt(1)));
																				
			final int x4 = getNumberForChar(shipCoordinates[i + 1].charAt(0));
			final int y4 = Integer.parseInt(String.valueOf(shipCoordinates[i + 1].charAt(1)));
			
			if (isHorizontal) {
				if (x3 != x4 || Math.abs(y4 - y3) != 1) { 							// Horizontal check
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				}
			} else {
				if (y3 != y4 || Math.abs(x4 - x3) != 1) { 							// Vertical check
					System.out.println("Incorrect input of ship coordinates. Try again!");
					return false;
				}
			}
		}
		
		return true;	
	}
	
	public static boolean shipAreolaCheck(String [] shipCoordinates, String [][] playerField) {
		for (int i = 0; i < shipCoordinates.length; i++) {
			
			final int x = getNumberForChar(shipCoordinates[i].charAt(0));
			final int y = Integer.parseInt(String.valueOf(shipCoordinates[i].charAt(1)));

			for (int newX = x-1; newX <= x+1; newX++) {
				for (int newY = y-1; newY <= y+1; newY++) {
					if (coordinateCheck(newX, newY) && (playerField[newX][newY].equals("🚢"))) {
						System.out.println("The areola of this ship intersects with another ship. Try again!");
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
	
	public static boolean fieldCellFilling(String [] shipCoordinates, String [][] playerField) {
		for (int i = 0; i < shipCoordinates.length; i++) { // filling the field cell by a ship
			final int x = getNumberForChar(shipCoordinates[i].charAt(0));
			final int y = Integer.parseInt(String.valueOf(shipCoordinates[i].charAt(1)));
			playerField[x][y] = "🚢";
		}
		
		return true;
	}
	
	public static boolean generalCheckOfTheShip(String [] shipCoordinates, final int shipLength, String [][] playerField) {
		
		boolean allIsOk = true;
		
		allIsOk = allIsOk && HelpFunctions.shipLengthCheck(shipCoordinates, shipLength);  // Second operand (method) of && will not be call. Because first operand are already false. because false will never become true
		allIsOk = allIsOk && HelpFunctions.coordinateValidation(shipCoordinates);
		allIsOk = allIsOk && HelpFunctions.coordinateEqualityCheck(shipCoordinates);
		allIsOk = allIsOk && HelpFunctions.coordinateLinearityCheck(shipCoordinates);
		allIsOk = allIsOk && HelpFunctions.shipAreolaCheck(shipCoordinates, playerField);
		return allIsOk;
	}
	
	public static boolean checkShipDestroy(final int x, final int y, String [][] playerField, final int ignoreX, final int ignoreY) {		
		for (int newX = x-1; newX <= x+1; newX++) {
			for (int newY = y-1; newY <= y+1; newY++) {
				if (!coordinateCheck(newX, newY) || (newX == x && newY == y) || (newX == ignoreX && newY == ignoreY)) {
					continue;
				}
				
				if (playerField[newX][newY].equals("🚢")) {
					return false;
				}
				
				if (playerField[newX][newY].equals("🟥") && !checkShipDestroy(newX, newY, playerField, x, y)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static int hit(String [][] playerFieldForGame, String [][] playerFieldForCheck, final int currentPlayer, final int x, final int y) {
		int player = currentPlayer;
		if (playerFieldForGame[x][y].equals("🚢")) {
			playerFieldForGame[x][y] = "🟥";
			playerFieldForCheck[x][y] = "🟥";
			HelpFunctions.clearScreen();
			if (checkShipDestroy(x, y, playerFieldForGame, x, y)) {
				System.out.println("Ship is destroyed!");
			} else {
				System.out.println("Hit!");
			}
			
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
