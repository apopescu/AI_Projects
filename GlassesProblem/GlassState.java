import java.lang.*;
import java.util.Arrays;

import aima.search.framework.GoalTest;
import aima.search.framework.StepCostFunction;
import aima.search.framework.HeuristicFunction;


public class GlassState implements GoalTest, StepCostFunction, HeuristicFunction {
	private Glass[] glasses;

	public GlassState(Glass[] glasses) {
		this.glasses = glasses;
	}
	public Glass[] getGlasses() {
		return this.glasses;
	}

	/* Verifico se ho raggiunto lo stato finale/goal */
	public boolean isGoalState(Object mState) {
		if(mState instanceof GlassState) {
			GlassState state = (GlassState) mState;
			Glass[] glasses = state.getGlasses();

			int i = 0;
			int positionedGlasses = 0;
			for(Glass glass : glasses) {
				if(glass.getUp())
					return false;
				if(i == 0 && glass.getDown() && glass.getX() == 2 && glass.getY() == 1)
					positionedGlasses++;
				if(i == 1 && glass.getDown() && glass.getX() == 1 && glass.getY() == 1)
					positionedGlasses++;
				if(i == 2 && glass.getDown() && glass.getX() == 1 && glass.getY() == 2)
					positionedGlasses++;
				if(i == 3 && glass.getDown() && glass.getX() == 2 && glass.getY() == 2)
					positionedGlasses++;

				i++;
			}
			if(positionedGlasses == 4) {
				System.out.println("\nSituazione finale:");
				printGlasses(glasses);
				return true;
			}
		}
		return false;
	}

	public Double calculateStepCost(Object fromState, Object toState, String action) {
 		return new Double(1);
 	}


 	/* Restituisce il valore dell'euristica, # bicchieri
 	 	rivolti verso l'alto */
 	public double getHeuristicValue(Object mState) {

		if (mState instanceof GlassState) {
			int upGlasses = 0;
			GlassState state = (GlassState) mState;
			Glass[] glasses = state.getGlasses();
			for(Glass glass : glasses) {
				if(glass.getUp())
					upGlasses++;
			}

			return upGlasses;
 		}
 		else return Integer.MAX_VALUE;
	}

	/* Dalle specifiche la rotazione è sempre possibile. */
	public boolean isRotatePossible(Glass[] glasses) {
		return true;
	}

	/* Restituisce vero se il flipdown è possibile */
	public boolean isFlipDownPossible(Glass[] glasses) {
		int upCounter = 0;

		for(Glass glass : glasses) {
			if(glass.getY() == 2 && glass.getUp())
				upCounter++;
		}
		if(upCounter == 2)
			return true;
		else return false;
	}

	/* Ruota l'insieme di tutti i bicchieri */
	public GlassState rotate(Glass[] glasses) {
		try {
			for(int i=0; i<glasses.length; i++) {
				Glass mGlass = new Glass(glasses[i].getX(), glasses[i].getY(), glasses[i].getUp(), glasses[i].getDown());
				if(mGlass.getX() == 1 && mGlass.getY() == 1) {
					glasses[i].setX(2);
					glasses[i].setY(1);
				}
				if(mGlass.getX() == 2 && mGlass.getY() == 1) {
					glasses[i].setX(2);
					glasses[i].setY(2);
				}
				if(mGlass.getX() == 2 && mGlass.getY() == 2) {
					glasses[i].setX(1);
					glasses[i].setY(2);
				}
				if(mGlass.getX() == 1 && mGlass.getY() == 2) {
					glasses[i].setX(1);
					glasses[i].setY(1);
				}
			}
			return new GlassState(glasses);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/* Flipdown di 2 bicchieri */
	public GlassState flipdown(Glass[] glasses) {
		try {
			for(int i=0; i<glasses.length; i++) {
				if(glasses[i].getY() == 2) {
					glasses[i].setUp(false);
					glasses[i].setDown(true);
				}
			}

			return new GlassState(glasses);	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/* Stampo a video la situazione dei bicchieri */
	public void printGlasses(Glass[] glasses) {
		int index = 0;
		for(Glass glass : glasses) {
			if(glass.getUp()) {
				System.out.println("Bicchiere " + (index+1) + ": " + "x:" + glass.getX() + " y:" + glass.getY() + ", orientamento: sù");
			}
			if(glass.getDown()) {
				System.out.println("Bicchiere " + (index+1) + ": " + "x:" + glass.getX() + " y:" + glass.getY() + ", orientamento: giù");
			}
			index++;
		}
	}
}