
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDTO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


public class UpdateCandidateServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sess=request.getSession();
            String uid=(String)sess.getAttribute("userid");
            if(uid==null){
                sess.invalidate();
               // response.sendRedirect("Accessdenied.html");
              }
            String cid=request.getParameter("cid");
           RequestDispatcher rd=null;
           try{
                DiskFileItemFactory df=new DiskFileItemFactory();
                ServletFileUpload sfu=new ServletFileUpload(df);
                ServletRequestContext src=new ServletRequestContext(request);
                List<FileItem>multilist=sfu.parseRequest(src);
                ArrayList <String>objValues=new ArrayList<>();
                  InputStream inp=null;
                for(FileItem fit:multilist){
                if(fit.isFormField())
                {
                    String fname=fit.getFieldName();
                    String value=fit.getString();
                    System.out.println(fname+":"+value);
                    objValues.add(value);
                   
                }
                 else
                {
                    inp=fit.getInputStream();
                    String key=fit.getFieldName();
                    String fileName=fit.getName();
                    System.out.println("Inside else");
                    System.out.println(key+":"+fileName);
                }
                }
                CandidateDTO c=new CandidateDTO();
                c.setCandidateId(objValues.get(0));
                c.setCity(objValues.get(3));
                c.setParty(objValues.get(2));
                c.setSymbol(inp);
                boolean b=CandidateDAO.updateCandidate(c);
               if(b)
                    rd=request.getRequestDispatcher("success.jsp");
                else
                    rd=request.getRequestDispatcher("failure.jsp");
                 rd.forward(request, response);
           }
           catch(Exception ex){
              rd=request.getRequestDispatcher("showexception.jsp");
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
