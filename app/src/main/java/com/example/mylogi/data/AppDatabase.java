package com.example.mylogi.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//import com.example.mylogi.data.dao.DriversAndTrucksDao;
import com.example.mylogi.data.converters.Converters;
import com.example.mylogi.data.dao.DriversDao;
import com.example.mylogi.data.dao.LocationsDAO;
import com.example.mylogi.data.dao.TrailersDao;
import com.example.mylogi.data.dao.TrucksDao;
import com.example.mylogi.data.entities.DriverEntity;
import com.example.mylogi.data.entities.LocationsEntity;
import com.example.mylogi.data.entities.TrailerEntity;
import com.example.mylogi.data.entities.TruckEntity;
//import com.example.mylogi.data.entities.TruckEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {DriverEntity.class, TruckEntity.class, TrailerEntity.class,
        LocationsEntity.class}, version = 13,
        exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `truck_info` " +
                    "(`truck_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `truck_brand` TEXT)");

        }
    };
    static final Migration Migration_2_3 = new Migration(2, 3) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'drivers_info' ADD COLUMN `driven_truck` INTEGER");
        }
    };
    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE IF NOT EXISTS `drivers_info1` (`id` INTEGER " +
                    "PRIMARY KEY AUTOINCREMENT NOT NULL," + " `first_name` TEXT, `middle_name` TEXT, " +
                    "`last_name` TEXT, `pid` TEXT," +
                    " `city` TEXT, `address` TEXT, `phone_number` TEXT, `email` TEXT," +
                    " `username` TEXT," + " `password` TEXT, `driven_truck` INTEGER, " +
                    "FOREIGN KEY(`driven_truck`) REFERENCES `truck_info`(`truck_id`)" +
                    " ON UPDATE NO ACTION ON DELETE NO ACTION )");

            database.execSQL("DROP TABLE 'drivers_info'");


        }
    };
    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `trailer_info`(`trailer_id` INTEGER " +
                    "PRIMARY KEY AUTOINCREMENT NOT NULL , `trailer_brand` TEXT)");

            database.execSQL("CREATE TABLE IF NOT EXISTS `truck_info1` (`truck_id` INTEGER " +
                    "PRIMARY KEY AUTOINCREMENT NOT NULL, `truck_brand` TEXT, `attached_trailer` INTEGER, " +
                    "FOREIGN KEY(`attached_trailer`) REFERENCES `trailer_info`(`trailer_id`)" +
                    " ON UPDATE NO ACTION ON DELETE NO ACTION )");

            database.execSQL("DROP TABLE 'truck_info'");

            database.execSQL("ALTER TABLE 'truck_info1' RENAME TO 'truck_info'");


        }
    };
    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE INDEX IF NOT EXISTS 'index_drivers_info_driven_truck' on 'drivers_info'('driven_truck')");
            database.execSQL("CREATE INDEX IF NOT EXISTS 'index_truck_info_attached_trailer' on 'truck_info'('attached_trailer')");
        }
    };
    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `locations`(`location_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `location_latitude` TEXT, `location_longitude` TEXT," +
                    "`driver_id` INTEGER, FOREIGN KEY (`driver_id`) REFERENCES `drivers_info`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION)");

            database.execSQL("CREATE INDEX IF NOT EXISTS 'index_locations_driver_id' on 'locations'('driver_id')");
        }
    };
    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `locations`(`location_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `location_latitude` TEXT, `location_longitude` TEXT," +
                    "`driver_id` INTEGER, FOREIGN KEY (`driver_id`) REFERENCES `drivers_info`(`id`) ON UPDATE NO ACTION ON DELETE NO ACTION)");

            database.execSQL("CREATE INDEX IF NOT EXISTS 'index_locations_driver_id' on 'locations'('driver_id')");
        }
    };
    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `truck_info` ADD COLUMN `registration_number` TEXT");
        }
    };
    static final Migration MIGRATION_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `locations` ADD COLUMN `location_datetime` INTEGER");
        }
    };
    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `drivers_info` ADD COLUMN `image_path` TEXT");
        }
    };
    static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `truck_info` ADD COLUMN `truck_model` TEXT");
        }
    };
    static final Migration Migration_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE `truck_info` ADD COLUMN `truck_mileage` TEXT");
        }
    };
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile AppDatabase INSTANCE = null;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database_name").addMigrations(MIGRATION_1_2,
                            Migration_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7,
                            MIGRATION_7_8, MIGRATION_8_9, MIGRATION_9_10, MIGRATION_10_11,
                            MIGRATION_11_12, Migration_12_13)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract DriversDao driversDao();

    public abstract TrucksDao trucksDao();

    public abstract LocationsDAO locationsDAO();

    public abstract TrailersDao trailersDao();
}
