package org.petstore.soplets;

import lombok.soplets.Sop;

import org.petstore.aspects.Artifact;
import org.petstore.entity.MCustomer;
import org.petstore.util.PizzaUtil;

@Sop(aspects=Artifact.class)
public enum SopTestSetup {

	@Soplet(
		description= "")
	simpleCustomer {
		@Override
		public void doSetup() {
			MCustomer customer = new MCustomer();
			customer.setName("Prince Charles");
			customer.setAddress("Buckingham Palce, 1");
			customer.setPhone("555-3845-321");
			PizzaUtil.getSessionFactory().getCurrentSession().save(customer);
			
			customer = new MCustomer();
			customer.setName("Ray Charles");
			customer.setAddress("New Orleans, Main Road 17");
			customer.setPhone("555-4212-131");
			PizzaUtil.getSessionFactory().getCurrentSession().save(customer);
		}
	},

//	@Soplet(
//		/* requires=simpleCustomer */
//		description= "")
//	simpleOrder {
//		@Override
//		public void doSetup() {
//			MOrder order = new MOrder();
//			MCustomer customer = DaoCustomer.getInstance().findByName("Prince Charles");
//			order.setCustomer(customer);
//			order.setOrderTime(Calendar.getInstance().getTime());
//			
//			MOrderDetail d1 = new MOrderDetail();
//			//d1.setArticle(SopArticle.pizzaNapoli);
//			d1.setQuantity(3);
//			order.getDetails().add(d1);
//			
//			MOrderDetail d2 = new MOrderDetail();
//			//d2.setArticle(SopArticle.coke);
//			d2.setQuantity(5);
//			order.getDetails().add(d2);
//			DaoOrder.getInstance().saveOrder(order);
//		}
//	},
	
	NONE; 

	public void doSetup(){};
}
