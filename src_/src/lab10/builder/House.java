package lab10.builder;

import java.util.ArrayList;
import java.util.List;

public class House {

	// TODO: write optional and mandatory facilities to have in a house
	private String location;
	private int nretaje;
	private String incalzire;
	List<String> electrocasnice;

	private String piscina;
	private String securitate;


	// TODO: complete the private constructor
	private House(HouseBuilder builder) {
		House house = builder.build();
	}

	// TODO: generate getters


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNretaje() {
		return nretaje;
	}

	public void setNretaje(int nretaje) {
		this.nretaje = nretaje;
	}

	public String getIncalzire() {
		return incalzire;
	}

	public void setIncalzire(String incalzire) {
		this.incalzire = incalzire;
	}

	public List<String> getElectrocasnice() {
		return electrocasnice;
	}

	public void setElectrocasnice(List<String> electrocasnice) {
		this.electrocasnice = electrocasnice;
	}

	public String getPiscina() {
		return piscina;
	}


	public void setPiscina(String piscina) {
		this.piscina = piscina;
	}

	public String getSecuritate() {
		return securitate;
	}

	public void setSecuritate(String securitate) {
		this.securitate = securitate;
	}


	// TODO: override toString method
	@Override
	public String toString() {
		return "House{" +
				"location='" + location + '\'' +
				", nretaje=" + nretaje +
				", incalzire='" + incalzire + '\'' +
				", electrocasnice=" + electrocasnice +
				", piscina='" + piscina + '\'' +
				", securitate='" + securitate + '\'' +
				'}';
	}

	public static class HouseBuilder {

		// TODO: write same facilities
		private String locatie;

		@Override
		public String toString() {
			return "HouseBuilder{" +
					"locatie='" + locatie + '\'' +
					", nretaje=" + nretaje +
					", incalzire='" + incalzire + '\'' +
					", electrocasnice=" + electrocasnice +
					", piscina='" + piscina + '\'' +
					", securitate='" + securitate + '\'' +
					'}';
		}

		private int nretaje;
		private String incalzire;


		// TODO: complete the house builder constructor only with the mandatory facilities
		public HouseBuilder(String locatie, int nretaje, String incalzire) {
			this.locatie = locatie;
			this.nretaje = nretaje;
			this.incalzire = incalzire;
		}

		// TODO: add the optional facilities in a builder design
		List<String> electrocasnice;
		private String piscina;
		private String securitate;

		public HouseBuilder poolCount(String piscina) {
			this.piscina = piscina;
			return this;
		}

		public HouseBuilder electronicCount(List<String> electrocasnice) {
			this.electrocasnice = electrocasnice;
			return this;
		}

		public HouseBuilder securityCount(String securitate) {
			this.securitate = securitate;
			return this;
		}

		// TODO: complete the final build method
		public House build() {
			return new House(this);
		}

		// TODO: test functionality in a Main class
	}
}
