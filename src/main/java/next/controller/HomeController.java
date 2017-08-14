package next.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		if (!UserSessionUtils.isLogined(request.getSession())) {
        	return "redirect:/users/loginForm";
        }
        return "/home.jsp";
	}
}
//public class HomeController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setAttribute("users", DataBase.findAll());
//		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
//		rd.forward(req, resp);
//	}
//}
