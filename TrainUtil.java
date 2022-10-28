package Train;

import java.security.SecureRandom;
import java.util.Date;

public class TrainUtil {
    //임시 비밀번호 생성 메서드 >> size 매개 변수에 10을 입력하면 10글자의 대소문자번호의 비밀번호가 생성된다.
    public String getRamdomPassword(int size) {
        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&'};

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime()); // random 클래스는 난수 생성 시 seed값으로 시간 이용
        // Random 클래스 사용 난수 생성하여 동일한 값 리턴됨.

        int idx = 0;
        int len = charSet.length;
        for (int i = 0; i < size; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
}
