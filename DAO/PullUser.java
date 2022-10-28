package Train;

import DBdriver.DB;
import DBdriver.VocDto;
import TrainSystem.VocDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PullUser extends VocDao {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    //user Insert
    public void InsertPullBoard(VocDto ins) {
        String query = "insert Into VOC values(seq_Voc.nextval,?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),0,?)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ins.getSub());
            pstmt.setString(2, ins.getCont());
            pstmt.setInt(3, ins.getUno());
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("게시물 등록이 완료되었습니다.");
            } else {
                System.out.println("게시물 등록이 실패하였습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }


    }

    public void DeletePullBoard(VocDto ins) {
        String query = "Delete from VoC\n" +
                "where uno = (\n" +
                "select uno from users\n" +
                "where id = ?\n" +
                "and pass = ?)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ins.getId());
            pstmt.setString(2, ins.getPass());
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("게시물 삭제가 완료되었습니다.");
            } else {
                System.out.println("아이디와 비밀번호를 확인해주세요.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }
    public void UpdatePullBoard(String sub,String cons,VocDto ins) {
        String query = "update voc set sub = ? cont = ? where uNo = ?";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,sub);
            pstmt.setString(2,cons);
            pstmt.setInt(3, ins.getUno());
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("게시물 수정이 완료되었습니다.");
            } else {
                System.out.println("게시물 수정이 실패하였습니다. 아이디와 비밀번호를 확인해주세요.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }
    public void replyPullBoard(VocDto ins) {
        String query = "insert Into VOC values(seq_Voc.nextval,?,?,to_char(sysdate,'YYYY-MM-DD HH24:MI:SS'),?,?)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ins.getSub());
            pstmt.setString(2, ins.getCont());
            pstmt.setInt(3,ins.getReplyno());
            pstmt.setInt(4, ins.getUno());
            con.commit();
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("관리자 답변 등록이 완료되었습니다.");
            } else {
                System.out.println("관리자 계정인지 확인하세요. 등록이 실패하였습니다.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }


    }

}

