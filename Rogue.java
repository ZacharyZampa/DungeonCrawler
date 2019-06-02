/**
 * This class contains the moves a Rogue class makes
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Rogue extends Player {

	public Rogue(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence,
			int wisdom, int charisma, int viewDistance, int tmpAC, String type, int xp) {
		super(health, currentHealth, name, dexterity, strength, armor, intelligence, wisdom, charisma, viewDistance, tmpAC,
				type, xp);
	}
	
	public void swingPrimaryWeapon(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getArmor()) {
			System.out.println("The attack from " + getName() + " bounces off of  " + enemy.getName() + "'s  armor");
			return;
		}
		
		int damage = Dice.rollD6();
		
		// add in modifiers based on ability score
		if (getDexterity() <= 10) {
		} else if (getDexterity() > 10 && getDexterity() < 16){
			damage += 2;
		} else {
			damage += 4;
		}
		
		System.out.println(getName() + " swings their short sword for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}
	
	public void sneakAttack(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getIntelligence()) {
			System.out.println(getName() + " notices that " + enemy.getName() + " is too observant to launch a sneak attack against");
			return;
		}

		System.out.println(getName() + " manages to sneak up to " + enemy.getName());
		
		int damage = Dice.rollD4() + 2;  // adds two damage since surprise
		
		// add in modifiers based on ability score
		if (getDexterity() <= 10) {
		} else if (getDexterity() > 10 && getDexterity() < 16){
			damage += 2;
		} else {
			damage += 4;
		}
		
		System.out.println(getName() + " swings their dagger for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}

	public boolean sneakPastEnemy(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getIntelligence()) {
			System.out.println(getName() + " notices that " + enemy.getName() + " is too observant to sneak past");
			return false;
		}

		System.out.println(getName() + " manages to sneak past " + enemy.getName());
		enemy.setHealth(-1);  // treat enemy as dead since no longer an issue
		return true;
	}

}
