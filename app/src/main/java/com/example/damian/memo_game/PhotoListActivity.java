package com.example.damian.memo_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.damian.memo_game.factory.PhotoFactory;
import com.example.damian.memo_game.model.PhotoModel;

import java.util.ArrayList;

public class PhotoListActivity extends AppCompatActivity {
    private static ArrayList<PhotoModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);

        RecyclerView mRecyclerView = findViewById(R.id.list);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setHasFixedSize(true);

        PhotoGalleryAdapter galleryAdapter = new PhotoGalleryAdapter(this, data);
        mRecyclerView.setAdapter(galleryAdapter);
    }

    public void takePhoto(MenuItem item) {
        PhotoFactory.takePhoto(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = PhotoFactory.saveThumbnail(requestCode, resultCode, data, this);
        if (path != null) {
            this.addPhotoItemToList(path);
        }
    }

    public void clickPhotoItem(View view) {
        Toast.makeText(getApplicationContext(), "Usuwam...", Toast.LENGTH_LONG).show();
    }

    private void addPhotoItemToList(String path) {
        PhotoModel model = new PhotoModel();
        model.setUrl(path);
        data.add(model);
    }

    public void clickMenuNavigationButton(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
