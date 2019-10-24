package com.philippathefusionworks.mylabdaemonapp_v2.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.philippathefusionworks.mylabdaemonapp_v2.database.dao.ActionDao;
import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;

import java.util.UUID;

@Database(entities = {Action.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    /*declare it here so we only ever use ne instance of the DB*/
    private static MyDatabase instance;

    public abstract ActionDao actionDao();
    /*synchronised so even if multiple threads access it only one instance is created*/
    public static synchronized MyDatabase getInstance(Context context){
        if(instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "mylabdaemon_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ActionDao actionDao;

        private PopulateDbAsyncTask(MyDatabase db)
        {
            actionDao = db.actionDao();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            actionDao.insert(new Action(true, "This is a new action!"));
            actionDao.insert(new Action(true, "This is another new action..."));
            actionDao.insert(new Action( true, "This is yet another new action!"));
            return null;
        }
    }
}