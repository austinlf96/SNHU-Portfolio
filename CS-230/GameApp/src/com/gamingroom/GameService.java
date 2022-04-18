package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A singleton service for the game engine
 * 
 * @author Austin Frey (austin.frey@snhu.edu)
 */

/*
 * The singleton pattern is used for GameService because GameService keeps track of all current games and their respective names.
 * Having multiple GameService instances could introduce bugs where two games have the same name. Thus a singleton pattern is 
 * utilized to ensure a one-to-one relationship between a game's name and a game. It also creates a one-to-many relationship
 * between GameService and instances of Game, because GameService manages all the instances of Game. - Austin Frey
 */

/*
 * The iterator pattern is used to determine if instances of a game exist. The iterator pattern is used with the Iterator object
 * rather than a for loop with an iterator variable or a for-each loop because it allows the Game object to be modified without 
 * throwing exceptions. 
 */
public class GameService {

	// A list of the active games
	private static List<Game> games = new ArrayList<Game>();

	//Holds the next game identifier
	private static long nextGameId = 1;

	// Holds the next player identifier
	private static long nextPlayerId = 1;
	
	// Holds the next team identifier
	private static long nextTeamId = 1;
	
	// Private variable declaration ensures only one instance of the GameService exists at any given time
	private static GameService service;
	
	// Private constructor prevents instantiation of additional instances of GameService 
	private GameService() {}
	
	// Public method allows a single instance of GameService to be created
	public static GameService createNewGameService() {
		
		// Check if an instance of GameService has been created yet
		if (service == null) {
			service = new GameService();
			System.out.println("New Game service instantiated");
		} 
		else {
			System.out.println("Game service already exists.");
		}
		return service;
	}
	


	/**
	 * Construct a new game instance
	 * 
	 * @param name the unique name of the game
	 * @return the game instance (new or existing)
	 */
	public Game addGame(String name) {

		// a local game instance
		Game game = null;

		/*
		 * Use iterator pattern to determine if a game with the same ID exists
		 * Iterator object was chosen because it allows Game objects to be modified.
		*/
		
		// Create Iterator object from List<Game> games
		Iterator<Game> gamesIterator = games.iterator();
		
		// While loop checks if there is another object in the list, ends when there are no more entries in games
		while(gamesIterator.hasNext()) {
			// Check if the next game (which is technically the game the iterator is currently observing) already exists by comparing name attributes. 
			if (gamesIterator.next().getName() == name) {
				game = gamesIterator.next();
				System.out.println("The game named " + name + " already exists.");
			}
		}
		
		// if not found, make a new game instance and add to list of games
		if (game == null) {
			game = new Game(nextGameId++, name);
			games.add(game);
		}

		// return the new/existing game instance to the caller
		return game;
	}
	
	/* 
	 * ************************************************************************************************************************
	 * ***** getGame(int index) IS NOT part of the UML! It was left in because it was part of the original project files. *****
	 * ************************************************************************************************************************
	 */
	
	/**
	 * Returns the game instance at the specified index.
	 * <p>
	 * Scope is package/local for testing purposes.
	 * </p>
	 * @param index index position in the list to return
	 * @return requested game instance
	 */
	public Game getGame(int index) {
		return games.get(index);
	}
	
	/**
	 * Returns the game instance with the specified id.
	 * 
	 * @param id unique identifier of game to search for
	 * @return requested game instance
	 */
	public Game getGame(long id) {

		// a local game instance
		Game game = null;

		/*
		 * Use iterator pattern to determine if a game with the same ID exists
		 * Iterator object was chosen because it allows Game objects to be modified.
		*/
		
		// Create Iterator object from List<Game> games
		Iterator<Game> gamesIterator = games.iterator();
		
		// While loop checks if there is another object in the list, ends when there are no more entries in games
		while(gamesIterator.hasNext()) {
			// Check if the next game (which is technically the game the iterator is currently observing) already exists by comparing ID attributes.
			if (gamesIterator.next().getId() == id) {
				game = gamesIterator.next();
			}
		}

		return game;
	}

	/**
	 * Returns the game instance with the specified name.
	 * 
	 * @param name unique name of game to search for
	 * @return requested game instance
	 */
	public Game getGame(String name) {

		// a local game instance
		Game game = null;

		/*
		 * Use iterator pattern to determine if a game with the same ID exists
		 * Iterator object was chosen because it allows Game objects to be modified.
		*/
		
		// Create Iterator object from List<Game> games
		Iterator<Game> gamesIterator = games.iterator();
		
		// While loop checks if there is another object in the list, ends when there are no more entries in games
		while(gamesIterator.hasNext()) {
			// Check if the next game (which is technically the game the iterator is currently observing) already exists by comparing name attributes. 
			if (gamesIterator.next().getName() == name) {
				game = gamesIterator.next();
			}
		}

		return game;
	}

	/**
	 * Returns the number of games currently active
	 * 
	 * @return the number of games currently active
	 */
	public int getGameCount() {
		return games.size();
	}

	/**
	 * 
	 * @return next player numerical identifier of type long
	 */
	public static long getNextPlayerId() {
		return nextPlayerId++;
	}

	/**
	 * 
	 * @return next team numerical identifier of type long
	 */
	public static long getNextTeamId() {
		return nextTeamId++;
	}
}
