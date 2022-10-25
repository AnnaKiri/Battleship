package FinalProject;

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
		
		System.out.println("Введи координаты четырехпалубного корабля (формат: x,y;x,y;x,y;x,y)");
		String ship = scan.nextLine();
		String[] fourDeck = ship.split(";"); // 4 элемента
		for (int i = 0; i < fourDeck.length; i++) {
			String[] temp2 = fourDeck[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты трехпалубного корабля (формат: x,y;x,y;x,y)");
		ship = scan.nextLine();
		String[] threeDeck1 = ship.split(";"); // 3 элемента
		for (int i = 0; i < threeDeck1.length; i++) {
			String[] temp2 = threeDeck1[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
/*		System.out.println("Введи координаты трехпалубного корабля (формат: x,y;x,y;x,y)");
		ship = scan.nextLine();
		String[] threeDeck2 = ship.split(";"); // 3 элемента
		for (int i = 0; i < threeDeck2.length; i++) {
			String[] temp2 = threeDeck2[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
		ship = scan.nextLine();
		String[] doubleDeck1 = ship.split(";"); // 2 элемента
		for (int i = 0; i < doubleDeck1.length; i++) {
			String[] temp2 = doubleDeck1[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
		ship = scan.nextLine();
		String[] doubleDeck2 = ship.split(";"); // 2 элемента
		for (int i = 0; i < doubleDeck2.length; i++) {
			String[] temp2 = doubleDeck2[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты двухпалубного корабля (формат: x,y;x,y)");
		ship = scan.nextLine();
		String[] doubleDeck3 = ship.split(";"); // 2 элемента
		for (int i = 0; i < doubleDeck3.length; i++) {
			String[] temp2 = doubleDeck3[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
		ship = scan.nextLine();
		String[] singleDeck1 = ship.split(";"); // 1 элемент
		for (int i = 0; i < singleDeck1.length; i++) {
			String[] temp2 = singleDeck1[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
		ship = scan.nextLine();
		String[] singleDeck2 = ship.split(";"); // 1 элемент
		for (int i = 0; i < singleDeck2.length; i++) {
			String[] temp2 = singleDeck2[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
		ship = scan.nextLine();
		String[] singleDeck3 = ship.split(";"); // 1 элемент
		for (int i = 0; i < singleDeck3.length; i++) {
			String[] temp2 = singleDeck3[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
		
		System.out.println("Введи координаты однопалубного корабля (формат: x,y)");
		ship = scan.nextLine();
		String[] singleDeck4 = ship.split(";"); // 1 элемент
		for (int i = 0; i < singleDeck4.length; i++) {
			String[] temp2 = singleDeck4[i].split(",");
			int x = Integer.parseInt(temp2[0]);
			int y = Integer.parseInt(temp2[1]);
			playerField[x][y] = "🚢";
		}
*/		
		for(int i = 0; i<playerField.length; i++) {
			for(int j = 0; j<playerField[i].length; j++) {
			System.out.print(playerField[i][j] + " ");
			}
			System.out.println();
		}
		return playerField;
		
	}

}
