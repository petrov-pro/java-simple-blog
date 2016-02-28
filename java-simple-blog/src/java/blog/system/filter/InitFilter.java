/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blog.system.filter;

import blog.model.AuthModel;
import blog.system.dao.DaoFactory;
import blog.system.loader.Load;
import blog.system.tools.DbManager;
import blog.system.tools.ErrorPage;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author petroff
 */
@WebFilter("/*")
public class InitFilter implements Filter {

    private ServletContext context;
    private String encoding;

    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        encoding = fConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    public void doFilter(ServletRequest requestS, ServletResponse responseS, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) requestS;
        HttpServletResponse response = (HttpServletResponse) responseS;
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Connection dbConnection = DbManager.getConnection(request.getServletContext().getInitParameter("DbConnectorName"));
        DaoFactory.setConnection(dbConnection);
        ErrorPage errorPage = new ErrorPage(request, response);
        Load load = new Load(request, response, errorPage);
        context.setAttribute("Load", load);
        setSession();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        //we can close resources her
    }

    private void setSession() {
        AuthModel authModel = new AuthModel();
        authModel.setSession();
    }

}
