package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDAO extends DAO {

    // base.sql のSQL文
    private final String baseSql = "SELECT NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD FROM STUDENT WHERE ";

    // postFilter (学校情報を基に結果をフィルタリング)
    public List<Student> postFilter(ResultSet resultSet, School school) throws Exception {
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student();
            student.setNo(resultSet.getString("NO"));
            student.setName(resultSet.getString("NAME"));
            student.setEntYear(resultSet.getInt("ENT_YEAR"));
            student.setClassNum(resultSet.getString("CLASS_NUM"));
            student.setAttend(resultSet.getBoolean("IS_ATTEND"));
            // 学校情報をセット
            School studentSchool = new School();
            studentSchool.setCd(resultSet.getString("SCHOOL_CD"));
            student.setSchool(studentSchool);
            students.add(student);
        }
        return students;
    }

    // filter (学校、入学年、クラス番号、出席情報でフィルタリング)
    public List<Student> filter(School school, int entYear, String clasNum, boolean isAttend) throws Exception {
        String sql = baseSql + "SCHOOL_CD = ? AND ENT_YEAR = ? AND CLASS_NUM = ? AND IS_ATTEND = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setString(3, clasNum);
            ps.setBoolean(4, isAttend);
            ResultSet rs = ps.executeQuery();

            students = postFilter(rs, school);
        }

        return students;
    }

    // filter (学校、入学年、出席情報でフィルタリング) クラス番号なし
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        String sql = baseSql + "SCHOOL_CD = ? AND ENT_YEAR = ? AND IS_ATTEND = ?";
        List<Student> students = new ArrayList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ps.setInt(2, entYear);
            ps.setBoolean(3, isAttend);
            ResultSet rs = ps.executeQuery();

            students = postFilter(rs, school);
        }

        return students;
    }

    // save (学生情報を保存)
    public boolean save(Student student) throws Exception {
        String sql = "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getEntYear());
            ps.setString(4, student.getClassNum());
            ps.setBoolean(5, student.isAttend());
            ps.setString(6, student.getSchool().getCd());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 1件以上保存できた場合は true
        }
    }
}
