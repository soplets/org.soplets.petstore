package org.petstore.soplets;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Translatable;
import org.petstore.soplets.SopRegion.Regionable;

@Sop(aspects = {Artifact.class, Translatable.class, Regionable.class})
public enum SopRegion {

	@Soplet(
		textEN = "Inner city", 
		textDE = "Innenstadt",
		description = "Inner area of the city (max. 5 kms)",  
		minimumDelivery = 10.0, 
		surcharge = 0.0f)
	innerCity,
	  
	@Soplet(
		textEN = "Outer city",
		textDE = "Aussenbezirke",
		description = "Outer area of the city (max. 10 kms)",  
		minimumDelivery = 15.0, 
		surcharge = 2.0f)
	outerCity,
	
	@Soplet(
		textEN = "Outside of city",
		textDE = "Ausserhalb der Stadt",
		description = "Anything which is beyond the outer city.\n" +
				"The surcharge is calculated dynamically according " +
				"to the distance (45 cents per km distance)",  
		minimumDelivery = 20.0)
		//testCase = HPTest.testSurcharge1)
	outsideCity {
		@Override
		public double getSurcharge(int distance) {
			return distance * 0.45f;  
		}
	};
	
	public double getSurcharge(int distance) {
		return surcharge();
	}

	/**
	 * describes extra properties for a region type
	 */
	public static @interface Regionable {
		
		public double minimumDelivery() default 0;
		
		/**
		 * The surcharge for delivery, dependant on the region and distance of the customer"
		 */
		public double surcharge() default 0; 

		//public HPTest testCase() default HPTest.NONE;
	}
}
