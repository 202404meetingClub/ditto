package user;

import util.SimpleInput;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static user.DittoRepository.*;

public class UserView {

    //    static SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    static SimpleInput si = new SimpleInput();
    private static UserRepository ur = new UserRepository();
    private static DittoView dv = new DittoView();

    public static void start() {
        showLogo();
        while (true) {
            User loginedUser = showLoginPage();
            if (loginedUser == null) {
                System.exit(0);
            }
            showMainMenu(loginedUser);
        }
    }


    private static void showLogo() {
        System.out.println(
                "⢿⢻⢻⣿⡟⠻⠻⣿⡿⠻⠟⣿⣿⠟⢿⣿⣿⢿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠋⠀⠙\n" +
                        "⣾⣸⣾⣿⣦⡀⣠⣿⣧⣀⣠⣿⣿⣄⣼⣿⣯⣼⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡖⠋⣩⣄⠀⠀⢀\n" +
                        "⢿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡸⠋⠀⠀⠀⠀⠀⠀⢀⣀⡤⠾⠟⠋⠀⠀⠈⠋⠀⠀⠙\n" +
                        "⣾⣿⣿⣿⣷⣤⣼⣿⣿⣬⣽⣿⣿⣤⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡤⠶⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⣿⣿⣿⣿⣿⣏⣿⣿⣿⣿⣻⡿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣀⣤⣤⣤⢤⣶⣶⣶⣾⣿⣿\n" +
                        "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⣏⣿⣿⣿⣟⣁⣹⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⣌⣿⣿⣭⠁⣹⣿⣁⡁⣿\n" +
                        "⣿⣿⣿⣿⣿⢿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⡿⣿⣿\n" +
                        "⣼⣹⣿⣿⣇⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣉⣉⣽⣿⡀⣀⣿\n" +
                        "⢿⢻⣿⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡟⣿⣿⣿⣿⣿⣿\n" +
                        "⣾⣼⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡞⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢷⣘⣿⣿⣆⣁⣿\n" +
                        "⢿⣿⣿⣿⠁⠀⠔⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢯⣻⣿⣿⣿⣿\n" +
                        "⣾⣾⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿\n" +
                        "⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣾⣿⣝⠛⢦⡾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿\n" +
                        "⣿⣿⣿⣷⠀⠀⣠⣶⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⡇⠈⣿⠋⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣿⣿⣿\n" +
                        "⣏⣽⣿⣿⣄⣨⣿⣿⣿⠉⢷⡀⠀⠀⠀⠀⠀⠀⠀⣹⣯⣈⣽⣿⣷⠀⠈⢀⣠⡤⠒⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣫⣏⣽\n" +
                        "⣿⣿⣿⣿⣷⢿⣿⣿⣿⣆⣼⠷⠛⠙⡛⠛⠒⠀⠀⠿⠿⠛⠉⠉⠀⠀⠴⠟⠉⢀⣠⣴⣤⠤⠤⠤⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿\n" +
                        "⣼⣽⣿⣿⣿⠈⠛⠿⢿⡏⠀⣴⣟⢉⣹⠆⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠴⠚⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣏⣿\n" +
                        "⢿⢻⣿⡟⠁⠀⠀⠀⢸⠀⠀⠈⠉⠛⠃⠀⠀⠀⠀⠛⣶⣄⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿\n" +
                        "⣾⣾⣿⣧⠄⠀⠀⠀⠸⣄⣀⣀⣠⣶⣦⣤⣤⣤⡤⠞⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣏⣤⣿⣿⣧⣅⣿\n" +
                        "⢿⣿⣿⣧⣴⠂⠀⠀⠀⠀⠉⠉⠁⠉⠻⠿⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⣿⣿⣿⣍⣿⣿⡿⣿⣿\n" +
                        "⣿⣼⣿⣿⣿⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣴⣾⣿⣿⣶⣿⣿⣿⣯⣭⣿⣿⣯⣿⣿\n" +
                        "⡿⣿⣿⣿⣏⣤⣽⣿⣶⣤⣄⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠛⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉\n" +
                        "⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⣿⣹⣿⣿⣏⣀⣭⣿⣿⣀⣩⣿⣿⣅⣸⣿⣿⠤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠉⠉⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                        "⠀⢦⡀⠀⠀⠉⠻⣿⣿⣽⣽⣿⡿⠛⢉⣾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣤⣤⣤⣤⣀⣀\n" +
                        "⠀⠈⠁⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⢼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏⠀⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣽⣿⣿⣿⣿⣿⣿\n" +
                        "⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⠀⠀⠑⢤⣸⣦⣀⣀⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⠻⢿⣿⣿⣿⣿\n" +
                        "\n\n      \uD83C\uDF80\uD83C\uDF80\uD83C\uDF80    Ditto 에 오신것을 환영합니다.   \uD83C\uDF80\uD83C\uDF80\uD83C\uDF80 \n");
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
                    if (user == null) break;
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
                    if (adminLogin()) showAdminMenu();
                default:
                    System.out.println("올바른 번호를 입력하세요");
            }
            si.stop();
        }
    }


    private static Boolean adminLogin() {

        while (true) {
            String adminId = si.input("관리자 아이디를 입력하세요: ");
            User adminUser = ur.checkAdmin(adminId);
            if (adminUser == null) {
                System.out.println("존재하지 않는 아이디입니다.");
                break;
            }

            String adminPassword = si.input("관리자 비밀번호를 입력하세요: ");
            User checkedAdmin = ur.checkAdminPassword(adminUser, adminPassword);
            if (checkedAdmin == null) {
                System.out.println("잘못된 비밀번호입니다.");
                break;
            }

            // 관리자 인증이 완료되었으므로 해당 관리자를 반환합니다.
            return true;
        }
        return false;
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("=====================");
            System.out.println("1. 회원 조회");
            System.out.println("2. 회원 추방");
            System.out.println("3. 디토 목록 열람 및 삭제");
            System.out.println("4. 로그아웃");
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
                    dv.showAllDittos();
                    break;
                case "4":
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
            System.out.println("아이디를 입력하세요.");
            String userInput = si.input(">> ");
            User user = ur.searchUser(userInput);
            if (user == null) {
                System.out.println("해당하는 회원이 없습니다.");
                continue;
            }
            String userName = user.getName();
            System.out.printf("'%s'(%s)님을 추방 하시겠습니까?\n", userName, user.getId());
            System.out.println("1. 추방하기");
            System.out.println("2. 뒤로가기");
            userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    ur.removeUser(user);
                    System.out.printf("'%s'님을 추방했습니다.\n", userName);
                    return;
                case "2":
                    return;
                default:
                    System.out.println("올바른 메뉴를 선택하세요.");
            }
        }
    }

    private static User userLogin() {
        User user = null;
        while (true) {
            String userId = si.input("아이디\n>> ");
            User currentUser = ur.checkId(userId);
            if (currentUser == null) {
                System.out.println("존재하지 않는 아이디 입니다.");
                break;
            }

            String userPassword = si.input("비밀번호\n>> ");
            User checkedUser = ur.checkPassword(currentUser, userPassword);
            if (checkedUser == null) {
                System.out.println("잘못된 비밀번호 입니다.");
                break;
            }
            user = checkedUser;
            break;
        }
        return user;
    } // userLogin 종료


    private static void userJoin() {
        System.out.println("회원가입하기");

        // 이름 공백검증
        String userName = null;
        while (true) {
            userName = si.input("이름 : ");
            if (userName.length() == 0) {
                System.out.println("이름을 입력해 주세요.");
            } else break;

        }

        String userId; // 여기는 중복검증 해야함
        while (true) {
            String inputId = si.input("아이디 : ");
            // 아이디 공백검증
            if (inputId.length() == 0) {
                System.out.println("아이디를 입력해 주세요.");
                continue;
            }
            boolean flag = ur.getUserList().stream().anyMatch(user -> user.getId().equals(inputId));
            if (!flag) {
                userId = inputId;
                break;
            } else {
                System.out.println("중복된 아이디입니다.");
            }
        }

        // 비밀번호 공백검증
        String userPassword = null;
        while (true) {
            userPassword = si.input("비밀번호 : ");
            if (userPassword.length() == 0) {
                System.out.println("비밀번호를 입력해 주세요.");
            } else break;
        }
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
        outer:
        while (true) {
            System.out.println("====================="); // 나중에 유저관련 메시지를 넣을 수 있음
            System.out.println("1. 마이페이지");
            System.out.println("2. 디토 만들기");
            System.out.println("3. 디토 참여하기");
            System.out.println("4. 내 디토 조회하기");
            System.out.println("5. 로그아웃");
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
                    si.stop();
                    break;
                case "4":
                    myDitto(user);
                    break;
                case "5":
                    break outer;
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
        int numbering = 1;
        for (Ditto ditto : getDittos()) {


            System.out.printf("%d. %s - 주최자: %s 장소: %s 날짜: %s 연령제한: %d세 참가비: %d원\n",
                    numbering++, ditto.getDittoTitle(), ditto.getUser().getName(), ditto.getDittoPlace(),
                    ditto.getDittoDay().format((DateTimeFormatter.ofPattern("MM월 dd일"))), ditto.getAge(),
                    ditto.getCost());
        }
        System.out.printf("\n현재 내 소지금: %d원", user.getMoney());

        int i = 1;
        Ditto selectedDitto = null;

        while (true) {
            try {
                i = Integer.parseInt(si.input("\n참가할 디토의 번호: "));
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해 주세요.");
                continue;
            }

            try {
                selectedDitto = getDittos().get(i - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("올바른 번호를 입력해 주세요.");
            }
        }

        if (user.getAge() < selectedDitto.getAge()) {
            System.out.println("참여연령에 맞지 않습니다.");
            return;
        } // 연령검증

        if (selectedDitto.getUserList().size() >= selectedDitto.getPersonnel()) {
            System.out.println("참여 인원이 가득 찼습니다.");
            return;
        } // 참여 인원제한 검증

        if (selectedDitto.getUser().equals(user)) {
            System.out.println("자신이 만든 디토에는 참여할 수 없습니다.");
            return;
        } // 작성자 검증

        if (selectedDitto.getUserList().stream().anyMatch(user1 -> user1.equals(user))) {
            System.out.println("이미 참여한 디토입니다.");
            return;
        } // 중복참여 검증

        if (user.getMoney() < selectedDitto.getCost()) {
            System.out.printf("소지금이 부족합니다. 부족한 금액: %d\n", selectedDitto.getCost() - user.getMoney());
            return;
        } // 소지금 검증

        user.setMoney(user.getMoney() - selectedDitto.getCost()); // 소지금 차감


        selectedDitto.getUserList().add(user); // 디토안의 유저리스트에 추가
        System.out.printf("'%s'디토에 참가하였습니다. 내 디토 계좌에서 %d원이 사용되었습니다.\n", selectedDitto.getDittoTitle(), selectedDitto.getCost());

    } // joinDitto 종료

    private static void myDitto(User user) {
        outer:
        while (true) {
            System.out.println("1. 내 디토 참여자 보기");
            System.out.println("2. 내 참여 디토");
            System.out.println("3. 뒤로 가기");

            String userInput = si.input(">> ");

            switch (userInput) {
                case "1":
                    boolean flag1 = ur.myDittoList(user);
                    if (!flag1) {
                        System.out.println("내 주최 디토가 없습니다.");
                    }
                    break;
                case "2":
                    boolean flag2 = ur.joinDittoList(user);
                    if (!flag2) {
                        System.out.println("내 참여 디토가 없습니다.");
                    }
                    break;
                case "3":
                    break outer;
            }
            si.stop();
        }


    } // myDitto 종료

    private static void MypageMenu(User user) {
        outer:
        while (true) {
            System.out.printf("\uD83D\uDC30 %s님의 마이페이지 \uD83D\uDC30\n", user.getName());
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
                    si.stop();
                    break;
                case "2":
                    modifyInfo(user);
                    si.stop();
                    break;
                case "3":
                    depositAndWithdrawal(user);
                    break;
                case "4":
                    balanceCheck(user);
                    si.stop();
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


    private static void depositAndWithdrawal(User user) {
        outer:
        while (true) {
            System.out.println("1. 입금하기");
            System.out.println("2. 출금하기");
            System.out.println("3. 뒤로가기");
            String userInput = si.input(">> ");
            switch (userInput) {
                case "1":
                    deposit(user);
                    si.stop();
                    break;
                case "2":
                    withdrawal(user);
                    si.stop();
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
            setDittos(getDittos().stream().filter(ditto -> !ditto.getUser().equals(user)).collect(Collectors.toList()));
            // 본인의 디토를 제외한 다른사람의 디토만 리스트로 만들어서 덮어씌움
            getDittos().stream().forEach(ditto -> {
                ditto.getUserList().remove(user);
            });
            // 유저가 탈퇴하면 참가한 디토에서 자신을 삭제
            ur.deleteUser(user);
            System.out.printf("# %s님의 회원정보가 삭제되었습니다.\n# 초기화면으로 돌아갑니다.\n", user.getName());

            si.stop();
            start();
        } else {
            System.out.println("\n# 비밀번호가 일치하지 않습니다. 탈퇴를 취소합니다.");
        }
    }


    // 입금 기능을 수행하는 메서드
    private static void deposit(User user) {
        System.out.printf("내 디토 계좌: %s\n", user.getAccount());
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

        // 사용자의 잔액에 입력받은 금액 추가
        user.setMoney(user.getMoney() + deposit);
        System.out.printf("%d원이 입금되었습니다.\n", deposit);
        System.out.printf("입금 후 잔액: %d원\n", user.getMoney());
    }

    // 출금 기능을 수행하는 메서드.
    private static void withdrawal(User user) {
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


        // 출금할 금액이 사용자의 잔액보다 크거나 같은지 확인.
        if (user.getMoney() >= withdrawal) {
            user.setMoney(user.getMoney() - withdrawal);
            System.out.printf("%d원이 출금되었습니다.\n", withdrawal);
            System.out.printf("현재 내 잔액: %d원\n", user.getMoney());
        } else {
            System.out.println("잔액이 부족합니다.");
        }

    }

    // 잔액 조회 기능을 수행하는 메서드.
    private static void balanceCheck(User user) {

        System.out.printf("현재 잔액 : %s원\n", user.getMoney());
    }

    private static void modifyInfo(User user) {

        String inputPassword = si.input("비밀번호를 입력하세요.\n>>  ");
        if (inputPassword.equals(user.getPassword())) {

            System.out.printf("# %s님의 비밀번호를 변경합니다.\n", user.getName());
            String newPassword = null;
            while (true) {
                newPassword = si.input("# 새 비밀번호: ");
                if (newPassword.length() == 0) {
                    System.out.println("새 비밀번호를 입력해 주세요");
                    continue;
                }
                String checkPassword = si.input("# 비밀번호 확인: ");
                if (newPassword.equals(checkPassword)) break;
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
            System.out.println("# 나이: " + user.getAge() + "세");
            System.out.println("# 계좌: " + user.getAccount());
            System.out.println("# 잔액: " + user.getMoney() + "원");
            System.out.println();
        } else {
            System.out.println("\n# 잘못된 비밀번호입니다.");
        }
    }


}
