
package evoting.controller;

import evoting.dao.RegistrationDao;
import evoting.dto.UserDetails;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;


public class DeleteUserServlet extends HttpServlet {

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try (PrintWriter out = response.getWriter()) {
              HttpSession sess=request.getSession();
            String uid=(String)sess.getAttribute("userid");
            if(uid==null){
                sess.invalidate();
               // response.sendRedirect("Accessdenied.html");
              }
            
           String cid=request.getParameter("id");
           RequestDispatcher rd=null;
        if(cid.equalsIgnoreCase("cid")&&cid!=null){
               try{
               ArrayList<String>list=RegistrationDao.getAllUserId();
               JSONObject json=new JSONObject();
               StringBuffer sb=new StringBuffer();
                sb.append("<option values='candidate'>select value</option>");
               for(String c:list){
                   sb.append("<option values='"+c+"'>"+c+"</option>");
               }
               json.put("cid",sb.toString());
               out.println(json);
               }
               catch(SQLException ex){
                   out.println(ex);
               }
           }
        else if(cid!=null ){
            try{
              out.println("candidate id is "+cid);
             UserDetails user=RegistrationDao.showUserDetails(cid);
              rd=request.getRequestDispatcher("ShowUserById.jsp");
              request.setAttribute("user",user);
              rd.forward(request, response);
               }
            catch(NullPointerException ex){
                   out.println(ex);
                   ex.printStackTrace();
                   
               }
            catch(Exception ex){
               rd=request.getRequestDispatcher("Error.jsp");
               request.setAttribute("exception", ex);
               rd.forward(request, response);
               }
        }
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
