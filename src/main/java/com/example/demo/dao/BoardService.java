package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.Criteria;

@Service
public interface BoardService {
	
	
	
	public List<BoardDto> getListWithPaging(Criteria cri);
	
	public int gettotalcount(Criteria cri);


}
