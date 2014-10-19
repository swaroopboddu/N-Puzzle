package com.n_puzzle;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.n_puzzle.Board;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Records.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link Records#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class Records extends Fragment {
	private TextView totalGames;
	private TextView totalOwn;
	private TextView average;

	private OnFragmentInteractionListener mListener;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment Records.
	 */
	// TODO: Rename and change types and number of parameters
	public static Records newInstance() {
		Records fragment = new Records();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public Records() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_records, container, false);
		totalGames = (TextView) v.findViewById(R.id.total_games_count);
		totalOwn = (TextView) v.findViewById(R.id.total_games_own_count);
		average = (TextView) v.findViewById(R.id.average_moves_value);
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);

		double avg = sharedPref.getFloat(Board.ARG_AVG, 0);
		int numOfGames = sharedPref.getInt(Board.ARG_NUM_GAMES, 0);
		int numOfOwn = sharedPref.getInt(Board.ARG_NUM_OWN, 0);
		totalGames.setText("" + numOfGames);
		totalOwn.setText("" + numOfOwn);
		average.setText("" + avg);
		return v;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onResume() {
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		double avg = sharedPref.getFloat(Board.ARG_AVG, 0);
		int numOfGames = sharedPref.getInt(Board.ARG_NUM_GAMES, 0);
		int numOfOwn = sharedPref.getInt(Board.ARG_NUM_OWN, 0);

		totalGames.setText("" + numOfGames);
		totalOwn.setText("" + numOfOwn);
		average.setText("" + avg);
		super.onResume();
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		/*
		 * try { mListener = (OnFragmentInteractionListener) activity; } catch
		 * (ClassCastException e) { throw new
		 * ClassCastException(activity.toString() +
		 * " must implement OnFragmentInteractionListener"); }
		 */
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

}
