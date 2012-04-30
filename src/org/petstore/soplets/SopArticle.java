package org.petstore.soplets;

import java.util.Calendar;

import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Priceable;
import org.petstore.aspects.Translatable;


/**
 * Is an entity static or dynamic? This example illustrates the borderline
 * between static and dynamic entities.
 *
 * An entity is static if it does not change in between one product cycle, 
 * that is, from one release to the next. If there are chances, that this will happen,
 * or if it is a hard business requirement, then the entity should be dynamic.
 * 
 *  Generally, it is preferable to use static entities (i.e. Soplet Lists), as they are far 
 *  more 'semantically coherent' (strong typed!) and easier to maintain than database
 *  based entities.
 *  
 *  In this specific case of the articles of a Pet Store, it has been
 *  proved that the items do not change frequently, maybe once in a year, 
 *  as also the marketing materials have to be adapted accordingly etc.
 *  
 *  Furthermore, it has been determined that (due to an agile development process
 *  in place) releasing the software in sync with a article/price update is not a big issue.
 *  For that reason it has been deemed justifiable to chose the static alternative 
 *  in this case.
 * 
 * @author chrismay
 */
@Sop(aspects = {Artifact.class, Translatable.class, Priceable.class}) 
public enum SopArticle {

	@Soplet(
		textEN = "Puppy Chow",
		textDE = "Puppy Chow",
		description = "Healthy Morsels Soft & Crunchy Bites Puppy Food, 4.40 lb", 
		price = 6.5f)
	puppyChow,

	@Soplet(
		textEN = "Hartz Powdered Milk",
		textDE = "Hartz Trockenmilch",
		description = "The new Hartz Precision Nutrition Powdered Milk Replacement " +
				"for Puppies is formulated to be nutritionally similar to mother's milk",
		price = 10.00f)
	hartzPowderedMilk,

	@Soplet(
		textEN = "6-Day Automatic Pet Feeder",
		textDE = "6-Tage automtische Tränke",
		description = "Make sure your pet is well fed your when you're away. The Lentek Automatic Six Days Pet Dish ensures that pets never miss a meal and maintain their normal eating schedule",
		price = 39.00f)
	automaticPetFeeder,
	
	@Soplet(
		textEN = "Kookamunga Catnip Bubbles, 4 oz. Bottle",
		textDE = "Kookamunga Katzenflocken, 4 oz. Flasche",
		description = "Watch your kitty go crazy for Kookamunga Catnip bubbles!",
		price = 4.94f)
	kookamungaBubbles,
	
	@Soplet(
		textEN = "Friday Special Combo",
		textDE = "Freitag's Spzeialangebot",
		description = "Special offer for 3 Kookamunga Bubbles + 6-Day Automatic Feeder, instead of 53.82$' just 44.44$ - valid only Fridays!")
	fridayCombo {
		@Override
		public double price() {
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				return 44.44d;
			} else {
				return 53.82d;
			}
		}
	};
	
	@Override
	public String toString() {
		return textEN();
	}
}
