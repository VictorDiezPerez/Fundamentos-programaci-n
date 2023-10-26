//V�ctor D�ez P�rez
import java.util.Scanner;
public class Main {
	public static void main (String args[]){
		/*
		 * Mostrar el n�mero con menor divisor esencial dentro de un intervalo
		 * Entrada dos enteros positivos
		 * Salida el numero que tiene menor divisor esencial en el intervalo
		 * PRE: Dos n�meros positivos y ordenados en orden creciente  
		 */
		int num1,num2, numero, minimo, div;
		Scanner in = new Scanner (System.in);
		do {
		System.out.print("Escriba los extremos del intervalo (enteros positivos): ");
		num1 = in.nextInt();
		num2 = in.nextInt();
		}while(num1<=0 || num2<=0 || num1>num2);//Cuando no se cumple la condici�n se 
		//repite el scanner
		numero=num2;
		minimo=divisorEsencial(num2);
		for(int i=num1;i<num2;i++) {  //Realiza el intervalo
		div=divisorEsencial(i);
		if(div<minimo) {
		numero=i;
		minimo=div;
		}
		}
		System.out.print("El n�mero "+numero+" tiene el divisor esencial " 
		+minimo+" m�s peque�o del rango.");
		in.close();
		}

		public static int divisorEsencial(int n) {
			/*
			 * DEVUELVE el divisor esencial de un n�mero
			 */
		boolean factor;
		int res=1;
		for(int i=2;i<n;i++) { //Genera el divisor esencial
		      factor=false;
		      while(n%i==0) {
		  factor=true;
		  n=n/i;
		       }
		       if(factor) {
		   res*=i;
		       }
		 }
		 return res*n;
		}

}
