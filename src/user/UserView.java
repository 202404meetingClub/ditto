package user;

import util.SimpleInput;

import java.util.List;

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
            if (!flag) {
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
            System.out.println("1. 회원정보 조회");
            System.out.println("2. 회원정보 수정");   // view 레파지토리
            System.out.println("3. 입 * 출금하기");  //  뷰
            System.out.println("4. 잔액조회");       //  뷰
            System.out.println("5. 회원탈퇴");  // view 뷰안에서 삭제하는걸 레파지토리
            System.out.println("6. 뒤로가기");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    getUser();
                    break;
                case "2":
                    modifiyInfo();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    deleteUser();
                    break;
                case "6":
                    break;
                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

    private static void deleteUser() {
        String inputId = si.input("삭제할 회원의 아이디를 입력하세요.\n>>  ");

        User foundUser = ur.findById(inputId);
        if (foundUser != null) {
            String inputPassword = si.input("비밀번호를 입력하세요.\n>>  ");
            if (inputPassword.equals(foundUser.getPassword())) {
                ur.removeUser(inputId);
                System.out.printf("# %s님의 회원정보가 삭제되었습니다.\n", foundUser.getName());
            } else {
                System.out.println("\n# 비밀번호가 일치하지 않습니다. 탈퇴를 취소합니다.");
            }
        } else {
            System.out.println("\n# 해당 회원은 존재하지 않습니다.");
        }
    }

    private static void modifiyInfo() {
        String inputId = si.input("수정할 회원의 아이디를 입력하세요.\n>>  ");

        User foundUser = ur.findById(inputId);
        if (foundUser != null) {
            String inputPassword = si.input("비밀번호를 입력하세요.\n>>  ");
            User foundUserPassword = ur.findByPassword(inputPassword);
            if (foundUserPassword != null) {

                System.out.printf("# %s님의 비밀번호를 변경합니다.\n", foundUserPassword.getName());
                String newPassword = si.input("# 새 비밀번호: ");

                foundUserPassword.changePassword(newPassword);

                System.out.println("# 비밀번호 변경이 완료되었습니다.");
            } else {
                System.out.println("\n# 비밀번호를 확인해주세요.");
            }
        } else {
            System.out.println("\n# 해당 회원은 존재하지 않습니다.");
        }
    }

    private static void getUser() {
        String inputId = si.input("# 조회하실 회원의 아이디를 입력하세요.\n>> ");
        User foundUser = ur.findById(inputId);

        if (foundUser != null) {

            String inputPassword = si.input("비밀번호를 입력하세요.\n>> ");
            User foundUserPassword = ur.findByPassword(inputPassword);
            if (foundUserPassword != null) {
                System.out.println("========== 조회 결과 ==========");
                System.out.println("# 이름: " + foundUser.getName());
                System.out.println("# 아이디: " + foundUser.getId());
                System.out.println("# 비밀번호: " + foundUser.getPassword());
                System.out.println("# 나이: " + foundUser.getAge());
                System.out.println("# 계좌: " + foundUser.getAccount());
                System.out.println("# 잔액: " + foundUser.getMoney());
                System.out.println();
            } else {
                System.out.println("\n# 잘못된 비밀번호입니다.");
            }

        } else {
            System.out.println("\n# 해당 회원은 존재하지 않습니다.");
        }

    }

} // class 종료
