package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class Graph implements GraphInterface<Town, Road>{

	private static final String closesReachableTwon = null;
	HashMap<Town, Set<Road>> map;
	HashMap<Town, Town> pointerMap;
	HashMap<Town, Integer> shortestPathMap;
	ArrayList<String> path;
	Town globalEnd;
	StringBuilder builder;
	
	public Graph() {
		map = new HashMap<>();
		builder = new StringBuilder();
	}

    public void populateTownGraph(File file) throws FileNotFoundException{
		BufferedReader reader = new BufferedReader(new FileReader(file));
		reader.lines().forEach((line)->{
			String[] params = line.split("[!^;,]");
			addVertex(new Town(params[2]));
			addVertex(new Town(params[3]));
			addEdge(new Town(params[2]), new Town(params[3]), Integer.parseInt(params[1]), params[0]);
		});
	}

    @Override
    public Road getEdge(Town sourceVertex, Town destinationVertex) {
        if(!containsVertex(sourceVertex)){
            return null;
        }
        for(Road r : map.get(sourceVertex)){
            if(r.equals(new Road(sourceVertex, destinationVertex, ""))){
                return r;
            }
        }
        return null;
    }
    @Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road1 = new Road(sourceVertex, destinationVertex, weight, description);
		Road roead2 = new Road(destinationVertex,sourceVertex, weight, description);
	    map.get(sourceVertex).add(road1);
	    map.get(destinationVertex).add(roead2);
	    return road1;
	}

	@Override
	public boolean addVertex(Town town) {
		if(map.containsKey(town)) {
			return false;
		}
		map.put(town, new HashSet<>());
		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		for(Road r:map.get(sourceVertex)) {
			if(r.equals(new Road(sourceVertex, destinationVertex, ""))) {
				return true;
			}
		}
		return false;
	}



    @Override
    public boolean containsVertex(Town town) {
        return map.containsKey(town);
    }
    
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road dummy = new Road(sourceVertex, destinationVertex, weight, description);
		map.get(sourceVertex).removeIf((road)-> road.equals(dummy)&& road.getWeight() == dummy.getWeight()&&road.getName().equals(dummy.getName()));
		return dummy;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> set = new HashSet<>();
		map.forEach((town, roads) ->{
			boolean contains = false;
			for(Road r: roads) {
				for(Road check : set) {
					if(r.equals(check)) {
						contains = true;
						break;
					}
				}
			if(!contains) {
				set.add(r);
			}
			else {
				contains = false;
			}
			}
		});
		return set;
	}



    @Override
    public Set<Road> edgesOf(Town v) {
        return map.get(v);
    }

	@Override
	 public boolean removeVertex(Town town) {
       map.remove(town);
       return true;
   }
	
    @Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		map.keySet().forEach((town -> town.visited = false));
		path = new ArrayList<>();
		pointerMap = new HashMap<>();
		
		shortestPathMap = new HashMap<>();
		for(Town t : map.keySet()) {
			shortestPathMap.put(t, Integer.MAX_VALUE);
		}
		shortestPathMap.put(sourceVertex, 0);
		for(Road r: map.get(sourceVertex)) {
			shortestPathMap.put(r.getDestination(), r.getWeight());
			pointerMap.put(r.getDestination(), sourceVertex);
		}
		
		sourceVertex.visited = true;
		globalEnd = destinationVertex;
		dijkstraShortestPath(sourceVertex);
		Collections.reverse(path);
		return path;
	}

	@Override
	 public Set<Town> vertexSet() {
       return map.keySet();
   }
	
	@Override
	 public void dijkstraShortestPath(Town sourceVertex) {
        while(true){
            int shortestDistance = Integer.MAX_VALUE;
            Town closestReachableTown = null;
            for (Town t : map.keySet()) {
                if (!t.visited && (shortestPathMap.get(t) != Integer.MAX_VALUE)){
                    int currentDistance = shortestPathMap.get(t);
                    if (currentDistance < shortestDistance) {
                        shortestDistance = currentDistance;
                        closestReachableTown = t;
                    }
                }
            }
            if(closestReachableTown == null){
                return;
            }
            if(closestReachableTown.equals(globalEnd)){
                Town nextPointer = closestReachableTown;
                Town currentPointer;
                while(true){
                    currentPointer = pointerMap.get(nextPointer);
                    if(currentPointer == null){
                        break;
                    }
                    Road fetch = getEdge(currentPointer, nextPointer);
                    builder.append(currentPointer).append(" via ").append(fetch).append(" to ").append(nextPointer).append(" ").append(fetch.getWeight()).append(" mi");
                    path.add(builder.toString());
                    builder.setLength(0);
                    nextPointer = currentPointer;
                }
                return;
            } else {
                closestReachableTown.visited = true;
                for(Road r : map.get(closestReachableTown)){
                    if(!r.getDestination().visited){
                        if(shortestPathMap.get(closestReachableTown) + r.getWeight() < shortestPathMap.get(r.getDestination())){
                            shortestPathMap.put(r.getDestination(), shortestPathMap.get(closestReachableTown) + r.getWeight());
                            pointerMap.put(r.getDestination(), closestReachableTown);
                        }
                    }
                }
            }
        }
    }
}
