package com.biologi.ugm.reptilamfibijogja.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.biologi.ugm.reptilamfibijogja.Model.TeamModel;
import com.biologi.ugm.reptilamfibijogja.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Fauziw97 on 15/12/17.
 */

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private FirebaseAuth firebaseAuth;
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    TeamModel team;
    private List<TeamModel> teamModels;
    private Context context;

    public TeamAdapter(List<TeamModel> teamModels, Context context) {
        this.teamModels = teamModels;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.teamImage)
        CircleImageView teamImage;
        @BindView(R.id.teamName)
        TextView teamName;
        @BindView(R.id.teamRole)
        TextView teamRole;
        @BindView(R.id.teamMail)
        LinearLayout teamMail;
        @BindView(R.id.teamFb)
        LinearLayout teamFb;
        View root;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            root = view;
        }

    }

    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_team, parent, false);
        ButterKnife.bind(this, view);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamAdapter.ViewHolder holder, int position) {
        holder.root.setTag(teamModels.get(position));
        team = teamModels.get(position);
        Glide.with(context)
                .load(team.getTeamImage())
                .into(holder.teamImage);
        holder.teamName.setText(team.getTeamName());
        holder.teamRole.setText(team.getTeamRole());
        final String email = team.getTeamMail();
        final String fb = team.getTeamFb();
        holder.teamMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMail = new Intent(Intent.ACTION_SENDTO);
                intentMail.setData(Uri.parse("mailto:" + email));
                context.startActivity(intentMail);
            }
        });
        holder.teamFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFb = new Intent(Intent.ACTION_VIEW);
                intentFb.setData(Uri.parse(fb));
                intentFb.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentFb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teamModels.size();
    }


}
