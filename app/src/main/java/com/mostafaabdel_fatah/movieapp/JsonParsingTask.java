package com.mostafaabdel_fatah.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Mostafa AbdEl_Fatah on 7/9/2017.
 */

public class JsonParsingTask extends AsyncTask<String ,String,String> {


    HttpURLConnection connection=null;
    BufferedReader buf=null;
    Context context=null;

    public JsonParsingTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... param) {
        String traliers[]={"https://www.youtube.com/watch?v=eOrNdBpGMv8"
                ,"https://www.youtube.com/watch?v=zSWdZVtXT7E"
                ,"https://www.youtube.com/watch?v=_rRoD28-WgU"
                ,"https://www.youtube.com/watch?v=_PZpmTj1Q8Q"
                ,"https://www.youtube.com/watch?v=y2rYRu8UW8M"
                ,"https://www.youtube.com/watch?v=8CTjcVr9Iao"
                ,"https://www.youtube.com/watch?v=owK1qxDselE"
                ,"https://www.youtube.com/watch?v=4sj1MT05lAA"
                ,"https://www.youtube.com/watch?v=8-_9n5DtKOc"
                ,"https://www.youtube.com/watch?v=zwhP5b4tD6g"};
       /* try {
            //fetch json file
            URL url = new URL(param[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            buf = new BufferedReader( new InputStreamReader(in) );
            StringBuilder text = new StringBuilder();
            String line = "";
            while ( (line = buf.readLine())!= null){
                text.append(line);
            }
            String finalJson = text.toString();
            JSONObject parentObject = new JSONObject(finalJson);
            JSONArray parentArray = parentObject.getJSONArray("movies");

            for(int i=0; i<parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                String Name     = finalObject.getString("movie");
                int year     = finalObject.getInt("year");
                double rating   = finalObject.getDouble("rating");
                String duration = finalObject.getString("duration");
                String director = finalObject.getString("director");
                String tagline  = finalObject.getString("tagline");
                String image    = finalObject.getString("image");
                //String tralier    = finalObject.getString("tralier");
                String story    = finalObject.getString("story");
                ContentValues insert = new ContentValues();
                insert.put(DatatbaseConstants.MOVIE_NAME_FIELD,Name);
                insert.put(DatatbaseConstants.YEAR_FIELD,year);
                insert.put(DatatbaseConstants.RATING_FIELD,rating);
                insert.put(DatatbaseConstants.DURATION_FIELD,duration);
                insert.put(DatatbaseConstants.DIRTECTOR_FIELD,director);
                insert.put(DatatbaseConstants.TAGLINE_FIELD,tagline);
                insert.put(DatatbaseConstants.IMAGLE_URL_FIELD,image);
                insert.put(DatatbaseConstants.STORY_FIELD,story);
                //insert.put(DatatbaseConstants.YOUTUBE_URL_TFIELD,tralier);
                long n=db.insert(DatatbaseConstants.MOVIE_TABLE , null , insert);
                JSONArray childArray = finalObject.getJSONArray("cast");
                for (int j = 0 ; j < childArray.length(); j++){
                    String nameCast = childArray.getJSONObject(j).getString("name");
                    ContentValues insert1 = new ContentValues();
                    insert1.put(DatatbaseConstants.CAST_NAME_TFIELD,nameCast);
                    insert1.put(DatatbaseConstants.MOVIE_ID_TFIELD,i);
                    long n1=db.insert(DatatbaseConstants.CAST_TABLE , null , insert1);
                }


            }
            Toast.makeText(context,"done",Toast.LENGTH_LONG).show();
            return  "done";
        }catch (Exception e){
        }finally {
            if(connection !=null)
                connection.disconnect();
            if (buf !=null)
                try {
                    buf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/
        return null;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
    }
}