package LeetCode;

import java.util.*;

/**
 * Created by warn on 1/4/2016.
 * Store puzzles with tag graph
 */
public class TagGraph {
    /**
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
     * the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin
     * with JFK.
     * <p>
     * Note:
     * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
     * when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
     * ["JFK", "LGB"].
     * All airports are represented by three capital letters (IATA code).
     * You may assume all tickets form at least one valid itinerary.
     * Example 1:
     * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
     * Example 2:
     * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
     * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
     * <p>
     * https://leetcode.com/problems/reconstruct-itinerary/
     *
     * @param tickets a list of airline tickets represented by pairs of departure and arrival airports
     * @return reconstruct the itinerary in order
     */
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) return null;
        Map<String, List<String[]>> travelMap = new HashMap<>();
        int ticketNum = tickets.length;
        for (int i = 0; i < ticketNum; i++) {
            if (travelMap.containsKey(tickets[i][0])) {
                travelMap.get(tickets[i][0]).add(new String[]{tickets[i][1], Integer.toString(i)});
            } else {
                travelMap.put(tickets[i][0], new ArrayList<>(Collections.singletonList(new String[]{tickets[i][1],
                        Integer.toString(i)})));
            }
        }
        for (String key : travelMap.keySet()) {
            travelMap.get(key).sort((o1, o2) -> o2[0].compareTo(o1[0]));
        }
        Stack<List<Integer>> ticketUsed = new Stack<>();
        Stack<List<String>> traveledAirport = new Stack<>();
        for (String[] firstTarget : travelMap.get("JFK")) {
            traveledAirport.add(new ArrayList<>(Arrays.asList("JFK", firstTarget[0])));
            ticketUsed.add(new ArrayList<>(Collections.singletonList(Integer.parseInt(firstTarget[1]))));
        }
        while (!ticketUsed.isEmpty()) {
            List<String> frontier = traveledAirport.pop();
            List<Integer> frontierUsedTicket = ticketUsed.pop();
            if (travelMap.containsKey(frontier.get(frontier.size() - 1))) {
                List<String[]> possibleNextCity = travelMap.get(frontier.get(frontier.size() - 1));
                for (String[] nextAirport : possibleNextCity) {
                    if (frontierUsedTicket.indexOf(Integer.parseInt(nextAirport[1])) > 0) continue;
                    else {
                        List<Integer> newTicketUsed = new ArrayList<>(frontierUsedTicket);
                        newTicketUsed.add(Integer.parseInt(nextAirport[1]));
                        List<String> newTravelMap = new ArrayList<>(frontier);
                        newTravelMap.add(nextAirport[0]);
                        if (newTravelMap.size() > ticketNum) return newTravelMap;
                        traveledAirport.add(newTravelMap);
                        ticketUsed.add(newTicketUsed);
                    }
                }
            }
        }
        return null;
    }
}
