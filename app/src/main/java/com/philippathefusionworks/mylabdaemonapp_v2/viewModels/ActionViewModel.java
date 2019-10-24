package com.philippathefusionworks.mylabdaemonapp_v2.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;
import com.philippathefusionworks.mylabdaemonapp_v2.repository.ActionRepository;

import java.util.List;

/*This has AndroidViewModel as its Superclass*/

public class ActionViewModel extends AndroidViewModel {
    private ActionRepository repository;
    private LiveData<List<Action>> allActions;

    public ActionViewModel(@NonNull Application application) {
        super(application);
        repository = new ActionRepository(application);
        allActions = repository.getAllActions();
    }

    public void insert(Action action){
        repository.insert(action);
    }

    public void delete(Action action){
        repository.delete(action);
    }

    public void update(Action action){
        repository.update(action);
    }

    public void deleteAllActions(){
        repository.deleteAllActions();
    }

    public LiveData<List<Action>> getAllActions(){
        return allActions;
    }
}
