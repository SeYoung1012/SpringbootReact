package com.example.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyDto {
	
	private int reply_idx;
	private String reply_name;
	private String reply_cn;
	private Date reply_date;
	private int reply_board_idx;
	
		
	public ReplyDto() {
		
	}


	public ReplyDto(int reply_idx, String reply_name, String reply_cn, Date reply_date, int reply_board_idx) {
		super();
		this.reply_idx = reply_idx;
		this.reply_name = reply_name;
		this.reply_cn = reply_cn;
		this.reply_date = reply_date;
		this.reply_board_idx = reply_board_idx;
	}


	public int getReply_idx() {
		return reply_idx;
	}


	public void setReply_idx(int reply_idx) {
		this.reply_idx = reply_idx;
	}


	public String getReply_name() {
		return reply_name;
	}


	public void setReply_name(String reply_name) {
		this.reply_name = reply_name;
	}


	public String getReply_cn() {
		return reply_cn;
	}


	public void setReply_cn(String reply_cn) {
		this.reply_cn = reply_cn;
	}


	public Date getReply_date() {
		return reply_date;
	}


	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}


	public int getReply_board_idx() {
		return reply_board_idx;
	}


	public void setReply_board_idx(int reply_board_idx) {
		this.reply_board_idx = reply_board_idx;
	}


	@Override
	public String toString() {
		return "ReplyDto [reply_idx=" + reply_idx + ", reply_name=" + reply_name + ", reply_cn=" + reply_cn
				+ ", reply_date=" + reply_date + ", reply_board_idx=" + reply_board_idx + "]";
	}
	
	
	
}
