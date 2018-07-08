package com.practice;

import java.util.ArrayList;
import java.util.List;

public class CountryList {

	public ArrayList<Country> getCountryList() {
		ArrayList<Country> countryList = new ArrayList<Country>();

		ArrayList<String> states = new ArrayList<String>();
		states.add("AP");
		states.add("TS");
		states.add("KA");
		states.add("TN");
		countryList.add(produce("India", states, "Hindi"));

		return countryList;
	}

	private Country produce(String name, List<String> states, String language) {
		Country country = new Country();
		country.setCountry(name);
		country.setStates(states);
		country.setLanguage(language);
		return country;
	}

}
