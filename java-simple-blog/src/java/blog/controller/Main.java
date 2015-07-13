/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.controller;

import blog.dao.DaoFactory;
import blog.tools.Url;
import blog.system.ModelIntf;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author petroff
 */
@WebServlet(name = "Main", loadOnStartup = 1, urlPatterns = {"/"})
public class Main extends HttpServlet {

    final String modelPrefix = "Model";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if (servletPath.equals("/")) {
            servletPath = "Main";
        }
        String path = "blog.model.";

        String class_name = Url.normalizeUrl(servletPath);
        String class_name_path = path + class_name + modelPrefix;
        Class c;
        try {
            c = Class.forName(class_name_path);
        } catch (ClassNotFoundException cN) {
            return;
        }
        Object obj;
        try {
            obj = c.newInstance();
        } catch (InstantiationException ie) {
            return;
        } catch (IllegalAccessException il) {
            return;
        }
        HttpSession session = request.getSession();
        DaoFactory.setConnection(getServletContext());
        ModelIntf base = (ModelIntf) obj;

        base.init(request);
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("Data", base.getData());
        String pathToView = "/WEB-INF/views" + base.getView();
        request.getRequestDispatcher(pathToView).forward(request, response);
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
