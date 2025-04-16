package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.javafx.collections.MappingChange.Map;

import bean.Teacher;
import tool.Action;

public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();// セッション
		Teacher teacher = (Teacher) session.getAttribute("user");

		String endYearStr = "";
		String classNum = "";
		String isAttendStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todayDate = LocalDate.now();
		int year = todayDate.getYear();
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap();
	}

}
