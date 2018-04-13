package com.biologi.ugm.reptilamfibijogja.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.biologi.ugm.reptilamfibijogja.Model.GuideModel;
import com.biologi.ugm.reptilamfibijogja.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fauziw97 on 09/12/17.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.MyViewHolder> {

    private Context mContext;
    private List<GuideModel> guideList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.guide_image)
        ImageView guideImage;
        @BindView(R.id.guide_name)
        TextView guideText;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    public GuideAdapter(Context mContext, List<GuideModel> guideList) {
        this.mContext = mContext;
        this.guideList = guideList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_guide, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        GuideModel guide = guideList.get(position);
        holder.guideText.setText(guide.getGuideText());
        Glide.with(mContext).load(guide.getGuideImage()).into(holder.guideImage);
    }

    @Override
    public int getItemCount() {
        return guideList.size();
    }


}
