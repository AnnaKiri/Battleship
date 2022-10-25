package FinalProject;

import java.io.IOException;

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
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
	public static void clearScreen2() {   // заработает после компиляции
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

}
