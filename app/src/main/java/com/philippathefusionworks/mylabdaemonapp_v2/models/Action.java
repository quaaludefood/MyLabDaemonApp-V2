package com.philippathefusionworks.mylabdaemonapp_v2.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

import java.util.UUID;

public class Action {

    @SerializedName("Identifier")
    @Expose
    private UUID Identifier;
    @SerializedName("Active")
    @Expose
    private boolean Active;
    @SerializedName("Name")
    @Expose
    private String Name;

    public UUID getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(UUID identifier) {
        Identifier = identifier;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
