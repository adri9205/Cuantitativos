package sim;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
	
	public static double parExp(double a, double b){
		Random rand = new Random();  
		double pickedNumber = rand.nextDouble();
		double p=a*Math.pow(pickedNumber, (-1/b));
		return p;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		double A=1.77059;
		double numRechazados=0;
		double B=1.51111;
		double alfa=1.7;
		double beta=1.8;
		double tiempoT=1e3;
		double metrica=Math.exp(-4.3);
		double metrica2=Math.exp(-3.4);
		double [] mult= new double [2000]; //si es mayor a mult se rechaza
		mult[0]=1;
		double [] random= new double [2000];
		double Ex=(A*alfa)/(alfa-1);
		double Ey=(B*beta)/(beta-1);
		double fA=((alfa*B)/(beta*A)*100);
		double fB=(((alfa-1)*beta*B)/((beta-1)*alfa*A))*100;
		double finT = tiempoT;     
		double t = 0.0;          
		double   entradaT = 0;         
		double   salidaT = tiempoT;     
		int n=0;           
		int id=0;         
		int id2=0;        
		int c=0;           
		double b=0;             
		double serv1=0;           
		double fin=t;           
		double inicio=0;              
		int o=1;
		int om=0;
		boolean ver=true;
		double [] []cosas=new double[3000][2];
		  while (ver)
		  {
		    if (entradaT < salidaT)            
		    {
		      t = entradaT;
		      serv1 = serv1 + n * (t - fin);
		      n++;
		      id++;   
		      fin = t;           
		      cosas[id][om] = t;
		      entradaT = t + parExp(A,alfa);
		      mult[n]=mult[n-1]+parExp(A,alfa);
		      if (mult[n]>metrica){
		    	  numRechazados++;
		      }
		      if (n == 1)
		      {
		        inicio = t;              
		        salidaT = t + parExp(B, beta);
		      }
		    }
		    else                 
		    {
		      t = salidaT;
		      serv1 = serv1 + n * (t - fin);
		      n--;
		      fin = t;               
		      id2++;
		      cosas[id2][o]= t;
		      c++;                      
		      if (n > 0)
		        salidaT = t + parExp(B, beta);
		      else
		      {
		        salidaT = tiempoT;
		        b = b + t - inicio;      
		      }
		    }
		    if(t < finT){
		    	ver=false;
		    }
		  }
		  double x;                
		  double u;                
	      double l;                  
		  double w;       

		  x = c / t; 
		  u = b / t;   
		  l = serv1 / t; 
		  w = l / x;      
		
		System.out.println("Tiempo: "+tiempoT);
		System.out.println("Razon de Llegadas: "+Ex);
		System.out.println("Razon de Salidas: "+Ey);
		System.out.println("Utilizacion en porcentaje de A: "+fA);
		System.out.println("Utilizacion en porcentaje de B: "+fB);
		System.out.println("Atendidos: "+c);
		System.out.println("Rechazados: "+numRechazados);
		System.out.println("Utilizacion de Router: "+u);
		System.out.println("Promedio de paquetes en memoria: "+l);
		System.out.println("Tiempo de espera entre llegada de paquete y transmision final: "+w);
		
	}

}
