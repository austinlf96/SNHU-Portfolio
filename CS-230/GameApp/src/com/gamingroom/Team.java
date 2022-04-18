package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A simple class to hold information about a team
 * <p>
 * Notice the overloaded constructor that requires
 * an id and name to be passed when creating.
 * Also note that no mutators (setters) defined so
 * these values cannot be changed once a team is
 * created.
 * </p>
 * @author Austin Frey (austin.frey@snhu.edu)
 *
 */
public class Team extends Entity{

	// A list of current players in this instance of team
	private List<Player> players = new ArrayList<Player>(); 

	/**
	 * Constructor with an identifier and name
	 * 
	 * @param id numerical identifier of type long
	 * @param name String representation of the name of a team
	 */
	public Team(long id, String name) {
		super(id, name);
	}

	/**
	 * Add a new player to the team
	 * 
	 * @param name String representation of a new player's name
	 * @return instance of Player (new or existing)
	 */
	public Player addPlayer(String name) {

		// a local player instance
		Player player = null;

		/*
		 * Use iterator pattern to determine if a player with the same ID or name exists
		 * Iterator object was chosen because it allows player objects to be modified.
		*/
		
		// Create Iterator object from List<player> players
		Iterator<Player> playersIterator = players.iterator();
		
		// While loop checks if there is another object in the list, ends when there are no more entries in players
		while(playersIterator.hasNext()) {
			// Check if the next player (which is technically the player the iterator is currently observing) already exists by comparing name attributes. 
			if (playersIterator.next().getName() == name) {
				player = playersIterator.next();
				System.out.println("The player named " + name + " already exists in the team named " + super.getName());
			}
		}
		
		// if not found, make a new team instance and add to list of teams
		if (player == null) {
			player = new Player(GameService.getNextPlayerId(), name);
			players.add(player);
		}

		// return the new/existing player instance to the caller
		return player;
	}

	@Override
	// Print out Team instance information including all players that are part of that team
	public String toString() {
		String printString = "\n\tTeam [id=" + super.getId() + ", name=" + super.getName() + "]";
		Iterator<Player> playersIterator = players.iterator();
		
		// Create string that includes all Player instances that are part of the current Team instance
		while(playersIterator.hasNext()) {
			printString += playersIterator.next().toString();
		}
		
		return printString;
	}
}
