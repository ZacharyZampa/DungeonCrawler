/**
 * This class contains the moves a Wizard class makes
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Wizard extends Player {

	public Wizard(int health, int currentHealth, String name, int dexterity, int strength, int armor, int intelligence,
			int wisdom, int charisma, int viewDistance, int tmpAC, String type, int xp) {
		super(health, currentHealth, name, dexterity, strength, armor, intelligence, wisdom, charisma, viewDistance, tmpAC,
				type, xp);
	}
	
	public void swingPrimaryWeapon(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getArmor()) {
			System.out.println("The attack from " + getName() + " does not score a hit");
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
		
		System.out.println(getName() + " swings their quarterstaff for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}
	
	public void acidThrow(Entity enemy) {
		int success = Dice.rollD20();
		if (success < enemy.getDexterity()) {
			System.out.println(getName() + " was unable to predict the " + enemy.getName() + "'s evasive manouvers");
			return;
		}
		
		int damage = Dice.rollD4();
		
		// add in modifiers based on ability score
		if (getWisdom() <= 10) {
		} else if (getWisdom() > 10 && getWisdom() < 16){
			damage += 2;
		} else {
			damage += 4;
		}
				
		System.out.println(getName() + " hurls acid at " + enemy.getName() + " for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}

	public void burningHands(Entity enemy) {
		int damage = Dice.rollD6() + Dice.rollD6() + Dice.rollD6();
		
		// add in modifiers based on ability score
		if (getWisdom() <= 10) {
		} else if (getWisdom() > 10 && getWisdom() < 16){
			damage += 1;
		} else {
			damage += 2;
		}
		
		int success = Dice.rollD20();
		if (success < enemy.getDexterity()) {
			System.out.println(getName() + " barely hits the enemy");
			damage /= 2;
			return;
		}
				
		System.out.println(getName() + " hurls fire at " + enemy.getName() + " for " + damage + " damage");
		enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
	}
	
	

}
