package com.gamingroom;

/**
 * A class to test a singleton's behavior
 * 
 * @author Austin Frey (austin.frey@snhu.edu)
 */
public class SingletonTester {

	public void testSingleton() {
		
		System.out.println("\nAbout to test the singleton...");
		
		// Create test instance of GameService. Should return that Game service already exists.
		GameService service = GameService.createNewGameService(); 
		
		// a simple for loop to print the games
		for (int i = 0; i < service.getGameCount(); i++) {
			System.out.println(service.getGame(i));
		}

	}
	
}
