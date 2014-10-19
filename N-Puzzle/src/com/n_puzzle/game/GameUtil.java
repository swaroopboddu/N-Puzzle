package com.n_puzzle.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.graphics.Bitmap;

public class GameUtil {

	private List<Square> list;
	private int size;
	private int numberOfMoves;
	private int numberOfGames;
	private int numberOfOwn;
	private double average;
	private String name;

	public GameUtil(int size, Bitmap[] images, int games, int own,
			double average) {
		this.size = size;
		int totalSize = size * size;

		list = new ArrayList<Square>(totalSize);
		for (int i = totalSize - 1; i > 0; i--) {
			list.add(new Square(i, images[i - 1], false));
		}
		if (totalSize % 2 == 0) {
			Square last = list.get(totalSize - 2);
			list.set(totalSize - 2, list.get(totalSize - 3));
			list.set(totalSize - 3, last);
		}
		list.add(new Square(totalSize, images[images.length - 1], true));
		numberOfMoves = 0;
		numberOfGames = games;
		numberOfOwn = own;
		this.average = average;
	}

	public int getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(int numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public int getNumberOfOwn() {
		return numberOfOwn;
	}

	public void setNumberOfOwn(int numberOfOwn) {
		this.numberOfOwn = numberOfOwn;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setNumberOfMoves(int numberOfMoves) {
		this.numberOfMoves = numberOfMoves;
	}

	public boolean isGameOver() {
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 != list.get(i).getCorrectPosition())
				return false;
		}

		numberOfOwn++;
		average = (average*(numberOfGames-1) + numberOfMoves) / numberOfGames;
		return true;
	}

	public boolean swap(int position) {
		if (list.get(position).isBlank())
			return false;
		int row = (position) / size;
		int col = (position) % size;

		// check right
		if (col != size - 1 && list.get(position + 1).isBlank()) {
			Collections.swap(this.list, position, position + 1);
			numberOfMoves++;
			return true;
		}
		// move left
		else if (col != 0 && list.get(position - 1).isBlank()) {
			Collections.swap(this.list, position, position - 1);
			numberOfMoves++;
			return true;
		}

		// move up
		else if (row != 0 && list.get((row - 1) * size + col).isBlank()) {
			Collections.swap(this.list, position, (row - 1) * size + col);
			numberOfMoves++;
			return true;
		}

		// move down
		else if (row != size - 1 && list.get((row + 1) * size + col).isBlank()) {
			Collections.swap(this.list, position, (row + 1) * size + col);
			numberOfMoves++;
			return true;
		}
		return false;

	}

	public int getSize() {
		return size;
	}

	public int getNumberOfMoves() {
		return numberOfMoves;
	}

	public List<Square> getList() {
		return new ArrayList<Square>(list);
	}

	public void setList(List<Square> list) {
		this.list = list;
	}

	public int getWidth() {
		int width = 0;
		for (int i = 0; i < size; i++) {
			width = width + list.get(i).getCroppedImage().getWidth();
		}
		return width;
	}

	public int getHeight() {
		int height = 0;
		for (int i = 0; i < size; i++) {
			height = height + list.get(i).getCroppedImage().getHeight();
		}
		return height;
	}

}
