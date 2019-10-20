package com.philippathefusionworks.mylabdaemonapp_v2.models;
import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActionList {

    @SerializedName("actions")
    @Expose
    private ArrayList<Action> actions = null;

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

}
