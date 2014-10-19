package com.n_puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link Gallery.OnFragmentInteractionListener} interface to
 * handle interaction events. Use the {@link Gallery#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class Gallery extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

	// TODO: Rename and change types of parameters
	private String path;

	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment Gallery.
	 */
	// TODO: Rename and change types and number of parameters
	public static Gallery newInstance() {
		Gallery fragment = new Gallery();
		Bundle args = new Bundle();

		fragment.setArguments(args);
		return fragment;
	}

	public Gallery() {

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		String path = sharedPref.getString(getString(R.string.path), "");

		this.path = path;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_gallery, container, false);
		ImageView image = (ImageView) v.findViewById(R.id.imageView1);
		if (!path.equals(""))
			image.setImageBitmap(BitmapFactory.decodeFile(path));
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

}
