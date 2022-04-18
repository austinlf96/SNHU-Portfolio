package com.gamingroom;

/**
 * Application start-up program
 * 
 * @author Austin Frey (austin.frey@snhu.edu)
 */
public class ProgramDriver {
	
	/**
	 * The one-and-only main() method
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		// Create initial instance of GameService
		GameService service = GameService.createNewGameService();
		
		System.out.println("\nAbout to test initializing game data...");
		
		// initialize with some game data
		Game game1 = service.addGame("Game #1");
		
		// Create sample team
		Team team1 = game1.addTeam("Beardies");
		Player player1 = team1.addPlayer("Austin");
		Player player2 = team1.addPlayer("Julian");
		
		// Create second sample team
		Team team2 = game1.addTeam("Geckos");
		Player player3 = team2.addPlayer("Jerry");
		Player player4 = team2.addPlayer("Rick");
		
		// Test string outputs
		System.out.println(game1);
		
		// Initialize second game instance
		Game game2 = service.addGame("Game #2");
		
		// Create sample team for game 2 with same names. (This is allowed, because it is a unique game instance)
		// The entities should each have different id numbers despite having the same names.
		Team team3 = game2.addTeam("Beardies");
		Player player5 = team3.addPlayer("Austin");
		Player player6 = team3.addPlayer("Julian");
		
		System.out.println(game2);
		
		// Test game name control
		Game testGame = service.addGame("Game #1");
		
		// Verify game name control by checking game count. Should be two
		System.out.println("Game count: " + service.getGameCount());
		
		// Test team name control
		Team testTeam = game1.addTeam("Beardies");
		
		// Test player name control
		Player testPlayer = team2.addPlayer("Jerry");
		
		// use another class to prove there is only one instance of GameService
		SingletonTester tester = new SingletonTester();
		tester.testSingleton();
	}
}
