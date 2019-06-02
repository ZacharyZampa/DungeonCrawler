/**
 * This class contains the moves a Paladin class makes
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Paladin extends Player {

	public Paladin(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence,
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
		
		int damage = Dice.rollD8();
		
		if (success > 18) {
			System.out.println(getName() + " swings their sword with such devotion the weapon itself glows");
			damage += 4;
		}
		
		
		
		// add in modifiers based on ability score
		if (getStrength() <= 10) {
		} else if (getStrength() > 10 && getStrength() < 16){
			damage += 2;
		} else {
			damage += 4;
		}
		
		System.out.println(getName() + " swings their great sword for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}
	
	public boolean command(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getWisdom()) {
			System.out.println(getName() + " is unable to break through " + enemy.getName() + "'s  mental defenses");
			return false;
		}
				
		System.out.println(getName() + " commands " + enemy.getName() + " into surrenduring; thus ending the battle");
		enemy.setCurrentHealth(-1);
		return true;
	}

	public void healSelf() {
		System.out.println(getName() + " heals themselves 5 hit points");
		setCurrentHealth(getCurrentHealth() + 5);
	}
	
	
}
