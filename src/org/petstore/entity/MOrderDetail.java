package org.petstore.entity;

import javax.persistence.Entity;

import lombok.SopEntity;

import org.petstore.soplets.SopArticle;
import org.petstore.soplets.SopOrderDetail;

@Entity
@SopEntity(type=SopOrderDetail.class)
public class MOrderDetail extends MBase {
}
