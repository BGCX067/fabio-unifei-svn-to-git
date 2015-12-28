package lista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
	
	Lista lista;
	
	int total_de_retas;
	
	DesenhandoRetas window;

/*************************************************************************************/	
	
	public Menu(){	
		lista = new Lista();
	}

/*************************************************************************************/	
	
	public void criaMenu(){
		int Opcao = 0;
		InputStreamReader st_reader;
		BufferedReader bf_reader;
		String aux = null;
		
		//Imprime o Menu de Opções
		while(Opcao != 9){
			System.out.println("//-------- Menu de Opções --------//");
			System.out.println("0- Obter arquivo");
			System.out.println("1- Mostrar todas as retas");
			System.out.println("2- Mostrar as retas horizontais");
			System.out.println("3- Mostrar as retas verticais");
			System.out.println("4- Mostrar as retas nao horizontais nem verticais");
			System.out.println("5- Mostrar as retas que se interceptam");
			System.out.println("6- Mostrar as retas que formam um poligono fechado");
			System.out.println("7- Mostra todas as retas em modo gráfico");
			System.out.println("9- Finalizar o programa");
			System.out.println("//--------------------------------//");
			System.out.println("Opção: ");
			
			st_reader = new InputStreamReader(System.in);
			bf_reader = new BufferedReader(st_reader);
			
			try {
				//Leitura de opção do teclado
				aux = bf_reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Opcao = Integer.parseInt(aux);
			
			//Com a opção selecionada, chama os métodos
			switch(Opcao){
				case 0:
					total_de_retas = lista.Monta_Lista_Arquivo("dados.dat");					
					System.out.println("Lista criada com sucesso! Total de Retas: "+total_de_retas);						
					break;
				case 1:						
						//Imprime todas as retas
						lista.Percorre_lista(lista.getConj_Retas(),"");
					break;
				case 2:
						//Imprime as retas horizontais
						lista.Percorre_lista(lista.getConj_Retas_Horizontais(),"Horizontais");
					break;
				case 3:
						//Imprime as retas verticais
						lista.Percorre_lista(lista.getConj_Retas_Verticais(),"Verticais");
					break;
				case 4:
						//Imprime as retas diagonais
						lista.Percorre_lista(lista.getConj_Retas_Diagonais(),"Diagonais");
					break;
				case 5:
						//Imprime todas as retas que se interceptam
						lista.Mostra_Retas_Interceptadas();
					break;
				case 6:
					break;
				case 7:
						//Cria a interface gráfica e mostra todas as retas
						window = new DesenhandoRetas();
						window.AbreWindow(lista);
					break;
			}
			
		}
	}

/*************************************************************************************/	
	
}
