/*
 * @author NiJen NiJen@gmx.eu
 * @version alpha
 */
package de.nijen.RFLineCalc.Wires;


public class Symetricstripline {
	
	private double Z0Luft=376.73;
//	private double Z0Vacuum=60;
//	private double Epsilon0=8.854187817e-12;//[As/Vm] Elektrische Feldkonstante
//	private double C0=299792458;			//[m/s] 
	
	/**
	 * Berechnet die effektive Breite der Symetrischenstreifenleitung 
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return   effektive Breite der Mirkrostreifenleitung     
	 */
	private double Weff(double h, double t, double w, double ER, double m){
		
		return w+(t/Math.PI)*Math.log(Math.E/(Math.sqrt(Math.pow((t/(4*h+t)), 2) + Math.pow((Math.PI*t/(4*(w+1.1*t))), m) ) ));
				
	}
	
	/**
	 * Berechnet den Leitungswiderstand der Symetrischenstreifenleitung 
	 * 
	 * Berechnung nach der Formel IPC-2141A Design Guide for High-Speed Controlled Impedance Circuit Boards
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return  ZL der Mikrostreifenleitung     
	 */
	public double  SymetricSLZL(double h, double t, double w, double ER){
		double ZL=0,m,b,D,sig;
		double PI=Math.PI;		
		b=2*h+t;

		
		if( (0.35>(w/b)) & (0.25>=(t/b)) & (0.11>=(t/w)) ){
			D=w/2*(1+t/(PI*w)*(1+Math.log(4*PI*w/t))+0.551*Math.pow((t/w),2));
			ZL=(60/Math.sqrt(ER))*Math.log(4*b/(PI*D));
		}
		else if(0.35<=(w/b)){//FIXME
			sig=( (2*b)/(b-t) )*Math.log( (2*b-t)/(b-t) )-( (t/(b-t))*Math.log( (2*b*t-Math.pow(t, 2) )/Math.pow((b-t), 2)) );
			
			ZL=94.15/( ((w/b)/(1-(t/b))) + (sig/PI) );
		}
		else{//FIXME
			m=6*h/(3*h+t);
			w=this.Weff(h, t, w, ER, m);
			ZL=((this.Z0Luft/(2*PI*Math.sqrt(ER)))*Math.log(1+(8*h/(PI*w))*(16*h/(PI*w)+Math.sqrt(Math.pow((16*h/(PI*w)),2)+6.27))));
		}
					
		return ZL;
	}
}
