package user;

import java.util.ArrayList;
import java.util.List;

public class DittoRepository {

    private static List<Ditto> dittos;

    static {
        dittos = new ArrayList<>();
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
}
