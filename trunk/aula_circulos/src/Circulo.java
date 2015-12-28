package src;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo {
	
	private Graphics desenho;
	// X e Y são as coordenadas do centro
	// R é o raio do círculo
	int x_centro, y_centro, r;
	
	public Circulo(){	
	}
	
	public void setDesenho(Graphics desenho) {
		this.desenho = desenho;
	}
	
	protected void plota_pixel( int x, int y, Color cor)
	{
		desenho.setColor( cor );
		desenho.drawLine( x, y, x, y );
	}
	
	public void Desenha_Metodo_Direto(Graphics desenho){
		int x;
		int y, y2;
		int temp_1;		
		Color cor = Color.black;
		
		//Iniciando o método com x em x_centro - r e y igual ao y_centro
		x = x_centro - r;
		y = y_centro;
		
		
		plota_pixel(x, y, cor);
		
		for(x = (x_centro - r + 1); x <= (x_centro + r); x++ ){
			//Calcula a parte da raiz
			temp_1 = (int) Math.sqrt(Math.pow(r, 2) - Math.pow((x - x_centro), 2));
			//plota o y positivo
			y2 = y + temp_1;
			plota_pixel(x, y2, cor);
			
			//plota o y negativo
			y2 = y - temp_1;
			plota_pixel(x, y2, cor);			
		}	
		
	}
	
	public void Desenha_Metodo_Parametrico(Graphics desenho){
		int x;
		int y;
		int angulo;
		int delta_x, delta_y;
		Color cor = Color.red;
		
		for(angulo = 0; angulo <= 45; angulo++){
			//Achando os pontos iniciais
			delta_x = (int) (r*Math.cos(Math.toRadians(angulo)));
			delta_y = (int) (r*Math.sin(Math.toRadians(angulo)));
			
			x = x_centro;
			y = y_centro;
			
			//Plotando os 8 pontos espelhados
			plota_pixel(x + delta_x, y + delta_y, cor);
			plota_pixel(x + delta_x, y - delta_y, cor);
			plota_pixel(x - delta_x, y - delta_y, cor);
			plota_pixel(x - delta_x, y + delta_y, cor);
			
			plota_pixel(y + delta_y, x + delta_x, cor);
			plota_pixel(y + delta_y, x - delta_x, cor);
			plota_pixel(y - delta_y, x - delta_x, cor);
			plota_pixel(y - delta_y, x + delta_x, cor);
			
		}
		
	}
	
	public void Desenha_Metodo_Incremental(Graphics desenho){
		float x1, y1;
		float x2, y2;
		
		
		float incremento_angular;
		float cos_angulo, sen_angulo;
		float angulo;
		
		Color cor = Color.black;
				
		x1 = 0;
		y1 = r;
		
		incremento_angular = (float)1/r;
		
		cos_angulo = (float) Math.cos(Math.toRadians(incremento_angular));
		sen_angulo = (float) Math.sin(Math.toRadians(incremento_angular));
		
		//Plota o primeiro ponto
		plota_pixel((int)(x1) + x_centro, (int)(y1) + y_centro, cor);
		
		for(angulo = 0; angulo <= 45; angulo += incremento_angular){
			//Calcula o próximo pixel
			x2 = x1*cos_angulo - y1*sen_angulo;
			y2 = y1*cos_angulo + x1*sen_angulo;
			
			//Plotando os 8 pontos espelhados		
			plota_pixel((int)(x_centro + x2), (int)(y_centro + y2), cor);
			plota_pixel((int)(x_centro + x2), (int)(y_centro - y2), cor);
			plota_pixel((int)(x_centro - x2), (int)(y_centro - y2), cor);
			plota_pixel((int)(x_centro - x2), (int)(y_centro + y2), cor);
			
			plota_pixel((int)(y_centro + y2), (int)(x_centro + x2), cor);
			plota_pixel((int)(y_centro + y2), (int)(x_centro - x2), cor);
			plota_pixel((int)(y_centro - y2), (int)(x_centro - x2), cor);
			plota_pixel((int)(y_centro - y2), (int)(x_centro + x2), cor);
			
			//Guardando o X e Y calculados
			x1 = x2;
			y1 = y2;
		}
		
	}
	
	public void Desenha_Metodo_DDA_Inteiro(Graphics desenho){
		int x, y;
		int dA, dB;
		int S;
		
		Color cor = Color.pink;
		
		//Recebe as coordenadas iniciais
		x = 0;
		y = r;
		
		
		for(x = 0; x <= (r/Math.sqrt(2)); x++){
			//Calculo a distancia do ponto A e do ponto B
			dA = (int) (Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(r, 2));
			dB = (int) (Math.pow(x, 2) + Math.pow((y-1), 2) - Math.pow(r, 2));
			
			//Verifica o valor de S
			S = dA + dB;
			
			//Verifica qual o ponto a ser escolhido
			if(S>0){
				//Escolhe o ponto B, ou seja, decrementa y
				y = y-1;
			}
			else{
				//Escolhe o ponto A, ou seja, não mexe em y		
			}

			//Plotando os 8 pontos espelhados		
			plota_pixel((int)(x_centro + x), (int)(y_centro + y), cor);
			plota_pixel((int)(x_centro + x), (int)(y_centro - y), cor);
			plota_pixel((int)(x_centro - x), (int)(y_centro - y), cor);
			plota_pixel((int)(x_centro - x), (int)(y_centro + y), cor);
			
			plota_pixel((int)(y_centro + y), (int)(x_centro + x), cor);
			plota_pixel((int)(y_centro + y), (int)(x_centro - x), cor);
			plota_pixel((int)(y_centro - y), (int)(x_centro - x), cor);
			plota_pixel((int)(y_centro - y), (int)(x_centro + x), cor);
		}
		
	}

}
