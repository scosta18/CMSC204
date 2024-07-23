package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class TownGraphManager implements TownGraphManagerInterface{

	Graph  graph;
	
	public TownGraphManager() {
		graph = new Graph();
	}
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		return graph.addEdge(new Town(town1), new Town(town2), weight, roadName)!=null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		// TODO Auto-generated method stub
		return graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return graph.addVertex(new Town(v));
	}

	@Override
	public Town getTown(String name) {
		for(Town t: graph.vertexSet()) {
			if(t.equals(new Town(name))) {
				return t;
			}
		}
		return null;
	}
	
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graph.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String>storage = new ArrayList<>();
		graph.edgeSet().stream().sorted().forEach((road)->storage.add(road.toString()));
		return storage;
	}

	@Override
	public boolean containsTown(String v) {
		return graph.containsVertex(new Town(v));
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		return graph.removeEdge(new Town(town1), new Town(town2), graph.getEdge(new Town(town1), new Town(town2)).getWeight(),road)!=null;
	}
	
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		// TODO Auto-generated method stub
		return graph.shortestPath(new Town(town1), new Town(town2));
	}
	
	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException{
		graph.populateTownGraph(selectedFile);
	}

	@Override
	public boolean deleteTown(String v) {
		// TODO Auto-generated method stub
		return graph.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		// TODO Auto-generated method stub
		ArrayList<String> storage = new ArrayList<>();
		graph.vertexSet().stream().sorted().forEach((town)->storage.add(town.toString()));
		return storage;
	}

}
