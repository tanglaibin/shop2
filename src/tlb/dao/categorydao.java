package tlb.dao;

import java.util.List;

import tlb.domain.Category;

public interface categorydao {

	List<Category> findlanmu()throws Exception;

	void updatec(Category cat)throws Exception;

	Category findbycid(String cid)throws Exception;

	void update2(Category cat2)throws Exception;

	void deletec(String cid)throws Exception;

}
