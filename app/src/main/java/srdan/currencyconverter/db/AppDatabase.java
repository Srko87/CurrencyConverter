package srdan.currencyconverter.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import srdan.currencyconverter.db.dao.CurrenciesDao;
import srdan.currencyconverter.db.entity.CurrenciesEntity;

@Database(entities = CurrenciesEntity.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "currencies_database";
    private static volatile AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    public abstract CurrenciesDao currenciesDao();
}
