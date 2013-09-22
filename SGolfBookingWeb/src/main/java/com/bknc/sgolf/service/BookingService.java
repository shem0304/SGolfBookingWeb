package com.bknc.sgolf.service;

import java.util.List;

import com.bknc.sgolf.vo.BookingVO;

public interface BookingService {

	public int addItem(BookingVO[] bookingvo);
	public BookingVO getItem(int id);
	public List<BookingVO> getList();
	public int updateItem(int id, String subject, String detail);
	public int deleteItem(int id);
}
