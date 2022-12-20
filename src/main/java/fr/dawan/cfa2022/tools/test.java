package fr.dawan.cfa2022.tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.aspectj.weaver.ast.Literal;

public class test {
	   public String[] ingredients;
	    public String[] toppings;
	    
	    public static class IceCream {
	        public String ingredient;
	        public String topping;

	        public IceCream(String ingredient, String topping) {
	            this.ingredient = ingredient;
	            this.topping = topping;
	        }
	    }

	    public test(String[] ingredeints, String[] toppings) {
	    	  this.ingredients = ingredeints;
		        this.toppings = toppings;
		}

	    public List<IceCream> scoops() {
	    	List<IceCream> res =  new ArrayList<>();
	    	
	    	for (int i = 0; i < ingredients.length; i++) {
				for (int j = 0; j < toppings.length; j++) {
					IceCream ice = new IceCream(ingredients[i], toppings[j]);
					res.add(ice);
				}
			}
	    	
	    	return res;
	    }

	    public static void main(String[] args) {
	        test machine = new test(new String[]{
	                "vanilla", "chocolate"
	        }, new String[]{
	                "chocolate sauce"
	        });
	        List<IceCream> scoops = machine.scoops();

	        /*
	         * Should print:
	         * vanilla, chocolate sauce
	         * chocolate, chocolate sauce
	         */
	        for (IceCream iceCream : scoops) {
	            System.out.println(iceCream.ingredient + ", " + iceCream.topping);
	        }
	    }

}
