package srdan.currencyconverter.db.repository;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import srdan.currencyconverter.db.AppDatabase;
import srdan.currencyconverter.db.dao.CurrenciesDao;
import srdan.currencyconverter.db.entity.CurrenciesEntity;
import srdan.currencyconverter.network.model.Currencies;
import srdan.currencyconverter.thread.DefaultExecutorSupplier;

public class CurrenciesRepository {

    private CurrenciesDao currenciesDao;

    public CurrenciesRepository(Application application) {

        AppDatabase db = AppDatabase.getDatabase(application);
        currenciesDao = db.currenciesDao();
    }

    public List<String> getAllCurrencyCodes() {
            return currenciesDao.getCurrencyCodes();
        }


    public CurrenciesEntity getCurrencyByCode(String currencyCode) {
        return currenciesDao.getCurrencyByCodeFromDb(currencyCode);
    }

    public void insertAllCurrencies(List<Currencies> currenciesList) {
        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            List<CurrenciesEntity> currenciesEntities = new ArrayList<>();

            for (Currencies currencies : currenciesList) {

                CurrenciesEntity currenciesEntity = new CurrenciesEntity();
                currenciesEntity.setSellingRate(currencies.getSellingRate());
                currenciesEntity.setMedianRate(currencies.getMedianRate());
                currenciesEntity.setUnitValue(currencies.getUnitValue());
                currenciesEntity.setCurrencyCode(currencies.getCurrencyCode());
                currenciesEntity.setBuyingRate(currencies.getBuyingRate());

                currenciesEntities.add(currenciesEntity);
            }

            currenciesDao.insert(currenciesEntities.toArray(new CurrenciesEntity[0]));
        });
    }
}
