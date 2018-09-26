package com.udacity.mregtej.mymealplanner.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.mregtej.mymealplanner.application.MyMealPlannerExecutors;
import com.udacity.mregtej.mymealplanner.datamodel.MealDay;
import com.udacity.mregtej.mymealplanner.datamodel.Menu;
import com.udacity.mregtej.mymealplanner.datamodel.MenuCategory;
import com.udacity.mregtej.mymealplanner.remotedatabase.MyMealPlannerRTDBContract;

import java.util.ArrayList;
import java.util.List;

public class MyMealPlannerRepository {

    //--------------------------------------------------------------------------------|
    //                                Constants                                       |
    //--------------------------------------------------------------------------------|

    private static final String TAG = MyMealPlannerRepository.class.getName();


    //--------------------------------------------------------------------------------|
    //                                  Params                                        |
    //--------------------------------------------------------------------------------|

    private static MyMealPlannerRepository INSTANCE;

    private FirebaseDatabase mMyMealPlannerFirebaseDatabase;

    private final MyMealPlannerExecutors mExecutors;

    //--------------------------------------------------------------------------------|
    //                  Constructor (Singleton Pattern)                               |
    //--------------------------------------------------------------------------------|

    private MyMealPlannerRepository(final MyMealPlannerExecutors executors) {
        mExecutors = executors;
        mMyMealPlannerFirebaseDatabase = FirebaseDatabase.getInstance();
        mMyMealPlannerFirebaseDatabase.setPersistenceEnabled(true);
    }

    public static MyMealPlannerRepository getInstance(
                                               final MyMealPlannerExecutors executors) {
        if (INSTANCE == null) {
            synchronized (MyMealPlannerRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MyMealPlannerRepository(executors);
                }
            }
        }
        return INSTANCE;
    }


    //--------------------------------------------------------------------------------|
    //                               Local DB Ops                                     |
    //--------------------------------------------------------------------------------|


    //--------------------------------------------------------------------------------|
    //                               Network Requests                                 |
    //--------------------------------------------------------------------------------|

    public LiveData<List<Menu>> getMenus() {
        final MutableLiveData<List<Menu>> menus = new MutableLiveData<>();
        mExecutors.mainThread().execute(new Runnable() {
           @Override
           public void run() {
               mMyMealPlannerFirebaseDatabase.getReference(
                       MyMealPlannerRTDBContract.RT_MENUS_TABLE_NAME)
                       .addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       List<Menu> data = new ArrayList<>();
                       for(DataSnapshot menu : dataSnapshot.getChildren()){
                           data.add(menu.getValue(Menu.class));
                       }
                       menus.setValue(data);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {
                       Log.w(TAG, "Failed to read value.", databaseError.toException());
                       menus.setValue(null);
                   }
               });
           }
       });
        return menus;
    }

    public LiveData<List<MenuCategory>> getMenuCategories() {
        final MutableLiveData<List<MenuCategory>> menuCategories = new MutableLiveData<>();
        mExecutors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                mMyMealPlannerFirebaseDatabase.getReference(
                        MyMealPlannerRTDBContract.RT_MENU_CATEGORIES_TABLE_NAME)
                        .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<MenuCategory> data = new ArrayList<>();
                        for(DataSnapshot menuCategory : dataSnapshot.getChildren()){
                            data.add(menuCategory.getValue(MenuCategory.class));
                        }
                        menuCategories.setValue(data);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "Failed to read value.", databaseError.toException());
                        menuCategories.setValue(null);
                    }
                });
            }
        });
        return menuCategories;
    }

    /**
     * TODO Remove it before release
     */
    public void populateMenus() {
        /*
        ArrayList<MealDay> meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 1",
                meals));
        meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 2",
                meals));
        meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 3",
                meals));
        meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 4",
                meals));
        meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 5",
                meals));
        meals = new ArrayList<MealDay>();
        meals.add(new MealDay(0, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(1, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(2, "aaa", "bbb", "ccc", "ddd"));
        meals.add(new MealDay(3, "aaa", "bbb", "ccc", "ddd"));
        mMyMealPlannerFirebaseDatabase.getReference("menus").push().setValue(new Menu(
                new MenuCategory("veggies"),
                " ",
                "Veggies Menu 6",
                meals));
                */
    }

}
