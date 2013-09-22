package com.bknc.sgolf.common;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

public class PageNavigation {
    private int firstPageNo;
    private int lastPageNo;
    private int prevPageNo;
    private int nextPageNo;
    private int beginPageNo;
    private int endPageNo;
    private int beginRowNo;
    private int endRowNo;
    private int totalCnt;
    private int currentPageNo;
    private int pageRowCnt;
    private HttpServletRequest req;

   public int getBeginRowNo() {
        return beginRowNo;
    }
    public int getEndRowNo() {
        return endRowNo;
    }
    public int getCurrentPageNo() {
        return currentPageNo;
    }
    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }    

   public void setNavigationInfo(int totalCnt, int pageRowCnt, int currentPageNo, HttpServletRequest request){
        this.totalCnt = totalCnt;
        this.pageRowCnt = pageRowCnt;
        this.currentPageNo = currentPageNo;
        this.req = request;
        
       
       this.firstPageNo = 1;
        this.lastPageNo = (totalCnt / pageRowCnt);
        if((totalCnt % pageRowCnt) > 0){
            lastPageNo++;
        }
        
       this.beginPageNo = ((currentPageNo - 1) / 10) * 10 + 1;
        this.endPageNo = beginPageNo + 9;
        if(endPageNo > lastPageNo){
            endPageNo = lastPageNo;
        }
        
       this.prevPageNo = beginPageNo - 1;
        if(prevPageNo < firstPageNo){
            prevPageNo = 1;
        }
        this.nextPageNo = endPageNo + 1;
        if(nextPageNo > lastPageNo){
            nextPageNo = lastPageNo;
        }
        
       this.beginRowNo = (currentPageNo - 1) * pageRowCnt;
        this.endRowNo = (currentPageNo * pageRowCnt) + 1;
    }
    
   public String getNavigationStr(){
        StringBuffer sb = new StringBuffer();
        sb.append("");
        sb.append("<a href='javascript:goPage("+firstPageNo+")'>" + firstPageNo + "</a> << ");
        sb.append("<a href='javascript:goPage("+prevPageNo+")'>" + prevPageNo + "</a> < ");
        
       for(int i = beginPageNo; i <= endPageNo; i++){
            if(i == currentPageNo){
                sb.append(" " + i + " ");                
           }else{
                sb.append(" <a href='javascript:goPage("+i+")'>[" + i + "]</a> ");
            }            
       }

       sb.append(" > <a href='javascript:goPage("+nextPageNo+")'>" + nextPageNo + "</a>");
        sb.append(" >> <a href='javascript:goPage("+lastPageNo+")'>" + lastPageNo + "</a>");
        sb.append("");
        
       return sb.toString();
    }
    
   public String getMakeParamForm(){
        Enumeration enu = req.getParameterNames();
        StringBuffer sb = new StringBuffer();
        
       sb.append("<form name='pageForm' method='post'>");
        sb.append("        <input type='hidden' name='pageNo' value='"+currentPageNo+"'/>");
        
       while(enu.hasMoreElements()){
            String pName = (String)enu.nextElement();
            String pValue = req.getParameter(pName);
            if(!pName.equals("pageNo")){
                sb.append("        <input type='hidden' name='"+pName+"' value='"+pValue+"'/>");
            }
        }
        
       sb.append("</form>");
        sb.append("");
        sb.append("<script type='text/javascript'>");
        sb.append("function goPage(pageNo){");
        sb.append(" var doc = document.pageForm;");
        sb.append(" doc.pageNo.value = pageNo;");
        sb.append("doc.action='boardList.sn';");
        sb.append("doc.submit();");
        sb.append("");
        sb.append("}");
        sb.append("</script>");
        
       return sb.toString();
    }
        
}
