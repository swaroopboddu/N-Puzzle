package com.n_puzzle.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

//from stack overflow
public class BitMapUtil {

	public static Bitmap[] getCroppedImages(Resources resource, int id,
			int width, int height, int size) {
		Bitmap mainImage = lessResolution(resource, id, width, height);
		int newHeight = mainImage.getHeight() / size;
		int newWidth = mainImage.getWidth() / size;
		Bitmap[] res = new Bitmap[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				res[j * size + i] = Bitmap.createBitmap(mainImage,
						i * newWidth, j * newHeight, newWidth, newHeight);
		}
		res[size * size - 1] = Bitmap.createBitmap(newWidth, newHeight,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(res[size * size - 1]);
		canvas.drawColor(0xffffff);
		return res;

	}

	public static Bitmap lessResolution(Resources resource, int id, int width,
			int height) {
		int reqHeight = width;
		int reqWidth = height;
		BitmapFactory.Options options = new BitmapFactory.Options();

		// First decode with inJustDecodeBounds=true to check dimensions
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resource, reqWidth, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeResource(resource, id, options);
	}

	public static Bitmap lessResolution(Resources resource, String path,
			int width, int height) {
		int reqHeight = width;
		int reqWidth = height;
		BitmapFactory.Options options = new BitmapFactory.Options();

		// First decode with inJustDecodeBounds=true to check dimensions
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(resource, reqWidth, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(path, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {

		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public static Bitmap[] getCroppedImages(Resources resources, String path,
			int width, int height, int size) {
		Bitmap mainImage = lessResolution(resources, path, width, height);
		int newHeight = mainImage.getHeight() / size;
		int newWidth = mainImage.getWidth() / size;
		Bitmap[] res = new Bitmap[size * size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				res[j * size + i] = Bitmap.createBitmap(mainImage,
						i * newWidth, j * newHeight, newWidth, newHeight);
		}
		res[size * size - 1] = Bitmap.createBitmap(newWidth, newHeight,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(res[size * size - 1]);
		canvas.drawColor(0xffffff);
		return res;
	}

}
