/*
 * @author NiJen NiJen@gmx.eu
 * @version alpha
 */
package de.nijen.RFLineCalc.Wires;

public class Wirestripline {
	
	
	private double Z0Luft=376.73;
	
	
	/**
	 * Berechnung den Wellenwiderstand für eine Drahtstreifenleitung
	 * 
	 * @param h Abstand zum GND
	 * @param d Drahtdurchmesser
	 * @param ER  Epsilon R
	 * 
	 * @return ZL gibt den Wellenwiderstand zurück
	 */
	public double MicrowireZL(double h, double d, double ER){
		double ZL=0,Ereff=0,temp=0;
		
		if( 1>(d/h) ){
			Ereff=(ER+1)/2+(ER-1)/2*( Math.sqrt(d/(d+12*h))+0.04*Math.pow((1-d/h),2) );
		}else{
			Ereff=(ER+1)/2+(ER-1)/2*( Math.sqrt(d/(d+12*h)));
		}
		
		temp=(2*h+d)/d;
		ZL=this.Z0Luft/(2*Math.PI*Math.sqrt(Ereff))*Math.log(temp+Math.sqrt(temp*temp-1));
		
		return ZL;
	}
	
	/**
	 * Berechnung den Wellenwiderstand für eine embeddede Drahtstreifenleitung
	 * 
	 * Fehler <1% für d<=h/2
	 * 
	 * @param h Abstand zu beiden GND-Planes
	 * @param d Drahtdurchmesser
	 * @param ER  Epsilon R
	 * 
	 * @return  gibt den Wellenwiderstand zurück
	 */
	public double MicrowireembeddedZL(double h, double d, double ER){
		
		return (this.Z0Luft/(2*Math.PI*Math.sqrt(ER))*Math.log((4*h)/(Math.PI*d)));
	}
	 

}
