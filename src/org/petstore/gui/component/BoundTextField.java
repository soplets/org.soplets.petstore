package org.petstore.gui.component;

import java.awt.Color;
import java.lang.reflect.Method;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.soplets.SopValidators;
import org.petstore.util.BindableEntity;

@DemoFeature(Feature.binding)
public class BoundTextField<T> extends JTextField implements Bindable<T> {

	private static final long serialVersionUID = 1L;

	private BindableEntity fieldDef;

	public void bind(BindableEntity fieldDef) {
		this.fieldDef = fieldDef;
		setDocument(new TextFieldLimiter(fieldDef.length()));  //set maxLength
		setColumns(fieldDef.length());
	}

	public void load(T entity) {
		if (entity == null) {
			setText(fieldDef.defaultValue() + "");
			return;
		}
		Class<?> clazz = entity.getClass();
		String getterName = fieldDef.name();
		getterName = getterName.substring(0, 1).toUpperCase()
				+ getterName.substring(1);
		getterName = "get" + getterName;
		try {
			Method getter = clazz.getMethod(getterName);
			Object value = getter.invoke(entity);
			Class<?> dataType = fieldDef.javaType();
			String formattedText = value + ""; //TODO dataType.doFormat(value); 
			setText(formattedText);
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
	}

	public void save(T entity) throws IllegalArgumentException {
		if (entity == null) {
			return;
		}
		Class<?> clazz = entity.getClass();
		String setterName = fieldDef.name();
		setterName = setterName.substring(0, 1).toUpperCase()
				+ setterName.substring(1);
		setterName = "set" + setterName;
		SopValidators validator = null;
		try {
			try {
				validator = fieldDef.validator();
			} catch (IllegalArgumentException ee) {
			}
			
			//validate value, may throw an IllegalArgumentException
			if (validator != null) {
				if (!validator.validate(getText())) {
					throw new IllegalArgumentException(validator.textEN());
				}
			}

			setBackground(Color.white);
			setToolTipText("");

			//apply the value
			Class<?> dataType = fieldDef.javaType();
			Method setter = clazz.getMethod(setterName, dataType);			
			setter.invoke(entity, getText()); //TODO parsedValue);
		} catch (IllegalArgumentException iae) {
			java.awt.Toolkit.getDefaultToolkit().beep();
			setBackground(Color.red);
			setToolTipText(iae.getMessage());
			//throw iae;
		} catch (Exception e) {
			e.printStackTrace(); // TODO Logging
		}
	}

	class TextFieldLimiter extends PlainDocument {
		
		private static final long serialVersionUID = 1L;

		//the max length of characters
		int maxChar = -1;

		public TextFieldLimiter(int len) {
			maxChar = len;
		}

		public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
			if (str != null 
					&& maxChar > 0
					&& this.getLength() + str.length() > maxChar) {
				java.awt.Toolkit.getDefaultToolkit().beep();
				return;
			}
			super.insertString(offs, str, a);
		}
	}

	@Override
	public BindableEntity getBoundField() {
		return fieldDef;
	}
}
