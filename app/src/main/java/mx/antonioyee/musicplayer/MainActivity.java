package mx.antonioyee.musicplayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ListView listNames;
    MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Music> musica = new ArrayList<Music>();

        musica.add( new Music("In Public", "Tigerskin", "Try the imposible EP", "8:00",getResources().getDrawable(R.mipmap.tigerskininpublic), "tigerskin_in_public.mp3") );
        musica.add( new Music("Like a Stone","Audioslave", "Audioslave", "4.53",getResources().getDrawable(R.mipmap.audioslavelikeastone), "audioslave_like_a_stone.mp3") );
        musica.add( new Music("Midnight City","M83", "Midnight City", "4:03",getResources().getDrawable(R.mipmap.m83midnightcity), "m83_midnight_city.mp3") );
        musica.add( new Music("Otherside","Red Hot Chili Peppers", "Californication", "4:15",getResources().getDrawable(R.mipmap.otherside), "red_hot_chili_peppers_otherside.mp3") );
        musica.add( new Music("Ready of Star","Arcade Fire", "The Suburbs", "4:15",getResources().getDrawable(R.mipmap.ready_of_star), "tigerskin_in_public.mp3") );

        listNames = (ListView) findViewById(R.id.listNames);
        adapter = new MusicAdapter(getApplicationContext(), R.layout.simple_list_item_1, musica);

        listNames.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
