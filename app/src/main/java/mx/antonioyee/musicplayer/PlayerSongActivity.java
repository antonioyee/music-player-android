package mx.antonioyee.musicplayer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayerSongActivity extends ActionBarActivity implements View.OnClickListener {

    private int position = 0;
    TextView textTest;
    TextView textTitle;
    TextView textArtist;
    TextView textAlbum;
    TextView textDuration;
    ImageView photoAlbum;
    TextView textFile;
    ArrayList<Music> musica;
    Button btnBack, btnPlayPause, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_song);
        musica = Music.getSongs(getApplicationContext());

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            this.position = bundle.getInt("position");
        }

        btnBack = (Button) findViewById(R.id.btnBack);
        btnNext = (Button) findViewById(R.id.btnNext1);
        btnPlayPause = (Button) findViewById(R.id.btnPlayPause);

        btnBack.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPlayPause.setOnClickListener(this);

        textTitle = (TextView) findViewById(R.id.textTitle);
        textArtist = (TextView) findViewById(R.id.textArtist);
        textAlbum = (TextView) findViewById(R.id.textAlbum);
        textDuration = (TextView) findViewById(R.id.textDuration);
        textFile = (TextView) findViewById(R.id.textFile);
        photoAlbum = (ImageView) findViewById(R.id.photoAlbum);

        loadData();
    }

    public void loadData(){
        Music music = musica.get(position);

        photoAlbum.setImageDrawable(music.getPhotoAlbum());

        textTitle.setText(music.getTitle());
        textArtist.setText( music.getArtist() );
        textAlbum.setText( music.getAlbum() );
        textDuration.setText( music.getDuration() );
        textFile.setText( music.getFile() );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_song, menu);
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
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.btnPlayPause:
                break;

            case R.id.btnBack:
                if ( position > 0 ){
                    position--;
                    loadData();
                }
                break;

            case R.id.btnNext1:
                Log.e("NEXT", "position"+position + " size " + musica.size());
                if ( position < musica.size() - 1 ){
                    position++;
                    loadData();
                }
                break;
        }
    }
}
