package user;

import util.SimpleInput;

public class UserView {

    static SimpleInput si = new SimpleInput();
    private static UserRepository ur = new UserRepository();


    public static void start() {
        showLogo();
        User loginedUser = showLoginPage();
        if (loginedUser == null) {
            return;
        }
        showMainMenu(loginedUser);
    }


    private static void showLogo() {
        System.out.println("로고 페이지");
        SimpleInput.stop();
    }

    private static User showLoginPage() {
        while (true) {
            System.out.println("==========================");
            System.out.println("1. 로그인하기");
            System.out.println("2. 회원가입하기");
            System.out.println("3. 프로그램 종료");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    User user = userLogin();
                    System.out.printf("'%s'님 환영합니다.\n", user.getName());
                    return user;

                case "2":
                    userJoin();
                    System.out.println("회원가입을 성공하였습니다.");
                    break;
                case "3":
                    System.out.println("프로그램을 종료합니다.");
                    return null;
                default:
                    System.out.println("올바른 번호를 입력하세요");
            }
        }
    }


    private static User userLogin() {
        User user;
        while (true) {
            String userId = si.input("아이디\n>> ");
            User currentUser = ur.checkId(userId);
            if (currentUser == null) {
                System.out.println("존재하지 않는 아이디 입니다.");
                continue;
            }

            String userPassword = si.input("비밀번호\n>> ");
            User checkedUser = ur.checkPassword(currentUser, userPassword);
            if (checkedUser == null) {
                System.out.println("잘못된 비밀번호 입니다.");
                continue;
            }
            user = checkedUser;
            break;
        }
        return user;
    } // userLogin 종료


    private static void userJoin() {
        System.out.println("회원가입하기");
        String userName = si.input("이름 : ");

        String userId; // 여기는 중복검증 해야함
        while (true) {
            String inputId = si.input("아이디 : ");
            boolean flag = ur.getUserList()
                    .stream()
                    .anyMatch(user -> user.getId().equals(inputId));
            if(!flag) {
                userId = inputId;
                break;
            } else {
                System.out.println("중복된 아이디입니다.");
            }
        }
        String userPassword = si.input("비밀번호 : ");
        int age = 0;
        while (true) {
            try {
                age = Integer.parseInt(si.input("나이 : "));
                if (age < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("숫자로 입력해 주세요.");
            }
        }
        ur.addUser(userName, userId, userPassword, age);



    } // userJoin 종료


    private static void showMainMenu(User user) {
        while (true) {
            System.out.println("====================="); // 나중에 유저관련 메시지를 넣을 수 있음
            System.out.println("1. 마이페이지");
            System.out.println("2. 모임 만들기");
            System.out.println("3. 모임 참여하기");
            System.out.println("4. 내 모임 조회하기");
            System.out.println("5. 프로그램 종료");
            System.out.println("=====================");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    showMyPage(user);
                    break;
                case "2":
                    makeDitto(user);
                    break;
                case "3":
                    joinDitto(user);
                    break;
                case "4":
                    myDitto(user);
                    break;
                case "5":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 입력하세요.");
            }
        }


    } // showMainMenu 종료

    private static void showMyPage(User user) {
        MypageMenu();
    } // showMyPage 종료


    private static void makeDitto(User user) {

    } // makeDitto 종료

    private static void joinDitto(User user) {

    } // joinDitto 종료

    private static void myDitto(User user) {

    } // myDitto 종료

    private static void MypageMenu() {
        while (true) {
            System.out.println("=====================");
            System.out.println("1. 회원정보 수정");   // view 레파지토리
            System.out.println("2. 입 * 출금하기");  //  뷰
            System.out.println("3. 잔액조회");       //  뷰
            System.out.println("4. 회원탈퇴");  // view 뷰안에서 삭제하는걸 레파지토리
            System.out.println("5. 뒤로가기");
            String userInput = si.input(">> ");
            switch(userInput){
                case"1":
                    modifiyInfo();
                    break;
                case"2":
                    break;
                case"3":
                    break;
                case"4":
                    break;
                case"5":
                    break;
                default:
                    System.out.println("숫자를 입력하세여");
            }
        }
    }

    private static void modifiyInfo() {

    }

} // class 종료
