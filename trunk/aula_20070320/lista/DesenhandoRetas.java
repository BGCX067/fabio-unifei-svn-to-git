package lista;

import java.awt.Color;

public class DesenhandoRetas {
	
	public DesenhandoRetas(){
		
	}
	
/*************************************************************************************/
	
	public void AbreWindow(Lista lista_retas) {
			
		Painel des;
	
		des = new Painel ( 800, 500, 10, 20, "DESENHANDO RETAS", lista_retas );
		des.setBackground ( Color.white );
	}
	
/*************************************************************************************/

}
