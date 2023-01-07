
package evoting.controller;

import evoting.dao.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteCandidate extends HttpServlet {
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out=response.getWriter()){
            HttpSession sess=request.getSession();
            String uid=(String)sess.getAttribute("userid");
            if(uid==null)
                sess.invalidate();
             String cid=request.getParameter("id");
             RequestDispatcher rd=null;
             try{
                 boolean b=CandidateDAO.deleteCandidate(cid);
                 if(b)
                     rd=request.getRequestDispatcher("success.jsp");
                 else
                     rd=request.getRequestDispatcher("failure.jsp");
                 rd.forward(request, response);
             }
             catch(SQLException ex){
             rd=request.getRequestDispatcher("showexception.jsp");
             request.setAttribute("Exception",ex);
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
