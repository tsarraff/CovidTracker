package io.Corona.coronavirus_tracker.models;

public class LocationsStats {
	private String state;
	private String country;
	private int latestTotal_Cases;
	private int diffFromPrevDay;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotal_Cases() {
		return latestTotal_Cases;
	}
	public void setLatestTotal_Cases(int latestTotal_Cases) {
		this.latestTotal_Cases = latestTotal_Cases;
	}
	@Override
	public String toString() {
		return "LocationsStats [state=" + state + ", country=" + country + ", latestTotal_Cases=" + latestTotal_Cases
				+ "]";
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	
}
