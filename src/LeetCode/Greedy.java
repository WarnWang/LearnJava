package LeetCode;

/**
 * Created by warn on 25/3/2016.
 * Use to store those questions with tag Greedy
 */
public class Greedy {

    public static void main(String[] args) {
        Greedy test = new Greedy();
        int[] gas = {1, 2, 1, 9, 6, 7, 8, 9, 100};
        int[] cost = {4, 6, 7, 8, 10, 8, 9, 10, 11};
        System.out.println(test.canCompleteCircuit(gas, cost));
    }

    /**
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     * <p>
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next
     * station (i+1). You begin the journey with an empty tank at one of the gas stations.
     *
     * @param gas  the amount of gas at station i is gas[i]
     * @param cost it costs cost[i] of gas to travel from station i to its next station (i+1)
     * @return Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) return -1;
        int nGas = gas.length;
        if (nGas != cost.length || nGas == 0) return -1;
        int startIndex = -1;
        for (int i = 0; i < nGas; i++) {
            if (gas[i] >= cost[i]) {
                startIndex = i;
                break;
            }
        }
        if (startIndex == -1) return -1;
        int totalGas = 0;
        int totalCost = 0;
        for (int i = 0; i < nGas; i++) {
            int index = (startIndex + i) % nGas;
            totalGas += gas[index];
            totalCost += cost[index];
            if (totalGas < totalCost) {
                int temp = startIndex;
                for (int j = i + 1; j < nGas; j++) {
                    if (gas[j] >= cost[j]) {
                        startIndex = j;
                        break;
                    }
                }
                if (startIndex == temp) return -1;
                i = 0;
                totalCost = cost[startIndex];
                totalGas = gas[startIndex];
            }
        }
        return startIndex;
    }
}
