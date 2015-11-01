/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.controller.router;

import blog.system.controller.ControllerIntf;
import blog.system.annotation.Get;
import blog.system.annotation.Post;
import blog.system.exception.Exception404;
import blog.system.loader.Load;
import blog.system.tools.ParseUrl;
import blog.system.tools.ErrorPage;
import blog.system.tools.Logger;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author petroff
 */
@WebServlet(name = "Router", loadOnStartup = 1, urlPatterns = {"/"})
public class Router extends HttpServlet {

    protected ErrorPage errorPage;
    final String modelPrefix = "Controller";
    final String path = "blog.controller.";
    final String mainServlet = "Router";

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
            throws ServletException, IOException, Exception404 {
        errorPage = Load.errorPage;
        String servletPath = request.getServletPath();
        ParseUrl parseUrl = new ParseUrl(servletPath);

        String class_name = parseUrl.getClassUrl();
        String class_name_path = path + class_name + modelPrefix;
        Class c;
        try {
            c = Class.forName(class_name_path);
        } catch (ClassNotFoundException cN) {
            throw new Exception404(cN);
        }
        Object obj;
        try {
            obj = c.newInstance();
        } catch (InstantiationException | IllegalAccessException ie) {
            throw new Exception404(ie);
        }

        ControllerIntf base = (ControllerIntf) obj;
        base.init(Load.request, Load.response, errorPage);
        if (parseUrl.getMethodUrl() != null) {
            try {
                checkMethod(c, parseUrl, request, obj);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                throw new Exception404(e);
            }

        } else {
            try {
                base.index();
            } catch (ServletException e) {
                throw new Exception404(e);
            }
        }

    }

    protected void checkMethod(Class c, ParseUrl parseUrl, HttpServletRequest request, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method methodContr = null;
        Object[] args = null;
        for (Method method : c.getMethods()) {
            if (parseUrl.getMethodUrl().equals(method.getName()) && method.isAnnotationPresent(Get.class) && request.getMethod().equals("GET")) {
                blog.system.environment.Get get = new blog.system.environment.Get(request);
                Object[] arg1 = new Object[1];
                arg1[0] = get;
                args = ArrayUtils.addAll(arg1, parseUrl.getParamsUrl());
                methodContr = method;
                break;
            } else if (parseUrl.getMethodUrl().equals(method.getName()) && method.isAnnotationPresent(Post.class) && request.getMethod().equals("POST")) {
                blog.system.environment.Post post = new blog.system.environment.Post(request);
                Object[] arg1 = new Object[1];
                arg1[0] = post;
                args = ArrayUtils.addAll(arg1, parseUrl.getParamsUrl());
                methodContr = method;
                break;
            } else if (parseUrl.getMethodUrl().equals(method.getName())) {
                args = parseUrl.getParamsUrl();
                methodContr = method;
            }
        }
        if (methodContr != null) {
            methodContr.invoke(obj, args);
        } else {
            throw new NoSuchMethodException();
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
        try {
            processRequest(request, response);
        } catch (ServletException ie) {
            throw new ServletException();
        } catch (IOException il) {
            throw new IOException();
        } catch (Exception404 e) {
            errorPage.show404(e);
        }

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
        try {
            processRequest(request, response);
        } catch (ServletException ie) {
            throw new ServletException();
        } catch (IOException il) {
            throw new IOException();
        } catch (Exception404 e) {
            errorPage.show404(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Main controller router";
    }// </editor-fold>

}
