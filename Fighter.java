/**
 * This class contains the moves a Fighter class makes
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Fighter extends Player {

	public Fighter(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence,
			int wisdom, int charisma, int viewDistance, int tmpAC, String type, int xp) {
		super(health, currentHealth, name, dexterity, strength, armor, intelligence, wisdom, charisma, viewDistance, tmpAC, type, xp);
	}
	
	public void swingPrimaryWeapon(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getArmor()) {
			System.out.println("The attack from " + getName() + " bounces off of  " + enemy.getName() + "'s  armor");
			return;
		}
		
		int damage = Dice.rollD6();
		
		// add in modifiers based on ability score
		if (getStrength() <= 10) {
		} else if (getStrength() > 10 && getStrength() < 16){
			damage += 2;
		} else {
			damage += 4;
		}
		
		System.out.println(getName() + " swings their sword for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}
	
	public void swingSecondaryWeapon(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getArmor()) {
			System.out.println("The attack from " + getName() + " bounces off of " + enemy.getName() + "'s armor");
			return;
		}
		
		int damage = Dice.rollD4();
		
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

	public void useShield() {
		System.out.println(getName() + " raises their shield to block");
		setTmpAC(getTmpAC() + 4);
	}
	
}
