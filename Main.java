import java.util.Scanner;
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Víctor Díez Pérez
		Scanner Teclado=new Scanner(System.in);
		int x,y,z,u,d,c;
		System.out.println("Introduzca un número de tres cifras");
		x=Teclado.nextInt();
		if(x<99 ||x>999) {
			System.out.println("INCORRECTO");
		}else {
	    c =x/100;
		d =(x-(c*100))/10;
		u = x%10;
		y=c*1+d*10+u*100;
		z=x+y;
		if(z%2==0){
			System.out.println("NO ("+x+"+"+y+"="+z+")");
		}else {
			System.out.println("SI ("+x+"+"+y+"="+z+")");
		}
		
		Teclado.close();
	}

}
}