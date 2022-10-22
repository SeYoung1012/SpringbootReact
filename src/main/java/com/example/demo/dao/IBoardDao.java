package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.Criteria;


@Mapper //Mybatis와 인터페이스 함수 연결
public interface IBoardDao {
	public List<BoardDto> list();
	public BoardDto viewDto(int board_idx);
	
	
	public int write(String board_name, String board_ttl, String board_cn);
	public int updateDto(int board_idx, String board_name, String board_ttl, String board_cn);
	public int deleteDto(int board_idx);
	
	public List<BoardDto> getListWithPageing(Criteria cri);
	public int gettotalcount(Criteria cri);
	
	
}
