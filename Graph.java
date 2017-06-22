import java.util.concurrent.ThreadLocalRandom;
import java.util.*;
import java.io.*;

public class Graph {
	
	/*** ATTRIBUTES ***/
	
	private int[][] adj;
	public List<Vertex> graph;
	public int size, start, finish;
	
	
	/*** CONSTRUCTORS ***/
	
	// user-defined maze
	public Graph() {
		Interface read = new Interface();
		this.size = read.readSize();
		this.start = 0;
		this.finish = this.size -1;
		this.adj = new int[this.size][this.size];
		this.graph = new ArrayList<Vertex>();
		
		for (int i=0; i<this.size; i++) {
			this.graph.add(new Vertex(i));
			for (int j=0; j<i; j++) {
				this.adj[i][j] = read.yes_or_no("Connect vertex ("+i+") with ("+j+") : (Y/N)");
				if (this.adj[i][j]==1) {
					this.graph.get(i).connectWith(this.graph.get(j));
				}
				this.adj[j][i] = this.adj[i][j];
			}
			this.adj[i][i] = 0;
		}
	}
	
	// random graph
	public Graph(int maxAdj) {
		Interface read = new Interface();
		this.size = read.readSize();
		this.start = 0;
		this.finish = this.size -1;
		this.adj = new int[this.size][this.size];
		
		int nbAdj, maxLocal;
		
		for (int i=0; i<this.size; i++) {
			nbAdj = 0;
			maxLocal = ThreadLocalRandom.current().nextInt(1,Math.min(maxAdj,this.size)+1);
			for (int j=0; j<i; j++) {
				if (this.adj[i][j]==1) nbAdj++;
			}
			for (int j=i+1; j<size; j++) {
				if (nbAdj<maxLocal) {
					this.adj[i][j] = ThreadLocalRandom.current().nextInt(0,2);
					if (this.adj[i][j]==1) {
						nbAdj++;
					}
				}
				else {
					this.adj[i][j] = 0;
				}
				this.adj[j][i] = this.adj[i][j];
			}
			this.adj[i][i] = 0;
		}
		
		this.graph = new ArrayList<Vertex>();
		for (int i=0; i<this.size; i++) {
			this.graph.add(new Vertex(i));
			for (int j=0; j<i; j++) {
				if (this.adj[i][j]==1) {
					this.graph.get(i).connectWith(this.graph.get(j));
				}
			}
		}
	}
	
	
	// extract graph form file
	public Graph(String fileName) {
		try {
			File f = new File("./files/"+fileName+".txt");
			BufferedReader b = new BufferedReader(new FileReader(f));
			
			// initialize adjacency matrix (adj)
			String ligne = b.readLine();
			this.size = Integer.valueOf(ligne);
			this.start = 0;
			this.finish = this.size -1;
			this.adj = new int[this.size][this.size];
			this.graph = new ArrayList<Vertex>();
			
			// fill adj
			int i=0, j=0,k;
			while ((ligne = b.readLine()) != null) {
				this.graph.add(new Vertex(i));
				j=0;
				for (k=0; k<ligne.length(); k++) {
					if (!(Character.isWhitespace(ligne.charAt(k)))) {
						if ((i<this.size)&&(j<this.size))
							this.adj[i][j] = Character.getNumericValue(ligne.charAt(k));
						j++;
					}
				}
				i++;
			}
		
			// check adj --> initialize list of vertices
			this.graph = new ArrayList<Vertex>();
			for (i=0; i<this.size; i++) {
				this.graph.add(new Vertex(i));
				this.adj[i][i] = 0;
				for (j=0; j<i; j++) {
					if (this.adj[i][j]!=this.adj[j][i]) {
						this.adj[j][i] = this.adj[i][j];
					}
					if (this.adj[i][j]==1) {
						this.graph.get(i).connectWith(this.graph.get(j));
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	/*** DISPLAY ***/
	
	public String adjacentMatrixToString() {
		String output="";
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				output += this.adj[i][j];
			}
			output +="\n";
		}
		return output;
	}
	
	public String toString() {
		String output="";
		for (int i = 0; i < this.size; i++) {
			output += "The vertex ("+i+") is linked with the vertex(ices) : ";
			for (int j = 0; j < this.size; j++) {
				if (this.adj[i][j]==1) output += "(" +j+") ";
			}
			output +="\n";
		}
		return output;
	}
}
