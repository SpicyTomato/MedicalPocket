package com.spicytomato.medicalpocket.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.spicytomato.medicalpocket.R;
import com.spicytomato.medicalpocket.database.Record;

public class AllDataListAdapter extends ListAdapter<Record,AllDataListAdapter.InnerHolder> {
    private OnItemClickListener onItemClickListener;

    public AllDataListAdapter(){
        super(new DiffUtil.ItemCallback<Record>() {
            @Override
            public boolean areItemsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Record oldItem, @NonNull Record newItem) {
                    return (oldItem.getThisSickName().equals(newItem.getThisSickName()) &&
                            oldItem.getBody_check().equals(newItem.getBody_check()) &&
                            oldItem.getSuggestion().equals(newItem.getSuggestion()) &&
                            oldItem.getTime().equals(newItem.getTime()) && oldItem.getId() == newItem.getId());
            }
        });

    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.recyclerview_1_cell,parent,false);

        InnerHolder holder = new InnerHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, final int position) {
        Record record = getItem(position);
        holder.itemView.setTag(R.id.record_view_holder,record);

        holder.mTime.setText(record.getTime());
        holder.mSickName.setText(record.getThisSickName());
        holder.mDoctorName.setText(record.getDoctorName());

        holder.mEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    Log.d("TAG", "onClick:  position ----- >" + position);
                    onItemClickListener.OnItemClick(position,getItem(position));
                }
            }
        });

    }

    static class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView mTime;
        private final TextView mSickName;
        private final TextView mDoctorName;
        private final LinearLayout mEntry;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTime = itemView.findViewById(R.id.time);
            mSickName = itemView.findViewById(R.id.sick_name);
            mDoctorName = itemView.findViewById(R.id.doctor_name);
            mEntry = itemView.findViewById(R.id.entry);


        }
    }

    public void SetOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener{
        void OnItemClick(int position, Record item);
    }
}
