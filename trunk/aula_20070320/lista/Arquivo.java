package lista;
 
import java.io.*;

public class Arquivo {
	
	// Atributos
	private FileReader fr;
	private BufferedReader br;
	
/*************************************************************************************/
	
	// Construtor
	public Arquivo () {		
	}
	
/*************************************************************************************/	
	
	public void abreArquivo (String Nome){	
		try {
			fr = new FileReader (Nome);
			br = new BufferedReader (fr);
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo " + Nome + " não encontrado!");
		}		
	}
	
/*************************************************************************************/	
	
	public void fechaArquivo()
	{
		try
		{
			br.close();
		}
		catch(IOException e)
		{
			System.out.println("Erro ao fechar arquivo!");
		}
	} 

/*************************************************************************************/	
	
	public String leArquivo (){
		
		String dado;
		
		try {
			dado = br.readLine();
			return dado;
		} catch (IOException e) {
			System.out.println("Erro na leitura dos dados do arquivo...");
			return null;
		} 		
	}

/*************************************************************************************/

}
