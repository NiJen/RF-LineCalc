package de.nijen.RFLineCalc.UI;


import de.nijen.RFLineCalc.R;
import de.nijen.RFLineCalc.RFLineCalcActivity;
import de.nijen.RFLineCalc.Wires.Coax;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class CoaxUI extends Activity {	


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coax_ui);
        
    }
    public void BerechnungZL(View v){
        EditText Edit_D= (EditText) (findViewById(R.id.editText1));
        EditText Edit_d= (EditText) (findViewById(R.id.editText2));
        EditText Edit_Er= (EditText) (findViewById(R.id.editText3));
        EditText Edit_ZL= (EditText) (findViewById(R.id.editText4));
        
        Double D,d,Er,ZL;
        try {
        	D=Double.valueOf(Edit_D.getText().toString());
            d=Double.valueOf(Edit_d.getText().toString());
            Er=Double.valueOf(Edit_Er.getText().toString());
            if(d<D){
            	Coax Test = new Coax();
            	ZL=Test.CoaxZL(D, d, Er);
            }else{
    			Toast.makeText(getApplicationContext(), this.getString(R.string.ERRAuskleinerInn),
                        Toast.LENGTH_LONG).show();
            	ZL=0.00;
            }
            Edit_ZL.setText(Double.toString(ZL));
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
                    Toast.LENGTH_SHORT).show();
		}
        
       
        
      //Schließt das virtuelle Keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


    }
    public void BerechnungGeo(View v){
        EditText Edit_D= (EditText) (findViewById(R.id.editText1));
        EditText Edit_d= (EditText) (findViewById(R.id.editText2));
        EditText Edit_Er= (EditText) (findViewById(R.id.editText3));
        EditText Edit_ZL= (EditText) (findViewById(R.id.editText4));
        CheckBox CB_D= (CheckBox) (findViewById(R.id.checkBox1));
        CheckBox CB_d= (CheckBox) (findViewById(R.id.checkBox2));
        
        boolean CBD,CBd;
        Double D,d,Er,ZL;
        Coax Test = new Coax();
        
        CBD=CB_D.isChecked();
        CBd=CB_d.isChecked();
        
        //TODO Das runden funktioniert noch nicht.
        if(CBD&&CBd){
        	try {
        		D=Double.valueOf(Edit_D.getText().toString());
                d=Double.valueOf(Edit_d.getText().toString());
                ZL=Double.valueOf(Edit_ZL.getText().toString());
            	Edit_Er.setText(Double.toString(Test.CoaxER(D, d, ZL)));
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
	                    Toast.LENGTH_SHORT).show();
			} 
        	
        }else if(CBD&&!CBd) {
        	try {
        		D=Double.valueOf(Edit_D.getText().toString());
                Er=Double.valueOf(Edit_Er.getText().toString());
                ZL=Double.valueOf(Edit_ZL.getText().toString());
                Edit_d.setText(Double.toString(Test.Coaxd(D, ZL, Er)));
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
	                    Toast.LENGTH_SHORT).show();
			}
        	
        }else if(!CBD&&CBd) {
        	try {
        		d=Double.valueOf(Edit_d.getText().toString());
                Er=Double.valueOf(Edit_Er.getText().toString());
                ZL=Double.valueOf(Edit_ZL.getText().toString());
            	Edit_D.setText(Double.toString(Test.CoaxD(d, ZL, Er)));
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
	                    Toast.LENGTH_SHORT).show();
			}
        	
        }else{
        	Toast.makeText(getApplicationContext(), this.getString(R.string.ErrFix),
                    Toast.LENGTH_SHORT).show();
        }
        //Schließt das virtuelle Keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

}