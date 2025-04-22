package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDAO extends DAO {

    public Subject get(String cd, School school) {
        Subject subject = null;
        String sql = "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, school.getCd());
            stmt.setString(2, cd);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                subject.setSchool(school);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public List<Subject> filter(School school) {
        List<Subject> list = new ArrayList<>();
        String sql = "SELECT CD, NAME FROM SUBJECT WHERE SCHOOL_CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, school.getCd());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setCd(rs.getString("CD"));
                subject.setName(rs.getString("NAME"));
                subject.setSchool(school);
                list.add(subject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean save(Subject subject) {
        boolean exists = get(subject.getCd(), subject.getSchool()) != null;
        String sql = exists
            ? "UPDATE SUBJECT SET NAME = ? WHERE SCHOOL_CD = ? AND CD = ?"
            : "INSERT INTO SUBJECT (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (exists) {
                stmt.setString(1, subject.getName());
                stmt.setString(2, subject.getSchool().getCd());
                stmt.setString(3, subject.getCd());
            } else {
                stmt.setString(1, subject.getSchool().getCd());
                stmt.setString(2, subject.getCd());
                stmt.setString(3, subject.getName());
            }

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Subject subject) {
        String sql = "DELETE FROM SUBJECT WHERE SCHOOL_CD = ? AND CD = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getSchool().getCd());
            stmt.setString(2, subject.getCd());

            int affected = stmt.executeUpdate();
            return affected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
