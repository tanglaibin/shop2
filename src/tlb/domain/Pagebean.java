package tlb.domain;

import java.util.List;
import java.util.Map;

public class Pagebean<T> {
//currentpage  pagesize  totalcout  totalpage 集合   5个参数
	
	private List<T> list;
	private int currentpage;
	private int pagesize;
	private int totalcount;
	private int totalpage;
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalpage() {
		return (int) Math.ceil(totalcount*1.0/pagesize);
	}
	public Pagebean() {
	}
	public Pagebean(List<T> list, int currentpage, int pagesize, int totalcount) {
		super();
		this.list = list;
		this.currentpage = currentpage;
		this.pagesize = pagesize;
		this.totalcount = totalcount;
	}
	
	
	
	
}
