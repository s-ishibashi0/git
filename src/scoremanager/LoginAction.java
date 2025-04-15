package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ユーザー名とパスワードを取得
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		// 認証チェック

	}
}