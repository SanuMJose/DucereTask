package com.sanu.duceretask.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.sanu.duceretask.Adapters.MyRecyclerViewAdapter;
import com.sanu.duceretask.Interfaces.ButtonInterface;
import com.sanu.duceretask.Interfaces.Button_fragment_interface;
import com.sanu.duceretask.R;
import com.sanu.duceretask.models.Pair;
import com.sanu.duceretask.models.RecyclerModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PuzzleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PuzzleFragment extends Fragment implements Button_fragment_interface, ButtonInterface {

    ButtonInterface buttonInterface;
    RecyclerView recyclerView;
    int maxIndex = 0;
    private static final int[][] directions_matric = new int[][]{
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}};
    int[][] matrix;
    List<Integer> randomList = new ArrayList<Integer>();
    ArrayList<RecyclerModel> recyclerModel = new ArrayList<>();
    ArrayList<Integer> neigbourcellsList = new ArrayList<>();
    MyRecyclerViewAdapter adapter;
    Button solve_farm_btn;

    int numberOfColumns = 5;
    int totalgrids = numberOfColumns * numberOfColumns;

    ArrayList<Integer> matrixzerothrow = new ArrayList<>();
    ArrayList<Integer> matrixfirstrow = new ArrayList<>();
    ArrayList<Integer> matrixsecondrow = new ArrayList<>();
    ArrayList<Integer> matrixthirdrow = new ArrayList<>();
    ArrayList<Integer> matrixfourthrow = new ArrayList<>();


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
        solve_farm_btn = (Button) view.findViewById(R.id.solve_big_btn);
        buttonInterface = (ButtonInterface) getActivity();

        randomList.clear();


        for (int i = 0; i < totalgrids; ) {
            int rand = ((int) (Math.random() * totalgrids)) + 1;
            if (!randomList.contains(rand)) {
                randomList.add(rand);
                i++;
            }
        }


        Log.i("randomlist", "" + randomList);

        for (int i = 0; i < randomList.size(); i++) {
            RecyclerModel newrecyclerModel = new RecyclerModel("0", 0, randomList.get(i) % 2);
            recyclerModel.add(newrecyclerModel);
        }

        getneighbour(recyclerModel);

        // set up the RecyclerView
        recyclerView = view.findViewById(R.id.rvNumbers);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getActivity(), recyclerModel);

        recyclerView.setAdapter(adapter);

        solve_farm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solve_farm_btn.setBackgroundResource(R.color.grey);
                buttonInterface.button_colour();
                colourbigfarm();

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void button_colour_fragment() {
        solve_farm_btn.setBackgroundResource(R.color.grey);
    }

    @Override
    public void button_colour() {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /*  Method  to find number of neighbours with same colour*/


    public void getneighbour(ArrayList<RecyclerModel> newlist) {


        for (int i = 0; i < newlist.size(); i++) {


            int top = -1, bottom = -1, right = -1, left = -1, count = 0;
            top = i - numberOfColumns;
            bottom = i + numberOfColumns;
            left = i - 1;
            right = i + 1;
            if (newlist.get(i).colourcount == 0) {

                if (top > -1) {
                    if (newlist.get(i).colourcount == newlist.get(top).colourcount) {
                        count++;
                    }
                }

                if (bottom < totalgrids) {
                    if (newlist.get(i).colourcount == newlist.get(bottom).colourcount) {
                        count++;
                    }
                }

                if (i % numberOfColumns != 4 && right < totalgrids && i > -1) {
                    if (newlist.get(i).colourcount == newlist.get(right).colourcount) {
                        count++;
                    }
                }

                if (i % numberOfColumns != 0 && left < totalgrids && i > 0) {
                    if (newlist.get(i).colourcount == newlist.get(left).colourcount) {
                        count++;
                    }
                }

                newlist.get(i).setCount(count);
            }
        }

        neigbourcellsList.clear();
        for (int i = 0; i < newlist.size(); i++) {
            neigbourcellsList.add(newlist.get(i).count);

        }


        for (Integer number : neigbourcellsList) {

            int newnumber = number;


            if ((newnumber > neigbourcellsList.get(maxIndex))) {


                maxIndex = neigbourcellsList.indexOf(newnumber);


            }


        }
    }

    public void colourbigfarm() {

        create_matrix();
        matrix = new int[][]{
                convertIntegers(matrixzerothrow),
                convertIntegers(matrixfirstrow),
                convertIntegers(matrixsecondrow),
                convertIntegers(matrixthirdrow),
                convertIntegers(matrixfourthrow)};


        Stack<Pair> stack = new Stack<>();
        Set<Pair> visited = new HashSet<>();


        int xcordinate = maxIndex / numberOfColumns;
        int ycordinate = maxIndex % numberOfColumns;


        Pair start = new Pair(xcordinate, ycordinate);
        stack.add(start);

        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            if (matrix[pair.row][pair.col] == 4) {
                continue;
            }
            for (int i = 0; i < directions_matric.length; i++) {
                int x = pair.row + directions_matric[i][0];
                int y = pair.col + directions_matric[i][1];
                if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] != 0 || visited.contains(new Pair(x, y))) {
                    continue;
                }
                stack.add(new Pair(x, y));
            }
            visited.add(pair);


            matrix[pair.row][pair.col] = 4;
        }

        resetadaapter(matrix);

    }
    /*reseting adapter to change green*/

    public void resetadaapter(final int[][] matrix) {
        recyclerModel.clear();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                RecyclerModel newrecyclerModel = new RecyclerModel("0", 0, matrix[i][j]);
                recyclerModel.add(newrecyclerModel);

            }

            adapter.notifyDataSetChanged();
        }
    }


    /*  creating single row for matrix*/

    public void create_matrix() {
        matrixzerothrow.clear();
        matrixfirstrow.clear();
        matrixsecondrow.clear();
        matrixthirdrow.clear();
        matrixfourthrow.clear();
        for (int i = 0; i < recyclerModel.size(); i++) {
            if (i < numberOfColumns) {
                matrixzerothrow.add(recyclerModel.get(i).getColourcount());
            } else if (i > 4 && i < numberOfColumns*2) {
                matrixfirstrow.add(recyclerModel.get(i).getColourcount());
            } else if (i > 9 && i < numberOfColumns*3) {
                matrixsecondrow.add(recyclerModel.get(i).getColourcount());
            } else if (14 > 4 && i < numberOfColumns*4) {
                matrixthirdrow.add(recyclerModel.get(i).getColourcount());
            } else if (i > 19 && i < numberOfColumns*numberOfColumns) {
                matrixfourthrow.add(recyclerModel.get(i).getColourcount());
            }
        }
    }


    /*arraylist to array*/

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }
}
