package core.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.Controller;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(HttpServlet.class);
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    
    private RequestMapping rm;
    
    @Override
    public void init() throws ServletException {
    	rm = new RequestMapping();
    	rm.initMapping();
    }
	
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String requestUri = req.getRequestURI();
    	logger.debug("Method : {}, Request URI : {}", req.getMethod(), requestUri);
    	
    	Controller controller = rm.findController(requestUri);
    	String viewName;
		try {
			viewName = controller.execute(req, resp);
			move(viewName, req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

	private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			resp.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewName);
		rd.forward(req, resp);
	}
}
