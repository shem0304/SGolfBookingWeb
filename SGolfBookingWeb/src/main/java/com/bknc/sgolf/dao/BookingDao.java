package com.bknc.sgolf.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bknc.sgolf.vo.BookingVO;

public interface BookingDao {
	public void setSqlSession(SqlSession sqlSession);
	public int addItem(BookingVO[] bookingvo);
	public BookingVO getItem(int id);
	public List<BookingVO> getList();
	public int updateItem(int id, String subject, String detail);
	public int deleteItem(int id);
	public BookingVO transactionAnnotation(int id);
    public void transcationProgrammatic(int id) throws Exception;
    public void setBookingDao(BookingDaoImpl bookingDao);    
}
