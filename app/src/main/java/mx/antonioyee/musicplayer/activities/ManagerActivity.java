package mx.antonioyee.musicplayer.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import mx.antonioyee.musicplayer.models.Album;
import mx.antonioyee.musicplayer.models.Artist;
import mx.antonioyee.musicplayer.models.Genre;
import mx.antonioyee.musicplayer.models.Music;
import mx.antonioyee.musicplayer.R;
import mx.antonioyee.musicplayer.fragments.ListMusicFragment;
import mx.antonioyee.musicplayer.fragments.PlayerFragment;
import mx.antonioyee.musicplayer.models.MusicOld;


public class ManagerActivity extends ActionBarActivity implements ListMusicFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager;
    private String resourceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_manager, null);
        resourceType = view.getTag().toString();

        setContentView(view);

        fragmentManager = getSupportFragmentManager();

        //insertSampleData();

        if ( resourceType.equals("sw600dp") || resourceType.equals("sw320dp-land") ){
            fragmentManager.beginTransaction()
                    .replace(R.id.containerLeft, ListMusicFragment.newInstance(MusicOld.getSongs(getApplicationContext())))
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ListMusicFragment.newInstance(MusicOld.getSongs(getApplicationContext())))
                    .commit();
        }
    }

    public void insertSampleData(){
        //long result = Artist.insert(getApplicationContext(), new Artist("Julion Alvarez", 35, "http://dinamica.radioramamexicali.com/wp-content/uploads/2015/01/julion-alvarez.jpg") );
        //Toast.makeText(getApplicationContext(), ""+result, Toast.LENGTH_SHORT).show();

        // INSERTAR GENEROS
        Genre.insert(getApplicationContext(), new Genre("Rock"));
        Genre.insert(getApplicationContext(), new Genre("Indie"));
        Genre.insert(getApplicationContext(), new Genre("House"));

        // INSERTAR ARTISTAS
        Artist.insert(getApplicationContext(), new Artist("Tigerskin", 30, "https://pbs.twimg.com/profile_images/568431733834797056/y72-JBes.jpeg") );
        Artist.insert(getApplicationContext(), new Artist("Audioslave", 35, "http://www.mtv.com/crop-images/2013/08/12/Audioslave%20UMG%20Ethan%20A.%20Russell%202005.jpg") );
        Artist.insert(getApplicationContext(), new Artist("M83", 24, "http://image.mp3.zdn.vn/avatars/1/1/1167610aa17b0813233fe82d99403e41_1289718347.jpg") );
        Artist.insert(getApplicationContext(), new Artist("Red Hot Chili Peppers", 40, "http://blogs.telegraph.co.uk/culture/files/2011/06/chili.jpg") );
        Artist.insert(getApplicationContext(), new Artist("Arcade Fire", 32, "http://assets.rollingstone.com/assets/2014/article/the-unforgettable-fire-can-arcade-fire-be-the-worlds-biggest-band-20140116/14068/_original/1035x596-20140114-arcadefire-x1800-1389721067.jpg") );

        // INSERTAR ALBUMNES
        Album.insert(getApplicationContext(), new Album("Try the imposible EP",
                "http://www.beatmyday.com/wp-content/uploads/2013/01/Try-The-Impossible-EP.jpg",
                Artist.GetIdArtist(getApplicationContext(), "Tigerskin"),
                Genre.GetIdGenre(getApplicationContext(), "House")));

        Album.insert(getApplicationContext(), new Album("Audioslave",
                "http://ecx.images-amazon.com/images/I/514RWTJvpHL.jpg",
                Artist.GetIdArtist(getApplicationContext(), "Audioslave"),
                Genre.GetIdGenre(getApplicationContext(), "Rock")));

        Album.insert(getApplicationContext(), new Album("Midnight City",
                "http://www.theburningear.com/media/2011/11/M83-Hurry-Up.jpeg",
                Artist.GetIdArtist(getApplicationContext(), "M83"),
                Genre.GetIdGenre(getApplicationContext(), "Indie")));

        Album.insert(getApplicationContext(), new Album("Californication",
                "http://upload.wikimedia.org/wikipedia/en/d/df/RedHotChiliPeppersCalifornication.jpg",
                Artist.GetIdArtist(getApplicationContext(), "Red Hot Chili Peppers"),
                Genre.GetIdGenre(getApplicationContext(), "Rock")));

        Album.insert(getApplicationContext(), new Album("The Suburbs",
                "http://hipsterconservative.com/wp-content/uploads/2012/02/arcade-fire-the-suburbs.png",
                Artist.GetIdArtist(getApplicationContext(), "Arcade Fire"),
                Genre.GetIdGenre(getApplicationContext(), "Indie")));

        // INSERTAR CANCIONES
        Music.insert(getApplicationContext(), new Music("In Public", "tigerskin_in_public",
                Album.GetIdAlbum(getApplicationContext(), "Try the imposible EP") ));

        Music.insert(getApplicationContext(), new Music("Like a Stone", "audioslave_like_a_stone",
                Album.GetIdAlbum(getApplicationContext(), "Audioslave") ));

        Music.insert(getApplicationContext(), new Music("Midnight City", "m83_midnight_city",
                Album.GetIdAlbum(getApplicationContext(), "Midnight City") ));

        Music.insert(getApplicationContext(), new Music("Otherside", "red_hot_chili_peppers_otherside",
                Album.GetIdAlbum(getApplicationContext(), "Californication") ));

        Music.insert(getApplicationContext(), new Music("Ready of Star", "arcade_fire_ready_to_start",
                Album.GetIdAlbum(getApplicationContext(), "The Suburbs") ));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager, menu);
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

    @Override
    public void onMusicSelect(int position) {
        if ( resourceType.equals("sw600dp") || resourceType.equals("sw320dp-land") ){
            fragmentManager.beginTransaction()
                    .replace(R.id.containerRight, PlayerFragment.newInstance(position))
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.container,PlayerFragment.newInstance(position))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
