import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

public class SortSuccessorFunction implements SuccessorFunction {
	public SortSuccessorFunction() {}

	public List getSuccessors(Object mState) {
		List list = new ArrayList();

		if(mState instanceof SortState) {
			SortState state = (SortState) mState;

			int[] array = state.getArray();

			for(int i=0; i<array.length; i++) {
				for(int j=i+1; j<array.length; j++) {
					if(array[i] > array[j]) {
						SortState newState = state.applyAction(i,j, array.clone());
						list.add(new Successor("Scambia " + array[i] +" con " + array[j], newState));
					}
				}
			}

		}
		return list;
	}
}