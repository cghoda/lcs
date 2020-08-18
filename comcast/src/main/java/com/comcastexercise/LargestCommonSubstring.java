package com.comcastexercise;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.comcastexercise.Model.LcsStringModel;

public class LargestCommonSubstring {

	/* Method to run the algorithm for finding Longest Common String */
	public static LcsStringModel getLargestCommonSubstringforList(List<LcsStringModel> inputStringList) {

		String commonString = new String();
		int inputListSize = inputStringList.size();

		/*
		 * sort the list of strings to shortest one first to decrease the number of
		 * iterations as LCS will not be bigger than shortest
		 */
		sortInputListByShortestFirst(inputStringList);

		LcsStringModel firstEntry = inputStringList.get(0);
		if (firstEntry != null) {
			String firstEntryValue = firstEntry.getValue();
			int length = firstEntryValue.length();

			// pointers of the first string entry
			for (int firstPointer = 0; firstPointer < length; firstPointer++) {
				for (int secondPointer = firstPointer + 1; secondPointer <= length; secondPointer++) {

					// compare through rest of string

					String currentSubString = firstEntryValue.substring(firstPointer, secondPointer);
					int subsequentIndex = 1;
					for (; subsequentIndex < inputListSize; subsequentIndex++) {

						if (!((LcsStringModel) inputStringList.get(subsequentIndex)).getValue().toLowerCase().contains(currentSubString.toLowerCase()))
							break;

					}
					if (subsequentIndex == inputListSize && commonString.length() < currentSubString.length())
						commonString = currentSubString;
				}
			}

		}

		// once iteration is complete create object
		if (!commonString.isEmpty()) {
			LcsStringModel lcsModel = new LcsStringModel();
			lcsModel.setValue(commonString);
			return lcsModel;
		} else {
			return null;
		}
	}

	
	
	private static void sortInputListByShortestFirst(List<LcsStringModel> lcsList) {

		Collections.sort(lcsList, new Comparator<LcsStringModel>() {
			@Override
			public int compare(LcsStringModel firstString, LcsStringModel secondString) {
				if (firstString.getValue().length() > secondString.getValue().length()) {
					return 1;
				} else {
					return firstString.getValue().compareTo(secondString.getValue());
				}
			}
		});

	}
}