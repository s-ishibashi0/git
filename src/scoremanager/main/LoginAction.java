package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LoginAction extends Action {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// ユーザー名とパスワードを取得
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		// Teacher.javaから↓
		// CustomerDAO dao=new CustomerDAO();
		// Customer customer=dao.search(login, password);
		//
		// if (customer!=null) {
		// session.setAttribute("customer", customer);
		// return "login-out.jsp";
		// }
		//
		// return "login-error.jsp";
		// 認証チェック
	}
}