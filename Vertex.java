import java.util.*;

public class Vertex {

	// ATTRIBUTES
	public int id;
	public boolean visited;
	public boolean blocked;
	public List<Vertex> adj;
	
	// CONSTRUCTOR
	public Vertex(int id) {
		this.id = id;
		this.visited = false;
		this.blocked = false;
		this.adj = new ArrayList<Vertex>();
	}
	
	// METHODES
	public void visit() {this.visited = true;}
	
	public void connectWith(Vertex v) {
		this.adj.add(v);
		v.adj.add(this);
	}

}
