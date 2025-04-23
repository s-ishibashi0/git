package scoremanager.main;

import tool.Action;

<<<<<<< HEAD
public abstract class SubjectListAction extends Action {

}
=======
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
>>>>>>> branch 'master' of https://github.com/s-ishibashi0/git.git
