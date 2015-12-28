package src;

import java.awt.Color;
import java.awt.Graphics;

public class Elipse {
	
	private Graphics desenho;
	
	int x_centro, y_centro, raioA, raioB;

	public void setDesenho(Graphics desenho) {
		this.desenho = desenho;
	}
	
	protected void plota_pixel( int x, int y, Color cor)
	{
		desenho.setColor( cor );
		desenho.drawLine( x, y, x, y );
	}
	
	public void Desenha_Metodo_Incremental(Graphics desenho){
		float x1, y1;
		float x2, y2;
		
		double delta;
		double cosD, sinD;
		
		double angulo;
				
		Color cor = Color.black;
		
		x1 = (float) 0;
		y1 = (float) raioB;
		
		delta = 1/(double)raioA;
				
		cosD = (double) Math.cos(delta);
		sinD = (double) Math.sin(delta);			
		
		//Plota x1 e y1 e ainda espelha
		plota_pixel((int)(x_centro + x1), (int)(y_centro + y1), cor);
		plota_pixel((int)(x_centro - x1), (int)(y_centro - y1), cor);
		plota_pixel((int)(x_centro + x1), (int)(y_centro - y1), cor);
		plota_pixel((int)(x_centro - x1), (int)(y_centro + y1), cor);
		
		
		for(angulo = 0; angulo <= Math.PI*raioA/2; angulo++){
			//Calcula x2 e y2
			x2 = (float) (x1*cosD - (raioA/(double)raioB)*y1*sinD);
			y2 = (float) (y1*cosD + (raioB/(double)raioA)*x1*sinD);
			
			//Plota x2 e y2 e ainda espelha
			plota_pixel((int)(x_centro + x2), (int)(y_centro + y2), cor);
			plota_pixel((int)(x_centro - x2), (int)(y_centro - y2), cor);
			plota_pixel((int)(x_centro + x2), (int)(y_centro - y2), cor);
			plota_pixel((int)(x_centro - x2), (int)(y_centro + y2), cor);
			
			x1 = x2;
			y1 = y2;
		}
	}

}
