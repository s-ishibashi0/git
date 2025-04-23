package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.ErrorState;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.ws.api.model.wsdl.editable.EditableWSDLService;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDAO;
import dao.StudentDAO;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher) session.getAttribute("user");

		String entYearStr = "";
		String classNum = "";
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear();
		StudentDAO sDao = new StudentDAO();
		ClassNumDAO cNumDao = new ClassNumDAO();
		Map<String, String> errors = new HashMap;

		// リクエストパラメータの取得2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");

		// ビジネスロジック4
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリスト化
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// DBからデータを取得3
		// ログインユーザの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYear != 0 && !classNum.equals("0"))
		// 入学年度とクラス番号を指定
		{
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

		} else if (entYear != 0 && classNum.equals("0")) {
			// 入学年度のみ指定
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);

		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0"))
			// 指定なしの場合
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}else

	{
		errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
		req.Attribute("errors", errors);
		// 全学年情報を取得
		students = sDao.filter(teacter.getSchool(), isAttend);
	}
}
