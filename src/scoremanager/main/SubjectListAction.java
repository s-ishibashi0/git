package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public void SubjectListAction extends Action{
	public abstract void execute(HttpServletRequest req, HttpServletResponse res) throws Exception
	{try{
		SubjectDAO dao = new SubjectDAO();
		List<Subject> list = dao.getAllSubjects();
		req.setAttribute("list",list);

		req.getRequestDispatcher("Subject_list.jsp").forward(req,res);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return "Subject_list.jsp";
	}
	}