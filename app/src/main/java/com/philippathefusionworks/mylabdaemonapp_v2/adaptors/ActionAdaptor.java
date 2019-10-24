package com.philippathefusionworks.mylabdaemonapp_v2.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philippathefusionworks.mylabdaemonapp_v2.R;
import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionAdaptor extends RecyclerView.Adapter<ActionAdaptor.ActionHolder> {

    private List<Action> actions = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public ActionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.action_item, parent, false );
        return new ActionHolder(itemView);
    }

    //Populate the holder with properties from the action
    @Override
    public void onBindViewHolder(@NonNull ActionHolder holder, int position) {
        Action currentaction = actions.get(position);
        holder.textViewName.setText(currentaction.getName());
        holder.textViewActive.setText(currentaction.getActive().toString());
    }

    @Override
    public int getItemCount() {
        //We want to return all the items in the list
        return actions.size();
    }

    //In main activity we observe the live data, and get passed a list of notes. This gets that list into the recyclerview!
    public void setActions(List<Action> actions){
        this.actions = actions;
        //Not the most efficient method but will use for now
        notifyDataSetChanged();
    }

    //This helps determine which action to delete  when the Viewmodel is swiped
    public Action getActionAt(int position){
        int yyy = 1;
        Action xxx = actions.get(yyy);
        return actions.get(yyy);
    }

    class ActionHolder extends RecyclerView.ViewHolder
    {
        private TextView textViewName;
        private TextView textViewActive;

        public ActionHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewActive = itemView.findViewById(R.id.text_view_active);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(actions.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Action action);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.listener = listener;
    }

}
