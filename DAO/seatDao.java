package Train;

import DBdriver.DB;
import DBdriver.SeatDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class seatDao {
    private Connection con = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private int seatNum = 40;

    public void SeatInfo(SeatDto ins) {
        try {
            String query = "select distinct s.seNo,s.seName from seat s ,hocha hs\n" +
                    "where seName not in (select s.seName\n" +
                    "                 from seat s,\n" +
                    "                      reservation re,\n" +
                    "                      SECTION se\n" +
                    "                 where s.SENO = re.SENO\n" +
                    "                   and re.SECNO = se.SECNO\n" +
                    "                   and re.rDate = ?\n" +
                    "                   and re.hNo = ?\n" +
                    "                   and se.TNO = ?)" +
                    "order by s.seNo";
            con = DB.con();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ins.getrDate());
            pstmt.setInt(2, ins.gethNo());
            pstmt.setString(3, ins.gettNo());
            //defalut 처리는 Main
            rs = pstmt.executeQuery();
            ArrayList<SeatDto> list = new ArrayList<SeatDto>();


            while (rs.next()) {
                SeatDto sd = new SeatDto();
                int y = rs.getInt(1);
                sd.setSeatName(rs.getString(2));
                list.add(sd);

            }
            for(int i=0;i<list.size();i++) {
                System.out.print(list.get(i).getSeatName()+"  ");
                if (i % 10 == 0) {
                    System.out.println();
                } else {
                }
            }
            /*while(rs.next()) {
                SeatDto dto = new SeatDto();
                dto.setStno(rs.getInt(1));
                dto.setScnt(rs.getInt(2));
                dto.setEcnt(rs.getInt(3));
                dto.setEcnt(rs.getInt(4));
                list.add(dto);
            }
*/
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt, con);
        }
    }
        public void SeatMajin() {
            try {
                String query = "select re.rDate,re.hNo,s.seName,se.STNO,se.EDNO from seat s, reservation re,SECTION se\n" +
                        "where s.SENO = re.SENO and re.SECNO = se.SECNO";
                con = DB.con();
                pstmt = con.prepareStatement(query);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getInt(2));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getInt(4));
                    System.out.println(rs.getInt(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DB.close(rs, pstmt, con);
            }

        }
            public static void main (String[]args){
                //ArrayList
                seatDao a = new seatDao();
                a.SeatInfo(new SeatDto("2022-11-14", 1, "KTX001"));
                a.SeatMajin();

            }
        }

