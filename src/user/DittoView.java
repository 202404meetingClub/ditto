package user;

import util.SimpleInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static util.SimpleInput.*;

public class DittoView {
    DittoRepository dr = new DittoRepository();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    static SimpleInput si = new SimpleInput();

    public void makeDitto(User user) {

        System.out.println("디토 만들기");

        String inputName = null;
        while (true) {
            inputName = input("디토이름: ");
            if (inputName.length() == 0) {
                System.out.println("디토이름을 입력해 주세요.");
            } else break;
        }


        LocalDate date;

        while (true) {
            try {
                String inputMonth = input("월: ");
                if (inputMonth.length() == 1) inputMonth = "0" + inputMonth;
                String inputDay = input("일: ");
                if (inputDay.length() == 1) inputDay = "0" + inputDay;
                String inputDate = "2024" + inputMonth + inputDay;
                System.out.println(inputDate);
                date = LocalDate.parse(inputDate, formatter);
                break;
            } catch (Exception e) {
                System.out.println("올바른 날짜를 입력해주세요.");
            }
        }


        int inputAge = -1;
        while (inputAge == -1) {
            try {
                inputAge = Integer.parseInt(input("최소연령: "));
                if (inputAge < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    inputAge = -1;
                }
                if (user.getAge() < inputAge) {
                    System.out.println("본인의 나이보다 높은 최소연령값은 지정하실 수 없습니다.");
                    inputAge = -1;
                }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
        int inputPersonnel = -1;
        while (inputPersonnel == -1) {
            try {
                inputPersonnel = Integer.parseInt(input("참가인원: "));
                if (inputPersonnel == 0 || inputPersonnel == 1) {
                    System.out.println("참여인원은 최소 두명입니다.");
                    inputPersonnel = -1;
                }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        int inputCost = -1;
        while (inputCost == -1) {
            try {
                inputCost = Integer.parseInt(input("참가회비: "));
                if (inputCost < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    inputCost = -1;
                }
                if (user.getMoney() < inputCost) {
                    System.out.println("본인의 소지금보다 높은 참가회비는 지정하실 수 없습니다.");
                    System.out.println("디토 계좌를 충전해주세요.");
                    inputCost = -1;
                } else {
                    user.setMoney(user.getMoney() - inputCost);
                }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        String inputPlace = null;
        while (true) {
            inputPlace = input("디토장소: ");
            if (inputPlace.length() == 0) {
                System.out.println("디토장소를 입력해 주세요.");
            } else break;
        }
        Ditto newDitto = new Ditto(inputName, inputPlace, date, inputAge, inputPersonnel, inputCost, user);
        newDitto.getUserList().add(user);
        dr.addDitto(newDitto);
        System.out.println("나만의 디토 생성이 완료되었습니다.");


    }

    public void showAllDittos() {
        outer:
        while (true) {
            Ditto selectDitto = null;
            while (true) {
                int countNum = 1;
                System.out.println("디토 목록");
                for (Ditto ditto : dr.getDittos()) {
                    System.out.printf("%d. ", countNum++);
                    System.out.println(ditto.getDittoTitle());
                }
                int userInput = 0;
                try {
                    System.out.println("\n- 뒤로가기: 0");
                    userInput = Integer.parseInt(si.input("조회할 디토의 번호: "));
                    if (userInput == 0) return;
                    selectDitto = dr.getDittos().get(userInput - 1);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력하세요.\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("올바른 번호를 입력하세요.\n");
                } catch (Exception e) {
                    System.out.println("모르는 오류"); // 오류 테스트용
                    e.printStackTrace();
                } // try catch


            } // while
            System.out.println(selectDitto);
            while (true) {
                System.out.println("1. 삭제하기");
                System.out.println("2. 뒤로가기");

                String userInput = si.input(">> ");

                switch (userInput) {
                    case "1":
                        checkDelete(selectDitto);
                        continue outer;
                    case "2":
                        continue outer;
                    default:
                        System.out.println("올바른 번호를 입력하세요.");
                }
            }
        }


    } // 메서드

    private void checkDelete(Ditto selectDitto) {
        System.out.println("1. 삭제");
        System.out.println("2. 취소");
        String userInput = si.input(">> ");
        switch (userInput) {
            case "1":
                System.out.printf("'%s'디토가 삭제되었습니다.\n", selectDitto.getDittoTitle());
                dr.deleteDitto(selectDitto);
                si.stop();
                break;
            case "2":
                System.out.println("디토 삭제를 취소합니다.");
                si.stop();
                return;
            default:
                System.out.println("올바른 번호를 입력하세요.");
        }

    }
}
