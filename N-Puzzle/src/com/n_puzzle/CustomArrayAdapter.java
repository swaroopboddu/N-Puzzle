package com.n_puzzle;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.n_puzzle.game.Square;

public class CustomArrayAdapter extends ArrayAdapter<Square> {

	private Context context;

	public CustomArrayAdapter(Context context, int resource,
			List<Square> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	/* private view holder class */
	private class ViewHolder {
		ImageView imageView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Square square = getItem(position);

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.square, null);
			holder = new ViewHolder();
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();
		holder.imageView.setImageBitmap(square.getCroppedImage());

		return convertView;
	}

}
