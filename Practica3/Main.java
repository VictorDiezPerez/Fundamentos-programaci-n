//Víctor Díez Pérez
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	
	public static void main(String args[]) {
		/*
		 * Se utiliza diferentes métodos para resolver tareas sobre una
		 * matriz de números reales, en su forma habitual o, en formato-C.
		 * 
		 * Entrada: Línea con un entero, con valor 1 ó 2, que determinará las
		 * entradas siguientes:
		 * Caso 1: Sí la primera entrada es 1 seguirá una matriz:
		 * una línea, dos enteros positivos que muestra la dimensión de la matriz.
		 * segunda linea en el que se muestre el contenido de la matriz por filas. 
		 * Caso 2: Sí la primera entrada es 2, el formato-C de una matriz:
		 * una línea con entero cuyo valor es la longitud de las sig. entradas. 
		 * una línea con n valores reales. 
		 * una línea con n valoresenteros estrictamente positivos
		 * (índices de filas). 
		 * una línea con n valoresenteros estrictamente positivos
		 * (índices de columnas). 
		 * dos enteros f, c en el rango adecuado para índice de fila y columna. 
		 * 
		 * Salida: En el primer caso, la matriz en formato-C.
		 * En el segundo caso, el número de filas y columnas de la matriz.
		 * la matriz representada, escrita en forma habitual. 
		 * el valor que está en la posición indicada por los dos últimos enteros
		 * de la entrada.
		 * Observaciones: se validan números reales.
		 */

		Scanner input;
		int opcion, filas, columnas, longitud, f, c;
		int matrizCI[], matrizCJ[];
		double matrizNatural[][], matrizCV[], valor;
		// Comprobamos si hay argumentos, no es necesario validar
		// un argumento solo cuando hay archivo,si hay redireccionamiento hay 2
		// y se hace como por consola
		if (args.length == 1) {
			try {
				input = new Scanner(new File(args[0]));
			} catch (FileNotFoundException e) {
				System.out.println("Fichero no encontrado");
				return;
			}
		} else {
			input = new Scanner(System.in);
		}
		input.useLocale(Locale.US);
		do {
		System.out.println("Introduzca un 1 para matriz, introduzca un 2 "
				+ "para expresarla en formato-C ");
		opcion = input.nextInt();
		}while(opcion != 1 && opcion != 2);
		// Opcion 1, lectura de matriz en formato habitual paso a formato C
		if(opcion == 1) {
			// Entrada:
			// Lectura de las dimensiones de la matriz: filas x columnas
			System.out.println("Introduzca las dimensiones de la matriz ");
			filas = input.nextInt();
			columnas = input.nextInt();
			System.out.println("Introduzca las filas de la matriz ");
			// Lectura de los datos por filas
			matrizNatural = new double[filas][columnas];
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					matrizNatural[i][j] = input.nextDouble();
				}
			}
			// Transformar formato natural a formato C -> vectores v,i,j
			longitud = numeroDatos(matrizNatural);
			matrizCV = obtenerVi(matrizNatural, longitud);
			matrizCI = obtenerIndices(matrizNatural, longitud, true);
			matrizCJ = obtenerIndices(matrizNatural, longitud, false);
			// Salida
			System.out.print("v= ");
			imprimirConsola(matrizCV);
			System.out.print("i= ");
			imprimirConsola(matrizCI);
			System.out.print("j= ");
			imprimirConsola(matrizCJ);
		}
		// Opcion 2, lectura de matriz en formato C paso a formato habitual
		if(opcion == 2) {
			// Entrada
			// Lectura del numero de elementos no nulos
			System.out.println("Introduzca la longitud del vector ");
			longitud = input.nextInt();
			// Lectura del vector vi
			System.out.println("Introduzca los valores del vector ");
			matrizCV = new double[longitud];
			for (int i = 0; i < longitud; i++) {
				matrizCV[i] = input.nextDouble();
			}
			// Lectura del vector i
			System.out.println("Introduzca indices de fila enteros positivos");
			matrizCI = new int[longitud];
			for (int i = 0; i < longitud; i++) {
				matrizCI[i] = input.nextInt();
			}
			// Lectura del vector j
			System.out.println("Introduzca indices columna enteros positivos");
			matrizCJ = new int[longitud];
			for (int i = 0; i < longitud; i++) {
				matrizCJ[i] = input.nextInt();
			}
			// Lectura de valores f y c
			System.out.println("Introduzca dos enteros indice fila y columna");
			f = input.nextInt();
			c = input.nextInt();
			// Salida
			filas = numeroFilas(matrizCI);
			columnas = numeroColumnas(matrizCJ);
			System.out.println("El número de filas es "+filas +", el número de"
					+ " columnas es " + columnas);
			System.out.println();
			matrizNatural = obtenerMatrizNatural(matrizCV, matrizCI, matrizCJ);
			imprimirConsola(matrizNatural);
			valor = obtenerCoordenadas(matrizCV, matrizCI, matrizCJ, f, c);
			System.out.println();
			System.out.println("El valor es "+valor);
		}

		input.close();
	}

	// Calcula el numero de elementos no nulos en la matriz
	private static int numeroDatos(double[][] m) {
		int res = 0;
		for (int j = 0; j < m[0].length; j++) {
			for (int i = 0; i < m.length; i++) {
				if (m[i][j] != 0) {
					res++;
				}
			}
		}
		return res;
	}

	// calcula la matriz v
	public static double[] obtenerVi(double[][] m, int tam) {
		double res[] = new double[tam];
		int n = 0;
		for (int j = 0; j < m[0].length; j++) {
			for (int i = 0; i < m.length; i++) {
				if (m[i][j] != 0) {
					res[n] = m[i][j];
					n++;
				}
			}
		}
		return res;
	}

	// devuelve vector de indices fila(i) para filas true, columnas(j) si false
	public static int[] obtenerIndices(double[][] m, int tam, boolean filas) {
		int res[] = new int[tam];
		int n = 0;
		for (int j = 0; j < m[0].length; j++) {
			for (int i = 0; i < m.length; i++) {
				if (m[i][j] != 0) {
					res[n] = filas ? i + 1 : j + 1;
					n++;
				}
			}
		}
		return res;
	}

	// imprimir un vector/matriz v en consola (sobrecargada)
	public static void imprimirConsola(int[] v) {
		for (int x : v)
			System.out.print(x + " ");
		System.out.println();
	}

	public static void imprimirConsola(double[] v) {
		for (double x : v)
			System.out.print(x + " ");
		System.out.println();
	}

	public static void imprimirConsola(double[][] m) {
		for (double[] f : m) {
			for (double y : f) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
	}

	// Dada matriz en formato C indica valor en posición (i,j)
	public static double obtenerCoordenadas(double valores[], int filas[], 
			int columnas[], int i, int j) {
		int posicion = 0;
		boolean encontrado = false;
		while (!encontrado && posicion < filas.length) {
			if (filas[posicion] == i && columnas[posicion] == j) {
				encontrado = true;
			}
			posicion++;
		}
		return encontrado ? valores[posicion - 1] : 0;
	}

	// Dada matriz en formato C indica en numero de filas de la matriz
	public static int numeroFilas(int filas[]){
		int max = filas[0];
		for (int i = 1; i < filas.length; i++) {
			if (filas[i] > max) {
				max = filas[i];
			}
		}
		return max;
	}

	// Dada matriz en formato C indica en numero de columnas de la matriz
	public static int numeroColumnas(int columnas[]) {
		return columnas[columnas.length - 1];
	}

	// Dados 3 vectores en formato C obtiene la matriz natural representada
	public static double[][] obtenerMatrizNatural(double v1[],int v2[],int v3[]){
		int filas = numeroFilas(v2);
		int columnas = numeroColumnas(v3);
		double res[][] = new double[filas][columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				res[i][j] = obtenerCoordenadas(v1, v2, v3, i + 1, j + 1);
			}
		}
		return res;
	}

}
