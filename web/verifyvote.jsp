

<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        session.invalidate();
        response.sendRedirect("accessdenied.html");
        return;
    }
     
      boolean result=(boolean)request.getAttribute("result");
      if(result)
         out.println("success");
      else
         out.println("failed");

      
 %>