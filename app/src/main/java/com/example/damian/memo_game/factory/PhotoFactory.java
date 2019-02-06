package com.example.damian.memo_game.factory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class PhotoFactory {
    private static final int REQUEST_TAKE_PHOTO = 1;

    private PhotoFactory() {
    }

    public static void takePhoto(Activity activity) {
        dispatchTakePictureIntent(activity);
    }

    private static String getNewFileName(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        return storageDir + "/" + imageFileName + ".bmp";
    }

    private static void dispatchTakePictureIntent(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    public static String saveThumbnail(int requestCode, int resultCode, Intent data, Activity activity) {
        String filename = null;
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            try {
                filename = getNewFileName(activity);
                FileOutputStream out = new FileOutputStream(filename);
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filename;
    }
}
