package com.shortbrodemo.pch.shortbrodemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Used to do some basics functions for the things related to the images.
 */
public class UtilsImage {
    static final String DEFAULT_PATH_NAME = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/shortdemodir";

    /**
     * Used to get a name from a String
     */
    public static String gettingImageName(String fullAddress) {
        String[] strings = fullAddress.split("/");

        return strings[strings.length - 1];
    }

    /**
     * Looking by the name of an image if it's already downloaded locally
     */
    public static boolean isImageAlreadyLocal(String imageName) {
        File file = new File(DEFAULT_PATH_NAME, imageName);
        if (file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * AsyncTask inside
     * Used to save a network image locally
     */
    public static void saveLocally(final String fullAddress) {
        if (!isImageAlreadyLocal(gettingImageName(fullAddress))) {
            AsyncTask<Void, Void, Void> voidVoidVoidAsyncTask = new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    File file = new File(DEFAULT_PATH_NAME);
                    file.mkdirs();

                    file = new File(DEFAULT_PATH_NAME, gettingImageName(fullAddress));

                    HttpURLConnection connection = null;
                    Bitmap bitmap = null;
                    InputStream in = null;
                    FileOutputStream out = null;

                    try {
                        connection = (HttpURLConnection) new URL(fullAddress).openConnection();
                        connection.connect();
                        in = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(in);
                        in.close();
                        out = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                        out.close();
                    } catch (Exception e) {
                        Log.d("DownloadImage", e.getLocalizedMessage());
                    }

                    return null;
                }
            };

            voidVoidVoidAsyncTask.execute();
        }
    }

    /**
     * Used to get a File by passing the name of an image
     * !!! Can return null !!!
     */
    public static File getLocally(String imageName) {
        if (isImageAlreadyLocal(imageName)) {
            return new File(DEFAULT_PATH_NAME, imageName);
        } else {
            return null;
        }
    }

    /**
     * Used to save locally a List of links of networks images
     *
     * Might be a leak with that way of doing it.
     */
    public static void saveAllLocally(final List<String> strings, final Context context) {

        AsyncTask<Void, Void, Boolean> voidVoidVoidAsyncTask = new AsyncTask<Void, Void, Boolean>() {
            int i = 0;
            int numberOf = strings.size();

            @Override
            protected Boolean doInBackground(Void... voids) {
                boolean isOk = false;
                File file = new File(DEFAULT_PATH_NAME);
                file.mkdirs();

                HttpURLConnection connection = null;
                Bitmap bitmap = null;
                InputStream in = null;
                FileOutputStream out = null;

                for (String s : strings) {
                    file = new File(DEFAULT_PATH_NAME, gettingImageName(s));
                    if (!isImageAlreadyLocal(gettingImageName(s))) {
                        try {
                            connection = (HttpURLConnection) new URL(s).openConnection();
                            connection.connect();
                            in = connection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(in);
                            in.close();
                            out = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                            out.close();
                            i++;
                        } catch (Exception e) {
                            Log.d("DownloadImage", e.getLocalizedMessage());
                        }

                    } else {
                        i++;
                    }
                }


                if (i == numberOf) {
                    isOk = true;
                }

                return new Boolean(isOk);
            }

            @Override
            protected void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);

                if (bool) {
                    Toast.makeText(context, "All images downloaded", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Some images where not downloaded", Toast.LENGTH_LONG).show();
                }
            }
        };

        voidVoidVoidAsyncTask.execute();
    }
}
