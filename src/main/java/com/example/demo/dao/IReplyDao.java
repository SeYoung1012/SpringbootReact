package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.ReplyDto;

@Mapper
public interface IReplyDao {
	public List<ReplyDto> reply_list(int reply_board_idx);
	public int reply_write(String reply_name, String reply_cn, int reply_board_idx);
	public int reply_deleteDto(int reply_idx);

}
