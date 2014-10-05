package com.n_puzzle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.n_puzzle.game.GameUtil;
import com.n_puzzle.game.Square;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Board.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link Board#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class Board extends Fragment {
	
	private static final String ARG_SIZE = "size";
	
	private int size;
	private GameUtil game;
	
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment Board.
	 */
	// TODO: Rename and change types and number of parameters
	public static Board newInstance(int size) {
		Board fragment = new Board();
		Bundle args = new Bundle();
		args.putInt(ARG_SIZE, size);
		fragment.setArguments(args);
		return fragment;
	}

	public Board() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			size = getArguments().getInt(ARG_SIZE);
			game = new GameUtil(size);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		GridView grid = new GridView(getActivity());
		grid.setId(1);
		
		grid.setNumColumns(size);
	
		grid.setGravity(Gravity.CENTER);
		
		final ArrayAdapter<Square> adapter = new ArrayAdapter<Square>(getActivity(),
				R.layout.square, game.getList());
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				if(game.swap(position))
				{
					adapter.clear();
					adapter.addAll(game.getList());
					adapter.notifyDataSetChanged();
				}
			}
		});
		
		return grid;
	}

	

	

	

	

}