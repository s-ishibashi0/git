package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scoremanager/LoginExecute.action")
public class LoginExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // フォームから送られた値を取得
        String loginId = request.getParameter("login");
        String password = request.getParameter("password");

        // ログイン処理（例：固定値での認証）
        if ("admin".equals(loginId) && "pass123".equals(password)) {
                       // 失敗時の処理
            request.setAttribute("error", "ログインIDまたはパスワードが間違っています。");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
