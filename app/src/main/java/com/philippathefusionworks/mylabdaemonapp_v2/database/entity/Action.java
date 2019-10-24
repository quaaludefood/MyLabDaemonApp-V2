package com.philippathefusionworks.mylabdaemonapp_v2.database.entity;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.UUID;

@Entity(tableName = "action_table")
public class Action {

    // Properties

    @PrimaryKey(autoGenerate = true)
    private int identifier;

    private boolean active;
    private String name;

    // Constructors

    public Action(boolean active, String name) {

        this.active = active;
        this.name = name;
    }

    // Methods

    public void setIdentifier(int identifier) {

        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }
    public Boolean getActive() {
        return active;
    }
    public String getName() {
        return name;
    }
}
