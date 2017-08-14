package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;

public class ListUserController implements Controller {
    private static final Logger logger = LoggerFactory.getLogger(ListUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
        if (!UserSessionUtils.isLogined(request.getSession())) {
        	return "redirect:/users/loginForm";
        }

        request.setAttribute("users", DataBase.findAll());
        return "/user/list.jsp";
    }
}
