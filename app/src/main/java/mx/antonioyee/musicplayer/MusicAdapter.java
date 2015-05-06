package mx.antonioyee.musicplayer;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by antonioyee on 06/05/15.
 */
public class MusicAdapter extends ArrayAdapter {

    ArrayList<Music> array;
    private Context context;

    public MusicAdapter(Context context, int resource, ArrayList<Music> array) {
        super(context, resource, array);
        this.array = array;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(R.layout.simple_list_item_1, parent, false);
        ViewHolder holder = new ViewHolder();

        holder.textTitle = (TextView) rootView.findViewById(R.id.textTitle);
        holder.textArtist = (TextView) rootView.findViewById(R.id.textArtist);
        holder.textTime = (TextView) rootView.findViewById(R.id.textTime);
        holder.photoAlbum = (ImageView) rootView.findViewById(R.id.photoAlbum);
        holder.textTitle.setText(array.get(position).getTitle());
        holder.textArtist.setText(array.get(position).getArtist());
        holder.textTime.setText(array.get(position).getDuration());
        holder.photoAlbum.setImageDrawable(array.get(position).getPhotoAlbum());

        return rootView;
    }

    class ViewHolder {
        TextView textTitle;
        TextView textArtist;
        TextView textTime;
        ImageView photoAlbum;
    }
}
