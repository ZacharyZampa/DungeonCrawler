import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Scanner;


public class MainController {
	static Scanner keyboard = new Scanner(System.in);

	/**
	 * Player levels up; resets XP to 0 but allows user to pick new 
	 */
	public static void levelup(Player play) {
		play.setXp(0);  // reset xp to 0
		System.out.println("What would you like to increase?\n"
				+ "Dexterity, Strength, Intelligence, Wisdom, Charisma, Health");
		char resp = 0;
		do {
			// loop until acceptable input occurs
			resp = keyboard.next().toUpperCase().charAt(0);
		} while (resp == 0 || resp != 'D' || resp != 'S' || resp != 'I' || resp != 'W' || resp != 'C' || resp != 'H');

		// increase whichever is chosen by 1
		switch (resp) {
		case 'D':
			play.setDexterity(play.getDexterity() + 1);
			break;
		case 'S':
			play.setStrength(play.getStrength() + 1);
			break;
		case 'I':
			play.setIntelligence(play.getIntelligence() + 1);
			break;
		case 'W':
			play.setWisdom(play.getWisdom() + 1);
			break;
		case 'C':
			play.setCharisma(play.getCharisma() + 1);
			break;
		case 'H':
			play.setHealth(play.getHealth() + 1);
		default:
			System.out.println("ERROR: Level up Error");
			System.exit(1);
			break;
		}
	}


	/**
	 * Player chooses what class they wish to be 
	 */
	public static String chooseClass() {
		System.out.println("What class do you wish to be?\n" + "Fighter, Paladin, Rogue, Wizard");
		char resp = 0;
		while (true) {
			// loop until acceptable input occurs
			resp = keyboard.next().toUpperCase().charAt(0);

			if (resp == 'F' || resp == 'P' || resp == 'R' || resp == 'W') {
				break;
			}
		}

		switch (resp) {
		case 'F':
			return "Fighter";
		case 'P':
			return "Paladin";
		case 'R':
			return "Rogue";
		case 'W':
			return "Wizard";
		default:
			System.out.println("ERROR: Class Choice Error");
			System.exit(2);
			return null;
		}
	}

	/**
	 * Allow the AI to choose their class type
	 * @return 
	 */
	private static String chooseClassAI() {
		switch (Dice.rollD4()) {
		case 1:
			return "Fighter";
		case 2:
			return "Paladin";
		case 3:
			return "Rogue";
		case 4:
			return "Wizard";
		default:
			System.out.println("ERROR: Class Choice Error");
			System.exit(2);
			return null;
		}
	}

	/**
	 * Allow the user to create their Player object
	 * @return Player object
	 */
	public static Player characterCreation() {
		System.out.println("Please build your character");
		System.out.println("What would you like to name your character?");
		String name = keyboard.nextLine();

		System.out.println("--Generating Assignable Attribute Values--");
		ArrayList<Integer> attributeValues = new ArrayList<>();
		for (int i = 0; i < 9; i++) {
			attributeValues.add(Dice.rollD20());  // generate one from 1-20
		}
		System.out.println(attributeValues);

		// begin assignment
		System.out.println("Assign health");
		int health = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign dexterity");
		int dexterity = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign strength");
		int strength = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign armor");
		int armor = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign intelligence");
		int intelligence = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign wisdom");
		int wisdom = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign charisma");
		int charisma = attributeChoice(attributeValues);
		System.out.println(attributeValues);
		System.out.println("Assign viewDistance");
		int viewDistance = attributeChoice(attributeValues) + 10;

		// choose class of warrior
		String type = chooseClass();

		// construct character based off of input
		Player play = null;
		switch (type) {
		case "Fighter":
			play = new Fighter(health, health, name, dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Rogue":
			play = new Rogue(health, health, name, dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Paladin":
			play = new Paladin(health, health, name, dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Wizard":
			play = new Wizard(health, health, name, dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		}


		System.out.println(play.toStringFull());  // display players attributes

		return play;
	}


	/**
	 * Facilitate player's choice of attributes for Player Object
	 * @param list List of generated choices
	 * @return Final Choice
	 */
	private static int attributeChoice(ArrayList<Integer> list) {
		int resp;
		
		while (true) {
			// loop until acceptable input occurs
			String fullResp = keyboard.next();
			if (isNumber(fullResp)) {
				// response was a number
				resp = Integer.parseInt(fullResp);  // parse to int
				if (list.contains(resp)) {
					// list contains response -- allow as response
					list.remove((Integer)resp);
					break;
				}
			}
		}
		
		return resp;
	}


	/**
	 * Adapted from https://rosettacode.org/wiki/Determine_if_a_string_is_numeric#Java
	 * Seemed to be a more robust way to discover if a String is a number than using
	 * exception throwing
	 * @param fullResp
	 * @return boolean true if String is number
	 */
	private static boolean isNumber(String fullResp) {
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(fullResp, pos);
		return fullResp.length() == pos.getIndex();
	}

	/**
	 * Generate an enemy
	 * @return
	 */
	private static Entity generateEnemy() {
		int[] attributeValues = new int[8];
		for (int i = 0; i < 8; i++) {
			attributeValues[i] = Dice.rollD20();  // generate one from 1-20
		}
	
		// begin assignment
		int health = attributeValues[0];
		int dexterity = attributeValues[1];
		int strength = attributeValues[2];
		int armor = attributeValues[3];
		int intelligence = attributeValues[4];
		int wisdom = attributeValues[5];
		int charisma = attributeValues[6];
		int viewDistance = attributeValues[7] + 10;
	
		// choose class of warrior
		String type = chooseClassAI();
	
		// construct character based off of input
		Entity enemy = null;
		switch (type) {
		case "Fighter":
			enemy = new Fighter(health, health, "The Enemy", dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Rogue":
			enemy = new Rogue(health, health, "The Enemy", dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Paladin":
			enemy = new Paladin(health, health, "The Enemy", dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		case "Wizard":
			enemy = new Wizard(health, health, "The Enemy", dexterity, strength, armor, intelligence,
					wisdom, charisma, viewDistance, armor, type, 0);
			break;
		}
		
		return enemy;
	}

	/**
	 * Decide if enemy exists up ahead
	 * @return boolean - true if enemy exists
	 */
	private static boolean possibleEnemy() {
		int rng = Dice.rollD20();
		
		if (rng > 12) {
			return true;
		}
		
		return false;
	}

	/**
	 * Allow the player to choose what attack to do
	 * @param play Players object
	 * @param enemy Enemies Object
	 */
	public static void attackSelectorPlayer(Player play, Entity enemy) {
		System.out.println("Choose your action: Enter number of choice");
		@SuppressWarnings("unused")
		int resp;  // user response
		switch(play.getType()) {
		case "Fighter":
			System.out.println("1. Swing Sword");  
			System.out.println("2. Stab Dagger");
			System.out.println("3. Use Shield");

			// convert back to fighter object
			if (!(play instanceof Fighter)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Fighter fig = (Fighter) play;

			switch (resp = getAttackAction()) {
			case 1: 
				fig.swingPrimaryWeapon(enemy);
				break;
			case 2: 
				fig.swingSecondaryWeapon(enemy);
				break;
			case 3: 
				fig.useShield();
				break;
			}
			break;
		case "Rogue":
			System.out.println("1. Swing Short Sword");  
			System.out.println("2. Sneak Attack");
			System.out.println("3. Sneak Past Enemy");

			// convert back to Rouge object
			if (!(play instanceof Rogue)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Rogue rg = (Rogue) play;

			switch (getAttackAction()) {
			case 1: 
				rg.swingPrimaryWeapon(enemy);
				break;
			case 2: 
				rg.sneakAttack(enemy);
				break;
			case 3: 
				rg.sneakPastEnemy(enemy);
				break;
			}
			break;
		case "Paladin":
			System.out.println("1. Swing Sword");  
			System.out.println("2. Command Enemy");
			System.out.println("3. Heal Yourself");

			// convert back to Paladin object
			if (!(play instanceof Paladin)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Paladin pal = (Paladin) play;

			switch (resp = getAttackAction()) {
			case 1: 
				pal.swingPrimaryWeapon(enemy);
				break;
			case 2: 
				pal.command(enemy);
				break;
			case 3: 
				pal.healSelf();
				break;
			}
			break;
		case "Wizard":
			System.out.println("1. Use Quarterstaff");  
			System.out.println("2. Acid Throw");
			System.out.println("3. Fire Blast");

			// convert back to Wizard object
			if (!(play instanceof Wizard)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Wizard wiz = (Wizard) play;

			switch (resp = getAttackAction()) {
			case 1: 
				wiz.swingPrimaryWeapon(enemy);
				break;
			case 2: 
				wiz.acidThrow(enemy);
				break;
			case 3: 
				wiz.burningHands(enemy);
				break;
			}
			break;
		}
	}


	/**
	 * Allow the AI to choose what attack to do
	 * @param play Players object
	 * @param enemy Enemys Object
	 */
	private static void attackSelectorAI(Player play, Entity enemy) {
		int resp = (int)(Math.random() * ((3 - 1) + 1)) + 1;  // AI response
		switch(enemy.getType()) {
		case "Fighter":
			// convert back to fighter object
			if (!(enemy instanceof Fighter)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Fighter fig = (Fighter) enemy;
	
			switch (resp) {
			case 1: 
				fig.swingPrimaryWeapon(play);
				break;
			case 2: 
				fig.swingSecondaryWeapon(play);
				break;
			case 3: 
				fig.useShield();
				break;
			}
			break;
		case "Rogue":	
			// convert back to Rouge object
			if (!(enemy instanceof Rogue)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Rogue rg = (Rogue) enemy;
	
			switch (resp) {
			case 1: 
				rg.swingPrimaryWeapon(play);
				break;
			case 2: 
				rg.sneakAttack(play);
				break;
			}
			break;
		case "Paladin":
			// convert back to Paladin object
			if (!(enemy instanceof Paladin)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Paladin pal = (Paladin) enemy;
	
			switch (resp) {
			case 1: 
				pal.swingPrimaryWeapon(play);
				break;
			case 2: 
				pal.command(play);
				break;
			case 3: 
				pal.healSelf();
				break;
			}
			break;
		case "Wizard":
			// convert back to Wizard object
			if (!(enemy instanceof Wizard)) {
				System.out.println("Error: Player Attack Selection");
				System.exit(3);
			}
			Wizard wiz = (Wizard) enemy;
	
			switch (resp) {
			case 1: 
				wiz.swingPrimaryWeapon(enemy);
				break;
			case 2: 
				wiz.acidThrow(enemy);
				break;
			case 3: 
				wiz.burningHands(enemy);
				break;
			}
			break;
		}
	
	}


	/**
	 * Get the action player wishes to take in a battle
	 * @return menu choice
	 */
	private static int getAttackAction() {
		char resp;
		while (true) {
			// loop until acceptable input occurs
			resp = keyboard.next().charAt(0);

			if (resp == '1' || resp == '2' || resp == '3') {
				break;
			} 
		}
		
		return Character.getNumericValue(resp);
	}


	/**
	 * Engage in a battle
	 * @param play player
	 * @param enemy the enemy
	 * @param first true if player engages first
	 */
	private static void battle(Player play, Entity enemy, boolean first) {
		if (play.getCurrentHealth() <= 0 || enemy.getCurrentHealth() <= 0) {
			// player or enemy dead
			// in case player wins; increase XP
			play.setXp(play.getXp() + 10);
			return;
		}

		if (first) {
			attackSelectorPlayer(play, enemy);
		} else {
			attackSelectorAI(play, enemy);
		}
		
		System.out.println("\nYour Health = " + play.getCurrentHealth());
		
		battle(play, enemy, !first);
	}



	/**
	 * The Method that organizes all of the actual game duties
	 * @param play Players object
	 */
	private static void playGame(Player play) {
		// continue game while player's health is above 0
		while (play.getCurrentHealth() > 0) {
			if (play.getXp() >= 100) {
				// player is ready to level up
				levelup(play);
			}
			
			boolean pEn = possibleEnemy();  // determine if an enemy exists nearby
			if (pEn) {
				Entity enemy = generateEnemy();  // generate the enemy from one of the classes
				// possible enemy exists
				int enDist = Dice.rollD20() + Dice.rollD20();  // Determine how close to player
				if (enDist <= play.getViewDistance()) {
					System.out.println("\nYou see the enemy in the distance; You move in for the attack");
					// player can see enemy -- player engage with first turn
					battle(play, enemy, true);
				} else if (enDist <= enemy.getViewDistance()) {
					// enemy can see player -- player engage with first turn
					System.out.println("\nSuprise! The enemy launches a suprise attack against you");
					battle(play, enemy, false);
				}
			}
	
			if (play.getCurrentHealth() <= 0) {
				// player died in fight
				break;
			}
	
			// else no enemy in sight			
			System.out.println("(C) Continue down path or (R) take a rest?");
			char resp = 0;
			while (true) {
				// loop until acceptable input occurs
				resp = keyboard.next().toUpperCase().charAt(0);
	
				if (resp == 'C' || resp == 'P' || resp == 'T' || resp == 'R') {
					break;
				} 
			}	
	
			if (resp == 'C' || resp == 'P') {
				System.out.println("You continue down further into the dungeon");
			} else if (resp == 'T' || resp == 'R') {
				boolean rflag = play.rest();
				if (rflag) {
					// enemy interrupted rest
					battle(play, (Entity) generateEnemy(), false);
				}
			}
		} 
	}


	public static void main(String[] args) {
		System.out.println("Welcome to the Dungeon Crawler Game\n");

		// Create player character
		Player play = characterCreation();

		System.out.println("\nYou enter the dungeon and begin the journey to unravel its secrets...\n");

		playGame(play);

		System.out.println("\nThank you for playing!");
		System.out.println("\tCredits\t\n Creator: Zachary Zampa\n Inspiration derrived from DnD and Friends");


	}

}
