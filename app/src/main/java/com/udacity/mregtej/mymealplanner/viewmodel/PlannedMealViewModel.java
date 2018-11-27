package com.udacity.mregtej.mymealplanner.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.udacity.mregtej.mymealplanner.application.MyMealPlanner;
import com.udacity.mregtej.mymealplanner.datamodel.PlannedMeal;
import com.udacity.mregtej.mymealplanner.repository.MyMealPlannerRepository;

import java.util.List;

public class PlannedMealViewModel extends AndroidViewModel {

    //--------------------------------------------------------------------------------|
    //                                  Params                                        |
    //--------------------------------------------------------------------------------|

    private final MediatorLiveData<List<PlannedMeal>> mObservablePlannedMeals;

    private final MyMealPlannerRepository myMealPlannerRepository;


    //--------------------------------------------------------------------------------|
    //                               Constructor                                      |
    //--------------------------------------------------------------------------------|

    public PlannedMealViewModel(Application application, MyMealPlannerRepository repository) {

        super(application);
        this.myMealPlannerRepository = repository;

        mObservablePlannedMeals = new MediatorLiveData<>();
        mObservablePlannedMeals.setValue(null);

        LiveData<List<PlannedMeal>> plannedMeals = myMealPlannerRepository.getPlannedMeals();

        // observe the changes of the products from the database and forward them
        mObservablePlannedMeals.addSource(plannedMeals, mObservablePlannedMeals::setValue);
    }


    //--------------------------------------------------------------------------------|
    //                             Factory injection                                  |
    //--------------------------------------------------------------------------------|

    /**
     * A creator is used to inject the product ID into the ViewModel
     * <p>
     * This creator is to showcase how to inject dependencies into ViewModels. It's not
     * actually necessary in this case, as the product ID can be passed in a public method.
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;
        private final MyMealPlannerRepository mRepository;

        public Factory(@NonNull Application application) {
            mApplication = application;
            mRepository = ((MyMealPlanner) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PlannedMealViewModel(mApplication, mRepository);
        }
    }

    //--------------------------------------------------------------------------------|
    //                              Database OPs                                      |
    //--------------------------------------------------------------------------------|

    public LiveData<List<PlannedMeal>> getPlannedMeals() {
        return mObservablePlannedMeals;
    }

    public void insertPlannedMeals(List<PlannedMeal> plannedMeals) {
        myMealPlannerRepository.insertPlannedMeals(plannedMeals);
    }

}
