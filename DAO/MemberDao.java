package Train;

import DBdriver.DB;
import DBdriver.MemberDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDao {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    // 회원가입 정보 입력
    public void signUp(MemberDto inp) {
        String query = "insert into users values(ID_seq.nextval,'y',?,?,?,?,?)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, inp.getId());
            pstmt.setString(2, inp.getPass());
            pstmt.setString(3, inp.getUserName());
            pstmt.setString(4, inp.getCall());
            pstmt.setString(5, inp.getAdress());
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("회원가입에 성공하셨습니다.");
            } else {
                System.out.println("회원가입에 실패하셨습니다.");
            }
            con.commit();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("아이디 중복입니다. 다시 회원가입을 진행해주세요.");
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("rollback 에러:" + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //비회원 등록
    public void unsignUp(MemberDto inp) {
        String query = "insert into users values(ID_seq.nextval,'n',?,?,?,?,null)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, inp.getId());
            pstmt.setString(2, inp.getPass());
            pstmt.setString(3, inp.getUserName());
            pstmt.setString(4, inp.getCall());
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("비회원등록에 성공하셨습니다.");
            } else {
                System.out.println("비회원등록에 실패하셨습니다.");
            }
            con.commit();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("다시 시도해주세요.");
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("rollback 에러:" + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int loginCheck(String userId, String userPass) {
        try {

            String query = "select pass from users where id = ?";
            con = DB.con();

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(userPass)) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
        return -2;
    }




    //회원가입 수정 DAO 메서드
    public void updateID(String pass,String adress,MemberDto upd){
        try {
            String query = "update users set pass = ?,adress = ?\n" +
                    "             where Id = ? and pass = ? ";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, pass);
            pstmt.setString(2, adress);
            pstmt.setString(3, upd.getId());
            pstmt.setString(4, upd.getPass());

            int value = pstmt.executeUpdate();
            if(value!=0){
                System.out.println("사용자의 정보를 업데이트하였습니다. 다시 로그인해주세요.");
            }else{
                System.out.println("아이디와 비밀번호를 다시 확인해주세요!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DB.close(rs,pstmt,con);
        }
    }
    // 비밀 번호 수정 메서드
    public void updatePass(MemberDto upd){
        try {
            String query = "update users set pass = ? where ID= ? and pass = ?";
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, upd.getSpass());
            pstmt.setString(2, upd.getId());
            pstmt.setString(3, upd.getPass());
            int value = pstmt.executeUpdate();
            con.commit();
            if(value!=0){
                System.out.println("사용자의 비밀번호를 업데이트하였습니다. 다시 로그인해주세요.");
            }else{
                System.out.println("아이디와 비밀번호를 다시 확인해주세요!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DB.close(rs,pstmt,con);
        }
    }
    // 아이디 삭제 메서드
    public void deleteID(String id,String pass){
        try {
            String query = "delete from users where id = ? and pass = ?";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pass);

            int value = pstmt.executeUpdate();

            if(value>0){
                System.out.println("아이디가 삭제되었습니다.");
            }else{
                System.out.println("아이디와 비밀번호를 다시 확인해주세요!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DB.close(rs,pstmt,con);
        }
    }

}
