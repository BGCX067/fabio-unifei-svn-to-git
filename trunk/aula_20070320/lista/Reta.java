package lista;

public class Reta {
	
	private Coordenada pi, pf;
	
/*************************************************************************************/
	
	public Reta(Coordenada coord_pi, Coordenada coord_pf){
		pi = coord_pi;
		pf = coord_pf;
	}

/*************************************************************************************/
	
	public Coordenada Coordenada_pf() {
		return pf;
	}

/*************************************************************************************/
	
	public Coordenada Coordenada_pi() {
		return pi;
	}
	
/*************************************************************************************/
	
}
