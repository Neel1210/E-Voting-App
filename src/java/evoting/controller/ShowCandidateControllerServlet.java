
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowCandidateControllerServlet extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher rd=null;
            HttpSession sess=request.getSession();
            String userid=(String)sess.getAttribute("userid");
            if(userid==null)
            {
                sess.invalidate();
                response.sendRedirect("accessdenied.html");
                return;
            }
            String data=(String)request.getParameter("data");
            try{
            if(data!=null && data.equalsIgnoreCase("cid")){
               ArrayList<String> candidateList=CandidateDAO.getCandidateId();
               request.setAttribute("candidateid",candidateList);
               request.setAttribute("result","candidateList");
               
            }
            else{                
                  CandidateDetails candidate=CandidateDAO.getDetailsById(data);
                  request.setAttribute("result","details");
                  request.setAttribute("candidate",candidate);
                 // Boolean b=CandidateDAO.deleteById(data);
            }
            rd=request.getRequestDispatcher("adminshowcandidate.jsp");
            
            }
            catch(Exception ex)
            {
                 ex.printStackTrace();
                 rd=request.getRequestDispatcher("showexception.jsp");
                 request.setAttribute("Exception",ex);

          }
            finally{
                rd.forward(request, response);
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
