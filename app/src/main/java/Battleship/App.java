/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Battleship;

import java.util.Random;
import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Начнем расставлять корабли на поле Player1. Другой игрок, не смотри!");
		String [][] player1FieldForGame = PlayingFieldInput.playingField(scan);
		scan.nextLine();
		HelpFunctions.clearScreen();
		
		System.out.println("Начнем расставлять корабли на поле Player2. Другой игрок, не смотри!");
		String [][] player2FieldForGame = PlayingFieldInput.playingField(scan);
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
				
		while (true) {
			if (currentPlayer == 1) {
				System.out.println("Ход игрока1");
				HelpFunctions.showMap(player2FieldForCheck);
			} else {
				System.out.println("Ход игрока2");
				HelpFunctions.showMap(player1FieldForCheсk);
			}
			
			System.out.println("Введи координаты удара (формат: x,y)");
			String hit = scan.nextLine();
			HelpFunctions.clearScreen();
			String[] temp5 = {hit};
			if (!HelpFunctions.coordinateValidation(temp5)) {
				continue;
			}
			
			String[] temp1 = hit.split(",");
			int x = Integer.parseInt(temp1[0]);
			int y = Integer.parseInt(temp1[1]);
			
			
			if (currentPlayer == 1) {
				currentPlayer = HelpFunctions.hit(player2FieldForGame, player2FieldForCheck, currentPlayer, x, y);
			} else {
				currentPlayer = HelpFunctions.hit(player1FieldForGame, player1FieldForCheсk, currentPlayer, x, y);
			}
			
			scan.nextLine();
			HelpFunctions.clearScreen();
				
				
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

