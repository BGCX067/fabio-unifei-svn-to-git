package lista;

import java.util.ArrayList;

public class Lista {
	
	private ArrayList conjunto_retas;
	private ArrayList conjunto_retas_horizontais;
	private ArrayList conjunto_retas_verticais;
	private ArrayList conjunto_retas_diagonais;
	private ArrayList conjunto_retas_interceptam;

/*************************************************************************************/	
	
	public Lista(){
		conjunto_retas = new ArrayList();
		conjunto_retas_horizontais = new ArrayList();
		conjunto_retas_verticais = new ArrayList();
		conjunto_retas_diagonais = new ArrayList();
		conjunto_retas_interceptam = new ArrayList();
	}

/*************************************************************************************/
	
	private void Insere_na_Lista(ArrayList lista_retas, Reta r){
		lista_retas.add(r);
	}

/*************************************************************************************/	
	
	public ArrayList getConj_Retas(){
		return conjunto_retas;
	}
/*************************************************************************************/
	public ArrayList getConj_Retas_Horizontais(){
		return conjunto_retas_horizontais;
	}
/*************************************************************************************/
	public ArrayList getConj_Retas_Verticais(){
		return conjunto_retas_verticais;
	}
/*************************************************************************************/
	public ArrayList getConj_Retas_Diagonais(){
		return conjunto_retas_diagonais;
	}
	
/*************************************************************************************/
	
	public void Percorre_lista(ArrayList lista, String list_type){
		int k;
		Reta r;
		
		System.out.println("Mostrando todas as listas "+list_type);
		System.out.println("");
		
		for ( k = 0; k < lista.size(); k++ ) {
			r = (Reta)lista.get(k);		
			
			System.out.print("Reta: ");
			System.out.print(r.Coordenada_pi().Coordenada_x()+" ");
			System.out.print(r.Coordenada_pi().Coordenada_y()+" ");
			System.out.print(r.Coordenada_pf().Coordenada_x()+" ");
			System.out.println(r.Coordenada_pf().Coordenada_y());
			System.out.println("-----------------------------------");
		}
	}
	
/*************************************************************************************/
	
	public int Monta_Lista_Arquivo(String nome_arquivo){
		Arquivo file;
		String linha;
		String[] pontos;		
		Coordenada coord_pi, coord_pf;
		Reta r;
		int total_de_retas;
		
		file = new Arquivo();		
		
		file.abreArquivo(nome_arquivo);
		linha = file.leArquivo();
		while(linha != null){		
			pontos = linha.split(" ");
			coord_pi = new Coordenada(Integer.parseInt(pontos[0]), Integer.parseInt(pontos[1]));
			coord_pf = new Coordenada(Integer.parseInt(pontos[2]), Integer.parseInt(pontos[3]));
			r = new Reta(coord_pi, coord_pf);
			Insere_na_Lista(conjunto_retas, r);
			linha = file.leArquivo();
		}		
		Monta_Lista_Retas_Horizontais(conjunto_retas);
		Monta_Lista_Retas_Verticais(conjunto_retas);
		Monta_Lista_Retas_Diagonais(conjunto_retas);
		Monta_Lista_Retas_Interceptam(conjunto_retas);
		Monta_Lista_Retas_Interceptam(conjunto_retas);
		
		total_de_retas = conjunto_retas_diagonais.size()+conjunto_retas_horizontais.size()+conjunto_retas_verticais.size();
		
		return total_de_retas;
	}
	
/*************************************************************************************/
	
	private void Monta_Lista_Retas_Horizontais(ArrayList lista){
		int k;
		Reta r;
		for ( k = 0; k < lista.size(); k++ ) {
			r = (Reta)lista.get(k);		
			
			//Reta horizontal
			if(r.Coordenada_pi().Coordenada_y() == r.Coordenada_pf().Coordenada_y()){
				Insere_na_Lista(conjunto_retas_horizontais, r);
			}			
		}
	}

/*************************************************************************************/	
	
	private void Monta_Lista_Retas_Verticais(ArrayList lista){
		int k;
		Reta r;
		for ( k = 0; k < lista.size(); k++ ) {
			r = (Reta)lista.get(k);		
			
			//Reta vertical
			if(r.Coordenada_pi().Coordenada_x() == r.Coordenada_pf().Coordenada_x()){
				Insere_na_Lista(conjunto_retas_verticais, r);
			}			
		}
	}

/*************************************************************************************/
	
	private void Monta_Lista_Retas_Diagonais(ArrayList lista){
		int k;
		Reta r;
		for ( k = 0; k < lista.size(); k++ ) {
			r = (Reta)lista.get(k);		
			
			//Reta diagonal
			if( (r.Coordenada_pi().Coordenada_x() != r.Coordenada_pf().Coordenada_x()) &&
					(r.Coordenada_pi().Coordenada_y() != r.Coordenada_pf().Coordenada_y()) ){
				Insere_na_Lista(conjunto_retas_diagonais, r);
			}			
		}
	}

/*************************************************************************************/	
	
	public void Monta_Lista_Retas_Interceptam(ArrayList lista){
		int k,j;
		Reta r, s;
		for ( k = 0; k < lista.size(); k++ ) {
			r = (Reta)lista.get(k);
			for(j = 0; j < lista.size(); j++){
				s = (Reta)lista.get(j);
				if(k != j){
					//Retas que se interceptam
					Inserseccao_Retas(r, s);
				}				
			}				
		}
	}

/*************************************************************************************/	
	
	private void Inserseccao_Retas(Reta reta1, Reta reta2){
		
		ArrayList par_retas_interceptadas = new ArrayList();
		
		
		if(Intersection.getIntersection(reta1, reta2)){
			par_retas_interceptadas.add(reta1);
			par_retas_interceptadas.add(reta2);
			
			conjunto_retas_interceptam.add(par_retas_interceptadas);
		}
	}

/*************************************************************************************/
	
	public void Mostra_Retas_Interceptadas(){
		
		ArrayList par_retas_interceptadas;
		int k,j=0;
		Reta r, s;
		
		for(k = 0; k < conjunto_retas_interceptam.size(); k++){
			par_retas_interceptadas = (ArrayList) conjunto_retas_interceptam.get(k);
			
			r = (Reta) par_retas_interceptadas.get(j);
			s = (Reta) par_retas_interceptadas.get(j+1);
			
			System.out.println("As retas se interceptam:");
			System.out.println("Reta r: "+r.Coordenada_pi().Coordenada_x()+" "+r.Coordenada_pi().Coordenada_y()+" "+r.Coordenada_pf().Coordenada_x()+" "+r.Coordenada_pf().Coordenada_y());
			System.out.println("Reta s: "+s.Coordenada_pi().Coordenada_x()+" "+s.Coordenada_pi().Coordenada_y()+" "+s.Coordenada_pf().Coordenada_x()+" "+s.Coordenada_pf().Coordenada_y());
			System.out.println("");
		}	
		
	}

/*************************************************************************************/
	
}
