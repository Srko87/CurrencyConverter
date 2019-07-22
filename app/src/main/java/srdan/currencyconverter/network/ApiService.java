package srdan.currencyconverter.network;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import srdan.currencyconverter.network.model.Currencies;

public interface ApiService {

    @GET("daily/")
    Single<List<Currencies>> getCurrencies();
}
