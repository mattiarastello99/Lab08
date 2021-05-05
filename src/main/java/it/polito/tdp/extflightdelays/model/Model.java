package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	ExtFlightDelaysDAO dao;
	Map<Integer, Airport> idMapAirport;
	Graph<Airport, DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new ExtFlightDelaysDAO();
		idMapAirport = new HashMap<Integer, Airport>(dao.loadAllAirports());
		
	}
	
	public void creaGrafo(int x) {
		this.grafo = new SimpleWeightedGraph<Airport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.loadAllAirports().values());
		
		for(Collegamento c : dao.getFlightValidi(x, idMapAirport)) {
			
			if(!grafo.containsEdge(c.getOriginAirport(), c.getDestinationAirport())) {
				Graphs.addEdgeWithVertices(grafo, c.getOriginAirport(), c.getDestinationAirport(), c.getPeso());
			}	
			else {
				DefaultWeightedEdge arco = grafo.getEdge(c.getOriginAirport(), c.getDestinationAirport());
				double media = this.grafo.getEdgeWeight(arco);
				media = (media + c.getPeso())/2;
				this.grafo.setEdgeWeight(arco, media);
				
			}
			
		} 
		System.out.println(dao.getFlightValidi(x, idMapAirport).size());
	}
	
	public int numeroVertici() {
		if(grafo.vertexSet().size()!=0)
			return grafo.vertexSet().size();
		else
			return 0;
	}
	
	public int numeroArchi() {
		if(grafo.edgeSet().size()!=0)
			return grafo.edgeSet().size();
		else
			return 0;
	}
	
	public String elencoVerticiDistanza() {
		
		String s = "";
		
		for(DefaultWeightedEdge e : grafo.edgeSet()) {
			s+=grafo.getEdgeSource(e).getAirportName() + " --> " + grafo.getEdgeTarget(e).getAirportName() + " : " + grafo.getEdgeWeight(e) + "\n";
		}
		
		return s;
	}
}
