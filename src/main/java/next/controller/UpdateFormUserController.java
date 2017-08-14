package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

public class UpdateFormUserController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(UpdateFormUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		String userId = request.getParameter("userId");
		User user = DataBase.findUserById(userId);
		if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
		}
		request.setAttribute("user", user);
		return "/user/updateForm.jsp";
	}
}
//public class UpdateUserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String userId = req.getParameter("userId");
//		User user = DataBase.findUserById(userId);
//		if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
//			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
//		}
//		req.setAttribute("user", user);
//		RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
//		rd.forward(req, resp);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		User user = DataBase.findUserById(req.getParameter("userId"));
//		if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
//			throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
//		}
//		
//		User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
//				req.getParameter("email"));
//		log.debug("Update User : {}", updateUser);
//		user.update(updateUser);
//		resp.sendRedirect("/");
//	}
//}
