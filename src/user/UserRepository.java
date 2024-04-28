package user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static List<User> userList;

    static {
        userList = new ArrayList<>();
        userList.add(new User("123", "1234", "12345", 12, "1231231234"));
        userList.add(new User("234", "2345", "23456", 23, "2342342345"));
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "userList=" + userList +
                '}';
    }

    public User checkId(String id) {
        List<User> checkedId = userList
                .stream()
                .filter(user -> user.getId().equals(id))
                .collect(Collectors.toList());
        if (checkedId.size() == 1) {
            return checkedId.get(0);
        }
        return null;

    }

    public User checkPassword(User user, String password) {
        if (user.getPassword().equals(password)) return user;
        return null;
    }

    public void addUser(String name, String id, String password, int age) {
        String userAccount;
        while (true) {
            int randomNumber = (int) Math.floor(Math.random() * 1000000);
            String randomAccount = "602" + Integer.toString(randomNumber);

            boolean flag = userList
                    .stream()
                    .anyMatch(user -> user.getAccount().equals(randomAccount));
            if (!flag) {
                userAccount = randomAccount;
                break;
            }
        }
        userList.add(new User(name, id, password, age, userAccount));


    }


    public User findById(String inputId) {

        for (User user : userList) {
            if(user.getId().equals(inputId)) {
                return user;
            }
        }
        return null;
    }

    public User findByPassword(String inputPassword) {
        for (User user : userList) {
            if(user.getId().equals(inputPassword)) {
                return user;
            }
        }
        return null;

    }

    public void removeUser(String inputId) {
        int index = userList.indexOf(inputId);
        if(index == -1) return;
        User remove = userList.remove(index);

    }
}
