

<%@page import="org.json.JSONObject"%>
<%@page import="evoting.dto.CandidateDetails"%>
<%@page import="java.util.ArrayList"%>
<%
     String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accessdenied.html");
                return;
            }
                StringBuffer displayBlock=new StringBuffer(" ");
                String result=(String)request.getAttribute("result");
            if(result!=null && result.equalsIgnoreCase("candidateList"))
            {
                ArrayList<String> candidateId=(ArrayList<String>)request.getAttribute("candidateid");
                displayBlock.append("<option value=''>Choose Id:</option>");
                for(String c:candidateId)
                {
                 displayBlock.append("<option value='"+c+"'>"+c+"</option>");
                }
                JSONObject json=new JSONObject();
                json.put("cid",displayBlock.toString());
                out.println(json);
              
            }
            else if(result.equals("details"))
            {
                CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
                String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px;'/>";
            displayBlock.append("<table>"
              +"<tr><th>User Id:</th><td>"+cd.getUserId()+"</td></tr>"
              +"<tr><th>Candidate Name:</th><td>"+cd.getCandidateName()+"</td></tr>"
               +"<tr><th>City:</th><td>"+cd.getCity()+"</td></tr>"
             +"<tr><th>Party:</th><td>"+cd.getParty()+"</td></tr>"+
                     "<br><br><tr><th>Symbol:</th><td id='image'>"+str+"</td></tr>"
                  +"</table>");
            
                 System.out.println(displayBlock);
                JSONObject json=new JSONObject();
                json.put("subdetails",displayBlock.toString());
                out.println(json);
                 System.out.println("In AdminShowCand");
                System.out.println(displayBlock); 
                System.out.println("Hello Jay"+cd.getCity());
                System.out.println("Helllo Dipu"+cd.getUserId());

            } 
               
              
    %>