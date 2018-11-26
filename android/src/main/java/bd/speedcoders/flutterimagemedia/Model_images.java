package bd.speedcoders.flutterimagemedia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deepshikha on 3/3/17.
 */

public class Model_images {
    String str_folder;
    ArrayList<String> al_imagepath;

    public String getStr_folder() {
        return str_folder;
    }

    public void setStr_folder(String str_folder) {
        this.str_folder = str_folder;
    }

    public ArrayList<String> getAl_imagepath() {
        return al_imagepath;
    }

    public void setAl_imagepath(ArrayList<String> al_imagepath) {
        this.al_imagepath = al_imagepath;
    }

    JSONObject getJsonObject(){
        try {
            JSONObject imageModel = new JSONObject();

            imageModel.put("folder", getStr_folder());
            JSONArray imagePaths=new JSONArray();

            for(String path : getAl_imagepath()){
                imagePaths.put(path);
            }

            imageModel.put("images",imagePaths);

            return imageModel;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}


class Model_images_thumbnail_mini {
    String str_folder;
    ArrayList<String> al_imagepath;
    ArrayList<String> al_mini_thumbnailpath;

    public String getStr_folder() {
        return str_folder;
    }

    public void setStr_folder(String str_folder) {
        this.str_folder = str_folder;
    }

    public ArrayList<String> getAl_imagepath() {
        return al_imagepath;
    }

    public ArrayList<String> getAl_thumbnailpath() {
        return al_mini_thumbnailpath;
    }

    public void setAl_imagepath(ArrayList<String> al_imagepath) {
        this.al_imagepath = al_imagepath;
    }

    public void setAl_thumbnailpath(ArrayList<String> al_thumbnailpath) {
        this.al_mini_thumbnailpath = al_thumbnailpath;
    }

    JSONObject getJsonObject(){
        try {
            JSONObject imageModelWithThumbnail = new JSONObject();

            imageModelWithThumbnail.put("folder", getStr_folder());
            JSONArray imagePaths=new JSONArray();
            JSONArray thumbnailPaths=new JSONArray();

            for(String path : getAl_imagepath()){
                imagePaths.put(path);
            }

            for(String path : getAl_thumbnailpath()){
                thumbnailPaths.put(path);
            }

            imageModelWithThumbnail.put("images",imagePaths);
            imageModelWithThumbnail.put("thumbnails",thumbnailPaths);

            return imageModelWithThumbnail;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}