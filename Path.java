import java.util.*;

public class Path {
	
	/*** ATTRIBUTES ***/
	private Stack<Vertex> path;
	public boolean exist;
	private int dim;
	
	
	/*** CONSTRUCTOR ***/
	public Path(Graph maze){
		this.path = new Stack<Vertex>();
		this.exist = false;
		this.dim = 0;
		this.DFT(maze.graph.get(maze.start),maze);
	}
	
	/*** MUTATORS ***/
	private void addVertex(Vertex v) {
		this.path.push(v);
		this.dim++;
	}
	
	private void removeLastVertex() {
		this.path.pop();
		this.dim--;
	}
	
	/*** Depth-First Traversal ***/
	private void DFT(Vertex v, Graph maze) {
		v.visit();
		this.addVertex(v);
		if (v.id==maze.finish) {
			this.exist = true;
		}
		else {
			v.blocked = true;
			for (int i=0; i<v.adj.size(); i++) {
				if (!v.adj.get(i).visited) {
					v.blocked = false;
					DFT(v.adj.get(i),maze);
					v.blocked = v.adj.get(i).blocked;
				}
				if (this.exist) break;
			}
			if (v.blocked) {
				this.removeLastVertex();
			}
		}
	}
	
	/*** DISPLAY ***/
	public String toString() {
		Vertex[] temp = this.path.toArray(new Vertex[this.dim]);
		String output="";
		for (int i = 0; i < this.dim-1; i++) {
			output += "("+ temp[i].id + ") --> ";
		}
		output += "("+ temp[this.dim-1].id + ")";
		return output;
	}
}
