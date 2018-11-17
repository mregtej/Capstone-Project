package com.udacity.mregtej.mymealplanner.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.udacity.mregtej.mymealplanner.application.MyMealPlannerExecutors;
import com.udacity.mregtej.mymealplanner.datamodel.PlannedMeal;
import com.udacity.mregtej.mymealplanner.datamodel.Recipe;

import java.util.List;

@Database(entities = {Recipe.class, PlannedMeal.class}, version = 3, exportSchema = false)
public abstract class MyMealPlannerDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "recipe-database";

    public abstract PlannedRecipeDao plannedRecipeDao();
    public abstract PlannedMealDao plannedMealDao();
    private static MyMealPlannerExecutors mExecutors;
    private static MyMealPlannerDatabase INSTANCE;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static MyMealPlannerDatabase getInstance(final Context context,
                                                    final MyMealPlannerExecutors executors) {
        if (INSTANCE == null) {
            synchronized (MyMealPlannerDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = buildDatabase(context.getApplicationContext(), executors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Build the database. {@link Builder#build()} only sets up the database configuration and
     * creates a new instance of the database.
     * The SQLite database is only created when it's accessed for the first time.
     */
    private static MyMealPlannerDatabase buildDatabase(final Context appContext,
                                                       final MyMealPlannerExecutors executors) {
        mExecutors = executors;
        return Room.databaseBuilder(appContext, MyMealPlannerDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                // Add Delay (simulate long-time task
                                // addDelay();
                                // Generate the data for pre-population
                                MyMealPlannerDatabase database = MyMealPlannerDatabase.
                                        getInstance(appContext, executors);
                                // notify that the database was created and it's ready to be used
                                database.setDatabaseCreated();
                            }
                        });
                    }
                })
                .fallbackToDestructiveMigration()
                .build();
    }

    /**
     * Check whether the database already exists and expose it via {@link #getDatabaseCreated()}
     */
    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private static void insertData(final MyMealPlannerDatabase database,
                                   final List<Recipe> recipeList) {
        database.runInTransaction(new Runnable() {
            @Override
            public void run() {
                database.plannedRecipeDao().insertPlannedRecipes(recipeList);
            }
        });
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

}
