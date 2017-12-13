package top.robotman.atm.flipPages;

public class FlipPage {
	public static final int EVERY_PAGE_FLOW_NUM = 5;
	private int currentPage=1;
	private int pagesNum=1;
	private int flowsNum;
	private Object obj;
	private int startNum;
	
	public int getStartNum(int currentPage) {
		return (currentPage-1)*EVERY_PAGE_FLOW_NUM;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

	public int getPagesNum() {
		return pagesNum;
	}

	public void setPagesNumxxx(int flowsNum) {
		pagesNum = flowsNum%EVERY_PAGE_FLOW_NUM == 0 ? flowsNum/EVERY_PAGE_FLOW_NUM : flowsNum/EVERY_PAGE_FLOW_NUM+1;
	}

	public int getFlowsNum() {
		return flowsNum;
	}

	public void setFlowsNum(int flowsNum) {
		this.flowsNum = flowsNum;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return " A:"+currentPage+"--"+pagesNum+"--"+flowsNum+"--"+obj+"--"+startNum;
	}
	

}
