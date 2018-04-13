package com.biologi.ugm.reptilamfibijogja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.biologi.ugm.reptilamfibijogja.Model.InstitutionModel;
import com.biologi.ugm.reptilamfibijogja.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fauziw97 on 15/12/17.
 */

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.MyViewHolder> {

    private Context mContext;
    private List<InstitutionModel> instList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.institutionImage)
        ImageView instImage;
        @BindView(R.id.institutionName)
        TextView instName;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    public InstitutionAdapter(Context mContext, List<InstitutionModel> instList) {
        this.mContext = mContext;
        this.instList = instList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_thanks, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        InstitutionModel inst = instList.get(position);
        holder.instName.setText(inst.getInstName());
        Glide.with(mContext)
                .load(inst.getInstImage())
                .into(holder.instImage);
    }

    @Override
    public int getItemCount() {
        return instList.size();
    }


}
