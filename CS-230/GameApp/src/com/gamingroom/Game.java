package com.gamingroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * A simple class to hold information about a game
 * 
 * <p>
 * Notice the overloaded constructor that requires
 * an id and name to be passed when creating.
 * Also note that no mutators (setters) defined so
 * these values cannot be changed once a game is
 * created.
 * </p>
 * 
 * @author Austin Frey (austin.frey@snhu.edu)
 *
 */
public class Game extends Entity{
	
	// A list of current teams in this instance of Game
	private List<Team> teams = new ArrayList<Team>();

	/**
	 * Constructor with an identifier and name. Uses superclass constructor for instantiation
	 * 
	 * @param id numerical identifier of type long
	 * @param name String representation of the Game name
	 */
	public Game(long id, String name) {
		super(id, name);
	}

	/**
	 * Add a new team to the game
	 * 
	 * @param name A String representation of a new Team's name
	 * @return instance of Team (new or existing)
	 */
	public Team addTeam(String name) {

		// a local team instance
		Team team = null;

		/*
		 * Use iterator pattern to determine if a team with the same ID or name exists
		 * Iterator object was chosen because it allows Team objects to be modified.
		*/
		
		// Create Iterator object from List<Team> teams
		Iterator<Team> teamsIterator = teams.iterator();
		
		// While loop checks if there is another object in the list, ends when there are no more entries in teams
		while(teamsIterator.hasNext()) {
			// Check if the next team (which is technically the team the iterator is currently observing) already exists by comparing name attributes. 
			if (teamsIterator.next().getName() == name) {
				team = teamsIterator.next();
				System.out.println("The team named " + name + " already exists in game named " + super.getName());
			}
		}
		
		// if not found, make a new team instance and add to list of teams
		if (team == null) {
			team = new Team(GameService.getNextTeamId(), name);
			teams.add(team);
		}

		// return the new/existing team instance to the caller
		return team;
	}

	@Override
	// Print out the game information, including all teams and players that are part of that game instance.
	public String toString() {
		String printString = "Game [id=" + super.getId() + ", name=" + super.getName() + "]";
		Iterator<Team> teamsIterator = teams.iterator();
		
		// Create string that represents all Team instances that are part of the current Game instance
		while(teamsIterator.hasNext()) {
			printString += teamsIterator.next().toString();
		}
		return printString;
	}

}
