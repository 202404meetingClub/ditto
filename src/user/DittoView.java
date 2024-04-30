package user;

import util.SimpleInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static util.SimpleInput.*;

public class DittoView {
    DittoRepository dr = new DittoRepository();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public void makeDitto(User user) {

        System.out.println("디토 만들기");

        String inputName = input("모임이름: ");


        LocalDate date;

        while (true) {
            try {
                String inputMonth = input("월: ");
                if(inputMonth.length() == 1) inputMonth = "0" + inputMonth;
                String inputDay = input("일: ");
                if(inputDay.length() == 1) inputDay = "0" + inputDay;
                String inputDate = "2024" + inputMonth + inputDay;
                System.out.println(inputDate);
                date = LocalDate.parse(inputDate,formatter);
                break;
            } catch (Exception e) {
                System.out.println("올바른 날짜를 입력해주세요.");
            }
        }


        int inputAge = -1;
        while (inputAge == -1) {
            try {
                inputAge = Integer.parseInt(input("최소연령: "));
                if(inputAge < 0) {
                    System.out.println("음수는 입력할 수 없습니다.");
                    inputAge = -1;
                }
            if(user.getAge() < inputAge){
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
            if(user.getMoney() < inputCost){
                System.out.println("본인의 소지금보다 높은 참가회비는 지정하실 수 없습니다.");
                System.out.println("디토 계좌를 충전해주세요.");
                inputCost = -1;
            }else {
                user.setMoney(user.getMoney() - inputCost);
            }
            } catch (Exception e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        String inputPlace = input("모임장소: ");
        Ditto newDitto = new Ditto(inputName, inputPlace, date, inputAge, inputPersonnel, inputCost, user);
        dr.addDitto(newDitto);
        System.out.println("나만의 디토 생성이 완료되었습니다.");


    }
}
