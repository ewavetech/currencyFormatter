package com.keystarcorp.currencyformatter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class Currency {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("is_fiat")
    @Expose
    private boolean isFiat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("wallet_image_url")
    @Expose
    private String walletImageUrl;
    @SerializedName("decimal_places")
    @Expose
    private int decimalPlaces;
    @SerializedName("rate")
    @Expose
    private double rate;
    @SerializedName("dollar_to_tokens")
    @Expose
    private double dollarToTokens;
    @SerializedName("dollar_in_tokens_as_int")
    @Expose
    private double dollarInTokensAsInt;
    @SerializedName("last_updated")
    @Expose
    private String lastUpdated;
    @SerializedName("tradable_balance")
    @Expose
    private double tradableBalance;

    @SerializedName("balance")
    @Expose
    private double balance;

    @SerializedName("withdrawal_fee")
    @Expose
    private double withdrawalFee;

    public Currency() {

    }

    public static Currency fromJSON(JSONObject obj) throws JSONException {

        Currency currency = new Currency();

        currency.setId(obj.optInt("id"));
        currency.setSymbol(obj.optString("symbol"));
        currency.setName(obj.optString("name"));
        currency.setImageUrl(obj.optString("image_url"));
        currency.setWalletImageUrl(obj.optString("wallet_image_url"));
        currency.setIsFiat(obj.optBoolean("is_fiat"));
        currency.setDecimalPlaces(obj.optInt("decimal_places"));
        currency.setRate(obj.optDouble("rate"));
        currency.setDollarToTokens(obj.optDouble("dollar_to_tokens"));
        currency.setDollarInTokensAsInt(obj.optDouble("dollar_in_tokens_as_int"));
        currency.setLastUpdated(obj.optString("last_updated"));
        currency.setTradableBalance(obj.optDouble("tradable_balance"));
        currency.setBalance(obj.optDouble("balance"));
        currency.setWithdrawalFee(obj.optDouble("withdrawal_fee"));

        return currency;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean getIsFiat() {
        return isFiat;
    }

    public void setIsFiat(boolean isFiat) {
        this.isFiat = isFiat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getDollarToTokens() {
        return dollarToTokens;
    }

    public void setDollarToTokens(double dollarToTokens) {
        this.dollarToTokens = dollarToTokens;
    }

    public double getDollarInTokensAsInt() {
        return dollarInTokensAsInt;
    }

    public void setDollarInTokensAsInt(double dollarInTokensAsInt) {
        this.dollarInTokensAsInt = dollarInTokensAsInt;
    }

    public String getWalletImageUrl() {
        return walletImageUrl;
    }

    public void setWalletImageUrl(String walletImageUrl) {
        this.walletImageUrl = walletImageUrl;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getTradableBalance() {
        return tradableBalance;
    }

    public void setTradableBalance(double tradableBalance) {
        this.tradableBalance = tradableBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(double withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    /**
     * Returns all the available property values in the form of JSONObject instance where the key is the approperiate json key and the value is the value of the corresponding field
     */
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("decimal_places", decimalPlaces);
            jsonObject.put("dollar_in_tokens_as_int", dollarInTokensAsInt);
            jsonObject.put("dollar_to_tokens", dollarToTokens);
            jsonObject.put("id", id);
            jsonObject.put("image_url", imageUrl);
            jsonObject.put("is_fiat", isFiat);
            jsonObject.put("last_updated", lastUpdated);
            jsonObject.put("name", name);
            jsonObject.put("rate", rate);
            jsonObject.put("symbol", symbol);
            jsonObject.put("tradable_balance", tradableBalance);
            jsonObject.put("balance", balance);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Instantiate the instance using the passed jsonObject to set the properties values
     */
    public Currency(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }
        decimalPlaces = jsonObject.optInt("decimal_places");
        dollarInTokensAsInt = jsonObject.optDouble("dollar_in_tokens_as_int");
        dollarToTokens = jsonObject.optDouble("dollar_to_tokens");
        id = jsonObject.optInt("id");
        imageUrl = jsonObject.optString("image_url");
        isFiat = jsonObject.optBoolean("is_fiat");
        lastUpdated = jsonObject.optString("last_updated");
        name = jsonObject.optString("name");
        rate = jsonObject.optDouble("rate");
        symbol = jsonObject.optString("symbol");
        walletImageUrl = jsonObject.optString("wallet_image_url");
        tradableBalance = jsonObject.optDouble("tradable_balance");
        balance = jsonObject.optDouble("balance");
    }

}