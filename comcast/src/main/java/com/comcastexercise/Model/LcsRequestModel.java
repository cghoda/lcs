package com.comcastexercise.Model;

import java.util.ArrayList;
import java.util.List;

/*
 * RequestModel to hold setOfStrings and a list of StringModels containing values of Strings.
 */
public class LcsRequestModel {

	private List<LcsStringModel> setOfStrings;

	public List<LcsStringModel> getSetOfStrings() {

		if (setOfStrings == null) {
			this.setOfStrings = new ArrayList<LcsStringModel>();
		}
		return setOfStrings;
	}

	public void setSetOfStrings(List<LcsStringModel> setOfStrings) {
		if (setOfStrings == null) {
			this.setOfStrings = new ArrayList<LcsStringModel>();
		}
		this.setOfStrings = setOfStrings;
	}

}
