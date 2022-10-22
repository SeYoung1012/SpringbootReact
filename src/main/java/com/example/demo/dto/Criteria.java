package com.example.demo.dto;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	
	private int page;
	private int perPageNum;
	
	public Criteria() {
	
		this.page = 1;
		this.perPageNum = 10;
	}
	
	
	
	public String makeQuery() {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.perPageNum)
				.build().encode().toString();
		
	}
	

}
