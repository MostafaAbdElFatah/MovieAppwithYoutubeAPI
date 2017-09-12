package com.mostafaabdel_fatah.movieapp;

/**
 * Created by Mostafa AbdEl_Fatah on 9/22/2016.
 */
public class DatatbaseConstants {

    final public static int DTABASE_VERION         =5;
    final public static String DTABASE_NAME        ="dbdb";
    final public static String ID_FIELD            ="id";
    final public static String MOVIE_NAME_FIELD    ="movie";
    final public static String YEAR_FIELD          ="year";
    final public static String RATING_FIELD        ="rating";
    final public static String DURATION_FIELD      ="duration";
    final public static String DIRTECTOR_FIELD     ="director";
    final public static String TAGLINE_FIELD       ="tagline";
    final public static String IMAGLE_URL_FIELD    ="image";
    final public static String YOUTUBE_URL_TFIELD  ="trailer";
    final public static String MOVIE_ID_TFIELD     ="movieid";
    final public static String CAST_NAME_TFIELD     ="cast";

    final public static String STORY_FIELD  ="story";
    final public static String MOVIE_TABLE  = "movie";
    final public static String CAST_TABLE   =  "cast";

    final public static String MOVIE_TABLE_SQL   =
            "CREATE TABLE "+MOVIE_TABLE+" ( "+ID_FIELD +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    MOVIE_NAME_FIELD+" TEXT NOT NULL , "+YEAR_FIELD +" INTEGER NOT NULL ,"+RATING_FIELD +" REAL NOT NULL ,"+
                    DURATION_FIELD+" TEXT NOT NULL , "+DIRTECTOR_FIELD +" TEXT NOT NULL ,"+TAGLINE_FIELD +" TEXT NOT NULL"+
                    IMAGLE_URL_FIELD+" TEXT NOT NULL , "+YOUTUBE_URL_TFIELD +" TEXT NOT NULL ,"+STORY_FIELD +" TEXT NOT NULL )";

    final public static String CAST_TABLE_SQL   =
            "CREATE TABLE "+CAST_TABLE+" ( "+ID_FIELD +" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    CAST_NAME_TFIELD+" TEXT NOT NULL ,"+ MOVIE_ID_TFIELD +" INTEGER NOT NULL)";

    public static String dropTable (String tableName){
        return "DROP TABLE IF EXISTS " + tableName ;
    }


}
