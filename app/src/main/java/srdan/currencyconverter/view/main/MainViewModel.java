package srdan.currencyconverter.view.main;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import srdan.currencyconverter.baseClasses.BaseViewModel;
import srdan.currencyconverter.db.entity.CurrenciesEntity;
import srdan.currencyconverter.db.repository.CurrenciesRepository;
import srdan.currencyconverter.network.ApiClient;
import srdan.currencyconverter.network.ApiService;
import srdan.currencyconverter.network.model.Currencies;

public class MainViewModel extends BaseViewModel {

    private ApiService apiService;
    private CurrenciesRepository currenciesRepository;
    private CompositeDisposable disposables = new CompositeDisposable();


    public MainViewModel(@NonNull Application application) {
        super(application);
        apiService = ApiClient.getClient().create(ApiService.class);
        currenciesRepository = new CurrenciesRepository(application);
    }

    private void insertCurrencies(List<Currencies> currenciesList) {
        currenciesRepository.insertAllCurrencies(currenciesList);
    }

    void fetchCurrencies() {
        disposables.add(apiService.getCurrencies()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(response -> {
            insertCurrencies(response);
        }, error -> {
            Toast.makeText(getApplication(),"Not able to fetch currencies!",Toast.LENGTH_SHORT).show();
        }));
    }

    public List<String> getCurrencyCodes() {
        return currenciesRepository.getAllCurrencyCodes();
    }

    public CurrenciesEntity getCurrencyByCode (String currencyCode) {
        return currenciesRepository.getCurrencyByCode(currencyCode);
    }




    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }

}
