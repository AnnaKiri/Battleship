package Battleship;

import java.io.IOException;
import java.util.Scanner;

public class HelpFunctions {
	
	public static void showMap(String [][] array) {
		
		for (int i = 0; i<array.length; i++) {
			for (int j = 0; j<array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
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
		if ((x < 0 || x > 9) || (y < 0 || y > 9)){ ///
			return false;
		}
		
		return true;
	}
	
	public static boolean shipLengthCheck(String [] array, int n) {  // проверка длины корабля
		if (array.length != n) {
			System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
			return false;
		}
		return true;
	}
	
	public static boolean coordinateValidation(String [] array) {  // проверка, что координаты формата х,у и типа int
		for (int i = 0; i < array.length; i++) {
			String[] temp2 = array[i].split(",");
			
			if (temp2.length != 2) {
				System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
				return false;
			}
			
			int x;
			int y;
			try {
				x = Integer.parseInt(temp2[0]);
				y = Integer.parseInt(temp2[1]);					
			} catch (NumberFormatException e) {
				System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
				return false;
			}
			
			if (!HelpFunctions.coordinateCheck(x, y)) {
				System.out.println("Координаты выходят за пределы поля! Попробуй еще раз!");
				return false;
			}
		}
		return true;
	}
	
	public static boolean coordinateEqualityCheck(String [] array) {   // проверка координат, что они не равны друг другу
		for(int i = 0; i<array.length; i++) {
			for(int j = 0; j<array.length; j++) {
				if ((i != j) && (array[i].equals(array[j]))) {
					System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
					return false;
				} 				// else можно не писать, т.к. далее кода нет
			}
		}
		return true;
	}
	
	public static boolean coordinateLinearityCheck(String [] array) {
		for (int i = 0; i < array.length -1; i++) {  // проверка, что координаты линейны
			String[] temp2 = array[i].split(",");
			int x1 = Integer.parseInt(temp2[0]);
			int y1 = Integer.parseInt(temp2[1]);
			
			String[] temp3 = array[i+1].split(",");  // TODO: добавить проверку координат диагонали
			int x2 = Integer.parseInt(temp3[0]);
			int y2 = Integer.parseInt(temp3[1]);
			
			if (Math.abs(x2-x1) > 1 || Math.abs(y2-y1) > 1) {
				System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
				return false;
			}
		}
		return true;	
	}
	
	public static boolean shipAreolaCheck(String [] array, String [][] array1) {
		for (int i = 0; i < array.length; i++) {  // проверка ареола корабля
			String[] temp2 = array[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			for (int newX = x-1; newX <= x+1; newX++) {
				for (int newY = y-1; newY <= y+1; newY++) {
					if (coordinateCheck(newX, newY) && (array1[newX][newY].equals("🚢"))) {
						System.out.println("Ареол этого корабля пересекается с другим кораблем. Попробуй заново!");
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
	
	public static boolean fieldCellFilling(String [] array, String [][] array1) {
		for (int i = 0; i < array.length; i++) { // заполнение ячейки поля кораблем
			String[] temp2 = array[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			array1[x][y] = "🚢";
		}
		return true;
	}
	
	public static boolean generalCheckOfTheShip(String [] fourDeck, int n, String [][] playerField) {
		
		boolean allIsOk = true;
		
		allIsOk = allIsOk && HelpFunctions.shipLengthCheck(fourDeck, n);  // если после какого-то метода allIsOk станет false, что дальше строки не будут читаться, т.к. из false уже никогда не станет true
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
			System.out.println("Попал!");
		} else {
			playerFieldForGame[x][y] = "⬜";
			playerFieldForCheck[x][y] = "⬜";
			HelpFunctions.clearScreen();
			System.out.println("Мимо!");
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
