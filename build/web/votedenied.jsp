

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='java.util.ArrayList' %>
<%@page import='evoting.dto.CandidateInfo' %>
<html>
    <head>
        <title>Show voting Information</title>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">   
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <script src="jsscript/jquery.js"></script>    
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
       
    </head>
    <body>
        <%
            
        CandidateInfo candidate=(CandidateInfo)request.getAttribute("candidate");
          out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"+
            "<div class='subcandidate'>vote already done</div><br><br>");
           StringBuffer sb=new StringBuffer();
       if(candidate!=null){
                     String id=candidate.getCandidateId();
                     String party=candidate.getParty();
                     String username=candidate.getCandidateName();
                     String image="<img src='data:image/jpg;base64,"+candidate.getSymbol()+"'style='width:300px;height:200px;'/>";                 
                     sb.append(image);
                     sb.append("<br>candidate id :"+id);
                     sb.append("<br>candidate name :"+username);
                     sb.append("<br>party :"+party);         
           out.println(sb); 
       }
    %>
    </body>
</html>
