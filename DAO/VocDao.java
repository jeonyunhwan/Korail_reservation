package Train;

import DBdriver.DB;
import DBdriver.VocDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VocDao {
    //로그인
    //고객 정보 다 받아오기 부모 테이블
    protected String name;
    protected char authority;// 권한
    protected int uno;
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public ArrayList<VocDto> getMemVoc(VocDto ins) {
        ArrayList<VocDto> list = new ArrayList<VocDto>();
        String query = "select Name,uno from users where ID = ? AND pass = ?";
        try {
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ins.getId());
            pstmt.setString(2, ins.getPass());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new VocDto(rs.getString(1),rs.getInt(2)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
        return list;
    }
    public int asUnumInsert(String id, String pass) {// 관리자 계정인 user 코드 받아오는 메서드
        int uno = 0;
        try {
            String query = "select uno from users where ucode = 'a' and ID = ? and pass = ?";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                uno = rs.getInt(1);
            } else {
                System.out.println("관리자 계정이 아닙니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
        return uno;
    }


}



