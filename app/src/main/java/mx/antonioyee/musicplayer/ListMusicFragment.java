package mx.antonioyee.musicplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMusicFragment extends Fragment {

    private static final String ARG_PARAM_ARRAY = "paramArray";
    private ArrayList<Music> mArray;
    private ListView listMusicFragment;
    private MusicAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public static ListMusicFragment newInstance(ArrayList<Music> mArray) {
        ListMusicFragment fragment = new ListMusicFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ARRAY, mArray);
        fragment.setArguments(args);
        return fragment;
    }

    public ListMusicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mArray = (ArrayList<Music>) getArguments().getSerializable(ARG_PARAM_ARRAY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_list_music, container, false);

        listMusicFragment = (ListView) viewRoot.findViewById(R.id.listNamesFragment);
        ArrayList<Music> music = Music.getSongs(getActivity());
        adapter = new MusicAdapter(getActivity(), R.layout.simple_list_item_1, music);

        listMusicFragment.setAdapter(adapter);

        listMusicFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if ( mListener != null ){
                    mListener.onMusicSelect(position);
                }

            }
        });

        return viewRoot;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onMusicSelect(int position);
    }

}
