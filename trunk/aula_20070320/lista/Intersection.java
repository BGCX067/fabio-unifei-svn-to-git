package lista;

public class Intersection {
  
	public Intersection(){
	}

/*************************************************************************************/	
	
	static public boolean getIntersection( Reta line1, Reta line2 )	{
		
	double dyline1, dxline1;
	double dyline2, dxline2;
	double x1line1, y1line1, x2line1, y2line1;
	double x1line2, y1line2, x2line2, y2line2;

	/* Primeiro, checa para ver se os segmentos se interceptam por parametrização de
   s e t; se s e t  encontram-se entre [0,1], então os segmentos se interceptam */
	x1line1 = (double)line1.Coordenada_pi().Coordenada_x();
	y1line1 = (double)line1.Coordenada_pi().Coordenada_y();
	x2line1 = (double)line1.Coordenada_pf().Coordenada_x();
	y2line1 = (double)line1.Coordenada_pf().Coordenada_x();
	
	x1line2 = (double)line2.Coordenada_pi().Coordenada_x();
	y1line2 = (double)line2.Coordenada_pi().Coordenada_y();
	x2line2 = (double)line2.Coordenada_pf().Coordenada_x();
	y2line2 = (double)line2.Coordenada_pf().Coordenada_y();
	
	/* Checa para ver se os segmentos o ponto final em comum. Se sim,
	   Então retorna que se interceptam neste ponto */
	if ((x1line1==x1line2) && (y1line1==y1line2))
	{
	  return true;
	}
	if ((x1line1==x2line2) && (y1line1==y2line2))
	{
	  return true;
	}
	if ((x2line1==x1line2) && (y2line1==y1line2))
	{
	  return true;
	}
	if ((x2line1==x2line2) && (y2line1==y2line2))
	{
	  return true;
	}
	
	dyline1 = -( y2line1 - y1line1 );
	dxline1 = x2line1 - x1line1;
	
	dyline2 = -( y2line2 - y1line2 );
	dxline2 = x2line2 - x1line2;
	
	/* Verifica o ponto de interesecção usando ax+by+e = 0 e cx+dy+f = 0 */
	    if( (dyline1 * dxline2 - dyline2 * dxline1) == 0 )
	    	return true;
	    else
	    	return false;
	  }
	
/*************************************************************************************/

}
