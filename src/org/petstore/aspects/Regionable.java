package org.petstore.aspects;

/**
 * describes extra properties for a region type
 */
public @interface Regionable {
	
	public double minimumDelivery() default 0;
	
	/**
	 * The surcharge for delivery, dependant on the region and distance of the customer"
	 */
	public double surcharge() default 0; 

	//public HPTest testCase() default HPTest.NONE;
}