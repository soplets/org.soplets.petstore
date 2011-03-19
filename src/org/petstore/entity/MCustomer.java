package org.petstore.entity;

import javax.persistence.Entity;

import lombok.SopEntity;

import org.petstore.soplets.SopCustomer;
import org.petstore.soplets.SopRegion;

@Entity
@SopEntity(type=SopCustomer.class) 
public class MCustomer extends MBase {
	
    public String toString() {
    	return name; 
    }
}
