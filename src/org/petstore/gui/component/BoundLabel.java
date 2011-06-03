package org.petstore.gui.component;

import javax.swing.JLabel;

import org.petstore.Feature;
import org.petstore.Feature.DemoFeature;
import org.petstore.aspects.Translatable;

@DemoFeature(Feature.binding)
public class BoundLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public BoundLabel(Translatable field) {
		setText(field.textEN());
	}
}
