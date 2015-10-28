
public class game {
	private static int rows, cols;
	private static int[] allElements;
	private static int[] dAllElements;
	
	public static int max(int[] a) {
		int maxNum = -9999999;
		for (int i : a) {
			maxNum = Math.max(maxNum, i);
		}
		return maxNum;
	}
	
	public static int findMaxDeepth(int point) {
		int path = -1;
		int rowOfPoint = point / rows;
		if (allElements[point] == -1) {
			path = -1;
		} else {
			int[] deepFromPoint = new int[cols];
			if (point < cols) {
				deepFromPoint[0] = allElements[point];
			} else if (dAllElements[point - cols] == -1){
				deepFromPoint[0] = -1;
			} else{
				deepFromPoint[0] = allElements[point] + 
						dAllElements[point - cols];
			}
			
			// find the deep from two direction
			for (int step = -1; step <= 1; step += 2){
				
				for (int i = step; Math.abs(i) < cols; i += step){
					int startPoint = point + i;
					int trueStartPoint = startPoint;
					int startPath = 0;
					if (startPoint < rowOfPoint * cols) {
						trueStartPoint = rowOfPoint * cols;
						if (startPoint > 0 && dAllElements[startPoint] < 0) {
							deepFromPoint[Math.abs(i) - 1] = -1;
							continue;
						}
						startPath = 0;
					} else if (startPoint >= ((rowOfPoint +1 ) *cols)) {
						trueStartPoint = (rowOfPoint +1 ) * cols - 1;
						if (startPoint >= 2 * cols 
								&& dAllElements[startPoint - 2 * cols] < 0 
								|| startPoint > (rows * cols - 1)) {
							deepFromPoint[Math.abs(i) - 1] = -1;
							continue;
						}
						trueStartPoint = (rowOfPoint +1) * cols -1;
					} else {
						if (startPoint >= cols) {
							startPath = dAllElements[startPoint - cols];
						} else {
							startPath = 0;
						}
					}
					
					for (int k = trueStartPoint; k != point; k -= step){
						if (allElements[k] == -1) {
							startPath = -1;
							break;
						}
						startPath += allElements[k];
					}
					deepFromPoint[Math.abs(i) - 1] = startPath;
				}
				path = Math.max(max(deepFromPoint), path);
			}
		}
		return path;
	}
	
	public static int calculateLineDeepth() {
		int maxPath = -1;
//		for (int j = 0; j < rows; j++){
//			for (int i = 0; i < cols; i++){
//				dAllElements[i] = findMaxDeepth(j * cols + i);
//			}
//		}
		for (int j = 0; j < allElements.length; j++){
			dAllElements[j] = findMaxDeepth(j);
		}
		
		for (int i = 0; i < cols; i++)
			maxPath = Math.max(maxPath, dAllElements[rows * cols - i - 1]);
		
		return maxPath;
	}

	public static void main(String[] args) {
		cols = Integer.parseInt(args[0]);
		rows = Integer.parseInt(args[1]);
		allElements = new int[cols * rows];
		dAllElements = new int[cols * rows];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) allElements[i * cols + j] = 
					Integer.parseInt(args[j * rows + i + 2]);
		}
		
//		for (int i : allElements) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
		
		System.out.println(calculateLineDeepth());
	}

}
