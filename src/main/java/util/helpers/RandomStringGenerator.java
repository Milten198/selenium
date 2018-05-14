package util.helpers;

import net._01001111.text.LoremIpsum;

import java.util.Date;
import java.util.Random;

public class RandomStringGenerator {
    private static Random random = new Random((int) new Date().getTime());

    public static String getRandomString(int size) {
        StringBuilder builder = new StringBuilder();
        char ch;
        for (int i = 0; i < size; i++) {
            ch = (char) ((int) (Math.floor(26 * random.nextDouble() + 65)));
            ch = Character.toLowerCase(ch);
            if (i % 3 == 0) ch = Character.toUpperCase(ch);
            builder.append(ch);
        }

        return builder.toString();
    }

    public static String getRandomWord() {
        return new LoremIpsum().randomWord();
    }

    public static String getRandomSentence() {
        return new LoremIpsum().sentence();
    }

    public static String getRandomSentences(int howMany) {
        return new LoremIpsum().sentences(howMany);
    }

    public static String getRandomStringNumbersWithoutLeadingZero(int length) {
        Random rand = new Random();
        StringBuilder build = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (i == 0)
                build.append(rand.nextInt(8) + 1);
            else
                build.append(rand.nextInt(9));
        }

        return build.toString();
    }

    public static String getRandomEmail() {
        return getRandomString(7) + "@" + getRandomString(2) + "." + getRandomString(2);
    }

    public static String getRandomPrice() {
        Random rand = new Random();

        double factor = rand.nextDouble() + 0.1;
        double multiplied = rand.nextInt(999) + 1;

        return DecimalFormatHelpers.getCurrencyFormat().format(factor * multiplied);
    }

}
