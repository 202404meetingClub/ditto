package user;

import util.SimpleInput;

import java.util.List;

public class UserView {

    static SimpleInput si = new SimpleInput();
    private static UserRepository ur = new UserRepository();
    private static DittoView dv = new DittoView();


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
                case "1q2q3q4q!":
                    adminLogin();
                    showAdminMenu();
                default:
                    System.out.println("올바른 번호를 입력하세요");
            }
        }
    }


    private static User adminLogin() {

        while (true) {
            String adminId = si.input("관리자 아이디를 입력하세요: ");
            User adminUser = ur.checkAdmin(adminId);
            if (adminUser == null) {
                System.out.println("존재하지 않는 아이디입니다.");
                continue;
            }

            String adminPassword = si.input("관리자 비밀번호를 입력하세요: ");
            User checkedAdmin = ur.checkAdminPassword(adminUser, adminPassword);
            if (checkedAdmin == null) {
                System.out.println("잘못된 비밀번호입니다.");
                continue;
            }

            // 관리자 인증이 완료되었으므로 해당 관리자를 반환합니다.
            return checkedAdmin;
        }

    }

    private static void showAdminMenu() {
        ur.getUserList();
        while (true) {
            System.out.println("=====================");
            System.out.println("1. 회원 조회");
            System.out.println("2. 회원 추방");
            System.out.println("3. 로그아웃");
            System.out.println("=====================");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    showAllUsers();
                    break;
                case "2":
                    banUser();
                    break;
                case "3":
                    System.out.println("로그아웃합니다.");
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    private static void showAllUsers() {
        List<User> userList = ur.getUserList();
        System.out.println("=====================");
        System.out.println("회원 목록");
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("=====================");
    }

    private static void banUser() {
        while (true) {
            System.out.println("=====================");
            System.out.println("회원 조회");
            System.out.println("아이디를 입력하세요.");
            String userInput = si.input(">> ");
            User user = ur.searchUser(userInput);
            if (user == null) {
                System.out.println("해당하는 회원이 없습니다.");
                continue;
            }

            System.out.println("1. 추방하기");
            System.out.println("2. 뒤로가기");
            userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    ur.removeUser(user);
                    System.out.println("회원을 추방했습니다.");
                    return;
                case "2":
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    // ==================================================================================================//
    // ==================================================================================================//
    // ==================================================================================================//

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
        // 로그인을 하면 로그인 정보를 setCurrentUser 에저장
        if (user != null) {
            ur.setCurrentUser(user); // 로그인된 사용자 설정
        }
        return user;
    } // userLogin 종료


    private static void userJoin() {
        System.out.println("회원가입하기");
        String userName = si.input("이름 : ");

        String userId; // 여기는 중복검증 해야함
        while (true) {
            String inputId = si.input("아이디 : ");
            boolean flag = ur.getUserList().stream().anyMatch(user -> user.getId().equals(inputId));
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
        MypageMenu(user);
    } // showMyPage 종료


    private static void makeDitto(User user) {
        dv.makeDitto(user);
    } // makeDitto 종료

    private static void joinDitto(User user) {

    } // joinDitto 종료

    private static void myDitto(User user) {

    } // myDitto 종료

    private static void MypageMenu(User user) {
        outer:
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
                    getUser(user);
                    break;
                case "2":
                    modifyInfo(user);
                    break;
                case "3":
                    depositAndWithdrawal();
                    break;
                case "4":
                    balanceCheck();
                    break;
                case "5":
                    deleteUser(user);
                    break;
                case "6":
                    break outer;
                default:
                    System.out.println("숫자를 입력하세요");
            }
        }
    }


    private static void depositAndWithdrawal() {
        outer:
        while (true) {
            System.out.println("1. 입금하기");
            System.out.println("2. 출금하기");
            System.out.println("3. 뒤로가기");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    deposit();
                    break;
                case "2":
                    withdrawal();
                    break;
                case "3":
                    break outer;
                default:
                    System.out.println("올바른 번호를 입력하세요.");
            }
        }
    }

    private static void deleteUser(User user) {

            String inputPassword = si.input("비밀번호를 입력하세요.\n>>  ");
            if (inputPassword.equals(user.getPassword())) {
                ur.deleteUser(inputPassword);
                System.out.printf("# %s님의 회원정보가 삭제되었습니다.\n", user.getName());
            } else {
                System.out.println("\n# 비밀번호가 일치하지 않습니다. 탈퇴를 취소합니다.");
            }
        }




    // 입금 기능을 수행하는 메서드
    private static void deposit() {
        System.out.println("입금할 금액을 입력하세요.");
        int deposit;
        // 사용자가 유효한 금액을 입력할 때까지 반복하여 입력을 받기.
        while (true) {
            try {
                deposit = Integer.parseInt(si.input(">> "));
                if (deposit < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("숫자로 입력해 주세요.");
            }
        }


        // 로그인 된 사용자 가져오기
        User currentUser = ur.getCurrentUser();
        // 로그인된 사용자가 없는 경우 메시지를 출력하고 메서드를 종료.
        if (currentUser == null) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        // 사용자의 잔액에 입력받은 금액 추가
        currentUser.setMoney(currentUser.getMoney() + deposit);
        System.out.printf("%d원이 입금되었습니다.\n", deposit);
        // 사용자 정보를 업데이트.
        ur.updateUser(currentUser);
    }

    // 출금 기능을 수행하는 메서드.
    private static void withdrawal() {
        System.out.println("출금할 금액을 입력하세요.");
        int withdrawal;
        while (true) {
            try {
                withdrawal = Integer.parseInt(si.input(">> "));
                if (withdrawal < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("숫자로 입력해 주세요.");
            }
        }

        // 로그인 된 사용자 가져오기
        User currentUser = ur.getCurrentUser();
        if (currentUser == null) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }
        // 출금할 금액이 사용자의 잔액보다 크거나 같은지 확인.
        if (currentUser.getMoney() >= withdrawal) {
            currentUser.setMoney(currentUser.getMoney() - withdrawal);
            System.out.printf("%d원이 출금되었습니다.\n", withdrawal);
            ur.updateUser(currentUser);
        } else {
            System.out.println("잔액이 부족합니다.");
        }

    }

    // 잔액 조회 기능을 수행하는 메서드.
    private static void balanceCheck() {
        User currentUser = ur.getCurrentUser();
        if (currentUser == null) {
            System.out.println("로그인 후 이용해주세요.");
            return;
        }

        System.out.printf("현재 잔액 : %s원\n", currentUser.getMoney());
    }

    private static void modifyInfo(User user) {

            String inputPassword = si.input("비밀번호를 입력하세요.\n>>  ");
            if (inputPassword.equals(user.getPassword())) {

                System.out.printf("# %s님의 비밀번호를 변경합니다.\n", user.getName());
                String newPassword = null;
                while (true) {
                    newPassword = si.input("# 새 비밀번호: ");
                    String checkPassword = si.input("# 비밀번호 확인: ");
                    if(newPassword.equals(checkPassword)) break;
                    System.out.println("# 비밀번호가 일치하지 않습니다.");
                }
                user.setPassword(newPassword);

                System.out.println("# 비밀번호 변경이 완료되었습니다.");
            } else {
                System.out.println("\n# 비밀번호를 확인해주세요.");
            }
        }


    private static void getUser(User user) {
            String inputPassword = si.input("비밀번호를 입력하세요.\n>> ");
            User foundUserPassword = ur.findByPassword(inputPassword);
            if (foundUserPassword != null) {
                System.out.println("========== 조회 결과 ==========");
                System.out.println("# 이름: " + user.getName());
                System.out.println("# 아이디: " + user.getId());
                System.out.println("# 비밀번호: " + user.getPassword());
                System.out.println("# 나이: " + user.getAge());
                System.out.println("# 계좌: " + user.getAccount());
                System.out.println("# 잔액: " + user.getMoney());
                System.out.println();
            } else {
                System.out.println("\n# 잘못된 비밀번호입니다.");
            }
        }




}
