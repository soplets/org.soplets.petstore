package org.petstore.entity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.soplets.SopBean;

import org.petstore.soplets.SopOrder;

@Entity
@SopBean(sopRef=SopOrder.class) 
public class MOrder extends MBase {
	
	@Override 
	public String toString() { 
		if (orderTime == null) return customer + "";
		String date = DateFormat.getDateTimeInstance().format(orderTime);
		return customer + " (" + date + ")"; 
	} 
}