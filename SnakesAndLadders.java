package comp2402a5;
// Thanks to Pat Morin for the skeleton of this file!

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
	/**
	 * Your code goes here
	 * @param r the reader to read from
	 * @param w the writer to write to
	 * @throws IOException
	 */
	public static class qentry{
		int vertex;
		int distance;
	}

	public static void doIt(BufferedReader r, PrintWriter w) throws IOException {
		//TODO: Your solution goes here. This example only shows how you can read the input.
		int N = Integer.parseInt(r.readLine());
		int[] move = new int[N*N +1];
		for (String line = r.readLine(); line != null; line = r.readLine()){
			String[] splitted = line.split(" ");
			int u = Integer.parseInt(splitted[0]), v = Integer.parseInt(splitted[1]);
			move[u] = v;
		}
		w.println(minDiceRolls(move, N*N));
	}

	public static int minDiceRolls(int[] move, int n){
		Queue<qentry> queue = new LinkedList<>();
		qentry qe = new qentry();
		qe.vertex = 1;
		qe.distance = 0;
		int[] visited = new int[n+1];
		queue.add(qe);
		visited[qe.vertex] = 1;
		while(!queue.isEmpty()){
			qe = queue.remove();
			int v = qe.vertex;
			if(v == n) break;

			for(int j=v+1; j <= v+6 && j<=n; j++){
				if(visited[j] == 0){
					qentry a = new qentry();
					a.distance = qe.distance + 1;
					if(move[j] != 0){
						a.vertex = move[j];
					}else{
						a.vertex = j;
					}
					visited[j] = 1;
					queue.add(a);
				}
			}
		}
	return qe.distance;
	}


	/**
	 * The driver.  Open a BufferedReader and a PrintWriter, either from System.in
	 * and System.out or from filenames specified on the command line, then call doIt.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader r;
			PrintWriter w;
			if (args.length == 0) {
				r = new BufferedReader(new InputStreamReader(System.in));
				w = new PrintWriter(System.out);
			} else if (args.length == 1) {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(System.out);
			} else {
				r = new BufferedReader(new FileReader(args[0]));
				w = new PrintWriter(new FileWriter(args[1]));
			}
			long start = System.nanoTime();
			doIt(r, w);
			w.flush();
			long stop = System.nanoTime();
			System.out.println("Execution time: " + 1e-9 * (stop-start));
		} catch (IOException e) {
			System.err.println(e);
			System.exit(-1);
		}
	}
}
