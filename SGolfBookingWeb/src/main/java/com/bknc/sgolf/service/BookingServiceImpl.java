package com.bknc.sgolf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bknc.sgolf.dao.BookingDao;
import com.bknc.sgolf.vo.BookingVO;

public class BookingServiceImpl implements BookingService {
	

	private BookingDao bookingDao;
	
	public void setBookingDao(BookingDao bookingDao)
	{
		this.bookingDao = bookingDao;
	}

	@Override
	public int addItem(BookingVO[] bookingvo) {
		// TODO Auto-generated method stub
		return bookingDao.addItem(bookingvo);
	}

	@Override
	public BookingVO getItem(int id) {
		// TODO Auto-generated method stub
		return bookingDao.getItem(id);
	}

	@Override
	public List<BookingVO> getList() {
		// TODO Auto-generated method stub
		return bookingDao.getList();
	}

	@Override
	public int updateItem(int id, String subject, String detail) {
		// TODO Auto-generated method stub
		return bookingDao.updateItem(id, subject, detail);
	}

	@Override
	public int deleteItem(int id) {
		// TODO Auto-generated method stub
		return bookingDao.deleteItem(id);
	}

}
