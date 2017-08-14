package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class ProfileController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		 String userId = request.getParameter("userId");
	     User user = DataBase.findUserById(userId);
	     if (user == null) {
	         throw new NullPointerException("사용자를 찾을 수 없습니다.");
	     }
	     request.setAttribute("user", user);
	     return "/user/profile.jsp";
	}
}
//public class ProfileController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String userId = req.getParameter("userId");
//		User user = DataBase.findUserById(userId);
//		if (user == null) {
//			throw new NullPointerException("사용자를 찾을 수 없습니다.");
//		}
//		req.setAttribute("user", user);
//		RequestDispatcher rd = req.getRequestDispatcher("/user/profile.jsp");
//		rd.forward(req, resp);
//	}
//}
