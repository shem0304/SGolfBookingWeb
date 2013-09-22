package com.bknc.sgolf.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.bknc.sgolf.vo.BookingVO;

public class BookingDaoImpl implements BookingDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private PlatformTransactionManager transactionManager;
	private BookingDaoImpl bookingDao;
	
	public void setSqlSession(SqlSession sqlSession)
	{
		this.sqlSession = sqlSession;
	}
	
	public int addItem(BookingVO[] bookingvo)
	{
		return sqlSession.insert("Booking.AddItem", bookingvo);
	}
	
	public BookingVO getItem(int id)
	{
		return (BookingVO)sqlSession.selectOne("Booking.GetItem", id);
	}
	
	public List<BookingVO> getList()
	{
		return (List<BookingVO>)sqlSession.selectList("Booking.GetList");
	}
	
	public int updateItem(int id, String subject, String detail)
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		parameters.put("id", id);
		parameters.put("subject", subject);
		parameters.put("detail", detail);
		
		return sqlSession.update("BookingVO.updateItem", parameters);
	}
	
	public int deleteItem(int id)
	{
		return sqlSession.delete("BookingVO.deleteItem");
	}
	
	@Transactional
	public BookingVO transactionAnnotation(int id)
	{
		sqlSession.update("BookingVO.addItem", id);
		BookingVO bookingvo = (BookingVO)sqlSession.selectOne("BookingVO.GetItem", id);
		
		return bookingvo;
		
	}
	
	// TransactionManager를 통해서 일반적인 형식의 트랜잭션 처리를 수행한다.
    public void transcationProgrammatic(int id) throws Exception
    {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
     
        try 
        {            
           // sqlSession.update("Board.IncreaseCount", id);
           // sqlSession.update("Board.fault", id);
           // sqlSession.update("Board.IncreaseCount", id);
        }
        catch (Exception ex) 
        {
           // transactionManager.rollback(status);
            throw ex;
        }        
        
        //transactionManager.commit(status);
    }	
    
    @Autowired
    public void setBookingDao(BookingDaoImpl bookingDao) 
    {
    	this.bookingDao = bookingDao;
    }

}
