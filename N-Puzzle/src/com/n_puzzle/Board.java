package com.n_puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.n_puzzle.bitmap.BitMapUtil;
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
	public static final String ARG_NUM_GAMES = "num_games";
	public static final String ARG_NUM_OWN = "num_own";
	public static final String ARG_AVG = "average";
	private int size;
	private GameUtil game;
	private ArrayAdapter<Square> adapter;
	private GridView grid;
	private String path;

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
	public static Fragment newInstance(int size) {
		Fragment fragment = new Board();
		Bundle args = new Bundle();
		args.putInt(ARG_SIZE, size);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		save();
		super.onDetach();
	}

	public void save() {
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putInt(ARG_NUM_GAMES, game.getNumberOfGames());
		editor.putInt(ARG_NUM_OWN, game.getNumberOfOwn());
		editor.putFloat(ARG_AVG, (float) game.getAverage());
		editor.commit();
	}

	public Board() {
		// Required empty public constructor
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		size = 3;
		setGame();

	}

	private void setGame() {
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		path = sharedPref.getString(getString(R.string.path), "");
		double avg = sharedPref.getFloat(ARG_AVG, 0);
		int numOfGames = sharedPref.getInt(ARG_NUM_GAMES, 0);
		int numOfOwn = sharedPref.getInt(ARG_NUM_OWN, 0);
		int width = getResources().getDisplayMetrics().widthPixels;
		int height = getResources().getDisplayMetrics().heightPixels / 2;

		if (path.isEmpty()) {
			game = new GameUtil(size, BitMapUtil.getCroppedImages(
					getResources(), R.drawable.ash, width, height, size),
					numOfGames + 1, numOfOwn, avg);

		} else {
			game = new GameUtil(size, BitMapUtil.getCroppedImages(
					getResources(), path, width, height, size), numOfGames + 1,
					numOfOwn, avg);

		}
	}

	private TextView result;
	private TextView count;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_board, container, false);

		grid = (GridView) v.findViewById(R.id.board2);
		result = (TextView) v.findViewById(R.id.result_label2);
		count = (TextView) v.findViewById(R.id.count2);

		grid.setId(1);
		grid.setNumColumns(size);
		adapter = new CustomArrayAdapter(getActivity(), R.layout.square,
				game.getList());
		grid.setAdapter(adapter);
		grid.setColumnWidth(game.getWidth() / size);

		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (game.swap(position)) {
					adapter.clear();
					adapter.addAll(game.getList());
					adapter.notifyDataSetChanged();
					count.setText(game.getNumberOfMoves() + "");

					if (game.isGameOver()) {
						result.setText("Congrats!! You completed the puzzle");
					}
				}
			}
		});

		Button button = (Button) v.findViewById(R.id.reset2);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				reset();
				result.setText("");
			}
		});

		return v;
	}

	public interface CounterUpdaterActivity {
		public void updateCount(int count);

		public void gameOver();
	}

	public void reset() {
		if (game.isGameOver()) {
			save();
			size++;
		}
		setGame();
		adapter.clear();
		adapter.addAll(game.getList());
		grid.setNumColumns(size);
		grid.setColumnWidth(game.getWidth() / size);
		adapter.notifyDataSetChanged();
		count.setText(game.getNumberOfMoves() + "");
	}

}
