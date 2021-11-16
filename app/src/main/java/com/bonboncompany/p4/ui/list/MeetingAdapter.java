package com.bonboncompany.p4.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bonboncompany.p4.R;
import com.bonboncompany.p4.data.model.Meeting;

import java.util.List;

public class MeetingAdapter extends ListAdapter<MeetingViewStateItem, MeetingAdapter.ViewHolder> {

    private final OnMeetingClickedListener listener;

    public MeetingAdapter(List<Meeting> items , OnMeetingClickedListener listener) {
        super(new listMeetingItemCallback());
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.meeting_item, parent, false)
        );
    }
    //Création d'une nouvelle ligne

    @Override
    public void onBindViewHolder(MeetingAdapter.ViewHolder holder, int position) {
    holder.bind(getItem(position),listener);
    }
    //Affichage de la ligne

    @Override
    public int getItemCount() {
        return 20;
    }
    //Combien d'item dans le recyclerview

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView info_meeting;
        public TextView mail_participant;
        public ImageView room_avatar;
        public ImageButton delete;


        public ViewHolder(View itemView) {
            super(itemView);
            info_meeting = itemView.findViewById(R.id.item_list_info);
            mail_participant = itemView.findViewById(R.id.item_list_mail_participant);
            room_avatar = itemView.findViewById(R.id.item_list_room_avatar);
            delete = itemView.findViewById(R.id.item_list_delete_button);
        }

        public void bind(MeetingViewStateItem item, OnMeetingClickedListener listener) {
            itemView.setOnClickListener(v -> listener.onMeetingClicked(item.getId()));
            info_meeting.setText(item.getTopic()+" - "+ item.getTime() + " - " + item.getRoom());
            mail_participant.setText(item.getParticipantMail());
           // deleteImageView.setOnClickListener(v -> listener.onDeleteNeighbourClicked(item.getId()));
        }

    }

    private static class listMeetingItemCallback extends DiffUtil.ItemCallback<MeetingViewStateItem> {

        @Override
        public boolean areItemsTheSame(@NonNull MeetingViewStateItem oldItem, @NonNull MeetingViewStateItem newItem) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MeetingViewStateItem oldItem, @NonNull MeetingViewStateItem newItem) {
            return false;
        }
    }
}
