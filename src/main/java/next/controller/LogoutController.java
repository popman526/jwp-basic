package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(LogoutController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		HttpSession session = request.getSession();
        session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
        return "redirect:/";
	}
}
//public class LogoutController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		HttpSession session = req.getSession();
//		session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
//		resp.sendRedirect("/");
//	}
//}
