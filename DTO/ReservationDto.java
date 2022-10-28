package Train;

public class ReservationDto {
    private String tNo; // 기차번호
    private String tName;
    private String stDate; // 출발 날짜
    private String stName; // 출발역
    private String stTime;
    private String edName;
    private String edTime;
    private String showSeat; // 좌석 현황
    private int seNo; // 좌석번호
    private String seName;
    private int hNo;
    private int hRank;
    private int scost;
    private String route;
    private String rDate;
    private int secNo;
    private int uNo;

    //기본 생성자
    public ReservationDto(){};
    // 구간 확인
    public ReservationDto(String stName, String edName){
        this.stName = stName;
        this.edName = edName;
    }
    //예약

    public ReservationDto(String rDate) {
        this.rDate = rDate;
    }

    public ReservationDto(String rDate, int secNo, int seno, int hNo, int uNo) {
        this.rDate = rDate;
        this.secNo = secNo;
        this.seNo = seno;
        this.hNo = hNo;
        this.uNo = uNo;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public int gethNo(int anInt) {
        return hNo;
    }

    public void sethNo(int hNo) {
        this.hNo = hNo;
    }

    // 좌석 확인
    public ReservationDto(String seName, int hNo) {
        this.seName = seName;
        this.hNo = hNo;
    }
    public ReservationDto(int seNo, int hNo){
        this.seNo = seNo;
        this.hNo = hNo;
    }
    // 매진처리 생성자
    public ReservationDto(String tno, String stDate, int hNo, int seNo){
        this.tNo = tno;
        this.stDate = stDate;
        this.hNo = hNo;
        this.seNo = seNo;
    }

    //일정 확인 생성자
    public ReservationDto(String tNo, String tName, String stDate, String stName, String stTime, String edName, String edTime, String showSeat) {
        this.tNo = tNo;
        this.tName = tName;
        this.stDate = stDate;
        this.stName = stName;
        this.stTime = stTime;
        this.edName = edName;
        this.edTime = edTime;
        this.showSeat = showSeat;
    }

    public String gettNo() {
        return tNo;
    }

    public void settNo(String tNo) {
        this.tNo = tNo;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStTime() {
        return stTime;
    }

    public void setStTime(String stTime) {
        this.stTime = stTime;
    }

    public String getEdName() {
        return edName;
    }

    public void setEdName(String edName) {
        this.edName = edName;
    }

    public String getEdTime() {
        return edTime;
    }

    public void setEdTime(String edTime) {
        this.edTime = edTime;
    }

    public String getShowSeat() {
        return showSeat;
    }

    public void setShowSeat(String showSeat) {
        this.showSeat = showSeat;
    }

    public int getSeNo(int anInt) {
        return seNo;
    }

    public void setSeNo(int seNo) {
        this.seNo = seNo;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public int getSeNo() {
        return seNo;
    }

    public int gethNo() {
        return hNo;
    }

    public int gethRank() {
        return hRank;
    }

    public void sethRank(int hRank) {
        this.hRank = hRank;
    }

    public int getScost() {
        return scost;
    }

    public void setScost(int scost) {
        this.scost = scost;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getSecNo() {
        return secNo;
    }

    public void setSecNo(int secNo) {
        this.secNo = secNo;
    }

    public int getuNo() {
        return uNo;
    }

    public void setuNo(int uNo) {
        this.uNo = uNo;
    }
}

