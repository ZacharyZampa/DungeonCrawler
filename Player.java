/**
 * This is the class that creates the player.
 * This feeds into the respective class the player plays as
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Player extends Entity {
	// instance variables
	private int xp;  // experience the player has; has increase a skill point at certain levels
	
	
	
	/**
	 * Constructor of the Player class
	 * @param health
	 * @param currentHealth
	 * @param name
	 * @param dexterity
	 * @param strength
	 * @param armor
	 * @param intelligence
	 * @param wisdom
	 * @param charisma
	 * @param viewDistance
	 * @param type
	 * @param xp
	 * @param speed
	 */
	public Player(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence, int wisdom,
			int charisma, int viewDistance, int tmpAC, String type, int xp) {
		super(health, currentHealth, name, dexterity, strength, armor, intelligence, wisdom, charisma, viewDistance, tmpAC, type);
		this.xp = xp;
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player {xp=" + xp + ", " + super.toString() + "}";
	}
	
	/**
	 * toString with all characteristics displayed
	 */
	public String toStringFull() {
		return "Player {xp=" + xp + ", " + super.toStringFull() + "}";
	}


	// getters and setters of instance variables
	
	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}
	/**
	 * @param xp the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}
	
	/**
	 * Allows for the player to recover their health
	 * @return true if enemy attacks (rng below 5)
	 */
	public boolean rest() {
		int rng = Dice.rollD20();
		if (rng < 5) {
			System.out.println("Rest fails and an enemy supprise attacks");
			return true;
		} else if (rng >= 5 && rng < 10) {
			System.out.println("You are unable to rest");
			return false;
		} else {
			System.out.println("Rest successful; You feel rejuvenated");
			setCurrentHealth(getHealth());
			return false;
		}
	}
	
	
}
