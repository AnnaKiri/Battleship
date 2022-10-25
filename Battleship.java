package FinalProject;

import java.util.Random;
import java.util.Scanner;

public class Battleship {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Начнем расставлять корабли на поле Player1. Другой игрок, не смотри!");
		String [][] player1FieldForGame = PlayingFieldInput.playingField();
		scan.nextLine();
		HelpFunctions.clearScreen();
		
		System.out.println("Начнем расставлять корабли на поле Player2. Другой игрок, не смотри!");
		String [][] player2FieldForGame = PlayingFieldInput.playingField();
		scan.nextLine();
		HelpFunctions.clearScreen();
		
		Random rand = new Random();
		int currentPlayer = (int) (rand.nextInt(2)+1);  // целые числа 1 и 2
		if (currentPlayer == 1) {
			System.out.println("Начинает игрок1");
		} else {
			System.out.println("Начинает игрок2");
		}
		
		String [][] player1FieldForCheсk = new String[10][10]; // поле первого игрока для второго игрока
		for(int i = 0; i<player1FieldForCheсk.length; i++) {
			for(int j = 0; j<player1FieldForCheсk[i].length; j++) {
				player1FieldForCheсk[i][j] = "🟦";
			}
		}
		String [][] player2FieldForCheck = new String[10][10]; // поле второго игрока для первого игрока
		for(int i = 0; i<player2FieldForCheck.length; i++) {
			for(int j = 0; j<player2FieldForCheck[i].length; j++) {
				player2FieldForCheck[i][j] = "🟦";
			}
		}
				
		while(true) {
			if (currentPlayer == 1) {
				System.out.println("Ход игрока1");
				HelpFunctions.showMap(player2FieldForCheck);
			} else {
				System.out.println("Ход игрока2");
				HelpFunctions.showMap(player1FieldForCheсk);
			}
			System.out.println("Введи координаты удара (формат: x,y)");
			String hit = scan.nextLine();
			String[] temp1 = hit.split(",");
			int x = Integer.parseInt(temp1[0]);
			int y = Integer.parseInt(temp1[1]);
			if (currentPlayer == 1) {
				if (player2FieldForGame[x][y].equals("🚢")) {
					player2FieldForGame[x][y] = "🟥";
					player2FieldForCheck[x][y] = "🟥";
					System.out.println("Попал!");
				} else {
					player2FieldForGame[x][y] = "⬜";
					player2FieldForCheck[x][y] = "⬜";
					System.out.println("Мимо!");
					currentPlayer = 2;
				}
				
				HelpFunctions.showMap(player2FieldForCheck);
				
			} else {
				if (player1FieldForGame[x][y].equals("🚢")) {
					player1FieldForGame[x][y] = "🟥";
					player1FieldForCheсk[x][y] = "🟥";
					System.out.println("Попал!");	
				} else {
					player1FieldForGame[x][y] = "⬜";
					player1FieldForCheсk[x][y] = "⬜";
					System.out.println("Мимо!");
					currentPlayer = 1;
				}
				
				HelpFunctions.showMap(player1FieldForCheсk);
			}
			
			boolean player1win = !HelpFunctions.shipsAvailability(player2FieldForGame);
			if (player1win) {
				System.out.println("Первый игрок победил!");
				break;
			}
			boolean player2win = !HelpFunctions.shipsAvailability(player1FieldForGame);
			if (player2win) {
				System.out.println("Второй игрок победил!");
				break;
			} 
						
		}
		
		
		
	}
}

