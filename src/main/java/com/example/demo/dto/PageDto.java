package com.example.demo.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDto {
	
	private int displayPageCnt = 10; // 화면에 보여질 페이지 번호 수
	private int total; //전체 데이터 개수
	private int startPage; //화면 페이지네이션의 맨 앞 첫 페이지
	private int endPage; //화면 페이지 네이션부분의 맨 마지막 페이지
	private boolean prev, next; //화면 페이지 네이션 부분의 이전 페이지와 다음 페이지
	
	private Criteria cri; //현재 페이지와 화면에 보여줄 데이터의 개수
	
	public PageDto(Criteria cri) {
		this.cri = cri;
	}
	
	
	public void setTotalCount(int total) {
		this.total = total;
		calcData();
	}
	
	public void calcData() {
		int page = this.cri.getPage();
		int perPageNum = this.cri.getPerPageNum();
		
		this.endPage = (int)(Math.ceil(page/(double)displayPageCnt)*displayPageCnt);
	
		this.startPage = (this.endPage-displayPageCnt) + 1;
		
		//전체 데이터의 마지막 페이지 계산 - totalPage
		int realEnd = (int)(Math.ceil(total / (double) perPageNum));
		
		//endPage는 realEnd를 초과할 수 없다. 그래서 계산 결과 초과가 되면 realEnd로 바꿔야한다. 
		if(realEnd < endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = (startPage != 1);
		this.next = (endPage * perPageNum < total);
	
	}
	
	public String makeQuery(int page) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.cri.getPerPageNum())
				.build()
				.encode();
				
		return uriComponents.toString();		
	}
		
	
}
