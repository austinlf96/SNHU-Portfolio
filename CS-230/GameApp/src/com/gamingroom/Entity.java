package com.gamingroom;

/**
 * A superclass for Game, Team, and Player to inherit from
 * 
 * <p>
 * An overloaded instructor is included that requires id and name. The default
 * constructor is hidden to prevent empty instantiations. There are no setter
 * methods, which assists with maintaining unique identifiers amongst all
 * game and team names. 
 * </p>
 * 
 * @author Austin Frey (austin.frey@snhu.edu)
 *
 */

public class Entity {
	private long id;
	private String name;
	
	/**
	 * Private default constructor prevents empty instantiations
	 */
	private Entity() {}
	
	/**
	 * Overloaded constructor that requires a numerical id and a name
	 * 
	 * @param id a numerical id of type long
	 * @param name a String representing the name of the entity
	 */
	public Entity(long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return string formatted to include type, name, and id
	 */
	public String toString() {
		return "Entity [id= " + id + ", name: " + name;
	}
	
}
