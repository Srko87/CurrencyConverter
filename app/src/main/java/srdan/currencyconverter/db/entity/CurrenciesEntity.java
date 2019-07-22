package srdan.currencyconverter.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "currencies")
public class CurrenciesEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "currency_code")
    private String currencyCode;
    @ColumnInfo(name = "selling_rate")
    private String sellingRate;
    @ColumnInfo(name = "median_rate")
    private String medianRate;
    @ColumnInfo(name = "unit_value")
    private Integer unitValue;
    @ColumnInfo(name = "buying_rate")
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
