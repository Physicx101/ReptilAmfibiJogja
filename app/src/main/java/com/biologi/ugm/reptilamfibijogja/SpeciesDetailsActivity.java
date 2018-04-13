package com.biologi.ugm.reptilamfibijogja;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bogi on 25-Sep-17.
 */

public class SpeciesDetailsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    FirebaseStorage mStorage = FirebaseStorage.getInstance();
    StorageReference mStorageRef = mStorage.getReferenceFromUrl("gs://taxapp-c61c1.appspot.com/FotoAmfirep/");
    StorageReference refDorsal, refLateral, refOveral, refVentral;


    String name, speciesName;
    String circle = "\u25CF";
    int i = 0;


    @BindView(R.id.speciesSlider)
    SliderLayout speciesSlider;
    @BindView(R.id.tvNama)
    TextView tvNama;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvKingdom)
    TextView tvKingdom;
    @BindView(R.id.tvPhylum)
    TextView tvPhylum;
    @BindView(R.id.tvClass)
    TextView tvClass;
    @BindView(R.id.tvOrder)
    TextView tvOrder;
    @BindView(R.id.tvFamily)
    TextView tvFamily;
    @BindView(R.id.tvGenus)
    TextView tvGenus;
    @BindView(R.id.tvIsiDeskripsi)
    TextView tvIsiDeskripsi;
    @BindView(R.id.tvIsiHabitat)
    TextView tvIsiHabitat;
    @BindView(R.id.tvIsiDistribute)
    TextView tvIsiDistribute;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    HashMap<String, String> image_url = new HashMap<String, String>();
    int position;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_details);
        ButterKnife.bind(this);

        //make full transparent statusBar
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("speciesName");
            speciesName = name.replaceAll("[^A-Za-z]+", "");
        }


        getSupportActionBar().setDisplayShowTitleEnabled(false);


        refDorsal = mStorageRef.child(speciesName + "D.png");
        refOveral = mStorageRef.child(speciesName + "O.png");
        refLateral = mStorageRef.child(speciesName + "L.png");
        refVentral = mStorageRef.child(speciesName + "V.png");

        getDownloadUrl();



        /*HashMap<String, String> image_url = new HashMap<String, String>();
        image_url.put("Overal", refOveral.toString());
        image_url.put("Dorsal", refDorsal.toString());
        image_url.put("Lateral", refLateral.toString());*/


    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void slider() {

        for (String species : image_url.keySet()) {
            DefaultSliderView defaultSliderView = new DefaultSliderView(this);
            defaultSliderView
                    .description(species)
                    .image(image_url.get(species))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra", species);

            speciesSlider.addSlider(defaultSliderView);
        }
        speciesSlider.stopAutoCycle();
        speciesSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        speciesSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //speciesSlider.setCustomAnimation(new DescriptionAnimation());
        //speciesSlider.setDuration(4000);
        speciesSlider.addOnPageChangeListener(this);
    }

    private void getDownloadUrl() {
        i = 0;
        refVentral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriVentral = uri;
                i++;
                image_url.put("Ventral", uriVentral.toString());

                if (i == 4) {
                    slider();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                i++;
                if (i == 4) {
                    slider();
                }
            }
        });

        refDorsal.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriDorsal = uri;
                i++;
                image_url.put("Dorsal", uriDorsal.toString());
                if (i == 4) {
                    slider();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                i++;
                e.printStackTrace();
                if (i == 4) {
                    slider();
                }
            }
        });

        refLateral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriLateral = uri;
                i++;
                image_url.put("Lateral", uriLateral.toString());
                if (i == 4) {
                    slider();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                i++;
                e.printStackTrace();
                if (i == 4) {
                    slider();
                }
            }
        });
        refOveral.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Uri uriOveral = uri;
                i++;
                image_url.put("Overal", uriOveral.toString());
                if (i == 4) {
                    slider();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                i++;
                e.printStackTrace();
                if (i == 4) {
                    slider();
                }
            }
        });
    }


    @Override
    protected void onStop() {
        speciesSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference ref = mRef.child("Amfirep").child(speciesName);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tvNama.setText(dataSnapshot.child("NamaSpesies").getValue().toString());
                tvStatus.setText(dataSnapshot.child("StatusKonservasi").getValue().toString());
                tvKingdom.setText(circle + " " + "Kingdom: " + dataSnapshot.child("Kingdom").getValue().toString());
                tvPhylum.setText(circle + " " + "Phylum: " + dataSnapshot.child("Phylum").getValue().toString());
                tvClass.setText(circle + " " + "Classis: " + dataSnapshot.child("Classis").getValue().toString());
                tvOrder.setText(circle + " " + "Ordo: " + dataSnapshot.child("Ordo").getValue().toString());
                tvFamily.setText(circle + " " + "Familia: " + dataSnapshot.child("Familia").getValue().toString());
                tvGenus.setText(circle + " " + "Genus: " + dataSnapshot.child("Genus").getValue().toString());
                tvIsiDeskripsi.setText(dataSnapshot.child("Deskripsi").getValue().toString());
                tvIsiHabitat.setText(dataSnapshot.child("Habitat").getValue().toString());
                tvIsiDistribute.setText(dataSnapshot.child("Distribusi").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Intent intent = new Intent(getApplicationContext(), ImageFullscreenActivity.class);
        String url = slider.getUrl();
        intent.putExtra("UrlImage",url);
        startActivity(intent);

    }




    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
