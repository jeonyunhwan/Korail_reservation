package Train;

public class VocDto {
    private String id;
    private String pass;
    private String sub; // 제목
    private String cont; // 내용
    private int uno;
    private String name;
    private int replyno;
    // 유저 내용 올리기
    public VocDto(String sub, String cont, int uno) {
        this.sub = sub;
        this.cont = cont;
        this.uno = uno;
    }
    // 관리자 답변 생성자
    public VocDto(String sub, String cont, int replyno, int uno) {
        this.sub = sub;
        this.cont = cont;
        this.replyno = replyno;
        this.uno = uno;
    }

    public VocDto(int uno) {
        this.uno = uno;
    }

    public VocDto(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public int getReplyno() {
        return replyno;
    }

    public void setReplyno(int replyno) {
        this.replyno = replyno;
    }

    public VocDto(String name, int uno) {
        this.name = name;
        this.uno = uno;
    }


    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
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

    public int getUno() {
        return uno;
    }

    public void setUno(int uno) {
        this.uno = uno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
