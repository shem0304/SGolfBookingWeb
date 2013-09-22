package com.bknc.sgolf.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bknc.sgolf.common.PageNavigation;
import com.bknc.sgolf.dao.BookingDaoImpl;
import com.bknc.sgolf.service.BookingService;
import com.bknc.sgolf.service.BookingServiceImpl;
import com.bknc.sgolf.vo.BookingVO;
import com.bknc.sgolf.vo.Shop;
import com.google.gson.Gson;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SGolfBookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(SGolfBookingController.class);
	
	@Autowired
	private BookingService bookingService;
	
	
	public void setBookingService(BookingService bookingService){
		this.bookingService = bookingService;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/a", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/jsontest.html", method = RequestMethod.GET)
	public @ResponseBody String getBookingVOInJSON() {
		
		List<BookingVO> objList = new ArrayList<BookingVO>();
		
		BookingVO bookingvo = new BookingVO();
		bookingvo.setHand_phone_no("010-1234-2340");
		bookingvo.setBooking_status("F");
		bookingvo.setPlayer_count("4");
		bookingvo.setLeftright_gubn("R");
		bookingvo.setBooking_date_time("20130918140000");
		bookingvo.setGame_kind("V");
		bookingvo.setRemark("±»¼¦ÇÏ¼¼¿ä");		
		
		objList.add(bookingvo);
		
		String json = new Gson().toJson(objList);
		
		return json;
	}
	
	@RequestMapping(value = "/getBookingList", method = RequestMethod.GET)
    public @ResponseBody String getList(HttpServletRequest request, HttpServletResponse response)
    {
		
//		PageNavigation pageNavigation = new PageNavigation();
//        String currPageNo = request.getParameter("pageNo");
//        if(currPageNo == null || currPageNo.equals("")){
//            currPageNo = "1";
//        }
//        
//       int currentPageNo = Integer.parseInt(currPageNo);
//        int pageRowCnt = 10;
//        pageNavigation.setCurrentPageNo(currentPageNo);
//        int totalCnt = boardLogic.boardListCnt();
//        
//       pageNavigation.setNavigationInfo(totalCnt, pageRowCnt, currentPageNo, request);
//        int beginRowNo = pageNavigation.getBeginRowNo();
//        int endRowNo = pageNavigation.getEndRowNo();
		
        List<BookingVO> list = bookingService.getList();
        
        String json = new Gson().toJson(list);
        return json;
    }
    
    @RequestMapping(value = "/board/add", method = RequestMethod.GET)
    public String addItemGet()
    {
        return "board/add";
    }
    
//    @RequestMapping(value = "/board/add", method = RequestMethod.POST)
//    public String addItemPost(Board board)
//    {
//        boardDao.addItem(board);            
//        
//        return "redirect:list";
//    }
//    
//    @RequestMapping(value = "/board/detail/{id}", method = RequestMethod.GET)
//    public ModelAndView getDetail(@PathVariable("id") int id)
//    {   
//        boardDao.increaseCount(id);
//       
//        ModelAndView view = new ModelAndView();
//        view.setViewName("board/detail");
//        view.addObject("board", boardDao.getItem(id));
//        
//        return view;
//    }
//    
//    @RequestMapping(value = "/board/detailajax/{id}", method = RequestMethod.GET)
//    public String getDetailAjax(@PathVariable("id") int id)
//    {
//        return "Board/detailajax";
//    }
//    
//    @RequestMapping(value = "/board/update/{id}", method = RequestMethod.GET)
//    public ModelAndView updateItemGet(@PathVariable("id") int id)
//    {
//        ModelAndView view = new ModelAndView();
//        view.setViewName("board/update");
//        view.addObject("board", boardDao.getItem(id));
//        
//        return view;
//    }
//    
//    @RequestMapping(value = "/board/update/{id}", method = RequestMethod.POST)
//    public String updateItemPost(@PathVariable("id") int id, Board board)
//    {
//        boardDao.updateItem(id, board.getSubject(), board.getDetail());
//        
//        return "redirect:../list";
//    }
//    
//    @RequestMapping(value = "/board/delete/{id}", method = RequestMethod.GET)
//    public String deleteItem(@PathVariable("id") int id)    
//    {
//        boardDao.deleteItem(id);
//        
//        return "redirect:../list";
//    }    
    	
}
