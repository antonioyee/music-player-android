package mx.antonioyee.musicplayer.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import mx.antonioyee.musicplayer.R;
import mx.antonioyee.musicplayer.models.Music;
import mx.antonioyee.musicplayer.models.MusicOld;


public class PlayerSongActivity extends ActionBarActivity implements View.OnClickListener, Runnable {

    private int position = 0;
    TextView textTitle;
    TextView textArtist;
    TextView textAlbum;
    TextView textDuration;
    ImageView photoAlbum;
    TextView textFile;
    ArrayList<MusicOld> musica;
    Button btnBack, btnPlayPause, btnNext;
    MediaPlayer mPlayer;
    Boolean play = false;
    ProgressBar progressBar;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_song);
        musica = MusicOld.getSongs(getApplicationContext());

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
        photoAlbum = (ImageView) findViewById(R.id.photoAlbum);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        thread= new Thread(this);

        loadData();
    }

    @Override
    protected void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }

    public void loadData(){
        MusicOld music = musica.get(position);

        photoAlbum.setImageDrawable(music.getPhotoAlbum());

        textTitle.setText(music.getTitle());
        textArtist.setText( music.getArtist() );
        textAlbum.setText(music.getAlbum());
        //textDuration.setText( music.getDuration() );

        mPlayer = MediaPlayer.create(PlayerSongActivity.this, getResources().getIdentifier("raw/"+music.getFile(), "raw", getPackageName()));

        int seconds = (int) (mPlayer.getDuration() / 1000) % 60 ;
        int minutes = (int) ((mPlayer.getDuration() / (1000*60)) % 60);
        int hours   = (int) ((mPlayer.getDuration() / (1000*60*60)) % 24);
        if(hours>0) {
            textDuration.setText("" + hours + ":" + minutes + ":" + seconds);
        }else{
            textDuration.setText(""+minutes + ":" + seconds);
        }

        progressBar.setVisibility(ProgressBar.VISIBLE);
        progressBar.setProgress(0);
        progressBar.setMax(mPlayer.getDuration());
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
                if ( play == false ){
                    mPlayer.start();
                    btnPlayPause.setText("=");
                    play = true;

                    if(thread.getState()!= Thread.State.TIMED_WAITING){
                        thread.start();
                    }

                }else{
                    mPlayer.pause();
                    btnPlayPause.setText(">");
                    play = false;
                }
                break;

            case R.id.btnBack:
                if ( position > 0 ){
                    position--;
                    mPlayer.stop();

                    loadData();

                    if ( play == true ){
                        mPlayer.start();
                        play = true;
                    }
                }
                break;

            case R.id.btnNext1:
                Log.e("NEXT", "position"+position + " size " + musica.size());
                if ( position < musica.size() - 1 ){
                    position++;

                    mPlayer.stop();

                    loadData();

                    if ( play == true ){
                        mPlayer.start();
                        play = true;
                    }
                }
                break;
        }
    }

    @Override
    public void run() {
        int currentPosition= 0;
        int total = mPlayer.getDuration();
        while (mPlayer!=null && currentPosition<total) {
            try {
                Thread.sleep(1000);
                currentPosition= mPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            progressBar.setProgress(currentPosition);
        }
    }
}
