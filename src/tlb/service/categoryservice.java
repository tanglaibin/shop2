package tlb.service;

import java.util.List;

import tlb.domain.Category;

public interface categoryservice {

	List<Category> findlanmu()throws Exception;

	void update(Category cat)throws Exception;

	Category findbycid(String cid)throws Exception;

	void update2(Category cat2)throws Exception;

	void deletec(String cid)throws Exception;

}
