<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null){
        session.invalidate();
        response.sendRedirect("acessdenied.html");
    }
    out.println("success");
    %>