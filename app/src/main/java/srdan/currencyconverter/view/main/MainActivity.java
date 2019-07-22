package srdan.currencyconverter.view.main;

import android.currencyconverter.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import srdan.currencyconverter.db.entity.CurrenciesEntity;
import srdan.currencyconverter.thread.DefaultExecutorSupplier;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.currency_from_spinner)
    Spinner currencyFromSpinner;

    @BindView(R.id.currency_to_spinner)
    Spinner currencyToSpinner;

    @BindView(R.id.et_currency_from)
    EditText editTextCurrencyFrom;

    @BindView(R.id.tv_result)
    TextView textViewResult;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.fetchCurrencies();

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            currencyFromSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mainViewModel.getCurrencyCodes()));
            currencyToSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mainViewModel.getCurrencyCodes()));

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
                    CurrenciesEntity fromCurrency = mainViewModel.getCurrencyByCode(currencyFromSpinner.getSelectedItem().toString());
                    CurrenciesEntity toCurrency = mainViewModel.getCurrencyByCode(currencyToSpinner.getSelectedItem().toString());
                    float convertToHRK = Float.parseFloat(fromCurrency.getSellingRate()) * Float.parseFloat(editTextCurrencyFrom.getText().toString());
                    float convertToCurrency = convertToHRK / Float.parseFloat(toCurrency.getBuyingRate());
                    textViewResult.setText(String.valueOf(convertToCurrency));
                });

            }
        });
    }


}
