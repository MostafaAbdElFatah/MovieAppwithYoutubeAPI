package com.mostafaabdel_fatah.movieapp;

import android.app.Notification;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class dbConnector extends SQLiteOpenHelper {

    Context context;
    public dbConnector(Context context){
        super(context,DatatbaseConstants.DTABASE_NAME,null,DatatbaseConstants.DTABASE_VERION);
        this.context=context;
        Toast.makeText(context,"create tables.....",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            //create tables in sqllite database
            db.execSQL(DatatbaseConstants.MOVIE_TABLE_SQL);
            db.execSQL(DatatbaseConstants.CAST_TABLE_SQL);
            Toast.makeText(context,"create tables.....",Toast.LENGTH_LONG).show();

        }catch (Exception e){
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DatatbaseConstants.dropTable(DatatbaseConstants.MOVIE_TABLE_SQL));
            db.execSQL(DatatbaseConstants.dropTable(DatatbaseConstants.CAST_TABLE_SQL));
            Toast.makeText(context,"drop tables.....",Toast.LENGTH_LONG).show();
            onCreate(db);
        }catch (Exception e) {
        }
    }
    public boolean InsertMovies(List<Movie> list){
        //SQLiteDatabase db = this.getWritableDatabase();
        /*for (int j = 0 ; j < list.size(); j++){
            ContentValues insert = new ContentValues();

            insert.put(DatatbaseConstants.MOVIE_NAME_FIELD,list.get(j).getMovie());
            insert.put(DatatbaseConstants.YEAR_FIELD,list.get(j).getYear());
            insert.put(DatatbaseConstants.RATING_FIELD,list.get(j).getRating());
            insert.put(DatatbaseConstants.DURATION_FIELD,list.get(j).getDuration());
            insert.put(DatatbaseConstants.DIRTECTOR_FIELD,list.get(j).getDirector());
            insert.put(DatatbaseConstants.TAGLINE_FIELD,list.get(j).getTagline());
            insert.put(DatatbaseConstants.IMAGLE_URL_FIELD,list.get(j).getImage().toString());
            insert.put(DatatbaseConstants.STORY_FIELD,list.get(j).getStory());
            insert.put(DatatbaseConstants.YOUTUBE_URL_TFIELD,list.get(j).getTrailer());

            long n=db.insert(DatatbaseConstants.MOVIE_TABLE , null , insert);
            if (n==-1)
                return false;
        }*/
        return  true;
    }
}
