package com.philippathefusionworks.mylabdaemonapp_v2.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.philippathefusionworks.mylabdaemonapp_v2.database.MyDatabase;
import com.philippathefusionworks.mylabdaemonapp_v2.database.dao.ActionDao;
import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;

import java.util.List;

public class ActionRepository {
    private ActionDao actionDao;
    private LiveData<List<Action>> allActions;

    public ActionRepository(Application application){
        MyDatabase database = MyDatabase.getInstance(application);
        actionDao = database.actionDao();
        allActions = actionDao.getAllActions();
    }

    public void insert(Action action)
    {
        new InsertActionAsyncTask(actionDao).execute(action);
    }

    public void update(Action action)
    {
        new UpdateActionAsyncTask(actionDao).execute(action);
    }

    public void delete(Action action)
    {
        new DeleteActionAsyncTask(actionDao).execute(action);
    }

    public void deleteAllActions()
    {
        new DeleteAllActionsAsyncTask(actionDao).execute();
    }

    public LiveData<List<Action>>getAllActions()
    {
        return allActions;
    }

    private static class InsertActionAsyncTask extends AsyncTask<Action, Void, Void>
    {
        private ActionDao actionDao;
        private InsertActionAsyncTask(ActionDao actionDao)
        {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action...actions)
        {
            actionDao.insert(actions[0]);
            return null;
        }
    }


    private static class UpdateActionAsyncTask extends AsyncTask<Action, Void, Void>
    {
        private ActionDao actionDao;
        private UpdateActionAsyncTask(ActionDao actionDao)
        {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action...actions)
        {
            actionDao.update(actions[0]);
            return null;
        }
    }

    private static class DeleteActionAsyncTask extends AsyncTask<Action, Void, Void>
    {
        private ActionDao actionDao;
        private DeleteActionAsyncTask(ActionDao actionDao)
        {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Action...actions)
        {
            actionDao.delete(actions[0]);
            return null;
        }
    }

    private static class DeleteAllActionsAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private ActionDao actionDao;
        private DeleteAllActionsAsyncTask(ActionDao actionDao)
        {
            this.actionDao = actionDao;
        }

        @Override
        protected Void doInBackground(Void...voids)
        {
            actionDao.deleteAllActions();
            return null;
        }
    }
}
