import java.util.*;
import java.lang.*;
import java.io.*;

class eyepaxChallenge {
	static int c = 0;
	static int r = 0;
	//c=n r=m
	
	static int largestCount;
	static int visited[][];
	static int result[][];
	 
	//Main method
	public static void main(String args[])
	{
		//init the Grid
		String grid [][]= {{"Red","Blue","Red"},
						   {"Green","Blue","Green"},
						   {"Red","Blue","Blue"}};
		
		//To get the coordinates
		c = grid.length;
		r = grid[0].length;
		visited = new int [r][c];
		result = new int [r][c];
		
		computeLargestConnectedGrid(grid);
	}
	
	static boolean isValidGrid(int x, int y,String key,String data[][]) {
		if (x < c && y < r &&
		        x >= 0 && y >= 0)
		    {
		        if (visited[x][y] == 0 &&
		            data[x][y] == key)
		            return true;
		        else
		            return false;
		    }
		    else
		        return false;
		
	}
	
	static void cellData(String x, String y, int i, int j, String input[][])
	{

		if (x != y)
			return;

		visited[i][j] = 1;
		largestCount++;


		int x_move[] = { 0, 0, 1, -1 };
		int y_move[] = { 1, -1, 0, 0 };


		for (int u = 0; u < 4; u++)
			if ((isValidGrid(i + y_move[u],
					j + x_move[u], x, input)) == true)
				cellData(x, y, i + y_move[u],
						j + x_move[u], input);
	}
	
	static void resetGrid()
	{
	    for (int i = 0; i < c; i++)
	        for (int j = 0; j < r; j++)
	            visited[i][j] = 0;
	}
	
	static void resetResult(String key,String input[][])
	{
		for (int i = 0; i < c; i++)
		{
			for (int j = 0; j < r; j++)
			{
				if (visited[i][j] ==1 &&
						input[i][j] == key)
					result[i][j] = visited[i][j];
				else
					result[i][j] = 0;
			}
		}
	}
	
	static void computeLargestConnectedGrid(String input[][])
	{
	    int current_max = Integer.MIN_VALUE;
	 
	    for (int i = 0; i < c; i++)
	    {
	        for (int j = 0; j < r; j++)
	        {
	        	resetGrid();
	        	largestCount = 0;
	 
	            // checking cell to the right
	            if (j + 1 < r)
	            	cellData(input[i][j], input[i][j + 1],
	                                    i, j, input);
	 
	            // updating result
	            if (largestCount >= current_max)
	            {
	                current_max = largestCount;
	                resetResult(input[i][j], input);
	            }
	            resetGrid();
	            largestCount = 0;
	 
	            // checking cell downwards
	            if (i + 1 < c)
	            	cellData(input[i][j],
	                    input[i + 1][j], i, j, input);
	 
	            // updating result
	            if (largestCount >= current_max)
	            {
	                current_max = largestCount;
	                resetResult(input[i][j], input);
	            }
	        }
	    }
	    System.out.println("Largest count of connected : "+current_max);
	}
}