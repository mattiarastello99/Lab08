package it.polito.tdp.extflightdelays.model;

public class Collegamento {
	
	private Airport originAirport;
	private Airport destinationAirport;
	private double peso;
	
	public Collegamento(Airport originAirport, Airport destinationAirport, double peso) {
		super();
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
		this.peso = peso;
	}

	public Airport getOriginAirport() {
		return originAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public double getPeso() {
		return peso;
	}


}
