import java.util.Scanner;

public class Interface {
	
	public int readSize() {
		int n;
		Scanner read = new Scanner(System.in);
		System.out.println("Please enter the size of the maze (the number of vertices)");
		n = read.nextInt();
		while (n<=0) {
			System.out.println("The number has to be a positive integer!");
			n = read.nextInt();
		}
		return n;
	}
	
	public int yes_or_no (String message) {
		char r;
		Scanner read = new Scanner(System.in);
		System.out.println(message);
		r = read.next().charAt(0);
		if ((r=='Y')||(r=='y')||(r=='1')) return 1;
		else return 0;
	}
	
	public int chooseMaze() {
		int n;
		Scanner read = new Scanner(System.in);
		System.out.println("\nInitialize your maze :");
		System.out.println("1.  Extract maze from file");
		System.out.println("2.  Extract maze from another file");
		System.out.println("3.  Define your own maze");
		System.out.println("4.  Random Maze");
		System.out.println("5.  Just get me out of here");
		n = read.nextInt();
		while ((n<=0)||(n>5)) {
			System.out.println("This isn't a valid choice...sucker");
			n = read.nextInt();
		}
		return n;
	}
}
