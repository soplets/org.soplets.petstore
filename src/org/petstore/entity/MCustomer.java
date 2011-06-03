package org.petstore.entity;

import javax.persistence.Entity;

import lombok.soplets.SopBean;

import org.petstore.soplets.SopCustomer;
import org.petstore.soplets.SopRegion;

@Entity
@SopBean(sopRef=SopCustomer.class) 
public class MCustomer extends MBase {
	
    public String toString() { 
    	return name; 
    }
}
