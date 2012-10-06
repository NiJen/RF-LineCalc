/*
 * @author NiJen NiJen@gmx.eu
 * @version alpha
 */
package de.nijen.RFLineCalc.Wires;


public class Microstripline {
	
	private double Z0Luft=376.73;
//	private double Z0Vacuum=60;
//	private double Epsilon0=8.854187817e-12;//[As/Vm] Elektrische Feldkonstante
	private double C0=299792458;			//[m/s] 

	
	
//Berechung
	
	public double microstripcalc(double h, double t, double w, double ER){
		double ZL,a,b,c;
		
		if(t!=0){
			w=this.weffneu(h, t, w, ER);
		}
		
		a=Math.pow(((14+8/ER)/(11))*(4*h/w),2)+Math.pow(Math.PI, 2)*((1+1/ER)/2);
		b=((14+8/ER)/(11))*(4*h/w)+Math.sqrt(a);
		c=(4*h/w)*b;
		
		ZL=this.Z0Luft/(2*Math.PI*Math.sqrt(2*(1+ER)))*Math.log(1+c);
	
		return ZL;
	}
	
	private double weffneu(double h, double t, double w, double ER){		
		double weff=0;
		
		weff=w+(t*(1+1/ER)/(2*Math.PI))*Math.log(Math.E*4/Math.sqrt(Math.pow((t/h), 2)+Math.pow(((1/Math.PI)*1/(w/t+11/10)), 2)));
		
		return weff;
	}

// Ende Berechnung nach Inet	
	//FIXME Hier ist noch ein Fehler drin
//Berechnung nach IPC2141A	
	/**
	 * Berechnet die effektive Breite der Mirkrostreifenleitung 
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return   effektive Breite der Mirkrostreifenleitung     
	 */
	private double weff(double h, double t, double w, double ER){		
		double weff=0;
		
		weff=w+(t/Math.PI)*Math.log(Math.E*4/Math.sqrt(Math.pow((t/h), 2)+Math.pow((t/w*Math.PI+1.1*t*Math.PI), 2)))*((ER+1)/(2*ER));
		
		return weff;
	}
	
	/**
	 * Berechnet die effektive Permittivitätszahl in Abhängigkeit von der Frequenz
	 * 
	 * @param h  Dicke des Substrats
	 * @param w  Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return     Die effektive Permittivitätszahl
	 */
	private double ereff(double h, double w, double ER){
		double y;
		
		if(1>w/h){		
			y=(ER+1)/2+((ER-1)/2)*(Math.sqrt(w/(w+12*h))+0.04*Math.pow((1-(w/h)),2));
		}else{
			y=(ER+1)/2+((ER-1)/2)*Math.sqrt(w/(w+12*h));
		}
		return y;
	}
	
	/**
	 * Berechnet den Leitungswiderstand der Mirkrostreifenleitung 
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param  ER Permittivitätszahl
	 * @return  ZL der Mikrostreifenleitung     
	 */
	public double  microSLZL(double h, double t, double w, double ER){
		double ZL=0,Ereff,A,B;
		
		Ereff=this.ereff(h, w, ER);
		
		if (t!=0){
			w=this.weff(h, t, w, Ereff);
		}

		A=4*((14*Ereff+8)/(11*Ereff))*(h/w);
		
		B=Math.sqrt(16*Math.pow(((14*Ereff+8)/(11*Ereff)), 2)*Math.pow((h/w),2)+((Ereff+1)/(2*Ereff))*Math.pow(Math.PI,2));	
		
		ZL=(this.Z0Luft/(2*Math.PI*Math.sqrt(2)*Math.sqrt(Ereff+1)))*Math.log(1+4*(h/w)*(A+B));
		
		return ZL;
	}
//Ende berechnung nach IPC 2141A
	
//FIXME Dieser Teil hat noch Fehler	
	//Berechnung nach Hammerstedt und Jensen
	/**
	 * Berechnet die effektive Breite der Mirkrostreifenleitung 
	 * 
	 * Für die Berechung vom coth wurde folgendes benutzt coth(x)=(cosh(x))/(sinh(x))
	 * der coth darf nicht 0 sein
	 * 
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return   effektive Breite der Mirkrostreifenleitung     
	 */
	private double Weff(double h, double t, double w, double ER){
		
		double x;
		x=Math.pow((6.517*w/h), 0.5);
		
		return (w+(1/(2*Math.PI)*(1+1/(Math.cosh(Math.sqrt(ER-1))))*Math.log(1+(4*Math.E)/((t/h)*Math.pow(((Math.cosh(x))/(Math.sinh(x))), 2)))));
				
	}
	
	/**
	 * Berechnet die effektive Permittivitätszahl in Abhängigkeit von der Frequenz
	 * 
	 * @param f  Die Freqeunz bei der die Mikrostirpline dimensioniert werden soll.
	 * @param h  Dicke des Substrats
	 * @param w  Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return     Die effektive Permittivitätszahl
	 */
	private double EReffF(double f, double h, double w, double ER){
		double A,y;
		
		if((0.06<w/h)&&(w/h<16)&&(0.0033<h/(this.C0/f))&&(h/(this.C0/f)<0.33)&&(2<ER)&&(ER<16)){
			A=(4*h*f*Math.sqrt(ER-1)/this.C0)*(0.5*+Math.pow((1+2*Math.log(1+w/h)),2));
			
			y=Math.pow(((Math.sqrt(ER)-Math.sqrt(this.EReffstat(h,w,ER)))/(1+4*Math.pow(A,-1.5)+Math.sqrt(this.EReffstat(h,w,ER)))), 2);
		}else{
			y=this.EReffstat(h, w, ER);
		}
		return y;
	}
	
	/**
	 * Berechnung des statischen Epsilon R ohne die Frequenz zu berücksichtigen
	 * 
	 * @param h  Dicke des Substrats
	 * @param w  Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return gibt das statische Epsilon R zurück
	 */
	private double EReffstat(double h, double w, double ER){
		double EReffstat,a,b;
		
		a=1+1/49*Math.log(Math.pow(w,4)/h+(w/52*h))+1/18.7*Math.log(1+(Math.pow((w/(18.1*h)),3)));
		
		b=0.564*Math.pow(((ER-0.9)/(ER+3)),0.053);
	
		EReffstat=(ER+1)/(2)+(ER-1)/(2)*Math.pow((1+(10*h)/(w)),(-a*b));
		
		return EReffstat;
	}
	
	/**
	 * Berechnet den Leitungswiderstand der Mirkrostreifenleitung 
	 * 
	 * Berechnung nach der Formel von Hammersted/ Jensen (Fehler <0.2%)
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @return  ZL der Mikrostreifenleitung     
	 */
	public double  MicroSLZL(double h, double t, double w, double ER){
		double ZL=0,Ereff;
		
		if (t!=0){
			w=this.Weff(h, t, w, ER);
		}

		Ereff=this.EReffstat(h,w,ER);
			
		ZL=60/Math.sqrt(Ereff)*(Math.log(6+(2*Math.PI)*Math.exp(-1*Math.pow((30.666*h/w),0.7528)))/w+Math.sqrt(1+Math.pow((2*h/w),2)));
		
		return ZL;
	}
	
	/** FIXME Mikrostreifenleitung unkorrekt mit und ohne Frequenz nochmal kontrollieren
	 * Berechnet den Leitungswiderstand der Mirkrostreifenleitung mit berücksichtigung der Frequenz
	 * 
	 * Berechnung nach der Formel von Hammersted/ Jensen (Fehler <0.2%)
	 * 
	 * @param  h Dicke des Substrats
	 * @param  t Dicke der Metallisierung
	 * @param  w Breite der Mikrostreifenleitung
	 * @param ER Permittivitätszahl
	 * @param  f Frequenz für die berücksichtigung der Frequenz
	 * @return  ZL der Mikrostreifenleitung     
	 */
	public double  MicroSLZL(double h, double t, double w, double ER, double f){
		double ZL=0,Ereff;
		
		if (t!=0){
			w=this.Weff(h, t, w, ER);
		}
		
		Ereff=this.EReffF(f,h,w,ER);
			
		ZL=60/Math.sqrt(Ereff)*(Math.log(6+(2*Math.PI)*Math.exp(-1*Math.pow((30.666*h/w),0.7528)))/w+Math.sqrt(1+Math.pow((2*h/w),2)));
		
		return ZL;
	}
	//Bis hierhin muss es kontrolliert werden
	
}
