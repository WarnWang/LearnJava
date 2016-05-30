package LeetCode.Algorithm;

import LeetCode.DataTypes.UndirectedGraphNode;

import java.util.*;

/**
 * Created by warn on 1/4/2016.
 * Store puzzles with tag graph
 */
public class TagGraph {
    private Map<String, ArrayList<String>> ticketsMap = new HashMap<>();
    private int ticketLength;

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
        if (ticketNum == 1) return Arrays.asList(tickets[0]);
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
        Stack<Set<Integer>> ticketUsed = new Stack<>();
        Stack<List<String>> traveledAirport = new Stack<>();
        for (String[] firstTarget : travelMap.get("JFK")) {
            traveledAirport.add(new ArrayList<>(Arrays.asList("JFK", firstTarget[0])));
            ticketUsed.add(new HashSet<>(Collections.singletonList(Integer.parseInt(firstTarget[1]))));
        }
        while (!ticketUsed.isEmpty()) {
            List<String> frontier = traveledAirport.pop();
            Set<Integer> frontierUsedTicket = ticketUsed.pop();
            if (travelMap.containsKey(frontier.get(frontier.size() - 1))) {
                List<String[]> possibleNextCity = travelMap.get(frontier.get(frontier.size() - 1));
                for (String[] nextAirport : possibleNextCity) {
                    if (frontierUsedTicket.contains(Integer.parseInt(nextAirport[1]))) continue;
                    else {
                        Set<Integer> newTicketUsed = new HashSet<>(frontierUsedTicket);
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

    public List<String> findItineraryRecursively(String[][] tickets) {
        if (tickets == null || tickets.length == 0) return null;
        else if (tickets.length == 1) return Arrays.asList(tickets[0]);
        for (String[] ticket : tickets) {
            if (!ticketsMap.containsKey(ticket[0])) {
                ticketsMap.put(ticket[0], new ArrayList<>());
            }
            ticketsMap.get(ticket[0]).add(ticket[1]);
        }
        for (ArrayList<String> value : ticketsMap.values()) Collections.sort(value);
        LinkedList<String> travelMap = new LinkedList<>();
        travelMap.add("JFK");
        ticketLength = tickets.length + 1;
        findItineraryRecursively("JFK", travelMap);
        return travelMap;
    }

    private boolean findItineraryRecursively(String nextCity, LinkedList<String> resultList) {
        if (resultList.size() == ticketLength) return true;
        ArrayList<String> possibleNextCity = ticketsMap.get(nextCity);
        if (possibleNextCity == null) return false;
        for (int i = 0, k = possibleNextCity.size(); i < k; i++) {
            String newNextCity = possibleNextCity.get(i);
            possibleNextCity.remove(i);
            resultList.add(newNextCity);
            if (findItineraryRecursively(newNextCity, resultList)) return true;
            else {
                possibleNextCity.add(i, newNextCity);
                resultList.remove(resultList.size() - 1);
            }
        }
        return false;
    }

    /**
     * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
     * <p>
     * OJ's undirected graph serialization:
     * Nodes are labeled uniquely.
     * <p>
     * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
     * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
     * <p>
     * The graph has a total of three nodes, and therefore contains three parts as separated by #.
     * <p>
     * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
     * Second node is labeled as 1. Connect node 1 to node 2.
     * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
     * Visually, the graph looks like the following:
     * 1
     * / \
     * /   \
     * 0 --- 2
     * / \
     * \_/
     *
     * @param node an undirected graph
     * @return the clone of such graph
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        UndirectedGraphNode clonedGraph = new UndirectedGraphNode(node.label);
        HashMap<Integer, UndirectedGraphNode> exploredSet = new HashMap<>();
        exploredSet.put(node.label, clonedGraph);
        Stack<UndirectedGraphNode> nodesToExplore = new Stack<>();
        Stack<UndirectedGraphNode> clonedNodesToExplore = new Stack<>();
        nodesToExplore.add(node);
        clonedNodesToExplore.add(clonedGraph);
        while (!nodesToExplore.isEmpty()) {
            UndirectedGraphNode nodesFrontier = nodesToExplore.pop();
            UndirectedGraphNode clonedNodesFrontier = clonedNodesToExplore.pop();
            for (UndirectedGraphNode tempNode : nodesFrontier.neighbors) {
                UndirectedGraphNode tempClonedNode = (exploredSet.containsKey(tempNode.label)) ? exploredSet.get(tempNode.label) : new UndirectedGraphNode(tempNode.label);
                clonedNodesFrontier.neighbors.add(tempClonedNode);
                if (!exploredSet.containsKey(tempNode.label)) {
                    nodesToExplore.push(tempNode);
                    clonedNodesToExplore.push(tempClonedNode);
                    exploredSet.put(tempNode.label, tempClonedNode);
                }
            }
        }
        return clonedGraph;
    }
}
