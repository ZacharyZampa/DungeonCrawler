import java.util.Random;

/**
 * Roll a dice
 * @author Zachary Zampa
 * @since 2019/05/26
 *
 */
public class Dice {
	private static Random rng = new Random();
	
	/**
	 * Roll a D4 Dice
	 * @return number it rolled
	 */
	public static int rollD4() {
		return rng.nextInt(3) + 1;
	}
	
	/**
	 * Roll a D6 Dice
	 * @return number it rolled
	 */
	public static int rollD6() {
		return rng.nextInt(5) + 1;
	}
	
	/**
	 * Roll a D8 Dice
	 * @return number it rolled
	 */
	public static int rollD8() {
		return rng.nextInt(7) + 1;
	}
	
	/**
	 * Roll a D10 Dice
	 * @return number it rolled
	 */
	public static int rollD10() {
		return rng.nextInt(9) + 1;
	}
	
	/**
	 * Roll a D12 Dice
	 * @return number it rolled
	 */
	public static int rollD12() {
		return rng.nextInt(11) + 1;
	}
	
	/**
	 * Roll a D4 Dice
	 * @return number it rolled
	 */
	public static int rollD20() {
		return rng.nextInt(19) + 1;
	}
}
