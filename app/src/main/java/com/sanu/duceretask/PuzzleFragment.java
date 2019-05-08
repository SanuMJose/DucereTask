package com.sanu.duceretask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PuzzleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment {
    List<Integer> list = new ArrayList<Integer>();
    ArrayList<RecyclerModel> recyclerModel = new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    ArrayList<Integer> sanu = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PuzzleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PuzzleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PuzzleFragment newInstance(String param1, String param2) {
        PuzzleFragment fragment = new PuzzleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_puzzle, container, false);

        list.clear();
        sanu.clear();

        for (int i = 0; i < 25; ) {
            int rand = ((int) (Math.random() * 25)) + 1;
            if (!list.contains(rand)) {
                list.add(rand);
                i++;
            }
        }


        Log.i("randomlist", "" + list);
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"};

        for (int i = 0; i < list.size(); i++) {
            RecyclerModel newrecyclerModel = new RecyclerModel(data[i], 0, list.get(i) % 2);
            recyclerModel.add(newrecyclerModel);
        }

        getneighbour(recyclerModel);

        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvNumbers);
        int numberOfColumns = 5;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getActivity(), recyclerModel);

        recyclerView.setAdapter(adapter);
        ;
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getneighbour(ArrayList<RecyclerModel> newlist) {

        for (int i = 0; i < newlist.size(); i++) {


            int top = -1, bottom = -1, right = -1, left = -1, count = 0;
            top = i - 5;
            bottom = i + 5;
            left = i - 1;
            right = i + 1;

            if (top > -1) {
                if (newlist.get(i).colourcount == newlist.get(top).colourcount) {
                    count++;
                }
            }

            if (bottom < 25) {
                if (newlist.get(i).colourcount == newlist.get(bottom).colourcount) {
                    count++;
                }
            }

            if (i % 5 != 4 && right < 25 && i > -1) {
                if (newlist.get(i).colourcount == newlist.get(right).colourcount) {
                    count++;
                }
            }

            if (i % 5 != 0 && left < 25 && i > 0) {
                if (newlist.get(i).colourcount == newlist.get(left).colourcount) {
                    count++;
                }
            }

            newlist.get(i).setCount(count);
        }

    }


}
