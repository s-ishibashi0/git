package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;

@WebServlet("/scoremanager/LoginAction")
public class LoginAction extends HttpServlet {

    // POSTメソッドを処理
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // フォームから送られた値を取得
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        // DAO を使って認証
        TeacherDAO dao = new TeacherDAO();
        Teacher teacher = dao.login(login, password);

        if (teacher != null) {
            // 成功：セッションに保存してmain.jspに遷移
            session.setAttribute("teacher", teacher);
            req.getRequestDispatcher("main.jsp").forward(req, res);
        } else {
            // 失敗：エラーメッセージを設定してlogin.jspに戻す
            req.setAttribute("error", "ログインIDまたはパスワードが正しくありません。");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}
