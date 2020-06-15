/**
 * 
 */
package main.java.com.klarna;

/**
 * @author Rupesh Sharma
 *
 */
public class SmoothieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Smoothie smoothie = new Smoothie();
		System.out.println(smoothie.ingredients("Classic,-strawberry,-peanut"));

	}

}

class Smoothie {
	public static String ingredients(String order) {

		return new SmoothieContext().getSmoothieIngredients(order);
	}
}

/**
 * Smoothie context to get the required Smoothie.
 * 
 * @author Rupesh Sharma
 *
 */
class SmoothieContext {

	// Smoothie Strategy
	private SmoothieStrategy strategy;

	/**
	 * Get Smoothie ingredients for the input order
	 * 
	 * @param order input order
	 */
	public String getSmoothieIngredients(String order) {

		if (order == null || "".equals(order.trim())) {
			throw new IllegalArgumentException();
		}

		String[] input = order.split(",");
		String smoothieType = input[0];
		SmoothieStrategy strategy;
		switch (smoothieType) {
		case "Classic":
			strategy = new ClassicSmoothie();
			break;
		case "Freezie":
			strategy = new FreezieSmoothie();
			break;
		case "Greenie":
			strategy = new GreenieSmoothie();
			break;
		case "Just Desserts":
			strategy = new JustDesserts();
			break;
		default:
			throw new IllegalArgumentException();
		}

		return strategy.getSmoothieIngredients(input, strategy);

	}

}

interface SmoothieStrategy {

	/**
	 * Get Smoothie ingredients for Smoothie
	 * 
	 * @param type Type of the Smoothie
	 */
	default String getSmoothieIngredients(String[] order, SmoothieStrategy strategy) {

		StringBuilder smothieIngredients = new StringBuilder("");

		for (String ingredientToAdd : strategy.getIngredients()) {

			boolean removeIngredient = false;

			for (int i = 1; i < order.length; i++) {
				if (ingredientToAdd.equals(order[i].substring(1))) {
					removeIngredient = true;
					break;
				}else {
					throw new IllegalArgumentException();
				}
			}
			if (!removeIngredient) {
				smothieIngredients.append(ingredientToAdd).append(",");
			}
		}

		String ingr = smothieIngredients.toString();
		if (ingr.endsWith(",")) {
			ingr = ingr.substring(0, ingr.length() - 1);
		}

		return ingr;
	}

	/**
	 * Get ingredients for a Smoothie
	 * 
	 * @return
	 */
	String[] getIngredients();

}

class ClassicSmoothie implements SmoothieStrategy {

	// Ingredients for Classic Smoothie
	private final String[] ingredients = { "banana", "honey", "mango", "peach", "pineapple", "strawberry" };

	@Override
	public String[] getIngredients() {
		return this.ingredients;
	}

}

class FreezieSmoothie implements SmoothieStrategy {

	// Ingredients for Freezie Smoothie
	private final String[] ingredients = { "blackberry", "black currant", "blueberry", " frozen yogurt",
			" grape juice" };

	@Override
	public String[] getIngredients() {
		return this.ingredients;
	}

}

class GreenieSmoothie implements SmoothieStrategy {

	// Ingredients for Greenie Smoothie
	private final String[] ingredients = { " apple juice", "avocado", "green apple", "ice", "lime", "spinach" };

	@Override
	public String[] getIngredients() {
		return this.ingredients;
	}

}

class JustDesserts implements SmoothieStrategy {

	// Ingredients for Desserts Smoothie
	private final String[] ingredients = { "banana", "cherry", "chocolate", "ice cream", "peanut" };

	@Override
	public String[] getIngredients() {
		return this.ingredients;
	}

}


