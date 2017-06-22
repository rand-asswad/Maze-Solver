public class Maze {
	
	public static void main(String[] args) {
		
		System.out.println("******   Maze Solver - Depth-First Traversal ******");
		System.out.println("******    by Rand ASSWAD & Cédric FRAGNAUD   ******");
		System.out.println("\n");
		
		Interface read = new Interface();
		int choix = read.chooseMaze();
		
		while (choix!=5) {			
			if ((choix>0)||(choix<5)) {
				Graph L;
			
				switch (choix) {
					case 1 : L = new Graph("laby1"); break;
					case 2 : L = new Graph("laby2"); break;
					case 3 : L = new Graph();		break;
					case 4 : L = new Graph(5);		break;
					default: L = new Graph();
				}
				
				System.out.println(L);
				
				Path C = new Path(L);
				if (C.exist) {
					System.out.println("Here's a path:\n"+C);
				}
				else {
					System.out.println("There is no path in this maze");
				}
			}
			
			choix = read.chooseMaze();
			
		}
		
		System.out.println("Bye :)");
	}

}
