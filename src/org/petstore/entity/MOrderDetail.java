package org.petstore.entity;

import javax.persistence.Entity;

import lombok.soplets.SopBean;

import org.petstore.soplets.SopArticle;
import org.petstore.soplets.SopOrderDetail;

@Entity
@SopBean(sopRef=SopOrderDetail.class)
public class MOrderDetail extends MBase {
}
