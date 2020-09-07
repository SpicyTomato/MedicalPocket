package com.spicytomato.medicalpocket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.spicytomato.medicalpocket.DetailActivity;
import com.spicytomato.medicalpocket.MainActivity;
import com.spicytomato.medicalpocket.MyViewModel;
import com.spicytomato.medicalpocket.R;
import com.spicytomato.medicalpocket.adapters.AllDataListAdapter;
import com.spicytomato.medicalpocket.base.BaseFragment;
import com.spicytomato.medicalpocket.content.Content;
import com.spicytomato.medicalpocket.database.Record;

import java.util.List;

public class Fragment_1 extends BaseFragment implements MainActivity.OnSearch {

    private View mRootView;
    private RecyclerView mRecyclerView;
    private MyViewModel mMyViewModel;
    private LiveData<List<Record>> allRecords;
    private LiveData<List<Record>> filterRecords;

    private List<Record> mRecordList;
    private Boolean mIsUndo;
    private AllDataListAdapter mAllDataListAdapter;

    @Override
    protected View onSubViewLoaded(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_1, container, false);

        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();
        mMyViewModel = ViewModelProviders.of(activity).get(MyViewModel.class);

        mRecyclerView = mRootView.findViewById(R.id.recyclerView_1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAllDataListAdapter = new AllDataListAdapter();

        MainActivity mainActivity = new MainActivity();
        mainActivity.setOnSearch(this);

        mAllDataListAdapter.SetOnItemClickListener(new AllDataListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position, Record item) {
                moveToDetailPage(position,item);
            }
        });
        mRecyclerView.setAdapter(mAllDataListAdapter);

        allRecords = mMyViewModel.getAllRecords();

        mRecyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
                super.onAnimationFinished(viewHolder);
                if (!mIsUndo) {
                    mRecyclerView.smoothScrollBy(0, -200);
                }
            }
        });

        allRecords.observe(getViewLifecycleOwner(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                int temp = mAllDataListAdapter.getItemCount();
                mRecordList = records;

                if (temp != records.size()) {
                    if (temp < records.size()) {
                        mIsUndo = false;
                    } else {
                        mIsUndo = true;
                    }
                }

                mAllDataListAdapter.submitList(records);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final Record record = mRecordList.get(viewHolder.getAdapterPosition());
                mMyViewModel.delete(record);
                Snackbar.make(requireView(), "我还不想走", Snackbar.LENGTH_SHORT)
                        .setAction("回来吧", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mIsUndo = true;
                                mMyViewModel.insert(record);
                            }
                        })
                        .show();
            }
        }).attachToRecyclerView(mRecyclerView);




    }

    private void moveToDetailPage(int position, Record item) {
        Intent intent = new Intent(requireContext(), DetailActivity.class);
//        Log.d("TAG", "moveToDetailPage: " + item.getDoctorName());
//
//        int i = 0;
//        for (Record record : mMyViewModel.getAllRecords().getValue()) {
//
//            if (record == item){
//                Log.d("TAG", i + "");
//            }
//            i++;
//        }
//
//        Log.d("TAG", "" + mMyViewModel.getAllRecords().getValue().get(position).getDoctorName());
        intent.putExtra(Content.DETAIL_PAGE,item);
        startActivity(intent);
    }


    @Override
    public void onSearch(String newText) {
        String pattern = newText.trim();

        filterRecords.removeObservers(getViewLifecycleOwner());
        filterRecords = mMyViewModel.getPatternRecord(pattern);
        filterRecords.observe(getViewLifecycleOwner(), new Observer<List<Record>>() {

            private List<Record> mNowRecords;

            @Override
            public void onChanged(List<Record> records) {
                int temp = mAllDataListAdapter.getItemCount();
                mNowRecords = records;
                if (temp != records.size()){
                    mAllDataListAdapter.submitList(records);;
                }
            }
        });
    }
}
