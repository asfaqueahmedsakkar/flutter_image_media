package bd.speedcoders.flutterimagemedia;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterImageMediaPlugin
 */
public class FlutterImageMediaPlugin implements MethodCallHandler {

    private final MethodChannel methodChalen;
    private Activity activity;
    boolean boolean_folder;

    public FlutterImageMediaPlugin(Activity activity,MethodChannel channel) {
        this.activity = activity;
        methodChalen=channel;
        channel.setMethodCallHandler(this);
    }

    private static ArrayList<Model_images> al_images = new ArrayList<>();

    private static ArrayList<Model_images_thumbnail_mini> al_images_with_thumbnail = new ArrayList<>();

    /**
     * Plugin registration.
     */
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_image_media");
        channel.setMethodCallHandler(new FlutterImageMediaPlugin(registrar.activity(),channel));
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("getAllImages")) {
            loadImages(result);
        }else if (call.method.equals("getAllImagesWithMiniThumbnail")) {
            loadImagesWithMiniThumbnail(result);
        } else {
            result.notImplemented();
        }
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    void loadImages(Result result){
        new LoadImageAsync(result).execute();
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    class LoadImageAsync extends AsyncTask<String, String, String> {

        Result result;

        public LoadImageAsync(Result result) {
            this.result = result;
        }

        @Override
        protected String doInBackground(String... strings) {
            List<Model_images> images=fn_imagespath();

            JSONArray allImages=new JSONArray();

            for (Model_images model:images){
                allImages.put(model.getJsonObject());
            }

            return allImages.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            result.success(s);
            Toast.makeText(activity.getApplicationContext(),"s",Toast.LENGTH_SHORT).show();
        }
    }

    ArrayList<Model_images> fn_imagespath() {
        al_images.clear();
        boolean_folder=false;

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            for (int i = 0; i < al_images.size(); i++) {
                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(al_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                al_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);

                al_images.add(obj_model);
            }
        }
        return al_images;
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    void loadImagesWithMiniThumbnail(Result result){
        new LoadImageAndThumbnailAsync(result).execute();
    }

    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    class LoadImageAndThumbnailAsync extends AsyncTask<String, String, String> {

        Result result;

        LoadImageAndThumbnailAsync(Result result) {
            this.result = result;
        }

        @Override
        protected String doInBackground(String... strings) {
            List<Model_images_thumbnail_mini> images=fn_images_mini_thumbnail_path();

            JSONArray allImages=new JSONArray();


            for (Model_images_thumbnail_mini model:images){
                allImages.put(model.getJsonObject());
            }

            return allImages.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(activity.getApplicationContext(),"s",Toast.LENGTH_SHORT).show();
            result.success(s);
        }
    }

    ArrayList<Model_images_thumbnail_mini> fn_images_mini_thumbnail_path() {
        al_images_with_thumbnail.clear();
        boolean_folder=false;

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        long imageId;
        String thumbnailPath="";

        String[] projection = {MediaStore.Images.Media._ID, MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = activity.getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        int column_index_of_id = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media._ID);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            thumbnailPath="";

            imageId = cursor.getLong(column_index_of_id);

            Cursor cursor2 = MediaStore.Images.Thumbnails.queryMiniThumbnail(
                    activity.getApplicationContext().getContentResolver(), imageId,
                    MediaStore.Images.Thumbnails.MINI_KIND,
                    null);
            if (cursor2 != null && cursor2.getCount() > 0) {
                cursor2.moveToFirst();
                thumbnailPath = cursor.getString(cursor2.getColumnIndexOrThrow(MediaStore.Images.Thumbnails.DATA));
                cursor2.close();
            }

            for (int i = 0; i < al_images_with_thumbnail.size(); i++) {
                if (al_images_with_thumbnail.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(al_images_with_thumbnail.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                al_images_with_thumbnail.get(int_position).setAl_imagepath(al_path);
                al_images_with_thumbnail.get(int_position).getAl_thumbnailpath().add(thumbnailPath);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                ArrayList<String> al_tn_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images_thumbnail_mini obj_model = new Model_images_thumbnail_mini();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);
                al_tn_path.add(thumbnailPath);

                obj_model.setAl_thumbnailpath(al_tn_path);
                al_images_with_thumbnail.add(obj_model);
            }


        }
        cursor.close();
        return al_images_with_thumbnail;
    }

}
