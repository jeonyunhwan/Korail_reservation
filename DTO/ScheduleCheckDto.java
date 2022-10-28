package Train;

public class ScheduleCheckDto {
    private String stName;
    private String stTime;
    private String edName;
    private String stDate;

    public ScheduleCheckDto(String stName, String edName, String stDate, String stTime) {
        this.stName = stName;
        this.edName = edName;
        this.stDate = stDate;
        this.stTime = stTime;

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



}
