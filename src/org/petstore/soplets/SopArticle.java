package org.petstore.soplets;

import java.util.Calendar;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Translatable;
import org.petstore.soplets.SopArticle.Priceable;


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
 *  In this specific case of the articles of a pizza delivery service, it has been
 *  proved that the menu items do not change frequently, maybe once in a year, 
 *  as also the marketing materials have to be adapted accordingly etc.
 *  
 *  Furthermore, it has been determined that (due to an agile development process
 *  in place) releasing the software in sync with a menu update is not a big issue.
 *  For that reason it has been deemed justifiable to chose the static alternative 
 *  in this case.
 * 
 * @author chrismay
 */
@Sop(aspects = {Artifact.class, Translatable.class, Priceable.class})
public enum SopArticle {

	@Soplet(
		textEN = "",
		textDE = "",
		description = "",
		price = 6.5f)
	pizzaMargarita,

	@Soplet(
		textEN = "",
		textDE = "",
		description = "",
		price = 8.25f)
	pizzaNapoli,

	@Soplet(
		textEN = "",
		textDE = "",
		description = "",
		price = 1.50f)
	coke,
	
	@Soplet(
		textEN = "",
		textDE = "",
		description = "",
		price = 1.75f)
	beerHeineken,
	
	@Soplet(
		textEN = "Friday Combo",
		textDE = "Freitag's Combo",
		description = "Special offer for 1 pizza + 1 beverage, only 7.99€ Fridays, 9.99€ the other days")
	fridayCombo {
		//@Override
		public float getPrice() {
			Calendar cal = Calendar.getInstance();
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				return 6.99f;
			} else {
				return 7.99f;
			}
		}
	};
	
	public @interface Priceable {
		public double price() default 0.0;
	}
}
