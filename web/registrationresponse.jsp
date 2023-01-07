
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    boolean result=(boolean)request.getAttribute("result");
    boolean userfound=(boolean)request.getAttribute("userfound");
    if(userfound==true)
      out.println("uap");
    else if(result==true)
       out.println("Seccess");
    else
       out.println("Error!");
    %>