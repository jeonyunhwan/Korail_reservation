package Train;

public class MemberDto {
    private int seq;
    private String authority; // ucode
    private String id;
    private String pass;
    private String userName;
    private String call;
    private String adress;
    private String spass;//수정할 메서드

    public MemberDto(){}
    //로그인 생성자
    public MemberDto(String pass) {
        this.id = id;
    }
    //회원 가입 생성자
    public MemberDto(String id, String pass, String userName, String call, String adress) {
        this.id = id;
        this.pass = pass;
        this.userName = userName;
        this.call = call;
        this.adress = adress;
    }
    //회원가입 수정 생성자 // 비회원 등록 생성자
    public MemberDto(String id, String pass, String userName, String call) {
        this.id = id;
        this.pass = pass;
        this.userName = userName;
        this.call = call;
    }
    // 비밀번호 수정
    public MemberDto(String spass, String id, String pass){
        this.id = id;
        this.pass = pass;
    }

    public MemberDto(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSpass() {
        return spass;
    }

    public void setSpass(String spass) {
        this.spass = spass;
    }
}


