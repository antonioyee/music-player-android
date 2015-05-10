package mx.antonioyee.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayerFragment extends Fragment implements  View.OnClickListener, Runnable{

    private static final String ARG_PARAM_POSITION = "paramPosition";
    private int mPosition = 0;
    private TextView textTitleF, textArtistF, textAlbumF, textDurationF;
    private ImageView photoAlbumF;
    private Button btnBackF, btnPlayPauseF, btnNext1F;
    private ProgressBar progressBarF;
    private Music music;
    private MediaPlayer mPlayer;
    private Boolean play = false;
    private Thread thread;
    private ArrayList<Music> musica;

    public static PlayerFragment newInstance(int mPosition) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM_POSITION, mPosition);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM_POSITION);
            musica = Music.getSongs(getActivity());
            music = musica.get(mPosition);
        }
        thread= new Thread(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_player, container, false);

        btnPlayPauseF = (Button) viewRoot.findViewById(R.id.btnPlayPauseF);
        btnBackF = (Button) viewRoot.findViewById(R.id.btnBackF);
        btnNext1F = (Button) viewRoot.findViewById(R.id.btnNext1F);

        textTitleF = (TextView) viewRoot.findViewById(R.id.textTitleF);
        textArtistF = (TextView) viewRoot.findViewById(R.id.textArtistF);
        textAlbumF = (TextView) viewRoot.findViewById(R.id.textAlbumF);
        textDurationF = (TextView) viewRoot.findViewById(R.id.textDurationF);

        photoAlbumF = (ImageView) viewRoot.findViewById(R.id.photoAlbumF);

        progressBarF = (ProgressBar) viewRoot.findViewById(R.id.progressBarF);

        btnBackF.setOnClickListener(this);
        btnNext1F.setOnClickListener(this);
        btnPlayPauseF.setOnClickListener(this);

        loadData(music);

        return viewRoot;
    }

    private void loadData(Music music){
        photoAlbumF.setImageDrawable(music.getPhotoAlbum());

        textArtistF.setText(music.getArtist());
        textAlbumF.setText(music.getAlbum());
        textTitleF.setText(music.getTitle());

        mPlayer = MediaPlayer.create(getActivity(), getResources().getIdentifier("raw/"+music.getFile(), "raw", getActivity().getPackageName()));

        int seconds = (int) (mPlayer.getDuration() / 1000) % 60 ;
        int minutes = (int) ((mPlayer.getDuration() / (1000*60)) % 60);
        int hours   = (int) ((mPlayer.getDuration() / (1000*60*60)) % 24);

        if(hours>0) {
            textDurationF.setText("" + hours + ":" + minutes + ":" + seconds);
        }else{
            textDurationF.setText(""+minutes + ":" + seconds);
        }

        progressBarF.setVisibility(ProgressBar.VISIBLE);
        progressBarF.setProgress(0);
        progressBarF.setMax(mPlayer.getDuration());
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.btnPlayPauseF:
                if ( play == false ){
                    mPlayer.start();
                    btnPlayPauseF.setText("=");
                    play = true;

                    if(thread.getState()!= Thread.State.TIMED_WAITING){
                        thread.start();
                    }

                }else{
                    mPlayer.pause();
                    btnPlayPauseF.setText(">");
                    play = false;
                }
                break;

            case R.id.btnBackF:
                if ( mPosition > 0 ){
                    mPosition--;
                    mPlayer.stop();

                    loadData(musica.get(mPosition));

                    if ( play == true ){
                        mPlayer.start();
                        play = true;
                    }
                }
                break;

            case R.id.btnNext1F:
                Log.e("NEXT", "position"+mPosition + " size " + musica.size());
                if ( mPosition < musica.size() - 1 ){
                    mPosition++;

                    mPlayer.stop();

                    loadData(musica.get(mPosition));

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
            progressBarF.setProgress(currentPosition);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if ( mPlayer != null ) {
            mPlayer.stop();
        }
    }
}
