import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class GlassSuccessorFunction implements SuccessorFunction {
	public GlassSuccessorFunction() {}

	public List getSuccessors(Object mState) {
		List list = new ArrayList();

		if(mState instanceof GlassState) {
			GlassState state = (GlassState) mState;
			Glass[] glasses = state.getGlasses();

			if(state.isRotatePossible(glasses)) {
				GlassState newState = state.rotate(clone(glasses));
				list.add(new Successor("Rotate", newState));
			}
			if(state.isFlipDownPossible(glasses)) {
				GlassState newState = state.flipdown(clone(glasses));
				list.add(new Successor("Flipdown", newState));
			}
		}
		return list;
	}

	public Glass[] clone(Glass[] glasses) {
		Glass[] newGlasses = new Glass[glasses.length];

		int i=0;
		for(Glass glass : glasses) {
			newGlasses[i] = new Glass(glass.getX(), glass.getY(), glass.getUp(), glass.getDown());
			i++;
		}

		return newGlasses;
	}
}