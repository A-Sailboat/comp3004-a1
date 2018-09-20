package core;
import java.util.Scanner;
public class blackJack {
	static Scanner reader = new Scanner(System.in);
	public static void main(String arg[]) {
		Casino gameSpace = new Casino();
	
		System.out.println("Play in console mode (c) or read from a file (f)");
		String selection = reader.next();
		gameSpace.runGame(selection);
			
		System.out.print("See you space cowboy...");
	}

}
