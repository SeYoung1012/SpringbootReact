package com.example.demo.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
public class BoardDto {

	private int board_idx;
	private String board_name;
	private String board_ttl;
	private String board_cn;
	private Date board_date;
	
	private Criteria cri;
}
