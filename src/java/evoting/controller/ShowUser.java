
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


public class ShowUser extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       try (PrintWriter out = response.getWriter()) {
           HttpSession sess=request.getSession();
             RequestDispatcher rd=null;
           String uid=(String)sess.getAttribute("userid");
              //out.println("user id is"+uid);
              sess.setAttribute("userid",uid);
              if(uid==null){
               //  response.sendRedirect("Accessdenied.html");
              }
              try{
                  ArrayList<UserDetails>list=RegistrationDao.showUsers();
                  request.setAttribute("list",list);
                  rd=request.getRequestDispatcher("showUser.jsp");
                  rd.forward(request, response);
              }
              catch(SQLException ex){
               rd=request.getRequestDispatcher("failure.jsp");
               request.setAttribute("exception", ex);
               rd.forward(request, response);
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
