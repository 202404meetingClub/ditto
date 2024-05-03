package user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import user.UserRepository;
public class DittoRepository {
    private static UserRepository ur = new UserRepository();
    private static List<Ditto> dittos;

    static {
        dittos = new ArrayList<>();
        dittos.add(new Ditto("602호에서 잠자기", "602호", LocalDate.now(), 11, 6, 6020, ur.getUserList().get(1)));
        dittos.add(new Ditto("301호에서 잠자기", "301호", LocalDate.now(), 8, 3, 3100, ur.getUserList().get(1)));
        dittos.add(new Ditto("피시방 고", "이대역 사거리 피시방", LocalDate.now(), 13, 5, 10000, ur.getUserList().get(0)));
        dittos.add(new Ditto("노래방 고", "이대역 사거리 노래방", LocalDate.now(), 19, 4, 2500, ur.getUserList().get(0)));
        dittos.add(new Ditto("칼퇴하기", "지금여기", LocalDate.now(), 8, 10, 10, ur.getUserList().get(2)));
        dittos.get(0).getUserList().add(ur.getUserList().get(0));
        dittos.get(0).getUserList().add(ur.getUserList().get(1));
        dittos.get(0).getUserList().add(ur.getUserList().get(2));
        dittos.get(1).getUserList().add(ur.getUserList().get(1));
        dittos.get(2).getUserList().add(ur.getUserList().get(0));
        dittos.get(3).getUserList().add(ur.getUserList().get(0));
        dittos.get(4).getUserList().add(ur.getUserList().get(2));
    }

    public static List<Ditto> getDittos() {
        return dittos;
    }

    public static void setDittos(List<Ditto> dittos) {
        DittoRepository.dittos = dittos;
    }
    public void addDitto(Ditto ditto) {
        dittos.add(ditto);
    }

    public void deleteDitto(Ditto selectDitto) {
        getDittos().remove(selectDitto);
    }
}
