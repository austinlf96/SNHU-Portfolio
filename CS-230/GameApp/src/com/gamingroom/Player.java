package com.gamingroom;

/**
 * A simple class to hold information about a player
 * <p>
 * Notice the overloaded constructor that requires
 * an id and name to be passed when creating.
 * Also note that no mutators (setters) defined so
 * these values cannot be changed once a player is
 * created.
 * </p>
 * @author Austin Frey (austin.frey@snhu.edu)
 *
 */
public class Player extends Entity{
	
	/*
	 * Constructor with an identifier and name
	 */
	/**
	 * Constructor with id and name. Uses super class constructor for instantiation
	 * @param id numerical identifier of type long
	 * @param name String representation of a player name
	 */
	public Player(long id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "\n\t\tPlayer [id=" + super.getId() + ", name=" + super.getName() + "]";
	}
}
