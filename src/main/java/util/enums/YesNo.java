package util.enums;

import java.util.Random;

public enum YesNo {
    Yes,
    No;

    public static YesNo randomYesNo() {
        int pick = new Random().nextInt(YesNo.values().length);
        return YesNo.values()[pick];
    }
}
