

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.UserDetails' %>
<%@page import='java.util.ArrayList' %>
<%@page import="org.json.JSONObject"%>
<%
    UserDetails u=(UserDetails)request.getAttribute("user");
    StringBuffer sb=new StringBuffer();
  JSONObject json=new JSONObject();
  json.put("name",u.getUsername());
  json.put("city",u.getCity());
  json.put("address",u.getAddress());
  json.put("mno",u.getMobile());
  json.put("mail",u.getEmail());
  out.println(json);
  %>
