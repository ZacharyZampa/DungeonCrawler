/**
 * This is the base class that all entities come from
 * Be it an NPC or the player
 * @author Zachary 
 * @since 2019/05/26
 *
 */
public class Entity {
	// instance variables
	private int health;  // health counts down to 0
	private int currentHealth; 
	private String name;  // name of entity
	private int dexterity;  // dexterity of entity
	private int strength;  // strength of entity
	private int armor;  // level of protection by armor (Armor Class)
	private int intelligence;  // intelligence of entity
	private int wisdom;  // wisdom of entity
	private int charisma;  // charisma of entity
	private int viewDistance; // once an entity can view a hostile one; combat begins
	private int tmpAC;  // temporary armor (i.e. blocking with shield)
	private String type;  // class entity is
	
		
	/**
	 * Constructor of the Entity class
	 * @param health
	 * @param name
	 * @param dexterity
	 * @param strength
	 * @param armor
	 * @param intelligence
	 * @param wisdom
	 * @param charisma
	 * @param viewDistance
	 * @param type
	 */
	public Entity(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence, int wisdom,
			int charisma, int viewDistance, int tmpAC, String type) {
		super();
		this.health = health;
		this.currentHealth = currentHealth;
		this.name = name;
		this.dexterity = dexterity;
		this.strength = strength;
		this.armor = armor;
		this.intelligence = intelligence;
		this.wisdom = wisdom;
		this.charisma = charisma;
		this.viewDistance = viewDistance;
		this.setTmpAC(tmpAC);
		this.type = type;
	}
	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Entity [name=" + name + "health=" + health + ", current health=" 
				+ currentHealth + ", type=" + type + "]";
	}

	/**
	 * Proper toString method. Tells everything about the entity
	 * @return
	 */
	public String toStringFull() {
		return "Entity [name=" + name + ", health=" + health + ", current health=" + currentHealth 
				+ ", dexterity=" + dexterity + ", strength=" + strength + ", armor=" + armor 
				+ ", temporary armor=" + getTmpAC()
				+ ", intelligence=" + intelligence + ", wisdom=" + wisdom + ", charisma="
				+ charisma + ", viewDistance=" + viewDistance + ", type=" + type + "]";
	}





	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + armor;
		result = prime * result + charisma;
		result = prime * result + dexterity;
		result = prime * result + health;
		result = prime * result + intelligence;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + strength;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + viewDistance;
		result = prime * result + wisdom;
		return result;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Entity)) {
			return false;
		}
		Entity other = (Entity) obj;
		if (armor != other.armor) {
			return false;
		}
		if (charisma != other.charisma) {
			return false;
		}
		if (dexterity != other.dexterity) {
			return false;
		}
		if (health != other.health) {
			return false;
		}
		if (intelligence != other.intelligence) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (strength != other.strength) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		if (viewDistance != other.viewDistance) {
			return false;
		}
		if (wisdom != other.wisdom) {
			return false;
		}
		return true;
	}


	// getters and setters of instance variables

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}
	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}
	/**
	 * @return the armor
	 */
	public int getArmor() {
		return armor;
	}
	/**
	 * @param armor the armor to set
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}
	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	/**
	 * @return the wisdom
	 */
	public int getWisdom() {
		return wisdom;
	}
	/**
	 * @param wisdom the wisdom to set
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}
	/**
	 * @return the charisma
	 */
	public int getCharisma() {
		return charisma;
	}
	/**
	 * @param charisma the charisma to set
	 */
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}
	public int getViewDistance() {
		return viewDistance;
	}
	public void setViewDistance(int viewDistance) {
		this.viewDistance = viewDistance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}






	/**
	 * @return the currentHealth
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}






	/**
	 * @param currentHealth the currentHealth to set
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}






	public int getTmpAC() {
		return tmpAC;
	}






	public void setTmpAC(int tmpAC) {
		this.tmpAC = tmpAC;
	}
	
	
	
	
	
}
