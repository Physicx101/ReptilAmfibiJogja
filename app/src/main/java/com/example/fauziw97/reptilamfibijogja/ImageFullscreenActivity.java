package com.example.fauziw97.reptilamfibijogja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fauziw97.reptilamfibijogja.View.TouchImageView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFullscreenActivity extends AppCompatActivity {
    @BindView(R.id.image)
    TouchImageView image;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Bitmap bm;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fullscreen);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        try {
            Intent data = getIntent();
            showProgressDialog();

            if (data.hasExtra("UrlImage")) {
                Bundle extras = getIntent().getExtras();
                String address = extras.getString("UrlImage");
                fullScreen(address);

            }
        } catch (Exception e) {
            Log.w("ShowImage.java", "There's error in get data:" + e.getMessage());
        }
    }


    private void fullScreen(String src) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(src);

        File rootPath = new File(getFilesDir().getAbsolutePath(), "TaxAppImage");
        if (!rootPath.exists()) {
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath, "Image-HistoryFullScreen.png");

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ", ";local tem file created  created " + localFile.toString());
                File imgFile = new File(getFilesDir().getAbsolutePath() + "/TaxAppImage/Image-HistoryFullScreen.png");

                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    dismissProgressDialog();
                    image.setImageBitmap(myBitmap);

                }


                //  updateDb(timestamp,localFile.toString(),position);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ", ";local tem file not created  created " + exception.toString());
                File imgFile = new File(getFilesDir().getAbsolutePath() + "/TaxAppImage/Image-HistoryFullScreen.png");

                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    dismissProgressDialog();
                    image.setImageBitmap(myBitmap);

                }

            }

        });


    }


    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(ImageFullscreenActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fullscreen_image, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.download_image) {
            Calendar c = Calendar.getInstance();

            try {
                image.buildDrawingCache();
                bm = image.getDrawingCache();
            } catch (Exception e) {
                Toast.makeText(ImageFullscreenActivity.this, "Foto tidak tersedia", Toast.LENGTH_SHORT).show();
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            File rootPath = new File(Environment.getExternalStorageDirectory(), "TaxAppImage");
            if (!rootPath.exists()) {
                rootPath.mkdirs();
            }

            File myDir = new File(rootPath + "/images");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = "Image-" + formattedDate + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(ImageFullscreenActivity.this, "Foto berhasil diunduh", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
