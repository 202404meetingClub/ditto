package user;

public class User {

    private String name; // 이름
    private String id;  // 아이디
    private String password;    // 비밀번호
    private int age;        // 나이
    private String account; // 계좌
    private int money; // 잔고

    public User(String name, String id, String password, int age, String account) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
        this.account = account;
        this.money = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "이름: " + name +
                " ID: " + id  +
                " 비밀번호: " + password +
                " 나이: " + age + "세" +
                " 계좌: " + account +
                " 잔액: " + money + "원";
    }

    // 회원의 비밀번호를 수정하는 메서드
    void changePassword(String newPassword) {
        this.password = newPassword;
    }
}
