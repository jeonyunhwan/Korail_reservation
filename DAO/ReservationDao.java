package Train;

import DBdriver.DB;
import DBdriver.ReservationDto;
import DBdriver.ScheduleCheckDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class ReservationDao {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String rDate;

    public ReservationDao() {
    }

    public ReservationDao(String rDate) {
        this.rDate = rDate;
    }

    //일정 확인 메서드
    public void scheduleSearch(ScheduleCheckDto ins) {
        try {
            String query = "select stDate,se.tno,s.STNAME,se.STTIME,e.EDNAME,se.EDTIME,se.SCOST\n" +
                    "from section se, SSTATION s, ESTATION e,seat es, hocha h, dateCont d, reservation re\n" +
                    "where se.STNO = s.STNO and se.EDNO = e.EDNO and h.hNo = d.hNo\n" +
                    "and s.STNAME like '%'||?||'%'\n" +
                    "and e.EDNAME like '%'||?||'%'\n" +
                    "and d.stDate like '%'||?||'%'\n" +
                    "and to_date(se.STTIME,'HH24:MI') >= TO_DATE(?,'HH24:MI')\n" +
                    "group by d.stDate,se.tno,s.STNAME,se.STTIME,e.EDNAME,se.EDTIME,se.SCOST";
            con = DB.con();
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, ins.getStName());
            pstmt.setString(2, ins.getEdName());
            pstmt.setString(3, ins.getStDate());
            pstmt.setString(4, ins.getStTime());
            rs = pstmt.executeQuery();
            System.out.println("출발날짜\t기차이름\t출발역\t출발시간\t도착역\t도착시간\t가격");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\t");
                System.out.print(rs.getString(2) + "\t");
                System.out.print(rs.getString(3) + "\t");
                System.out.print(rs.getString(4) + "\t");
                System.out.print(rs.getString(5) + "\t");
                System.out.print(rs.getString(6) + "\t");
                System.out.print(rs.getInt(7) + "\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }

    //출발역과 도착역 입력하여 구간 번호 가져오는 코드
    public int ReserInfomation1(ReservationDto ins) {
        int secNo = 0;
        try {

            String query = "select secNo from SECTION s, SSTATION ss,ESTATION e\n" +
                    "where s.STNO = ss.STNO and s.EDNO = e.EDNO and\n" +
                    "ss.STNAME = ? and e.EDNAME = ?";
            con = DB.con();
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, ins.getStName());
            pstmt.setString(2, ins.getEdName());
            //defalut 처리는 Main

            rs = pstmt.executeQuery();

            if (rs.next()) {
                secNo = rs.getInt(1);
            } else {
                System.out.println("구간에 대한 정보가 없습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
        return secNo;
    }

    //매진 처리하기 위한 메서드
    public int SoldOutticket(String tNo,String rdate,int hno,int seno) { // 좌석 매진 유무 같은날 같은호차 같은 좌석
        List<ReservationDto> list = new ArrayList<ReservationDto>();
        try {
            String query = "select se.TNO,RDATE,HNO,seNo from reservation re, SECTION se\n" +
                    "where re.SECNO = se.SECNO";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new ReservationDto(rs.getString(1),rs.getString(2),
                        rs.getInt(3),rs.getInt(4)));
            }
            System.out.println(list.size());
            for(ReservationDto s : list){
                if(s.gettNo().equals(tNo) && s.getStDate().equals(rdate)){
                    if(s.gethNo() == hno && s.getSeNo() == seno){
                        System.out.println("매진입니다.");
                        return 1;
                    }else{
                        System.out.println("예약중 ");
                        return 2;
                    }

                }else{
                    System.out.println("예약중");
                    return 2;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
        return -1;

}



    //좌석 번호와 호차를 입력하여 예약 insert 정보 가져오기
    public int[] ReserInfomation2(String seno,int hno) {
           int temp[] = new int[2];
        try {
            String query = "select s.seNo,h.hNo from seat s,dateCont d, hocha h\n" +
                    "where h.hNo = s.hNo and d.hNo = d.hNo and h.hNo = d.hNo\n" +
                    "and s.seName = ? and d.hNo = ? and s.seInfo = 'F'\n" +
                    "group by s.seNo,h.hNo";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,seno);
            pstmt.setInt(2,hno);
            //defalut 처리는 Main
            rs = pstmt.executeQuery();

            while (rs.next()) {
               temp[0] = rs.getInt(1); // 좌석 번호
               temp[1] = rs.getInt(2); // 호차 번호
            }
        } catch (SQLException e) {
            System.out.println("프로그램을 끄고 다시 켜주세요!");
        } catch (InputMismatchException e) {
            System.out.println("호차가 잘못 입력되셨습니다.");
        } catch (Exception e) {
            System.out.println("잘못입력하셨습니다.");
        } finally {
            DB.close(rs, pstmt, con);
        }
        return temp;
    }

    //이용자 번호 가져오는 메서드
    public int userNumInfomation(String id, String name) {
        int uNo = 0;
        try {
            String query = "select uno from users where id = ? and Name = ?";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            //defalut 처리는 Main

            rs = pstmt.executeQuery();

            if (rs.next()) {
                uNo = rs.getInt(1);
            } else {
                System.out.println("아이디에 대한 정보가 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println("프로그램을 끄고 다시 켜주세요!");
        } catch (Exception e) {
            System.out.println("잘못입력하셨습니다.");
        } finally {
            DB.close(rs, pstmt, con);
        }
        return uNo;
    }
    // 예매 등록

    public void ReservationInsert(ReservationDto ins) {
        String query = "insert into RESERVATION values(res_seq.nextval,to_char(to_date(?,'YYYY-MM-DD'),'YYYY-MM-DD'),?,?,?,?)";
        try {
            con = DB.con();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, ins.getrDate());
            pstmt.setInt(2, ins.getSecNo());//구간 번호
            pstmt.setInt(3, ins.getSeNo());//좌석 번호
            pstmt.setInt(4, ins.gethNo()); // 호차 번호
            pstmt.setInt(5, ins.getuNo()); // 이용자 번호
            int updateCount = pstmt.executeUpdate();
            if (updateCount != 0) {
                System.out.println("예매가 완료 되었습니다. 승차권 확인창에서 확인해주세요!");
            } else {
                System.out.println("예매 실패! 정보가 맞는지 확인해주세요.");
            }

            pstmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println("rollback 에러:" + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void CheckInfo(String id, String pass) {
        try {
            String query = "select re.rDate,ss.STNAME,se.STTIME,e.EDNAME,se.EDTIME,\n" +
                    "re.hNo,s.seName,u.NAME from reservation re,SECTION se, seat s, SSTATION ss,ESTATION e, USERS u\n" +
                    "where re.uNo = u.UNO and re.seNo = s.seNo and re.secNo = se.SECNO\n" +
                    "and se.STNO = ss.STNO and se.EDNO = e.EDNO\n" +
                    "and u.ID like '%'||?||'%'\n" +
                    "and u.PASS like '%'||?||'%'";
            con = DB.con();
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, id);
            pstmt.setString(2, pass);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                do {
                    System.out.println("-------------------승차권----------------------");
                    System.out.println("출발날짜\t출발역\t출발시간\t도착역\t도착시간\t호차\t좌석\t이름");
                    System.out.print(rs.getString(1) + "\t");
                    System.out.print(rs.getString(2) + "\t");
                    System.out.print(rs.getString(3) + "\t");
                    System.out.print(rs.getString(4) + "\t");
                    System.out.print(rs.getString(5) + "\t");
                    System.out.print(rs.getInt(6) + "\t");
                    System.out.print(rs.getString(7) + "\t");
                    System.out.print(rs.getString(8) + "\n");
                } while (rs.next());
                {
                    System.out.println("-------------------승차권----------------------");
                }
            } else {
                System.out.println("승차권이 없습니다.");
            }
        } catch (SQLException e) {
            System.out.println("아이디가 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }

    public void deleteReservation(String id, String pass, String name) {
        try {
            String query =
                    "Delete from reservation\n" +
                    "where uno = (\n" +
                    "select uno from users u\n" +
                    "where u.id = ?\n" +
                    "and u.pass = ?\n" +
                    "and u.name = ?)";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            pstmt.setString(3, name);

            int value = pstmt.executeUpdate();

            if (value > 0) {
                System.out.println("승차권이 취소되었습니다. 예약내역을 확인해주세요!");
            } else {
                System.out.println("아이디와 비밀번호, 이름을 다시 확인해주세요!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }
}






    //insert into RESERVATION values(res_seq.nextval,to_date('2022-11-14','YYYY-MM-DD'),1,1,1,2);





