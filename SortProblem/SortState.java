import java.lang.*;
import java.util.Arrays;

import aima.search.framework.GoalTest;
import aima.search.framework.StepCostFunction;
import aima.search.framework.HeuristicFunction;


public class SortState implements GoalTest, StepCostFunction, HeuristicFunction, Cloneable {
	private int[] array;

	public SortState(int[] array) {
		this.array = array;
	}
	public int[] getArray() {
		return this.array;
	}

	/* 
	Verifica se è stato raggiunto lo stato goal,
	cioè se il vettore è ordinato
	*/
	public boolean isGoalState(Object mState) {
		if(mState instanceof SortState) {
			SortState state = (SortState) mState;
			for(int i=0;i<state.array.length-1;i++){
        		if(state.array[i]>state.array[i+1]) {
        			return false;
        		}
    		}
    		System.out.println("Risultato: " + Arrays.toString(state.getArray()));
    		return true;
		}
		return false;
	}

	public Double calculateStepCost(Object fromState, Object toState, String action) {
 		return new Double(1);
 	}


 	/* Restituisce il valore dell'euristica */
 	/* Questa euristica è ammissibile e consistente, quindi
 	A* fornisce la soluzione ottimale */
 	public double getHeuristicValue(Object mState) {

		if (mState instanceof SortState) {
			SortState state = (SortState) mState;
			int fuoriPosto = 0;
 			int[] arrayH = state.array.clone();

 			/* Calcolo il numero di interi fuori posto */
 			for(int i=0; i<arrayH.length; i++) {
 				boolean found = false;
 				if(i == arrayH.length-1) {
					for(int k=i-1; k>=0; k--) {
						if(arrayH[i] < arrayH[k]) {
							fuoriPosto++;
							break;
						}
					}
 				} else {
	 				for(int j=i+1; j<arrayH.length; j++) {
	 					if(arrayH[i]>arrayH[j]) {
	 						fuoriPosto++;
	 						found = true;
	 					}
	 					if(found) break;
	 				}
 					if(!found && i!=0) {
 						for(int m=i-1; m>=0; m--) {
 							if(arrayH[i] < arrayH[m]) {
 								fuoriPosto++;
 								break;
 							}
 						}
 					}
 				}
 			}

 			return Math.ceil(fuoriPosto/2);
 		}
 		else return Integer.MAX_VALUE;
	}

	/* Applico l'azione sullo stato, cioè faccio swap di 2 interi non ordinati */
	public SortState applyAction(int index1, int index2, int[] mArray) {
		
		try {
			int number1 = mArray[index1];
			int number2 = mArray[index2];
			mArray[index1] = number2;
			mArray[index2] = number1;

			return new SortState(mArray);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}