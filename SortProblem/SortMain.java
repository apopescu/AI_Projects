import java.lang.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Arrays;

import aima.search.framework.*;
import aima.search.informed.*;


public class SortMain {
	public static void main(String[] args) {

		/* initial array to sort (is just an example, use one of your choice) */
		int[] array = new int[]{-9,5,2,10,6,89,-6,87,1,3,55,-20,0};

		System.out.println("==========INIZIO ESECUZIONE PROGRAMMA==========");
		SortState initState = new SortState(array);
		System.out.println("Vettore iniziale: " + Arrays.toString(array));

		try {
			Problem problem = new Problem(initState,
										new SortSuccessorFunction(),
										initState,
										initState,
										initState);

			/* Search strategy */
			Search search = new AStarSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());

			System.out.println("==========FINE ESECUZIONE PROGRAMMA==========");
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printInstrumentation(Properties properties) {
		Iterator keys = properties.keySet().iterator();
		System.out.println("\nAltri dati statistici:");
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			System.out.println(key + " : " + property.toString());
		}

	}

	private static void printActions(List actions) {
		System.out.println("\nAzioni eseguite:");
		for (int i = 0; i < actions.size(); i++) {
			String action = (String) actions.get(i);
			System.out.println(action);
		}
	}
}