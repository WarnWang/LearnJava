import java.util.ArrayList;

public class Main {
	private static int rows, cols;
	private static int[] allElements;
	private static int[] newAllElements;
	private static int[] dAllElements;
	
	public static ArrayList<Integer> findPossibleNextPoint(int start_point) {
		ArrayList<Integer> possibleNextPoint = new ArrayList<Integer>();
		int[] m = new int [] {start_point -1, start_point + 1, 
				start_point + cols};
		int row = start_point / cols;
		for (int i: m){
			int testPoint = i;
			if (i < row * cols) {
				testPoint = start_point + cols - 1;
			} else if (i == (row + 1) * cols) {
				testPoint = row * cols;
			} else if (testPoint > allElements.length) {
				continue;
			}
			int[] temp = newAllElements.clone();
			if (newAllElements[testPoint] >= 0) {
				possibleNextPoint.add(testPoint);
			}
		}
		return possibleNextPoint;
	}
	
	public static int findEachMaxPath(int start_point, int tempPath) {
		int maxPath = -1;
		
		newAllElements[start_point] = -1;
		
		ArrayList<Integer> possibleNextPoint = 
				findPossibleNextPoint(start_point);
		
		if (possibleNextPoint.size() == 0) {
			if (start_point / cols < rows - 1) maxPath = -1;
			else maxPath = tempPath;
			System.out.println();
		} else {
			for (int i : possibleNextPoint) {
				int tempMaxPath = 0;
				System.out.print(allElements[i] + " ");
				tempMaxPath = findEachMaxPath(i, 
						tempPath + allElements[i]);
				maxPath = Math.max(tempMaxPath, maxPath);
			}
		}
		newAllElements[start_point] = allElements[start_point];
		
		int[] temp = newAllElements.clone();
		int[] temp2 = allElements.clone();
		
		return maxPath;
	}
	
	public static int findMaxPath(){
		int maxPath = -1;
		
		for (int i = 0; i < cols; i++) {
			newAllElements = allElements.clone();
			if (allElements[i] < 0) {
				continue;
			}
			int tempPath = findEachMaxPath(i, allElements[i]);
			maxPath = Math.max(tempPath, maxPath);
		}
		return maxPath;
	}
	
	public static int max(int[] a) {
		int maxNum = -9999999;
		for (int i : a) {
			maxNum = Math.max(maxNum, i);
		}
		return maxNum;
	}
	
	public static int calculateThePathFromStart(int point) {
		int path = 0;
		int row = point / cols;
		int col = point % cols;
		int[] dFrom = new int[3];
		if (allElements[point] < 0) {
			path = -1;
		} else {
			if (col == 0) dFrom[0] = 0;
			else dFrom[0] = dAllElements[point - 1];
			
			if (col == cols - 1) dFrom[1] = 0;
			else dFrom[0] = dAllElements[point + 1];
			
			if (row == 0) dFrom[2] = -1;
			else dFrom[0] = dAllElements[point - cols];
			path = max(dFrom) + allElements[point];
		}
		return path;
	}
	
	public static int findMaxPathDp(int start_point) {
		int maxPath = -1;
		
		// find the distance before start point.
		for (int i = start_point - 1; i >= 0; i--) 
			dAllElements[i] = calculateThePathFromStart(i);
		for (int i = start_point + 1; i < allElements.length; i++) 
			dAllElements[i] = calculateThePathFromStart(i);
		
		for (int i = 0; i < cols; i++)
			maxPath = Math.max(maxPath, dAllElements[rows * cols - i - 1]);
		return maxPath;
	}
	
	public static int findMaximumPath(){
		int maxPath = -1;
		
		// find 
		for (int i = 0; i < cols; i ++){
			dAllElements = new int[rows * cols];
			
			// if start_point is 0, then the start point has nothing to do with
			// our answer.
			if (allElements[i] < 0) {
				continue;
			}
			dAllElements[i] = allElements[i];
			int tempPath = findMaxPathDp(i);
			maxPath = Math.max(tempPath, maxPath);
		}
		return maxPath;
	}

	public static void main(String[] args) {
		cols = Integer.parseInt(args[0]);
		rows = Integer.parseInt(args[1]);
		allElements = new int[cols * rows];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) allElements[i * cols + j] = 
					Integer.parseInt(args[j * rows + i + 2]);
		}
		
		for (int i : allElements) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println(findMaximumPath());
	}
}
