package tlb.domain;

import java.io.Serializable;

public class Cartitem  {
//包含商品  数量  小计
	
	private Product p;
	private int count=0;
	private double countm=0.0;
	public Product getP() {
		return p;
	}
	public void setP(Product p) {
		this.p = p;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCountm() {
		return p.getShop_price()*count;
	}
	

}
