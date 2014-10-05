package com.n_puzzle.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameUtil {
	
	private List<Square> list;
	private int size;
	public GameUtil(int size)
	{
		this.size =size;
		int totalSize = size*size;
		list = new ArrayList<Square>(totalSize);
		for(int i=totalSize-1;i>0;i--)
		{
			list.add(new Square(i,false));
		}
		if(totalSize%2==0)
		{
			Square last = list.get(totalSize-2);
			list.set(totalSize-2, list.get(totalSize-3));
			list.set(totalSize-3,last);
		}
		list.add(new Square(totalSize,true));
	}
	
	public boolean swap(int position)
	{
		int row = (position)/size;
		int col = (position)%size;
		//check right
		if(col!=size-1 && list.get(position+1).isBlank())
		{
			Collections.swap(this.list,position,position+1);
			return true;
		}
		//move left
		else if(col!=0 && list.get(position-1).isBlank())
		{
			Collections.swap(this.list,position,position-1);
			return true;
		}
		
		//move up
		else if(row!=0 && list.get((row-1)*size+col).isBlank())
		{
			Collections.swap(this.list,position,(row-1)*size+col);
			return true;
		}
		
		//move down
		else if(row!=size-1 && list.get((row+1)*size+col).isBlank())
		{
			Collections.swap(this.list,position,(row+1)*size+col);
			return true;
		}
		return false;
		
	}

	public List<Square> getList() {
		return new ArrayList<Square>(list);
	}

	public void setList(List<Square> list) {
		this.list = list;
	}

	
	
	

}
