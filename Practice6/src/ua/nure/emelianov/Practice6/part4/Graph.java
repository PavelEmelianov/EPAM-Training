package ua.nure.emelianov.Practice6.part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph {

	Graph(int number) {
		setVertexNumber(number);
		addVertexes();
	}

	private int vertexNumber;

	public final int getVertexNumber() {
		return vertexNumber;
	}

	public final void setVertexNumber(int vertexNumber) {
		this.vertexNumber = vertexNumber;
	}

	private Map<String, List<String>> graphMap = new TreeMap<String, List<String>>();

	public final void addVertexes() {
		for (int i = 1; i <= getVertexNumber(); i++) {
			graphMap.put(String.valueOf(i), new ArrayList<String>());
		}
	}

	public String addEdge(String firstVertex, String secondVertex) {

		if (Integer.valueOf(firstVertex) > getVertexNumber()
				|| Integer.valueOf(secondVertex) > getVertexNumber()
				|| Integer.valueOf(firstVertex) < 0
				|| Integer.valueOf(secondVertex) < 0) {
			return firstVertex + " and(or) " + secondVertex
					+ " are illegal vertexes, select vertexes from 1 to "
					+ getVertexNumber();
		}
		if (Integer.valueOf(firstVertex).equals(Integer.valueOf(secondVertex))) {
			return "Cannot create edge, vertexes are equal";
		}
		List<String> list = getGraphMap().get(firstVertex);
		List<String> list2 = getGraphMap().get(secondVertex);
		if (list.contains(secondVertex) && list2.contains(firstVertex)) {
			return "The edge between " + firstVertex + " and  " + secondVertex
					+ " already exists";
		}
		list.add(secondVertex);
		list2.add(firstVertex);
		return "added a new edge between " + firstVertex + " and "
				+ secondVertex;
	}

	public String removeEdge(String firstVertex, String secondVertex) {
		List<String> list = getGraphMap().get(firstVertex);
		List<String> list2 = getGraphMap().get(secondVertex);
		if (list.contains(secondVertex) && list2.contains(firstVertex)) {
			list.remove(secondVertex);
			list2.remove(firstVertex);
			return "Edge between " + firstVertex + " and  " + secondVertex
					+ " was removed";
		} else {
			return "The edge between " + firstVertex + " and " + secondVertex
					+ " does not exist";
		}
	}

	public Map<String, List<String>> getGraphMap() {
		return graphMap;
	}

}
