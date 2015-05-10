package mx.antonioyee.musicplayer;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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

        if ( resourceType.equals("sw600dp") ){
            fragmentManager.beginTransaction()
                    .replace(R.id.containerLeft, ListMusicFragment.newInstance(Music.getSongs(getApplicationContext())))
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ListMusicFragment.newInstance(Music.getSongs(getApplicationContext())))
                    .commit();
        }
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
        if ( resourceType.equals("sw600dp") ){
            fragmentManager.beginTransaction()
                    .replace(R.id.containerRight,PlayerFragment.newInstance(position))
                    .commit();
        }else{
            fragmentManager.beginTransaction()
                    .replace(R.id.container,PlayerFragment.newInstance(position))
                    .addToBackStack(null)
                    .commit();
        }
    }
}
