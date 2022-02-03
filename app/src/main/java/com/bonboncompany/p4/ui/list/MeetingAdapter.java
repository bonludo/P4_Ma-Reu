package com.bonboncompany.p4.ui.list;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bonboncompany.p4.R;

public class MeetingAdapter extends ListAdapter<MeetingViewStateItem, MeetingAdapter.ViewHolder> {

    private final OnMeetingClickedListener listener;

    public MeetingAdapter(OnMeetingClickedListener listener) {
        super(new ListMeetingItemCallback());
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
        holder.bind(getItem(position), listener);
    }
    //Affichage de la ligne

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView info_meetingTextView;
        public TextView mail_participantTextView;
        public ImageView room_avatarImageView;
        public ImageButton deleteImageView;


        public ViewHolder(View itemView) {
            super(itemView);
            info_meetingTextView = itemView.findViewById(R.id.item_list_info);
            mail_participantTextView = itemView.findViewById(R.id.item_list_mail_participant);
            room_avatarImageView = itemView.findViewById(R.id.item_list_room_avatar);
            deleteImageView = itemView.findViewById(R.id.item_list_delete_button);
        }

        public void bind(MeetingViewStateItem item, OnMeetingClickedListener listener) {
            itemView.setOnClickListener(v -> listener.onMeetingClicked(item.getId()));
            info_meetingTextView.setText(item.getMeetingTopic());
            mail_participantTextView.setText(item.getParticipants());
            room_avatarImageView.setImageTintList(
                    ColorStateList.valueOf(
                            ContextCompat.getColor(itemView.getContext(), item.getImageColorRes())
                    )
            );
            deleteImageView.setOnClickListener(v -> listener.onDeleteMeetingClicked(item.getId()));
        }

    }

    private static class ListMeetingItemCallback extends DiffUtil.ItemCallback<MeetingViewStateItem> {
        @Override
        public boolean areItemsTheSame(@NonNull MeetingViewStateItem oldItem, @NonNull MeetingViewStateItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MeetingViewStateItem oldItem, @NonNull MeetingViewStateItem newItem) {
            return oldItem.equals(newItem);
        }
    }
}
