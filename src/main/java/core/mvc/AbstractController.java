package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.view.JsonView;
import next.view.JspView;

public abstract class AbstractController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	protected ModelAndView jspView(String forwardUrl) {
		return new ModelAndView(new JspView(forwardUrl));
	}
	
	protected ModelAndView jsonView() {
		return new ModelAndView(new JsonView());
	}
}
