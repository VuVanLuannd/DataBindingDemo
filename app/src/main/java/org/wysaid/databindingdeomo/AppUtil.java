package org.wysaid.databindingdeomo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import org.wysaid.databindingdeomo.model.ImageModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class AppUtil {

    public static Single<List<ImageModel>> getImageMedia(Context context) {
        return Single.create(emitter -> {
            List<ImageModel> imageModels = new ArrayList<>();
            String condition = MediaStore.Images.Media.DATA + " like '%/" + "ArticaPhotoEditor" + "/%'";
            String[] projection = {MediaStore.Images.Media.DATA
                    , MediaStore.Images.Media.DISPLAY_NAME
                    , MediaStore.Images.Media.DATE_ADDED};
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection,
                    condition, null, "date_modified DESC");
            if (cursor != null) {
                boolean kt = cursor.moveToFirst();
                if (!kt) {
                    if (emitter.isDisposed()) {
                        return;
                    }
                    emitter.onSuccess(imageModels);
                    return;
                }
                do {
                    try {
                        ImageModel imageModel = new ImageModel();
                        String name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                        String uriImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                        imageModel.setName(name);
                        imageModel.setPath(uriImage);
                        imageModels.add(imageModel);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());
                cursor.close();
                if (emitter.isDisposed()) {
                    return;
                }
                emitter.onSuccess(imageModels);
            } else {
                emitter.onError(new Exception("error"));
            }
        });
    }
}
