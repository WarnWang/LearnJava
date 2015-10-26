import java.util.ArrayList;

public class Main {
	
	public static ArrayList<int []> findPossibleNextPoint(int[] start_point, 
			int[] allElement, int[][] grid) {
		ArrayList<int []> possibleNextPoint = new ArrayList<>();
		int i = start_point[0];
		int j = start_point[1];
		for (int m = j - 1; m < j + 2; m ++){
			if (m == j) {
				if (i + 1 < grid.length && grid[i + 1][j] >= 0) {
					possibleNextPoint.add(new int [] {i + 1, j});
				}
			}else{
				int possibleI = i;
				int possibleJ = m;
				if (m < 0) {
					possibleJ = grid[0].length + m;
				} else if (m == grid[0].length) {
					possibleJ = 0;
				}
				if (allElement[possibleI * grid[0].length + possibleJ] >= 0) {
					possibleNextPoint.add(new int[] {possibleI, possibleJ});
				}
			}
		}
		return possibleNextPoint;
	}
	
	public static int findEachMaxPath(int[] start_point, int[][] grid, 
			int tempPath, int[] allElements) {
		int maxPath = -1;
		int m = grid.length;
		int n = grid[0].length;
		
		int[] newAllElements = new int[m * n];
		for (int i = 0; i < n * m; i++) newAllElements[i] = allElements[i];
		newAllElements[start_point[0] * n + start_point[1]] = -1;
		
		ArrayList<int[]> possibleNextPoint = 
				findPossibleNextPoint(start_point, newAllElements, grid);
		
		if (possibleNextPoint.size() == 0) {
			if (start_point[0] < grid.length - 1) maxPath = -1;
			else maxPath = tempPath;
		} else {
			for (int[] i : possibleNextPoint) {
				int tempMaxPath = 0;
				tempMaxPath = findEachMaxPath(i, grid, 
						tempPath + grid[i[0]][i[1]], newAllElements);
				maxPath = Math.max(tempMaxPath, maxPath);
			}
		}
		
		return maxPath;
	}
	
	public static int findMaxPath(int[][] grid){
		int maxPath = -1;
		int[] allElements = new int[grid.length * grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++){
				allElements[i*grid.length + j] = grid[i][j];
			}
		}
		
		for (int i = 0; i < grid[0].length; i++) {
			if (grid[0][i] < 0) {
				continue;
			}
			int tempPath = findEachMaxPath(new int[] {0, i}, grid, grid[0][i],
					allElements);
			maxPath = Math.max(tempPath, maxPath);
		}
		return maxPath;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int m = Integer.parseInt(args[1]);
		int[][] grid = new int[m][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[j][i] = Integer.parseInt(args[2 + i * m + j]);
			}
		}
		
		System.out.println(findMaxPath(grid));
	}
}
