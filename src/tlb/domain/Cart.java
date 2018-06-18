package tlb.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart{
//包含map容器 定义key为pid  value为cartitem  装cartitem，还有总金额 
	
	private Map<String, Cartitem> map=new LinkedHashMap<>();
	
	private double totalm;
	

	//添加方法
	public void add2cart(Cartitem cartitem){
		String pid = cartitem.getP().getPid();
		if(map.containsKey(pid)){
			Cartitem oitem = map.get(pid);
			oitem.setCount(oitem.getCount()+cartitem.getCount());
		}else{
			map.put(pid, cartitem);
		}
		
		
		totalm+=cartitem.getCountm();
		
	}
	
	public Collection<Cartitem> getcartitems(){
		 return map.values();
	}
	
	
	//删除方法
	public void delete(String pid){
		Cartitem cartitem2 = map.remove(pid);
		totalm-=cartitem2.getCountm();
	}
	//清空方法
	public void clearcart(){
		map.clear();
		totalm=0.0;
	}

	public Map<String, Cartitem> getMap() {
		return map;
	}

	public void setMap(Map<String, Cartitem> map) {
		this.map = map;
	}

	public double getTotalm() {
		return totalm;
	}

	public void setTotalm(double totalm) {
		this.totalm = totalm;
	}
	
	
	
	
	
	
}
