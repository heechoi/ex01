package com.dgit.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private int totalCount;//게시물 전체갯수
	private int startPage;//현재 보이는 페이지 시작 번호
	private int endPage;//현재 보이는 페이지 끝 번호
	private boolean prev;//이전 10개 존재 여부
	private boolean next;//이후 10개 존재 여부
	private int displayPageNum=10;
	private Criteria cri;//현재 페이지 번호를 알아야 시작과 끝을 구할 수 있음
	
	private void calcData(){
		//현재 페이지의 끝 번호를 구한다 ex)15/10 => math.ceil(1.5) -> 2*10 ->20
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
		//현재 페이지의 시작 번호를 구한다 ex)20-10+1=11
		startPage = (endPage-displayPageNum)+1;
		
		//게시물의 갯수가 151이라고 가정
		//endPage는 20이 아니라 16이 되어야함
		int tempEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		
		//만약, 끝 페이지 번호가 실제 끝 번호보다 크다면 변경해 준다
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		
		prev = true;
		if(startPage == 1){
			prev = false;
		}
		
		next = true;
		if(endPage * cri.getPerPageNum()>=totalCount){
			next=false;
		}
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return String.format(
				"PageMaker [totalCount=%s, startPage=%s, endPage=%s, prev=%s, next=%s, displayPageNum=%s, cri=%s]",
				totalCount, startPage, endPage, prev, next, displayPageNum, cri);
	}

	public String makeQuery(int page){
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).queryParam("perPageNum", cri.getPerPageNum()).build();
		
		//?page=4&perPageNum=10
		return uriComponents.toString();
	}
	
	public String makeSearch(int page){
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
											.queryParam("page", page)
											.queryParam("perPageNum", cri.getPerPageNum())
											.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
											.queryParam("keyword", ((SearchCriteria)cri).getKeyword())
											.build();
		
		//?page=4&perPageNum=10&Searchtype=t&keyword=제목
		return uriComponents.toString();
	}
}
