package src;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Interface extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Interface(int largura, int altura, int pos_x, int pos_y, String titulo_frame){
		super(titulo_frame);
		setSize(largura, altura);
		setLocation(pos_x, pos_y);
		setVisible(true);
	}	
	
	
	public void paint(Graphics desenho){
		
		super.paint(desenho);
		
		Circulo_1 circulo_1 = new Circulo_1();		
		circulo_1.setDesenho(desenho);
		
		//circulo_1.Set_Circulo_1(120, 120, 80);
		//circulo_1.Desenha_Metodo_Direto(desenho);
		
		//circulo_1.Set_Circulo_1(250, 250, 50);
		//circulo_1.Desenha_Metodo_Parametrico(desenho);
		
		//circulo_1.Set_Circulo_1(380, 380, 30);
		//circulo_1.Desenha_Metodo_Incremental(desenho);
		
		circulo_1.Set_Circulo_1(300, 300, 100);
		circulo_1.Desenha_Metodo_DDA_Inteiro(desenho);
		
		
		//Implementação das Elipses
		//Elipse_1 elipse_1 = new Elipse_1();
		//elipse_1.setDesenho(desenho);
		
		//elipse_1.Set_Elipse_1(450, 450, 160, 130);
		//elipse_1.Desenha_Metodo_Incremental(desenho);		
		
	}

}
