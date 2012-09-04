/*
 * @author NiJen NiJen@gmx.eu
 * @version 1.0
 */
package de.nijen.RFLineCalc.Wires;

import java.lang.Math;

/**
 * @author Nils
 *
 */
public class Coax {
	
	/**
	 * Berechnet den Leitunsgwiderstand einer Koaxialen Leitung 
	 * 
	 * @param  D Aussendurchmesser
	 * @param  d Innendurchmesser
	 * @param Er Permittivitätszahl
	 * @return   den Leitungswiderstand     
	 */
	public double CoaxZL (double D, double d, double Er){
		
		return (60/(Math.sqrt(Er))*Math.log(D/d));
		
	}
	
	/**
	 * Berechnet den Aussendurchmesser einer Koaxialen Leitung 
	 * 
	 * @param  d Innendurchmesser
	 * @param ZL Leitungswiderstand
	 * @param Er Permittivitätszahl
	 * @return   Aussendurchmesser  
	 */
	public double CoaxD (double d, double ZL, double Er){
		
		return (Math.exp((ZL*(Math.sqrt(Er))/60))*d);
		
	}
	
	/**
	 * Berechnet den Innendurchmesser einer Koaxialen Leitung 
	 * 
	 * @param D  Aussendurchmesser
	 * @param ZL Leitungswiderstand
	 * @param Er Permittivitätszahl
	 * @return   Aussendurchmesser  
	 */
	public double Coaxd (double D, double ZL, double Er){

		return (D/Math.exp((ZL*Math.sqrt(Er)/60)));
	
	}
	
	/**
	 * Berechnet den Leitunsgwiderstand einer Koaxialen Leitung 
	 * 
	 * @param  D Aussendurchmesser
	 * @param  d Innendurchmesser
	 * @param ZL Leitungswiderstand
	 * @return   Permittivitätszahl    
	 */
	public double CoaxER(double D, double d, double ZL){
		
		return(Math.pow(((60/ZL)*Math.log(D/d)), 2));
		
	}
}
