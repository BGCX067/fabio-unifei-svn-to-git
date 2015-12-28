// A classe Painel é responsável por saber desenhar pontos e informações em si.
// Logo, ela é quem gerencia a plotagem de retas horizontais, verticais e diagonais.

package lista;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.lang.Math;

import javax.swing.JFrame;


public class Painel extends JFrame {
	
	private ArrayList lista_retas_horizontais;
	private ArrayList lista_retas_verticais;
	private ArrayList lista_retas_diagonais;

/*************************************************************************************/	

	public Painel(int largura_frame, int altura_frame, 
					int pos_x_frame, int pos_y_frame, String titulo_frame,
					Lista lista_retas){		
		
		super ( titulo_frame );
		
		lista_retas_horizontais = lista_retas.getConj_Retas_Horizontais();
		lista_retas_verticais = lista_retas.getConj_Retas_Verticais();
		lista_retas_diagonais = lista_retas.getConj_Retas_Diagonais();
		
		setSize ( largura_frame, altura_frame );
		setLocation ( pos_x_frame, pos_y_frame );
		
		setVisible ( true );
		
	}

/*************************************************************************************/	
	
	private void plota_pixel( int x, int y, Graphics desenho, Color  cor)
	{
		desenho.setColor( cor );
		desenho.drawLine( x, y, x, y );
	}
	
/*************************************************************************************/	
	
	private void reta_horizontal ( int x1, int x2, int y, 
									Graphics desenho, Color cor)
	{
		int k;
		
		for ( k = x1; k < x2; k++ )
			plota_pixel ( k, y, desenho, cor);
	}
	
/*************************************************************************************/
	
	private void reta_vertical ( int x, int y1, int y2,
								   Graphics desenho, Color cor)
	{
		int k;
		
		for ( k = y1; k < y2; k++ )
			plota_pixel ( x, k, desenho, cor );
	}

/*************************************************************************************/
	
	private void reta_diagonal ( int x1, int x2, int y1, int y2,
			   Graphics desenho, Color cor)
	{		
		
		int k, y;
				
		float m, b;
		
		m = (y2 - y1)/(x2 - x1);
		
		b = y1 - m * x1;
		if(x1 > x2){
			for(k = x2; k <= x1; k++){
								
				y = (int) (m * k + b);	
				plota_pixel ( k, y, desenho, cor );			
			}
			
		}else{
			
			for(k = x1; k <= x2; k++){
				
				y = (int) (m * k + b);	
				plota_pixel ( k, y, desenho, cor );			
			}
		}		
		
	}
/*************************************************************************************/	
	private void reta_inclinada_dda(int x1, int x2, int y1, int y2,
			   Graphics desenho, Color cor){
		int delta_x;
		int delta_y;
		int erro = 0;
		int r;
		int x, y;	
		int x_temp, y_temp;
			
		
		//Calcula do Dx e o Dy
		delta_x = x2 - x1;
		delta_y = y2 - y1;		
		
		//Verifica se há necessidade de trocar os pontos
		if(Math.abs(delta_y) < Math.abs(delta_x)){
			//Troca os pontos
			x_temp = x1;
			x1 = x2;
			x2 = x_temp;
			
			y_temp = y1;
			y1 = y2;
			y2 = y_temp;
			
			//Calcula novamente Dx e o Dy
			delta_x = x2 - x1;
			delta_y = y2 - y1;
		}		
		
		//Plota o primeiro ponto
		x = x1;
		y = y1;
		plota_pixel ( x, y, desenho, cor );	
		
		if(delta_x >= 0){
			//Caso 1 ou Caso 2
			if(Math.abs(delta_x) >= Math.abs(delta_y)){
				//Caso 1
				for(r = 1; r <= (Math.abs(delta_x)-1); r++ ){
					if(erro <= 0){
						x++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_y;						
					}
					else{
						x++;
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_y - delta_x;
					}
				}
			}
			else{
				//Caso 2
				for(r = 1; r <= (Math.abs(delta_y)-1); r++ ){
					if(erro < 0){
						x++;
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_y - delta_x;												
					}
					else{
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro - delta_x;
					}
				}
			}
		}
		else{
			//Caso 3 ou Caso 4
			if(Math.abs(delta_x) >= Math.abs(delta_y)){
				//Caso 3	
				for(r = 1; r <= (Math.abs(delta_x)-1); r++ ){
					if(erro < 0){
						x--;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_y;						
					}
					else{
						x--;
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_x + delta_y;
					}
				}
			}
			else{
				//Caso 4
				for(r = 1; r <= (Math.abs(delta_y)-1); r++ ){
					if(erro < 0){
						x--;
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_x + delta_y;												
					}
					else{
						y++;
						plota_pixel ( x, y, desenho, cor );
						erro = erro + delta_x;
					}
				}
			}
		}
		
		//Plota o ponto final
		x = x2;
		y = y2;
		plota_pixel ( x, y, desenho, cor );
		
	}
	
/*************************************************************************************/
	
	public void paint ( Graphics desenho ) 
	{
		int x1, x2, y1, y2, k;
		
		Reta r;
		
		for(k = 0; k < lista_retas_horizontais.size(); k++){
			r = (Reta)lista_retas_horizontais.get(k);
			
			x1 = r.Coordenada_pi().Coordenada_x();
			x2 = r.Coordenada_pf().Coordenada_x();
			y1 = r.Coordenada_pi().Coordenada_y();
			y2 = r.Coordenada_pf().Coordenada_y();
						
			// desenhando uma reta horizontal
			reta_horizontal ( x1, x2, y1, desenho, Color.red );
		}
		
		for(k = 0; k < lista_retas_verticais.size(); k++){
			r = (Reta)lista_retas_verticais.get(k);
			
			x1 = r.Coordenada_pi().Coordenada_x();
			x2 = r.Coordenada_pf().Coordenada_x();
			y1 = r.Coordenada_pi().Coordenada_y();
			y2 = r.Coordenada_pf().Coordenada_y();
						
			// desenhando uma reta horizontal
			reta_vertical ( x2, y1, y2, desenho, Color.black );
		}
				
		for(k = 0; k < lista_retas_diagonais.size(); k++){
			r = (Reta)lista_retas_diagonais.get(k);
			
			x1 = r.Coordenada_pi().Coordenada_x();
			x2 = r.Coordenada_pf().Coordenada_x();
			y1 = r.Coordenada_pi().Coordenada_y();
			y2 = r.Coordenada_pf().Coordenada_y();
			
			// desenhando uma reta diagonal
			//reta_diagonal( x1, x2, y1, y2, desenho, Color.blue );
			reta_inclinada_dda( x1, x2, y1, y2, desenho, Color.blue );
		}
			
		
	}
/*************************************************************************************/
	
	

}
