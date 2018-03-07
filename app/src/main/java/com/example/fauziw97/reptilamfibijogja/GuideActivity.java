package com.example.fauziw97.reptilamfibijogja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.fauziw97.reptilamfibijogja.Adapter.GuideAdapter;
import com.example.fauziw97.reptilamfibijogja.Model.GuideModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    private GuideAdapter adapter;
    private List<GuideModel> guideList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_guide)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        guideList = new ArrayList<>();
        adapter = new GuideAdapter(this, guideList);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Panduan");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        prepareGuide();
    }

    private void prepareGuide() {
        int[] images = new int[]{
                R.drawable.guide_snake_1, R.drawable.guide_snake_2, R.drawable.guide_snake_3, R.drawable.guide_lizard,
                R.drawable.guide_frog, R.drawable.guide_toad, R.drawable.guide_turtle, R.drawable.guide_hand};

        GuideModel guide = new GuideModel(images[0], "Panduan Ular 1");
        guideList.add(guide);

        guide = new GuideModel(images[1], "Panduan Ular 2");
        guideList.add(guide);

        guide = new GuideModel(images[2], "Panduan Ular 3");
        guideList.add(guide);

        guide = new GuideModel(images[3], "Panduan Kadal");
        guideList.add(guide);

        guide = new GuideModel(images[4], "Panduan Frog");
        guideList.add(guide);

        guide = new GuideModel(images[5], "Panduan Toad");
        guideList.add(guide);

        guide = new GuideModel(images[6], "Panduan Kura-Kura Karapas");
        guideList.add(guide);

        guide = new GuideModel(images[7], "Panduan Rumus Selaput");
        guideList.add(guide);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
