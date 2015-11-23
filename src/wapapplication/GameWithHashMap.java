package wapapplication;

/**
 * Author: Wang Youan, Date: 2015-10-28
 * 
 * I use dynamic programming to solve this question.
 * I find the fact that the highest score of each line has nothing to do with
 * the formal line, so I calculate each points of the given line, then use these
 * value to calculate those points on the next line.
 * When I reach the right border, I will find the max value of them, then get 
 * the answer.
 */

import java.util.HashMap;

public class GameWithHashMap {

	/**
	 * Two variables to store the number of rows and that of columns of the 
	 * input matrix.
	 * In my program, I transpose the input matrix. So, the rows here actually
	 * means the number of columns in the original matrix, and so is the cols.
	 */
	private static int rows, cols;
	
	/**
	 * This two variables stores the value and the max score one can get when
	 * it reach this point.
	 * I choose HashMap to store this values as if a point value is -1, then i
	 * don't need to handle it. I can simply neglect those point by using 
	 * HashMap.
	 */
	private static HashMap<Integer, Integer> allElements = new HashMap<>();
	private static HashMap<Integer, Integer> dAllElements = new HashMap<>();
	
	/**
	 * A simple function to find the max value of a list.
	 * @param a An integer list.
	 * @return the maximum of the given list.
	 */
	public static int max(int[] a) {
		int maxNum = -1;
		for (int i : a) {
			maxNum = Math.max(maxNum, i);
		}
		return maxNum;
	}
	
	/**
	 * Base on the last line's value to calculate the highest score of this 
	 * point.
	 * @param point tell this function to find the score of which.
	 * @return
	 */
	public static int findMaxDeepth(int point) {
		int path = -1;
		int rowOfPoint = point / rows;
		
		/**
		 * deepFromPoint stores the highest score one can get start from the
		 * last line. For example, I want to find an point (i, j), 
		 * deepFromPoint[0] stores the values of the highest score the (i-1,j)
		 * plus the score in (i, j)
		 */
		int[] deepFromPoint = new int[cols];

		/**
		 * The following part used to calculate deepFromPoint[0]
		 */
		if (point < cols) {
			deepFromPoint[0] = allElements.get(point);
		} else if (!dAllElements.containsKey(point - cols)){
			deepFromPoint[0] = -1;
		} else{
			deepFromPoint[0] = allElements.get(point) + 
					dAllElements.get(point - cols);
		}
		
		for (int step = -1; step <= 1; step += 2){
			for (int j = 1; j < deepFromPoint.length; j++) {
				deepFromPoint[j] = -1;
			}
			
			for (int i = step; Math.abs(i) < cols; i += step){
				int startPoint = point + i;
				int trueStartPoint = startPoint;
				int startPath = 0;

				if (startPoint < rowOfPoint * cols) {
					trueStartPoint = rowOfPoint * cols;
					
					// If this line contains -1, there no need to calculate.
					if (!allElements.containsKey(startPoint + cols)) break;
					
				} else if (startPoint >= ((rowOfPoint +1 ) *cols)) {
					trueStartPoint = (rowOfPoint + 1) * cols - 1;
					
					// If this line contains -1, there no need to calculate.
					if (!allElements.containsKey(startPoint - cols)) break;
					
				} else if (startPoint >= cols) {
					if (!allElements.containsKey(startPoint)) break;
					if (dAllElements.containsKey(startPoint - cols)){
						startPath = dAllElements.get(startPoint - cols);
					} else {
						startPath = -1;
					}
				}
				
				for (int k = trueStartPoint; k != point && startPath != -1; 
						k -= step){
					if (!allElements.containsKey(k)) {
						startPath = -1;
						break;
					}
					startPath += allElements.get(k);
				}
				if (startPath != -1) {
					startPath += allElements.get(point);
				}
				deepFromPoint[Math.abs(i)] = startPath;
			}
			path = Math.max(max(deepFromPoint), path);
		}
		return path;
	}
	
	/**
	 * this function used to go over these valid points.
	 * @return the highest score one can get. If no such score, will return -1.
	 */
	public static int calculateLineDeepth() {
		int maxPath = -1;

		// Calculate the highest score each point can get.
		for (int j: allElements.keySet()){
			int path = findMaxDeepth(j);
			if (path >= 0) {
				dAllElements.put(j, path);
			}
		}
		
		// Find the highest score one can get.
		for (int i = cols * rows - 1; i >= (rows - 1) * cols; i--){
			if (dAllElements.containsKey(i)){
				maxPath = Math.max(maxPath, dAllElements.get(i));
			}
		}
		
		return maxPath;
	}

	public static void main(String[] args) {
		
		// This is the input of this exam1. 
		String input = "4 4\n"
				+ "-1 4 5 1\n"
				+ "2 -1 2 4\n"
				+ "3 3 -1 3\n"
				+ "4 2 1 2\n";
		
		String[] temp = input.split("\n");
		cols = Integer.parseInt(temp[0].split(" ")[0]);
		rows = Integer.parseInt(temp[0].split(" ")[1]);
		
		for (int i = 1; i < temp.length; i++) {
			String[] string = temp[i].split(" ");
			for (int j = 0; j < string.length; j++) {
				int argValue = Integer.parseInt(string[j]);
				if (argValue != -1) {
					allElements.put((i - 1) + j * rows, argValue);
				}
			}
			
		}
		
		System.out.println(calculateLineDeepth());
	}

}
