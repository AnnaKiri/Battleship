package Battleship;

import java.io.IOException;
import java.util.Scanner;

public class PlayingFieldInput {
	public static String [][] playingField() {
				
		String [][] playerField = new String[10][10];
		for(int i = 0; i<playerField.length; i++) {
			for(int j = 0; j<playerField[i].length; j++) {
				playerField[i][j] = "🟦";
			}
		}
		
		Scanner scan = new Scanner(System.in);
		String ship = scan.nextLine();
		
		
		
		
		while(true) {
			System.out.println("Введи координаты четырехпалубного корабля (формат: x,y;x,y;x,y;x,y)");
			ship = scan.nextLine();
			String[] fourDeck = ship.split(";"); // 4 элемента
			
			if (fourDeck.length != 4) {
				System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
				continue;
			}
			
			// check
			boolean allIsOk = true;
			for (int i = 0; i < fourDeck.length; i++) {
				String[] temp2 = fourDeck[i].split(",");
				
				if (temp2.length != 2) {
					allIsOk = false;
					System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
					break;
				}
				
				int x;
				int y;
				try {
					x = Integer.parseInt(temp2[0]);
					y = Integer.parseInt(temp2[1]);					
				} catch (NumberFormatException e) {
					System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
					allIsOk = false;
					break;
				}
				
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					allIsOk = false;
					break;
				}
			}
			
			if (allIsOk) {
				for(int i = 0; i<fourDeck.length; i++) {   // проверка координат, что они не равны друг другу
					for(int j = 0; j<fourDeck.length; j++) {
						if ((i != j) && (fourDeck[i].equals(fourDeck[j]))) {
							allIsOk = false;
							System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
							break;
						} 				// else можно не писать, т.к. далее кода нет
					}
				}
			}
			
			if (allIsOk) {
				for (int i = 0; i < fourDeck.length -1; i++) {  // проверка, что координаты линейны
					String[] temp2 = fourDeck[i].split(",");
					int x1 = Integer.parseInt(temp2[0]);
					int y1 = Integer.parseInt(temp2[1]);
					
					String[] temp3 = fourDeck[i+1].split(",");  // TODO: добавить проверку координат диагонали
					int x2 = Integer.parseInt(temp3[0]);
					int y2 = Integer.parseInt(temp3[1]);
					
					if (Math.abs(x2-x1) > 1 || Math.abs(y2-y1) > 1) {
						allIsOk = false;
						System.out.println("Некорректный ввод координат корабля. Попробуй заново!");
						break;
					}
					
				}
			}

			if (allIsOk) {
				for (int i = 0; i < fourDeck.length; i++) {
					String[] temp2 = fourDeck[i].split(",");
					int x = Integer.parseInt(temp2[0]);
					int y = Integer.parseInt(temp2[1]);
					for (int newX = x-1; newX <= x+1; newX ++) {
						for (int newY = y-1; newY <= y+1; newY ++) {
							if (playerField[newX][newY].equals("🚢")) {
								allIsOk = false;
								System.out.println("Ареол этого корабля пересекается с другим кораблем. Попробуй заново!");
								break;
							} 
						}
					}
				}
			}
			

			// fill
			if (allIsOk) {
				for (int i = 0; i < fourDeck.length; i++) {
					String[] temp2 = fourDeck[i].split(",");
					int x = Integer.parseInt(temp2[0]);
					int y = Integer.parseInt(temp2[1]);
					playerField[x][y] = "🚢";
				}
				
				break;
			} else {
				continue;		
			}
		}
		
		
		
		
		
		
		
		
		while(true) {
			System.out.println("Введи координаты трехпалубного корабля (формат: x,y;x,y;x,y)");
			ship = scan.nextLine();
			String[] threeDeck1 = ship.split(";"); // 3 элемента
			for (int i = 0; i < threeDeck1.length; i++) {
				String[] temp2 = threeDeck1[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
		}
		
			System.out.println("Введи координаты трехпалубного корабля (формат: x,y;x,y;x,y)");
			ship = scan.nextLine();
			String[] threeDeck2 = ship.split(";"); // 3 элемента
			for (int i = 0; i < threeDeck2.length; i++) {
				String[] temp2 = threeDeck2[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
			ship = scan.nextLine();
			String[] doubleDeck1 = ship.split(";"); // 2 элемента
			for (int i = 0; i < doubleDeck1.length; i++) {
				String[] temp2 = doubleDeck1[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
			ship = scan.nextLine();
			String[] doubleDeck2 = ship.split(";"); // 2 элемента
			for (int i = 0; i < doubleDeck2.length; i++) {
				String[] temp2 = doubleDeck2[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
			ship = scan.nextLine();
			String[] doubleDeck3 = ship.split(";"); // 2 элемента
			for (int i = 0; i < doubleDeck3.length; i++) {
				String[] temp2 = doubleDeck3[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
			ship = scan.nextLine();
			String[] singleDeck1 = ship.split(";"); // 1 элемент
			for (int i = 0; i < singleDeck1.length; i++) {
				String[] temp2 = singleDeck1[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
			ship = scan.nextLine();
			String[] singleDeck2 = ship.split(";"); // 1 элемент
			for (int i = 0; i < singleDeck2.length; i++) {
				String[] temp2 = singleDeck2[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
			ship = scan.nextLine();
			String[] singleDeck3 = ship.split(";"); // 1 элемент
			for (int i = 0; i < singleDeck3.length; i++) {
				String[] temp2 = singleDeck3[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
			
			System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
			ship = scan.nextLine();
			String[] singleDeck4 = ship.split(";"); // 1 элемент
			for (int i = 0; i < singleDeck4.length; i++) {
				String[] temp2 = singleDeck4[i].split(",");
				int x = Integer.parseInt(temp2[0]);
				int y = Integer.parseInt(temp2[1]);
				boolean check = HelpFunctions.coordinateCheck(x, y);
				if (!check) {
					continue;
				}
				playerField[x][y] = "🚢";
			}
		
		for(int i = 0; i<playerField.length; i++) {
			for(int j = 0; j<playerField[i].length; j++) {
			System.out.print(playerField[i][j] + " ");
			}
			System.out.println();
		}
		return playerField;
		
	}

}
