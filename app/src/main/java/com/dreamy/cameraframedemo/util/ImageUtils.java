package com.dreamy.cameraframedemo.util;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by raomengyang on 4/25/16.
 */
public class ImageUtils {
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // save image to sdcard path: Pictures/MyTestImage/
    public static void saveImageData(byte[] imageData) {
        File imageFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        if (imageFile == null) return;
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            fos.write(imageData);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getOutputMediaFile(int type) {
        File imageFileDir =
                new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyTestImage");
        if (!imageFileDir.exists()) {
            if (!imageFileDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File imageFile;
        if (type == MEDIA_TYPE_IMAGE) {
            imageFile = new File(imageFileDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            imageFile = new File(imageFileDir.getPath() + File.separator +
                    "VID_" + timeStamp + ".mp4");
        } else return null;
        return imageFile;
    }
}
