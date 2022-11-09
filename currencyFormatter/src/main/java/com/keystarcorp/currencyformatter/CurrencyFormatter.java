package com.keystarcorp.currencyformatter;

import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    private static final String TAG = "CurrencyConverter";

    public static final int BOTH = 0;
    public static final int CRYPTO = 1;
    public static final int USD = 2;

    private int currencyDisplay;

    public int getCurrencyDisplay() {
        return currencyDisplay;
    }

    public void setCurrencyDisplay(int currencyDisplay) {
        this.currencyDisplay = currencyDisplay;
    }

    public CurrencyFormatter(int currencyDisplay) {
        // Initialize your Library here
        this.currencyDisplay = currencyDisplay;
    }

    public String formatBasedOnUserPreference(double amount, Currency currency, boolean... isExchangeFlow) {

        boolean exchangeFlow = false; // It the default value you want to give
        if (isExchangeFlow.length == 1) {
            exchangeFlow = isExchangeFlow[0];  // Overrided Value
        }

        if (getCurrencyDisplay() == BOTH) {
            return formatWithUsd(amount, currency, exchangeFlow);
        } else if (getCurrencyDisplay() == CRYPTO) {
            return format(amount, currency, exchangeFlow);
        } else if (getCurrencyDisplay() == USD) {
            return formatInUsd(amount, currency);
        } else {
            return formatWithUsd(amount, currency, exchangeFlow);
        }

    }

    public static String format(double amount, Currency currency, boolean... isExchangeFlow) {

        boolean exchangeFlow = false; // It the default value you want to give
        if (isExchangeFlow.length == 1) {
            exchangeFlow = isExchangeFlow[0];  // Overrided Value
        }

        String currencyStr = "";
        if (currency.getSymbol().equals("usd")) {
            currencyStr = "";
        } else {
            currencyStr = " " + currency.getSymbol().toUpperCase();
        }
        return formatWithoutSymbol(amount, currency, exchangeFlow) + currencyStr;

    }

    public static String formatWithoutSymbol(double amount, Currency currency, boolean... isExchangeFlow) {

        boolean exchangeFlow = false; // It the default value you want to give
        boolean isICXUSDTFlow = false;
        if (isExchangeFlow.length > 1) {
            exchangeFlow = isExchangeFlow[0];  // Overrided Value
            isICXUSDTFlow = isExchangeFlow[1];
        } else if (isExchangeFlow.length == 1) {
            exchangeFlow = isExchangeFlow[0];
        }

        String amountStr = "";
        double d = 0;
        int decimalPlaces = 0;
        if (isICXUSDTFlow) {
            decimalPlaces = 4;
            d = amount / Math.pow(10, decimalPlaces);
        } else {
            d = convertToDouble(amount, currency);
            decimalPlaces = getDecimalPlaces(currency, exchangeFlow);
        }

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols separatorSymbols = new DecimalFormatSymbols(currentLocale);
        separatorSymbols.setDecimalSeparator('.');
        separatorSymbols.setGroupingSeparator(',');
        if (currency.getSymbol().equals("usd")) {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            format.setMinimumFractionDigits(decimalPlaces);
            format.setMaximumFractionDigits(decimalPlaces);
            format.setRoundingMode(RoundingMode.FLOOR);
            amountStr = format.format(d);
        } else {

            if (currency.getSymbol().equals("sports") && d < 1) {
                DecimalFormat format = new DecimalFormat();
                format.setMinimumFractionDigits(0);
                format.setMaximumFractionDigits(4);
                format.setDecimalFormatSymbols(separatorSymbols);
                amountStr = format.format(d);

            } else {
                DecimalFormat format = new DecimalFormat();
                format.setMinimumFractionDigits(decimalPlaces);
                format.setMaximumFractionDigits(decimalPlaces);
                format.setDecimalFormatSymbols(separatorSymbols);
                if (currency.getSymbol().equalsIgnoreCase("icx")) {
                    format.setRoundingMode(RoundingMode.FLOOR);
                }
                amountStr = format.format(d);

            }

        }
        return amountStr;

    }

    public static String formatWithoutFraction(double amount, Currency currency) {

        String amountStr = "";
        int decimalPlaces = 0;
        double d = convertToDouble(amount, currency);

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols separatorSymbols = new DecimalFormatSymbols(currentLocale);
        separatorSymbols.setDecimalSeparator('.');
        separatorSymbols.setGroupingSeparator(',');
        if (currency.getSymbol().equals("usd")) {
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
            format.setMinimumFractionDigits(decimalPlaces);
            format.setMaximumFractionDigits(decimalPlaces);
            format.setRoundingMode(RoundingMode.FLOOR);
            amountStr = format.format(d);
        } else {

            if (currency.getSymbol().equals("sports") && d < 1) {
                DecimalFormat format = new DecimalFormat();
                format.setMinimumFractionDigits(0);
                format.setMaximumFractionDigits(4);
                format.setDecimalFormatSymbols(separatorSymbols);
                amountStr = format.format(d) + " " + currency.getSymbol().toUpperCase();

            } else {
                DecimalFormat format = new DecimalFormat();
                format.setMinimumFractionDigits(decimalPlaces);
                format.setMaximumFractionDigits(decimalPlaces);
                format.setDecimalFormatSymbols(separatorSymbols);
                if (currency.getSymbol().equalsIgnoreCase("icx")) {
                    format.setRoundingMode(RoundingMode.FLOOR);
                }
                amountStr = format.format(d) + " " + currency.getSymbol().toUpperCase();

            }

        }
        return amountStr;

    }

    public static String formatWithUsd(double amount, Currency currency, boolean... isExchangeFlow) {

        boolean exchangeFlow = false; // It the default value you want to give
        if (isExchangeFlow.length == 1) {
            exchangeFlow = isExchangeFlow[0];  // Overrided Value
        }

        String usdStr = "";
        if (!currency.getSymbol().equals("usd")) {

            Currency usd = new Currency();
            usd.setSymbol("usd");
            usd.setRate(1);
            usd.setDecimalPlaces(2);

            double usdAmount = convert(amount, currency, usd);
            usdStr = " (" + convertCentToMoney(usdAmount) + ")";

        }

        return format(amount, currency, exchangeFlow) + usdStr;

    }

    public static String formatInUsd(double amount, Currency currency) {

        Currency usd = new Currency();
        usd.setSymbol("usd");
        usd.setRate(1);
        usd.setDecimalPlaces(2);

        double usdAmount = convert(amount, currency, usd);
        return convertCentToMoney(usdAmount);

    }

    public static int getDecimalPlaces(Currency currency, boolean... isExchangeFlow) {

        boolean exchangeFlow = false; // It the default value you want to give
        if (isExchangeFlow.length == 1) {
            exchangeFlow = isExchangeFlow[0];  // Overrided Value
        }

        if (currency.getSymbol().equalsIgnoreCase("sports")) {
            return 0;
        } else if (currency.getSymbol().equalsIgnoreCase("icx")) {
            return 2;
        } else if (currency.getSymbol().equalsIgnoreCase("usdt")) {
            if (exchangeFlow) {
                return currency.getDecimalPlaces();
            } else {
                return 4;
            }
        } else {
            return currency.getDecimalPlaces();
        }

    }

    private static double convertToDouble(double amount, Currency currency) {

        double d = amount / Math.pow(10, currency.getDecimalPlaces());
        return d;

    }

    public static String formatSports(double amount, Boolean withSymbol) {
        double sportsAmount = amount / 10000;
        DecimalFormat format = new DecimalFormat();
        if (sportsAmount < 1) {
            format.setMinimumFractionDigits(0);
            format.setMaximumFractionDigits(4);
        } else {
            format.setMinimumFractionDigits(0);
            format.setMaximumFractionDigits(0);
        }
        String amountStr = format.format(sportsAmount);
        String symbol = "";
        if (withSymbol) {
            symbol = " SPORTS";
        }
        return amountStr + symbol;
    }

    public static double getAmount(Currency currency, EditText mEditText) {

        if (mEditText == null || mEditText.getText().toString().isEmpty() || mEditText.getText().toString().equals("")) {

            return 0;

        }

        return cleanAndMultiplyAmount(currency, mEditText.getText().toString());
    }

    private static double cleanAndMultiplyAmount(Currency currency, String str) {

        if (str == null || str.isEmpty() || str.equals("")) {
            return 0;
        }

        String cleanString = str.replaceAll("[^\\d]", "");

        if (cleanString.isEmpty() || cleanString.equals("")) {
            return 0;
        }

        long l = Long.parseLong(cleanString);

        if (currency.getSymbol().equals("sports")) {
            l *= Math.pow(10, currency.getDecimalPlaces());
        }

        return l;

    }

    public static Currency getUSDCurrency() {
        Currency usd = new Currency();
        usd.setId(5);
        usd.setIsFiat(true);
        usd.setName("US Dollar");
        usd.setSymbol("usd");
        usd.setRate(1);
        usd.setDecimalPlaces(2);
        usd.setImageUrl("https://res.cloudinary.com/production/image/upload/v1576101923/Icons/Currencies/usd-toggle-logo.png");
        usd.setWalletImageUrl("https://res.cloudinary.com/production/image/upload/v1575335967/Icons/Currencies/usd-wallet-logo.png");
        usd.setDollarToTokens(1);
        usd.setDollarInTokensAsInt(100);
        return usd;
    }

    public static double convert(double amount, Currency startCurrency, Currency endCurrency) {

        if (startCurrency == null || endCurrency == null) {

            return 0;

        }

        if (startCurrency.getSymbol().equals(endCurrency.getSymbol())) {

            return amount;

        }

        double dollarAmount = ((float) amount) / Math.pow(10, startCurrency.getDecimalPlaces()) * startCurrency.getRate();
        double newAmount = ((float) dollarAmount) / endCurrency.getRate() * Math.pow(10, endCurrency.getDecimalPlaces());

        return newAmount;

    }

    public static String convertCentToMoney(long amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        return format.format(amount / 100.0);
    }

    public static String convertCentToMoney(double amount) {
        BigDecimal fd = new BigDecimal(amount / 100.0);
        BigDecimal cutted = fd.setScale(3, RoundingMode.DOWN);
        amount = cutted.doubleValue();

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setRoundingMode(RoundingMode.FLOOR);
        return format.format(amount);
    }
}
