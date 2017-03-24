import java.lang.*;
import java.util.Arrays;

import aima.search.framework.GoalTest;
import aima.search.framework.StepCostFunction;
import aima.search.framework.HeuristicFunction;

public class ParkingState implements GoalTest, StepCostFunction, HeuristicFunction {

	private Car[] cars;
	private int[][] mState;
	private int dim;
	private Car redCar;
	public static enum Actions { UP, DOWN, LEFT, RIGHT };

	/* getters */
	public int[][] getMState() { return this.mState; }
	public int getDim() { return this.dim; }
	public Car[] getCars() { return this.cars; }
	public Car getRedCar() { return this.redCar; }

	public ParkingState(int dim, Car[] cars) {
		this.dim = dim;
		this.cars = cars;
		this.redCar = cars[cars.length - 1];

		/* reset with zeros the grid */
		this.mState = new int[dim][dim];
		for(int i=0; i<dim; i++) {
			for(int j=0; j<dim; j++) {
				this.mState[i][j] = 0;
			}
		}

		/* put all the cars on the grid using the ID of each one */
		for(Car car : cars) {
			if(car.getHorizontal()) {
				for(int i=car.getStartPosX(); i<=car.getEndPosX(); i++) {
					this.mState[car.getStartPosY()][i] = car.getId();
				}
			} else {
				for(int i=car.getStartPosY(); i<=car.getEndPosY(); i++) {
					this.mState[i][car.getStartPosX()] = car.getId();
				}
			}
		}
	}

	/* Goal state, no cars between the red one and the exit */
	public boolean isGoalState(Object state) {
		if(state instanceof ParkingState) {
			ParkingState mState = (ParkingState) state;

			for(int i=mState.getRedCar().getEndPosX()+1; i<mState.getDim(); i++) {
				if(mState.getMState()[mState.getRedCar().getStartPosY()][i] != 0) {
					return false;
				}
			}
			System.out.println("Situazione finale: " + mState.getRedCar().getEndPosX());
			printState(mState.getMState());
			return true;
		} 
		return false;
	}

	public Double calculateStepCost(Object fromState, Object toState, String action) {
 		return new Double(1);
 	};

 	public double getHeuristicValue(Object state) {
 		ParkingState mState = (ParkingState) state;
 		int blocking_cars = 0;

 		/* number of cars between the red one and the exit */
		for(int i=mState.getRedCar().getEndPosX()+1; i<mState.getDim(); i++) {
			if(mState.getMState()[mState.getRedCar().getStartPosY()][i] != 0) {
				blocking_cars++;
			}
		}

 		return blocking_cars;
 	}

 	/* move a car */
 	public ParkingState applyAction(int id, int unit, Actions direction, ParkingState modifiedState) {
 		for(Car car : modifiedState.getCars()) {
 			if(car.getId() == id) {
		 		if(car.getHorizontal()) {
		 			switch(direction) {
		 				case RIGHT:
		 					for(int i=car.getEndPosX()+1; i<=car.getEndPosX()+unit; i++) {
		 						modifiedState.getMState()[car.getStartPosY()][i] = car.getId();
		 					}
		 					for(int i=car.getStartPosX(); i<car.getStartPosX()+unit; i++) {
		 						modifiedState.getMState()[car.getStartPosY()][i] = 0;
		 					}
		 					car.setStartPosX(car.getStartPosX() + unit);
		 					car.setEndPosX(car.getEndPosX() + unit);
		 					car.setHasMoved(true);
		 					break;
		 				case LEFT:
		 					for(int i=car.getStartPosX()-1; i>=car.getStartPosX()-unit; i--) {
		 						modifiedState.getMState()[car.getStartPosY()][i] = car.getId();
		 					}
		 					for(int i=car.getEndPosX(); i>car.getEndPosX()-unit; i--) {
		 						modifiedState.getMState()[car.getStartPosY()][i] = 0;
		 					}
		 					car.setStartPosX(car.getStartPosX() - unit);
		 					car.setEndPosX(car.getEndPosX() - unit);
		 					car.setHasMoved(true);
		 					break;
		 				default: break;
		 			}
		 		} else {
		 			switch(direction) {
		 				case UP:
		 					for(int i=car.getStartPosY()-1; i>=car.getStartPosY()-unit; i--) {
		 						modifiedState.getMState()[i][car.getStartPosX()] = car.getId();
		 					}
		 					for(int i=car.getEndPosY(); i>car.getEndPosY()-unit; i--) {
		 						modifiedState.getMState()[i][car.getStartPosX()] = 0;
		 					}
		 					car.setStartPosY(car.getStartPosY() - unit);
		 					car.setEndPosY(car.getEndPosY() - unit);
		 					car.setHasMoved(true);
		 					break;
		 				case DOWN:
		 					for(int i=car.getEndPosY()+1; i<=car.getEndPosY()+unit; i++) {
		 						modifiedState.getMState()[i][car.getStartPosX()] = car.getId();
		 					}
		 					for(int i=car.getStartPosY(); i<car.getStartPosY()+unit; i++) {
		 						modifiedState.getMState()[i][car.getStartPosX()] = 0;
		 					}
		 					car.setStartPosY(car.getStartPosY() + unit);
		 					car.setEndPosY(car.getEndPosY() + unit);
		 					car.setHasMoved(true);
		 					break;
		 				default: break;

		 			}
		 		}
 			}
 		}
 		return modifiedState;
 	}

 	/* check if a move is possible */
 	public boolean moveIsPossible(Car mCar, int unit, Actions direction) {
 		                        /*&& !mCar.getHasMoved()*/
 		if(mCar.getHorizontal()) {
 			switch(direction) {
 				case RIGHT:
					if(mCar.getEndPosX()+unit > this.dim-1)
						return false;
 					for(int i=mCar.getEndPosX()+1; i<=mCar.getEndPosX()+unit; i++) {
 						if(this.mState[mCar.getEndPosY()][i] != 0)
 							return false;
 					}
 					return true;
 				case LEFT:
					if(mCar.getStartPosX()-unit<0)
						return false;
 					for(int i=mCar.getStartPosX()-1; i>=mCar.getStartPosX()-unit; i--) {
 						if(this.mState[mCar.getEndPosY()][i] != 0)
 							return false;
 					}
 					return true;
 				default: break;
 			}                          /*&& !mCar.getHasMoved()*/
 		} else if(!mCar.getHorizontal()) {
 			switch(direction) {
 				case UP:
					if(mCar.getStartPosY()-unit<0)
						return false;
 					for(int i=mCar.getStartPosY()-1; i>=mCar.getStartPosY()-unit; i--) {
 						if(this.mState[i][mCar.getStartPosX()] != 0)
 							return false;
 					}
 					return true;
 				case DOWN:
 					if(mCar.getEndPosY()+unit > this.dim-1)
 							return false;
 					for(int i=mCar.getEndPosY()+1; i<=mCar.getEndPosY()+unit; i++) {
 						if(this.mState[i][mCar.getStartPosX()] != 0)
 							return false;
 					}
 					return true;
 				default: break;
 			}
 		} else return false;
 		return false;
 	}

 	/* print the state */
 	public void printState(int[][] state) {
 		for(int i=0; i<this.dim; i++) {
 			for(int j=0; j<this.dim; j++) {
 				System.out.print(state[i][j] + " ");
 			}
 			System.out.println("");
 		}
 	}
}