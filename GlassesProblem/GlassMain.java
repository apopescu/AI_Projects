import java.lang.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Arrays;

import aima.search.framework.*;
import aima.search.informed.*;


public class GlassMain {
	public static void main(String[] args) {

		Glass[] glasses = new Glass[4];

		/* Configurazione iniziale dei bicchieri */
		glasses[0] = new Glass(1, 2, true, false);
		glasses[1] = new Glass(2, 2, true, false);
		glasses[2] = new Glass(2, 1, true, false);
		glasses[3] = new Glass(1, 1, true, false);
		System.out.println("==========INIZIO ESECUZIONE PROGRAMMA==========");
		GlassState initState = new GlassState(glasses);
		System.out.println("Situazione iniziale:");
		initState.printGlasses(glasses);

		try {
			Problem problem = new Problem(initState,
										new GlassSuccessorFunction(),
										initState,
										initState,
										initState);

			/* Strategia di ricerca */
			Search search = new AStarSearch(new GraphSearch());

			/* Provvede a cercare la soluzione nello spazio degli stati */
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