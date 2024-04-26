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
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", account='" + account + '\'' +
                ", money=" + money +
                '}';
    }
}
