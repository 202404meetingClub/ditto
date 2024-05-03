package user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Ditto {
    private String dittoTitle;
    private String dittoPlace; // 모임장소
    private LocalDate dittoDay; // 모임날짜
    private int age;
    private int personnel; // 참가인원
    private int cost; // 참가비
    private User user; // 입력받은 유저 정보
    private List<User> userList;

    public Ditto(String dittoTitle, String dittoPlace, LocalDate dittoDay, int age, int personnel, int cost, User user) {
        this.dittoTitle = dittoTitle;
        this.dittoPlace = dittoPlace;
        this.dittoDay = dittoDay;
        this.age = age;
        this.personnel = personnel;
        this.cost = cost;
        this.user = user;
        this.userList = new ArrayList<>(); // 참여하기 기능 사용할때 유저가 추가되는 빈 리스트
    }

    public String getDittoTitle() {
        return dittoTitle;
    }

    public void setDittoTitle(String dittoTitle) {
        this.dittoTitle = dittoTitle;
    }

    public String getDittoPlace() {
        return dittoPlace;
    }

    public void setDittoPlace(String dittoPlace) {
        this.dittoPlace = dittoPlace;
    }

    public LocalDate getDittoDay() {
        return dittoDay;
    }

    public void setDittoDay(LocalDate dittoDay) {
        this.dittoDay = dittoDay;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPersonnel() {
        return personnel;
    }

    public void setPersonnel(int personnel) {
        this.personnel = personnel;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "\n\uD83D\uDC30" + dittoTitle + "\uD83D\uDC30" +
                "\n디토장소: " + dittoPlace +
                "\n디토데이: " + dittoDay +
                "\n최소연령: " + age + "세" +
                "\n목표인원: " + personnel + "명" +
                "\n참가비: " + cost + "원" +
                "\n주최자 ID: " + user.getId() +
                "\n참가자: " + dittoUserList(userList);
    }

    private String dittoUserList(List<User> userList) {
        String result = "";
        for (User user1 : userList) {
            result += user1.getName() + " ";
        }

        return result;
    }
}