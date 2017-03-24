import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class ParkingSuccessorFunction implements SuccessorFunction {

	public ParkingSuccessorFunction() {}

	/* generating successors */
	public List getSuccessors(Object state) {
		List result = new ArrayList();
		
		if (state instanceof ParkingState) {
			ParkingState theState = (ParkingState) state;

			for(Car car : theState.getCars()) {
				//if(car.getId() != ParkingMain.redCarId) {
					for(int i=1; i<theState.getDim(); i++) {
						for(ParkingState.Actions action : ParkingState.Actions.values()) {
							if (theState.moveIsPossible(car, i, action)) {
		 						result.add(new Successor("Automobile " + car.getId() + ": " + action.toString() + " " + i,
		 												theState.applyAction(car.getId(), i, action, clone(theState))));
							}
						}
					}
				//}
			}
		}
		return result;
	}

	/* clone a state */
	private ParkingState clone(ParkingState theState) {
		int dim = theState.getDim();
		Car[] cars = new Car[ParkingMain.numberOfCars];

		int i=0;
		for(Car car : theState.getCars()) {
			cars[i] = new Car(car.getStartPosX(), car.getStartPosY(), car.getEndPosX(), car.getEndPosY(), car.getHorizontal(), car.getHasMoved(), car.getId());
			i++;
		}
		ParkingState newState = new ParkingState(dim, cars);

		return newState;
	}
}