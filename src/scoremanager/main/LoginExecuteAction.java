package scoremanager.main;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.login(login, password);

        String page;

        if (teacher != null) {
            session.setAttribute("teacher", teacher);
            page = "login-out.jsp";
        } else {
            request.setAttribute("error", "ログインIDまたはパスワードが正しくありません。");
            page = "login-error.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}