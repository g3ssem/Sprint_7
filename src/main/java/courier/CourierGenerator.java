package courier;
import model.Courier;

import static utils.Utils.randomString;

public class CourierGenerator {
    public static Courier randomCourier () {
    return new Courier()
            .withLogin(randomString(10))
            .withPassword(randomString(12))
            .withFirstName(randomString(20));
       }
       public static Courier sameCourier() {
        return new Courier()
                .withLogin("Gorinich")
                .withPassword("12345678")
                .withFirstName("Zmei");

       }
    public static Courier withoutLoginCourier() {
        return new Courier()
                .withLogin("")
                .withPassword(randomString(12))
                .withFirstName(randomString(20));
    }
    public static Courier withoutPasswordCourier() {
        return new Courier()
                .withLogin(randomString(10))
                .withPassword("")
                .withFirstName(randomString(20));
    }
}
