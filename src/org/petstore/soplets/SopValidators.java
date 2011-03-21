package org.petstore.soplets;

import java.util.Calendar;
import java.util.Date;

import lombok.Soplet;
import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.aspects.Translatable;

@Sop(aspects={Artifact.class, Translatable.class})
public enum SopValidators {
	
	@Soplet(
		description = "Checks if a date is current or in the past", 
		textEN = "Date must not be in the future",
		textDE = "Datum darf nicht in der Zukunft liegen")
	dateNotInTheFuture {
		@Override
		public boolean validate(Object value) {
			if (!(value instanceof Date)) {
				return false;
			}
			Calendar now = Calendar.getInstance();
			Calendar target = Calendar.getInstance();
			target.setTime((Date)value);
			return now.after(target);			
		}
	},
	
	@Soplet(
		description = "", 
		textEN = "Invalid phone number",
		textDE = "")
	phonePattern {
		@Override
		public boolean validate(Object value) {
			return (value + "").indexOf("/") > 0;			
		}
	},
	
	NULL;
	
	public boolean validate(Object value) {
		return true;
	}

}
