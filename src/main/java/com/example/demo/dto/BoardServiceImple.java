package com.example.demo.dto;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardService;
import com.example.demo.dao.IBoardDao;

import lombok.AllArgsConstructor;
import lombok.Setter;



@Service
@AllArgsConstructor
public class BoardServiceImple implements BoardService{
	
		@Setter(onMethod_ = {@Autowired})
		private IBoardDao mapper;
		
	
		@Override
		public List<BoardDto> getListWithPaging(Criteria cri) {
			
			return mapper.getListWithPageing(cri);
		}

		@Override
		public int gettotalcount(Criteria cri) {
			return mapper.gettotalcount(cri);
		}

		


}
