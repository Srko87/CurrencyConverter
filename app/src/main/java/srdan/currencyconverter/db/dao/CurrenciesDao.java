package srdan.currencyconverter.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import srdan.currencyconverter.db.entity.CurrenciesEntity;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CurrenciesDao {

    @Query("SELECT * FROM currencies WHERE currency_code = :currencyCode")
    CurrenciesEntity getCurrencyByCodeFromDb (String currencyCode);

    @Query("SELECT currency_code FROM currencies")
    List<String> getCurrencyCodes();

    @Insert(onConflict = REPLACE)
    void insert(CurrenciesEntity... currenciesEntities);
}
