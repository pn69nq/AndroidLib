package com.pq.test.network;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class NetWorkAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<NetWorkTestModel> mDatas;

    public NetWorkAdapter(List<NetWorkTestModel> datas){
        mDatas = datas;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {





    }

    @Override
    public int getItemCount() {
        if(mDatas == null){
            return 0;
        }
        return mDatas.size();
    }
}
