package util.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DecimalFormatHelpers {

    private static final String currencyPattern = "##,##0.00";

    /**
     * Format a currency value for display with no thousands separator (comma)
     *
     * @param value The currency value
     * @return A string representation of the value
     */
    public static String formatCurrencyValueNoSeparator(BigDecimal value) {
        DecimalFormat moneyFormat = new DecimalFormat();
        moneyFormat.setMinimumFractionDigits(2);
        moneyFormat.setMaximumFractionDigits(2);
        moneyFormat.setGroupingUsed(false);
        moneyFormat.setRoundingMode(RoundingMode.HALF_UP);
        return moneyFormat.format(value);
    }

    public static DecimalFormat getCurrencyFormat() {
        Locale locale = getUKLocale();

        DecimalFormatSymbols symbols = getDecimalFormatSymbols(locale);

        return new DecimalFormat(currencyPattern, symbols);
    }

    public static String getCurrencyFormat(double value) {
        Locale locale = getUKLocale();

        DecimalFormatSymbols symbols = getDecimalFormatSymbols(locale);

        return new DecimalFormat(currencyPattern, symbols).format(value);
    }

    public static String getCurrencyFormat(BigDecimal value) {
        Locale locale = getUKLocale();

        DecimalFormatSymbols symbols = getDecimalFormatSymbols(locale);

        return new DecimalFormat(currencyPattern, symbols).format(value);
    }

    private static Locale getUKLocale() {
        return new Locale("en", "UK");
    }

    private static DecimalFormatSymbols getDecimalFormatSymbols(Locale locale) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        return symbols;
    }
}
