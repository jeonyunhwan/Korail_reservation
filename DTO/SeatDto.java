package Train;

public class SeatDto {
     private int stno;
     private int Scnt;
     private int edNo;
     private int ecnt;

     // 좌석 받아올 전역변수
    private String rDate;
    private int hNo;
    private String tNo;
    private String seatName;


    public SeatDto(){}

    //좌석 확인을 위한 생성자
    public SeatDto(String rDate, int hNo, String tNo) {
        this.rDate = rDate;
        this.hNo = hNo;
        this.tNo = tNo;
    }
    public SeatDto(String seatName){
        this.seatName = seatName;
    }
    //매진유무 확인

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public int getStno() {
        return stno;
    }

    public void setStno(int stno) {
        this.stno = stno;
    }

    public int getScnt() {
        return Scnt;
    }

    public void setScnt(int scnt) {
        Scnt = scnt;
    }

    public int getEdNo() {
        return edNo;
    }

    public void setEdNo(int edNo) {
        this.edNo = edNo;
    }

    public int getEcnt() {
        return ecnt;
    }

    public void setEcnt(int ecnt) {
        this.ecnt = ecnt;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public int gethNo() {
        return hNo;
    }

    public void sethNo(int hNo) {
        this.hNo = hNo;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }
}
