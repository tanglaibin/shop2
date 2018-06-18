package tlb.service.impl;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import tlb.dao.Productdao;
import tlb.dao.categorydao;
import tlb.dao.impl.ProductDaoimpl;
import tlb.dao.impl.categorydaoimpl;
import tlb.domain.Category;
import tlb.service.categoryservice;
import tlb.utils.DataSourceUtils;

public class categoryserviceimpl implements categoryservice {

	@Override
	public List<Category> findlanmu() throws Exception{


/*//可以在此处引入缓存技术，，检查缓存是否有lanmu  如果有 直接从缓存中取出来  返回；如果没有  从数据库中查出来 放入缓存中
//1.创建缓存管理器
		CacheManager cm=CacheManager.create(categoryserviceimpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));
				
		//2.获取指定的缓存
		Cache cache = cm.getCache("categoryCache");
				
		//3.通过缓存获取数据  将cache看成一个map即可
		Element element = cache.get("clist");
				
		List<Category> list=null;
		//4.判断数据
		
				if(element==null){
					//从数据库中获取
					categorydao cdao=new categorydaoimpl();
		
					list=cdao.findlanmu();
					
					//将list放入缓存
					cache.put(new Element("clist", list));
					
					System.out.println("缓存中没有数据,已去数据库中获取");
				}else{
					//直接返回
					list=(List<Category>) element.getObjectValue();
					
					System.out.println("缓存中有数据");
				}*/
				
				
				categorydao cdao=new categorydaoimpl();
				
				return cdao.findlanmu();

	}

	@Override
	public void update(Category cat) throws Exception{
		// 更新栏目表
		categorydao cdao=new categorydaoimpl();
		cdao.updatec(cat);
	}

	@Override
	public Category findbycid(String cid) throws Exception{
		// 根据cid查找cat
		categorydao cdao=new categorydaoimpl();
		
		return cdao.findbycid(cid);
	}

	@Override
	public void update2(Category cat2) throws Exception{
		// TODO Auto-generated method stub
		categorydao cdao=new categorydaoimpl();
		cdao.update2(cat2);
	}

	@Override
	public void deletec(String cid) throws Exception{
		//要开启事务  把商品cid设置为null  与删除分类 要同步进行
		try {
			DataSourceUtils.startTransaction();
			Productdao pdao=new ProductDaoimpl();
			pdao.update(cid);
			
			categorydao cdao=new categorydaoimpl();
			cdao.deletec(cid);
			DataSourceUtils.commitAndClose();
			
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
		
	}

}
