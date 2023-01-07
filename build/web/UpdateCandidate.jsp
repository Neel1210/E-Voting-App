
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.CandidateDetails' %>
<%@page import="org.json.JSONObject"%>
<%
   CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
   if(cd!=null){
   String name=cd.getCandidateName();
   String city=cd.getCity();
   String userid=cd.getUserId();
   String party=cd.getParty();
   String cid=cd.getCandidateId();
    String image="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px;'/>";
   JSONObject json=new JSONObject();
   json.put("name",name);
   json.put("city",city);
   json.put("party",party);
   json.put("src",image);
   out.println(json);
   }
   else{
       out.println("null object");
   }
    %>
    


