package util.helpers;

import java.util.Date;
import java.util.Random;

public class RandomNumberGenerator {
    private static Random random = new Random((int) new Date().getTime());

    public static int getRandomIntBetween(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

}
