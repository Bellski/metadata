package org.vaadin.rise.codegen.model.aNewOne;

import org.vaadin.rise.codegen.model.FqnHolder;

/**
 * Created by oem on 7/28/16.
 */
public class PresenterData {
	public final FqnHolder fqn;


	public boolean defaultPlace;
	public boolean errorPlace;
	public boolean authorizePlace;

	public String place;

	public boolean placeBus;

	private FqnHolder gateKeeper;

	public PresenterData(FqnHolder fqn) {
		this.fqn = fqn;
	}

	public boolean isDefaultPlace() {
		return defaultPlace;
	}

	public void setDefaultPlace(boolean defaultPlace) {
		this.defaultPlace = defaultPlace;
	}

	public boolean isErrorPlace() {
		return errorPlace;
	}

	public void setErrorPlace(boolean errorPlace) {
		this.errorPlace = errorPlace;
	}

	public boolean isAuthorizePlace() {
		return authorizePlace;
	}

	public void setAuthorizePlace(boolean authorizePlace) {
		this.authorizePlace = authorizePlace;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public boolean isPlaceBus() {
		return placeBus;
	}

	public void setPlaceBus(boolean placeBus) {
		this.placeBus = placeBus;
	}

	public void setGateKeeper(FqnHolder gateKeeper) {
		this.gateKeeper = gateKeeper;
	}

	public FqnHolder getGateKeeper() {
		return gateKeeper;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PresenterData that = (PresenterData) o;

		return fqn.equals(that.fqn);

	}

	@Override
	public int hashCode() {
		return fqn.hashCode();
	}


}
