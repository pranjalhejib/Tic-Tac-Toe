import java.util.*;

public class BasicTicTacToe {
	static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();
	static String  user;
	static int userSymbol;
	BasicTicTacToe(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Your Name: ");
		user = scan.nextLine();
		System.out.println("Enter Your choice symbol \n Press 1 for: 'X' \n Press 2 for: 'O' \n");
		userSymbol = scan.nextInt();
		while(userSymbol >2 || userSymbol <=0) {
			if(userSymbol == 1 || userSymbol == 2) {
					userSymbol = scan.nextInt();
			}
			else {
				System.out.println("Oops... It's a wrong choice :( \n ");
				System.out.println("Please select \n 1 for: 'X' \n 2 for: 'O' \n");
				userSymbol = scan.nextInt();
			}
		}
	}

	public static void main(String[] args) {
		new BasicTicTacToe();
		
		char[][] gameBoard = { { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' },
				{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' }, };
		printGameConsole(gameBoard);
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("\n Enter Your placement number (1-9): ");
			int playerPlacement = sc.nextInt();
			while (playerPosition.contains(playerPlacement) || cpuPosition.contains(playerPlacement)) {
				System.out.println("\n Position Takened! Enter another positon : ");
				playerPlacement = sc.nextInt();
			}
			String finalResult = winner();
			if (finalResult.length() > 0) {
				System.out.print(finalResult);
				break;
			}
			placePiece(gameBoard, playerPlacement,user);
			printGameConsole(gameBoard);
			finalResult = winner();
			if (finalResult.length() > 0) {
				System.out.print(finalResult);
				break;
			}
			Random rand = new Random();
			int cpuPlacement = rand.nextInt(9) + 1;
			while (playerPosition.contains(cpuPlacement) || cpuPosition.contains(cpuPlacement)) {
				cpuPlacement = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPlacement, "cpu");
			printGameConsole(gameBoard);
		}
	}

	public static void placePiece(char[][] gameBoard, int placement, String user) {
		char symbol;
		char cpuIcon;
		char userIcon;
		if(userSymbol == 1) {
			cpuIcon = 'O';
			userIcon = 'X';
		} else {
			cpuIcon = 'X';
			userIcon = 'O';
		}
		if(user.equals("cpu")) {
			symbol = cpuIcon;
			cpuPosition.add(placement);
			System.out.println("\n Cpu placed at at " + placement);
			
		} else {
			symbol = userIcon;
			playerPosition.add(placement);
			System.out.println("\n" + user+ "Placed at " + placement);
			
		}
		switch (placement) {

		case 1:
			gameBoard[0][0] = symbol;
			break;
		case 2:
			gameBoard[0][2] = symbol;
			break;
		case 3:
			gameBoard[0][4] = symbol;
			break;
		case 4:
			gameBoard[2][0] = symbol;
			break;
		case 5:
			gameBoard[2][2] = symbol;
			break;
		case 6:
			gameBoard[2][4] = symbol;
			break;
		case 7:
			gameBoard[4][0] = symbol;
			break;
		case 8:
			gameBoard[4][2] = symbol;
			break;
		case 9:
			gameBoard[4][4] = symbol;
			break;
		}

	}

	// printing to console
	public static void printGameConsole(char[][] gameBoard) {
		for (char[] row : gameBoard) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	// Method to check the winner

	public static String winner() {
		List<List<Integer>> winCon = new ArrayList<List<Integer>>();
		winCon.add(Arrays.asList(1, 2, 3)); // first Row of the table
		winCon.add(Arrays.asList(4, 5, 6)); // Second Row of the table
		winCon.add(Arrays.asList(7, 8, 9)); // Third Row of the table
		winCon.add(Arrays.asList(1, 4, 7)); // First Column of the table
		winCon.add(Arrays.asList(2, 5, 8)); // Second Column of the table
		winCon.add(Arrays.asList(3, 6, 9)); // Third Column of the table
		winCon.add(Arrays.asList(1, 5, 9)); // first diagonal of the table
		winCon.add(Arrays.asList(3, 5, 7)); // Second diagonal of the table

		for (List<Integer> l : winCon) {
			if (playerPosition.containsAll(l)) {
				return "Congargulations !!!! You Won :)";
			} else if (cpuPosition.containsAll(l)) {
				return "Robot Nailed it. Cpu Won!!!";
			} else if (playerPosition.size() + cpuPosition.size() == 9) {
				return "It's a Tie Buddy";
			}   
		}
		return "";
	}

}