package com.mostafaabdel_fatah.movieapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listMovie = null;
    ProgressDialog progressDialog;
    String movieText   = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbConnector tablesCreater =new dbConnector(MainActivity.this);
        SQLiteDatabase db =tablesCreater.getWritableDatabase();
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading. Please Wait ...");
        /******************************************************************************************/
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        /*******************************************************************************************/

        listMovie = (ListView) findViewById(R.id.listview);

        // new JsonParsingTask(MainActivity.this).execute(movieText);
        new JsonTask().execute(movieText);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Refresh)
        {
            // new JsonParsingTask(MainActivity.this).execute(movieText);
            new JsonTask().execute(movieText);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  class JsonTask extends AsyncTask<String ,String,List<Movie>>{

        HttpURLConnection connection=null;
        BufferedReader buf=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List<Movie> doInBackground(String... param) {

            String traliers[]={"eOrNdBpGMv8","Lm8p5rlrSkY","DMEa0CJbAUs","EXeTwQWrcwY","r5X-hFf6Bwo"
            ,"pAYEQP8gx3w","ol67qo3WhJk","4sj1MT05lAA","8-_9n5DtKOc","RYID71hYHzg"};
            try {
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
                // create list to set movies

                List<Movie> movieList = new ArrayList<>();
                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {

                    JSONObject finalObject = parentArray.getJSONObject(i);
                    //Movie movie= gson.fromJson(finalObject.toString(),Movie.class);

                    //Above Line Equel all lines in comment
                    Movie movie = new Movie();
                    movie.setMovie(finalObject.getString("movie")  );
                    movie.setYear( finalObject.getInt("year") );
                    movie.setRating( (float) finalObject.getDouble("rating") );
                    movie.setDuration( finalObject.getString("duration") );
                    movie.setDirector( finalObject.getString("director") );
                    movie.setTagline( finalObject.getString("tagline") );
                    movie.setImage( finalObject.getString("image") );
                    movie.setStory( finalObject.getString("story") );
                    movie.setTrailer(traliers[i]);

                    JSONArray childArray = finalObject.getJSONArray("cast");

                    List<Movie.Cast> castList = new ArrayList<>();
                    for (int j = 0 ; j < childArray.length(); j++){
                        Movie.Cast cast =new Movie.Cast();
                        cast.setName( childArray.getJSONObject(j).getString("name") );
                        castList.add(cast);
                    }
                    movie.setCastList( castList );
                    movieList.add(movie);

                }
                return  movieList;
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
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Movie> list){
            super.onPostExecute(list);
            progressDialog.dismiss();

            /*
            boolean b = db.InsertMovies(list);
            if (b)
                Toast.makeText(getApplicationContext(),"inserted.....",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(),"not inserted.....",Toast.LENGTH_LONG).show();
            */

            MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext() ,R.layout.row,list);
            listMovie.setAdapter(movieAdapter);
            listMovie.setOnItemClickListener (new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(MainActivity.this , MovieDetails.class);
                    intent.putExtra("movie",list.get(position).getMovie());
                    intent.putExtra("year",list.get(position).getYear()+"");
                    intent.putExtra("tagline",list.get(position).getTagline());
                    intent.putExtra("duration",list.get(position).getDuration());
                    intent.putExtra("director",list.get(position).getDirector());
                    intent.putExtra("rating",list.get(position).getRating()/2+"");
                    intent.putExtra("story",list.get(position).getStory());
                    intent.putExtra("trailer",list.get(position).getTrailer());
                    StringBuffer castString = new StringBuffer();
                    for (int i=0;i<list.get(position).getCastList().size();i++ ) {
                        castString.append(list.get(position).getCastList().get(i).getName() + "  ");
                    }
                    intent.putExtra("cast",castString.toString());
                    startActivity(intent);

                }
            });
        }
    }

}

