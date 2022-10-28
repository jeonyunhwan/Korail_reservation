package Train;
import DBdriver.MemberDto;
import DBdriver.ReservationDto;
import DBdriver.ScheduleCheckDto;
import DBdriver.SeatDto;
import DBdriver.VocDto;
import TrainSystem.MemberDao;
import TrainSystem.PullUser;
import TrainSystem.ReservationDao;
import TrainSystem.TrainUtil;
import TrainSystem.VocDao;
import TrainSystem.seatDao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewTrainMainApp {
    public static void menu() {
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.print("1. 로그인/회원가입/마이페이지 2. 일정/좌석조회/예약  3. 승차권확인/승차권반환  4.고객 답변  5.프로그램 종료");
    }
    public static void Mypage(){
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.println("-------          마이페이지 화면창           --------");
        System.out.print("1. 비밀번호 수정 2. 비밀번호와 주소수정  3. 아이디 삭제  4. 프로그램 종료");
    }

    public static void loginMenu() {
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.println("-------          로그인/마이페이지 화면창           --------");
        System.out.println("1. 로그인 2. 회원가입 3. 로그인창 나가기");
    }
    public static void reservaionMenu() {
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.println("-------          승차권반환/확인 화면창           --------");
        System.out.println("1.승차권 반환 2. 승차권 확인");
    }


    public static void reservaionInfoMenu() {
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.println("-------          일정조회/예약 화면창           --------");
        System.out.println("1. 일정 조회 2.좌석 조회 3.회원 예약 하기 4.비회원 등록 및 예약 5. 처음화면으로 돌아가기");
    }

    public static void VocMenu() {
        System.out.println("-------Let`s Train Reservation System--------");
        System.out.println("-------          VOC(고객의 목소리)           --------");
        System.out.println("1.게시물 작성 2.게시물 삭제 3.게시물 업데이트 4.관리자 댓글 달기 5. 처음화면으로 돌아가기");
    }

    public static void main(String[] args) throws InputMismatchException {
        //로그인 객체 생성
        TrainSystem.MemberDao member = new MemberDao();
        //예약 객체 생성
        TrainSystem.ReservationDao reservation = new TrainSystem.ReservationDao();
        //좌석 객체 생성
        TrainSystem.seatDao sd = new seatDao();
        String menuStr = "";
        while (true) {
            ViewTrainMainApp.menu(); // 메뉴 화면창
            System.out.println(); // 줄바꿈
            System.out.print("메뉴 선택> ");
            Scanner in = new Scanner(System.in);
            menuStr = in.nextLine();
            if (menuStr.equals("1")) {
                while (true) {
                    Scanner e = new Scanner(System.in);
                    ViewTrainMainApp.loginMenu();
                    System.out.println();//줄바꿈
                    System.out.print("메뉴선택 :");
                    String loginStr = e.next();
                    if (loginStr.equals("1")) {
                        System.out.println("-----------로그인-----------");
                        System.out.print("아이디를 입력하세요 : ");
                        String id = e.next();
                        System.out.print("패스워드를 입력하세요 : ");
                        String pass = e.next();
                        int m = member.loginCheck(id, pass);
                        if (m == 1) {
                            System.out.println("로그인 성공");
                            System.out.println("1. 마이페이지 2. 로그인 창에서 나가기");
                            int s = in.nextInt();
                            if (s == 1) {
                                ViewTrainMainApp.Mypage();
                                System.out.println();
                                Scanner sc = new Scanner(System.in);
                                int input = sc.nextInt();
                                if (input == 1) { // 비밀번호 수정
                                    System.out.println("----------비밀번호 수정----------");
                                    System.out.println("아이디를 입력하세요 : ");
                                    String ID = in.next();
                                    System.out.println("처음 비밀번호를 입력하세요 : ");
                                    String hpass = in.next();
                                    System.out.println("바꾸실 비밀번호를 입력하세요 : ");
                                    String spass = in.next();
                                    member.updatePass(new DBdriver.MemberDto(spass, ID, hpass));
                                } else if (input == 2) { // 주소와 비밀번호 동시에 업데이트
                                    Scanner sc1 = new Scanner(System.in);
                                    System.out.println("--------비밀번호/주소업데이트---------");
                                    System.out.print("바꾸실 비밀번호를 적으세요: ");
                                    String pass3 = sc1.nextLine();
                                    System.out.print("바꾸실 주소를 적으세요");
                                    String adress = sc1.nextLine();
                                    System.out.println();//줄바꿈
                                    System.out.println("감사합니다. 회원님의 정보 확인을 위해 입력해주세요!");
                                    System.out.print("회원님의 아이디를 입력해주세요 :");
                                    String id3 = sc1.nextLine();
                                    System.out.print("회원님의 처음 비밀번호를 입력해주세요:");
                                    String pass4 = sc1.nextLine();
                                    member.updateID(pass3, adress, new DBdriver.MemberDto(id3,pass4));
                                } else if (input == 3) {
                                    System.out.println("--------회원삭제---------");
                                    System.out.print("회원님의 아이디를 입력해주세요!");
                                    String id1 = in.next();
                                    System.out.print("회원님의 비밀번호를 입력해주세요!");
                                    String pass1 = in.next();
                                    System.out.print("회원님. 진심으로 삭제하시겠다면 아이디를 한번더 입력해주세요!");
                                    String id2 = in.next();
                                    if (id1.equals(id2)) {
                                        member.deleteID(id1, pass1);
                                    } else {
                                        System.out.println("감사합니다. 좋은 모습으로 찾아뵙겠습니다.");
                                    }
                                } else {
                                    System.out.println("마이페이지 창을 종료합니다.");
                                    break;
                                }

                            }
                            break;
                        } else if (m == 0) {
                            System.out.println("비밀번호가 불일치합니다.");
                        } else if (m == -1) {
                            System.out.println("아이디가 없습니다. 회원가입을 진행해주세요!");
                        } else {
                            System.out.println("시스템 오류: 프로그램 종료 후 다시 실행해주세요!");
                        }
                    } else if (loginStr.equals("2")) { //회원가입
                        Scanner sc = new Scanner(System.in);
                        System.out.println("----------회원가입-----------");
                        System.out.print("중복없이 아이디를 입력해주세요 : ");
                        String id = sc.nextLine();
                        System.out.print(" 비밀번호를 입력해주세요 : ");
                        String pass = sc.nextLine();
                        System.out.print("이름을 입력해주세요 :");
                        String name = sc.nextLine();
                        System.out.print("전화번호를 입력해주세요 :");
                        String call = sc.nextLine();
                        System.out.print("주소를 입력해주세요 !");
                        String adress = sc.nextLine();
                        member.signUp(new DBdriver.MemberDto(id, pass, name, call, adress));
                    }else if (loginStr.equals("3")) {
                        System.out.println("로그인 창을 나갑니다.");
                        break;
                    }else{
                        break;
                    }
                }
            } else if (menuStr.equals("2")) {
                while (true) {
                    ViewTrainMainApp.reservaionInfoMenu();
                    System.out.println();
                    System.out.println("메뉴 선택>");
                    int input = in.nextInt();
                    if (input == 1) { // 일정/조회
                        System.out.println("[승차권 간편 조회 서비스창]");
                        System.out.print("출발역 : ");
                        String Sname = in.next();
                        System.out.print("도착역: ");
                        String Ename = in.next();
                        System.out.print("출발날짜 : ");
                        String sDate = in.next();
                        System.out.print("시간: ");
                        String stTime = in.next();
                        TrainSystem.ReservationDao a = new TrainSystem.ReservationDao();
                        a.scheduleSearch(new ScheduleCheckDto(Sname, Ename, sDate, stTime));
                    } else if (input == 2) {// 좌석 현황
                        System.out.println("---------좌석 현황----------");
                        System.out.print("출발날짜:");
                        String tName = in.next();
                        System.out.print("호차번호 :");
                        int hno = in.nextInt();
                        System.out.print("좌석을 선택하실 기차이름을 적어주세요 :");
                        String tno = in.next();
                        System.out.println("-----------" + hno + " 호차 좌석 현황" + "-----------");
                        sd.SeatInfo(new SeatDto(tName, hno, tno));
                        System.out.println("\n----------------------------------");
                    } else if (input == 3) {//회원 예약 기능
                        System.out.println("-----------------예약창---------------");
                        System.out.println("예약할 기차 이름을 입력하세요(ex/ktx001) :");
                        String tNo = in.next();
                        System.out.println("출발 날짜를 입력하세요 :");
                        String goDate = in.next();
                        System.out.println("출발역을 입력하세요 : ");
                        String goSta = in.next();
                        System.out.println("도착역을 입력하세요 : ");
                        String doSta = in.next();
                        System.out.print("호차 번호를 입력하세요 !");
                        int hochaNum = in.nextInt();
                        System.out.println("좌석확인 후 좌석을 입력하세요(ex 2B) : ");
                        String seatNa = in.next();
                        System.out.println("1.회원으로 예약하기 / 2. 비회원 예약하기");
                        System.out.println("---------------로그인-------------");
                        System.out.print("id를 입력하세요 : ");
                        String id = in.next();
                        System.out.println("이름을 입력하세요 : ");
                        String name = in.next();
                        System.out.println("---------------로그인-------------");
                        System.out.println("\n 정보가 맞는지 확인중입니다...");
                        TrainSystem.ReservationDao reser = new TrainSystem.ReservationDao();
                        int secNo = reser.ReserInfomation1(new DBdriver.ReservationDto(goSta, doSta));
                        int seho[];
                        seho = reser.ReserInfomation2(seatNa, hochaNum);
                        int se = seho[0];
                        int ho = seho[1];
                        int uno = reser.userNumInfomation(id, name);
                        //매진 처리
                        int a = reser.SoldOutticket(tNo, goDate, hochaNum, se);
                        if (a == 1) {
                            System.out.println("좌석 선택을 다시 해주세요.");
                            break;
                        } else if (a == -1) {
                            System.out.println("컴퓨터를 다시 시작해주세요! ");
                            break;
                        } else {
                            reser.ReservationInsert(new DBdriver.ReservationDto(goDate, secNo, se, ho, uno));
                        }
                    } else if (input == 4) {
                        try {
                            System.out.println("-----------------예약창---------------");
                            System.out.println("예약할 기차 이름을 입력하세요(ex/ktx001) :");
                            String tNo = in.next();
                            System.out.println("출발 날짜를 입력하세요 :");
                            String goDate = in.next();
                            System.out.println("출발역을 입력하세요 : ");
                            String goSta = in.next();
                            System.out.println("도착역을 입력하세요 : ");
                            String doSta = in.next();
                            System.out.print("호차 번호를 입력하세요 !");
                            int hochaNum = in.nextInt();
                            System.out.println("좌석확인 후 좌석을 입력하세요(ex 2B) : ");
                            String seatNa = in.next();
                            System.out.println("-----------비회원 등록------------");
                            //비회원 아이디 생성:
                            String unid = "";
                            System.out.println("비회원 아이디는 자동으로 생성됩니다.");
                            TrainSystem.TrainUtil RandomStringID = new TrainUtil();
                            unid = RandomStringID.getRamdomPassword(10);//열자리 랜덤 아이디 생성
                            System.out.println("비회원 ID: " + unid);// 이건 핸드폰 문자로 생각했음
                            System.out.println("비밀번호를 입력하세요 :");
                            String pass = in.next();
                            System.out.println("이름을 입력하세요 : ");
                            String name = in.next();
                            System.out.println("전화번호를 입력하세요 :");
                            String call = in.next();
                            member.unsignUp(new MemberDto(unid, pass, name, call));
                            System.out.println("---------------비회원 예약 로그인-------------");
                            System.out.print("id를 입력하세요 : ");
                            String inputId = in.next();
                            System.out.println("비밀번호를 입력하세요 : ");
                            String inputPass = in.next();
                            System.out.println("---------------비회원 예약 로그인-------------");
                            System.out.println("\n 정보가 맞는지 확인중입니다...");
                            TrainSystem.ReservationDao reser = new TrainSystem.ReservationDao();
                            int secNo = reser.ReserInfomation1(new DBdriver.ReservationDto(goSta, doSta));
                            int seho[];
                            seho = reser.ReserInfomation2(seatNa, hochaNum);
                            int se = seho[0];
                            int ho = seho[1];
                            int uno = reser.userNumInfomation(unid, name);
                            //매진 처리
                            int a = reser.SoldOutticket(tNo, goDate, hochaNum, se);
                            if (a == 1) {
                                System.out.println("좌석 선택을 다시 해주세요.");
                                break;
                            } else if (a == -1) {
                                System.out.println("컴퓨터를 다시 시작해주세요! ");
                                break;
                            } else {
                                reser.ReservationInsert(new ReservationDto(goDate, secNo, se, ho, uno));
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("호차는 숫자로 입력하세요!");
                        }
                    }else if(input == 5){
                        System.out.println("예약창 나갑니다.");
                        break;
                    }

                }
            } else if (menuStr.equals("3")) { //승차권 반환 기능
                ViewTrainMainApp.reservaionMenu();
                System.out.println();
                String input = "";
                System.out.println("메뉴선택>");
                Scanner sc = new Scanner(System.in);

                int srh = sc.nextInt();
                while (true) {
                    if(srh==1) {
                        System.out.println("----------승차권취소--------------");
                        System.out.println("아이디를 입력하세요 : ");
                        String id = in.next();
                        System.out.println("비밀번호를 입력하세요 : ");
                        String pass = in.next();
                        System.out.println("이름을 입력하세요 : ");
                        String name = in.next();
                        System.out.println("정말 취소하시겠습니까 ? ");
                        input = in.next();
                        if (input.equals("네")) {
                            TrainSystem.ReservationDao delIn = new TrainSystem.ReservationDao();
                            delIn.deleteReservation(id, pass, name);
                            break;
                        } else {
                            System.out.println("이전창으로 돌아갑니다.");
                            break;
                        }
                    }else if(srh == 2){
                        System.out.println("---------------승차권 정보창---------------");
                        System.out.println("아이디를 입력하세요 : ");
                        String id = in.next();
                        System.out.println("비밀번호를 입력하세요 : ");
                        String pass = in.next();
                        TrainSystem.ReservationDao checkIn = new ReservationDao();
                        checkIn.CheckInfo(id, pass);
                        break;
                    } else {
                        System.out.println("처음화면으로 돌아갑니다.");
                        break;
                    }

                }
            } else if (menuStr.equals("4")) {
                ViewTrainMainApp.VocMenu();
                TrainSystem.PullUser p = new PullUser();
                TrainSystem.VocDao vo = new TrainSystem.VocDao();
                System.out.print("메뉴 선택>");
                String input = in.nextLine();
                String name = "";
                int uNo = 0;
                while (true) {
                    if (input.equals("1")) { //게시물 작성(승객)
                        Scanner sc = new Scanner(System.in);
                        System.out.println("-------게시물 작성--------");
                        System.out.print("아이디를 입력해주세요 :");
                        String ID = sc.nextLine();
                        System.out.print("비밀번호를 입력해주세요 : ");
                        String pass = sc.nextLine();
                        ArrayList<DBdriver.VocDto> alist = vo.getMemVoc(new DBdriver.VocDto(ID, pass));
                        for (int i = 0; i < alist.size(); i++) {
                            uNo = alist.get(i).getUno();
                            name = alist.get(i).getName();
                        }
                        System.out.println("로그인 완료 [글쓰기]");
                        System.out.println("제목을 써주세요: ");
                        String sub = sc.nextLine();
                        System.out.println("내용을 써주세요: ");
                        String cont = sc.nextLine();
                        System.out.println("작성을 하시겠습니까 ?");
                        String y = sc.nextLine();

                        if (y.equals("네")) {
                            p.InsertPullBoard(new DBdriver.VocDto(sub, cont, uNo));
                            break;
                        } else {
                            break;
                        }
                    } else if (input.equals("2")) {// 게시물 삭제
                        Scanner sc = new Scanner(System.in); // 자꾸 오류가 나서 객체 생성
                        System.out.println("-------게시물 삭제--------");
                        System.out.print("아이디를 입력해주세요 :");
                        String ID = sc.nextLine();
                        System.out.print("비밀번호를 입력해주세요 : ");
                        String pass = sc.nextLine();
                        System.out.println("게시물 삭제 하시겠습니까 ?");
                        String y = sc.nextLine();
                        if (y.equals("네")) {
                            p.DeletePullBoard(new DBdriver.VocDto(ID, pass));
                            break;
                        } else {
                            break;
                        }

                    } else if (input.equals("3")) { // 게시물 업데이트
                        Scanner sc = new Scanner(System.in);
                        System.out.println("--------게시물 업데이트--------");
                        System.out.print("아이디를 입력해주세요 :");
                        String ID = sc.nextLine();
                        System.out.print("비밀번호를 입력해주세요 : ");
                        String pass = sc.nextLine();
                        ArrayList<DBdriver.VocDto> alist = vo.getMemVoc(new DBdriver.VocDto(ID, pass));
                        for (int i = 0; i < alist.size(); i++) {
                            uNo = alist.get(i).getUno();
                        }
                        System.out.println("로그인 완료 [글쓰기]");
                        System.out.println("수정할 제목을 써주세요: ");
                        String sub = sc.nextLine();
                        System.out.println("수정할 내용을 써주세요: ");
                        String cont = sc.nextLine();
                        System.out.println("수정 하시겠습니까 ?");
                        String y = sc.nextLine();
                        if(y.equals("네")) {
                            p.UpdatePullBoard(sub, cont, new DBdriver.VocDto(uNo));
                        }else{
                            System.out.println("처음화면으로 돌아갑니다.");
                            break;
                        }
                    } else if (input.equals("4")) { //관리자 답변
                        Scanner sc = new Scanner(System.in);
                        System.out.println("-------답변 작성--------");
                        System.out.print("아이디를 입력해주세요 :");
                        String ID = sc.nextLine();
                        System.out.print("비밀번호를 입력해주세요 : ");
                        String pass = sc.nextLine();
                        TrainSystem.VocDao vd = new VocDao();
                        int aUno = vd.asUnumInsert(ID,pass); // 관리자 계정 번호 받아오기
                        System.out.print("답변하실 게시물의 번호를 적으세요 : ");
                        int vnum = in.nextInt();
                        System.out.println("답변하실 제목을 입력하세요 : ");
                        String sub = in.nextLine();
                        System.out.println("답변하실 내용을 입력하세요 : ");
                        String con = in.nextLine();
                        System.out.println("수정 하시겠습니까 ?");
                        String y = sc.nextLine();
                        if(y.equals("네")) {
                            p.replyPullBoard(new VocDto(sub,con,vnum,aUno));
                        }else {
                            System.out.println("처음화면으로 돌아갑니다.");
                            break;
                        }

                    }
                }
            }
        }
    }
}

