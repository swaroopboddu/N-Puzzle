package com.n_puzzle.game;

public class Square {

	@Override
	public String toString() {
		if (isBlank)
			return "";
		return correctPosition + "";
	}

	private int correctPosition;
	private boolean isBlank;

	public Square(int correctPosition, boolean isBlank) {
		super();
		this.correctPosition = correctPosition;
		this.isBlank = isBlank;
	}

	public boolean isBlank() {
		return isBlank;
	}

	public void setBlank(boolean isBlank) {
		this.isBlank = isBlank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + correctPosition;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (correctPosition != other.correctPosition)
			return false;
		return true;
	}

	public int getCorrectPosition() {
		return correctPosition;
	}

	public void setCorrectPosition(int correctPosition) {
		this.correctPosition = correctPosition;
	}

}
