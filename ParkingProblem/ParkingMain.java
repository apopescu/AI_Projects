import java.lang.*;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Arrays;

import aima.search.framework.*;
import aima.search.informed.*;
import aima.search.uninformed.*;

public class ParkingMain {
	/* ID of red car, must be != from all other IDs */
	public static int redCarId = 9;

	/* number of cars, includng the red one*/
	public static int numberOfCars = 8;

	
	public static void main(String[] args) {
		/* grid dimension */
		int gridDim = 6;

		Car[] cars = new Car[numberOfCars];
	
		/* VARIOUS POSSIBLE ISTANCES, WHEN YOU CHOOSE ONE
			REMEMBER TO MODIFY ALSO THE 3 PARAMETERS ABOVE */

		/* very easy */
		/*cars[0] = new Car(0, 0, 1, 0, true, false, 1);
		cars[1] = new Car(0, 1, 0, 3, false, false, 2);
		cars[2] = new Car(1, 3, 3, 3, true, false, 3);
		cars[3] = new Car(3, 1, 3, 2, false, false, 4);
		cars[4] = new Car(1, 1, 2, 1, true, false, redCarId);*/
		
	
		/* very easy */
		/*cars[0] = new Car(0, 0, 1, 0, true, false, 1);
		cars[1] = new Car(0, 1, 0, 4, false, false, 2);
		cars[2] = new Car(1, 4, 3, 4, true, false, 3);
		cars[3] = new Car(3, 1, 3, 3, false, false, 4);
		cars[4] = new Car(4, 1, 4, 2, false, false, 5);
		cars[5] = new Car(1, 2, 2, 2, true, false, redCarId);*/
		
	
		/* easy */
		/*cars[0] = new Car(4, 0, 4, 2, false, false, 1);
		cars[1] = new Car(3, 3, 3, 5, false, false, 2);
		cars[2] = new Car(4, 5, 5, 5, true, false, 3);
		cars[3] = new Car(0, 4, 2, 4, true, false, 4);
		cars[4] = new Car(2, 2, 3, 2, true, false, redCarId);*/

		/* medium */
		/*
		cars[0] = new Car(2, 0, 2, 1, false, false, 1);
		cars[1] = new Car(4, 0, 5, 0, true, false, 2);
		cars[2] = new Car(4, 1, 4, 2, false, false, 3);
		cars[3] = new Car(3, 1, 3, 3, false, false, 4);
		cars[4] = new Car(4, 3, 5, 3, true, false, 5);
		cars[5] = new Car(1, 2, 2, 2, true, false, redCarId);*/

		/* hard */
		/*cars[0] = new Car(4, 0, 4, 2, false, false, 1);
		cars[1] = new Car(5, 1, 5, 2, false, false, 2);
		cars[2] = new Car(3, 4, 5, 4, true, false, 3);
		cars[3] = new Car(3, 5, 5, 5, true, false, 4);
		cars[4] = new Car(2, 4, 2, 5, false, false, 5);
		cars[5] = new Car(2, 2, 3, 2, true, false, redCarId);*/

		/* hard */
		/*
		cars[0] = new Car(4, 0, 4, 2, false, false, 1);
		cars[1] = new Car(5, 1, 5, 2, false, false, 2);
		cars[2] = new Car(3, 4, 5, 4, true, false, 3);
		cars[3] = new Car(3, 5, 5, 5, true, false, 4);
		cars[4] = new Car(1, 4, 1, 5, false, false, 5);
		cars[5] = new Car(0, 2, 1, 2, true, false, redCarId);*/

		/* hard */
		/*cars[0] = new Car(4, 0, 4, 2, false, false, 1);
		cars[1] = new Car(5, 1, 5, 2, false, false, 2);
		cars[2] = new Car(0, 0, 1, 0, true, false, 3);
		cars[3] = new Car(3, 5, 4, 5, true, false, 4);
		cars[4] = new Car(2, 3, 2, 5, false, false, 5);
		cars[5] = new Car(2, 2, 3, 2, true, false, redCarId);*/

		/* very hard */
		cars[0] = new Car(0, 0, 1, 0, true, false, 1);
		cars[1] = new Car(0, 1, 0, 3, false, false, 2);
		cars[2] = new Car(0, 4, 0, 5, false, false, 3);
		cars[3] = new Car(5, 0, 5, 2, false, false, 4);
		cars[4] = new Car(4, 4, 5, 4, true, false, 5);
		cars[5] = new Car(2, 5, 4, 5, true, false, 6);
		cars[6] = new Car(3, 1, 3, 3, false, false, 7);
		cars[7] = new Car(1, 2, 2, 2, true, false, redCarId);
		
		/* very hard */
		/*cars[0] = new Car(0, 0, 0, 1, false, false, 1);
		cars[1] = new Car(3, 0, 5, 0, true, false, 2);
		cars[2] = new Car(3, 1, 3, 2, false, false, 3);
		cars[3] = new Car(5, 1, 5, 3, false, false, 4);
		cars[4] = new Car(4, 2, 4, 3, false, false, 5);
		cars[5] = new Car(3, 5, 4, 5, true, false, 6);
		cars[6] = new Car(2, 4, 2, 5, false, false, 7);
		cars[7] = new Car(0, 5, 1, 5, true, false, 8);
		cars[8] = new Car(0, 3, 2, 3, true, false, 9);
		cars[9] = new Car(4, 4, 5, 4, true, false, 10);
		cars[10] = new Car(0, 2, 1, 2, true, false, redCarId);*/

		System.out.println("==========INIZIO ESECUZIONE PROGRAMMA==========");
		ParkingState initState = new ParkingState(gridDim, cars);
		System.out.println("Situazione iniziale:");
		initState.printState(initState.getMState());
		try {
			Problem problem = new Problem(initState,
										new ParkingSuccessorFunction(),
										initState,
										initState,
										initState);

			/* search startegy */
			Search search = new AStarSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);

			/* print results and statistics */
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