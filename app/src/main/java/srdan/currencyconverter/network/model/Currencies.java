package srdan.currencyconverter.network.model;

import com.google.gson.annotations.SerializedName;

public class Currencies {
    @SerializedName("selling_rate")
    private String sellingRate;
    @SerializedName("median_rate")
    private String medianRate;
    @SerializedName("unit_value")
    private Integer unitValue;
    @SerializedName("currency_code")
    private String currencyCode;
    @SerializedName("buying_rate")
    private String buyingRate;

    public String getSellingRate() {
        return sellingRate;
    }

    public void setSellingRate(String sellingRate) {
        this.sellingRate = sellingRate;
    }

    public String getMedianRate() {
        return medianRate;
    }

    public void setMedianRate(String medianRate) {
        this.medianRate = medianRate;
    }

    public Integer getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Integer unitValue) {
        this.unitValue = unitValue;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getBuyingRate() {
        return buyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        this.buyingRate = buyingRate;
    }
}
