package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public abstract class SubjectListAction extends Action{
	public abstract void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
