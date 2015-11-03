package ua.nure.emelianov.Practice6.part4;

public class Part4 {

	public static void main(String[] args) {
		Graph graph = new Graph(6);
		System.out.println("adding several edges... ");
		System.out.println(graph.addEdge("1", "2"));
		System.out.println(graph.addEdge("3", "4"));
		System.out.println(graph.addEdge("5", "6"));
		System.out.println(graph.addEdge("3", "6")+ System.lineSeparator());
		System.out.println(graph.getGraphMap()+ System.lineSeparator());
		System.out.println("adding illegal edge...");
		System.out.println(graph.addEdge("100", "500"));
		System.out.println(graph.addEdge("5", "5")+ System.lineSeparator());
		System.out.println("adding edge, that already exists...");
		System.out.println(graph.addEdge("1", "2")+ System.lineSeparator());
		System.out.println("removing edge...");
		System.out.println(graph.removeEdge("3", "6")+ System.lineSeparator());
		System.out.println("removing edge that does not exist...");
		System.out.println(graph.removeEdge("4", "6")+ System.lineSeparator());
		System.out.println("result:");
		System.out.println(graph.getGraphMap());
	}

}
