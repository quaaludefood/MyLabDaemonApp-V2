package com.philippathefusionworks.mylabdaemonapp_v2.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;

import java.util.List;

@Dao
public interface ActionDao {

    @Insert
    void insert(Action action);

    @Update
    void update(Action action);

    @Delete
    void delete(Action action);

    @Query("DELETE FROM action_table")
    void deleteAllActions();

    @Query("SELECT * FROM action_table")
    LiveData<List<Action>> getAllActions();

}
