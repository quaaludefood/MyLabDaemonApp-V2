package com.philippathefusionworks.mylabdaemonapp_v2.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.philippathefusionworks.mylabdaemonapp_v2.R;
import com.philippathefusionworks.mylabdaemonapp_v2.database.entity.Action;

import java.util.ArrayList;
import java.util.List;

public class ActionAdaptor extends ListAdapter<Action, ActionAdaptor.ActionHolder> {

    private OnItemClickListener listener;

    public ActionAdaptor() {
            super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Action> DIFF_CALLBACK = new DiffUtil.ItemCallback<Action>() {
        @Override
        public boolean areItemsTheSame(@NonNull Action oldItem, @NonNull Action newItem) {

            return oldItem.getIdentifier() == newItem.getIdentifier();
        }
        //If it returns false the adaptor will know it needs to update the item
        @Override
        public boolean areContentsTheSame(@NonNull Action oldItem, @NonNull Action newItem) {
            return oldItem.getIdentifier() == newItem.getIdentifier() &&
           oldItem.getName().equals(newItem.getName()) &&
            oldItem.getActive().equals(newItem.getActive());
        }
    };

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
        Action currentaction = getItem(position);
        holder.textViewName.setText(currentaction.getName());
        holder.textViewActive.setText(currentaction.getActive().toString());
    }



    //This helps determine which action to delete  when the Viewmodel is swiped
    public Action getActionAt(int position){
        return getItem(position);
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
                    listener.onItemClick(getItem(position));
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
