package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;

public class CreateUserController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserController.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));
        logger.debug("User : {}", user);
        
        UserDao userDao = new UserDao();
        userDao.insert(user);
        DataBase.addUser(user);

        return "redirect:/";
	}
}
//public class CreateUserController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");
//		rd.forward(req, resp);
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
//				req.getParameter("email"));
//		log.debug("User : {}", user);
//		
//		DataBase.addUser(user);
//		
//		resp.sendRedirect("/");
//	}
//}
