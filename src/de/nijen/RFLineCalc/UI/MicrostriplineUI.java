package de.nijen.RFLineCalc.UI;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import de.nijen.RFLineCalc.R;
import de.nijen.RFLineCalc.RFLineCalcActivity;
import de.nijen.RFLineCalc.Wires.Microstripline;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MicrostriplineUI extends Activity {
	private AdView adView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.microstripline_ui);

       EditText test= (EditText) findViewById(R.id.editTextmicro_w);
       test.requestFocus();
       
        
    }
    
    public void BerechnungMicroZL(View v){
    	EditText Edit_w= (EditText) (findViewById(R.id.editTextmicro_w));
        EditText Edit_t= (EditText) (findViewById(R.id.editTextmicro_t));
        EditText Edit_h= (EditText) (findViewById(R.id.editTextmicro_h));
        EditText Edit_Er= (EditText) (findViewById(R.id.editTextmicro_Er));
        EditText Edit_ZL= (EditText) (findViewById(R.id.editTextmicro_ZL));
        CheckBox CB_t= (CheckBox) (findViewById(R.id.checkBox1_t));
        
        double w,t,h,Er,ZL,Fr;
        boolean CBt,CBfreq;
        try {
        	w=Double.valueOf(Edit_w.getText().toString());            
            h=Double.valueOf(Edit_h.getText().toString());
            Er=Double.valueOf(Edit_Er.getText().toString());
            
            Microstripline Microstrip = new Microstripline();
            CBt=CB_t.isChecked();
            if(CBt){
            	t=Double.valueOf(Edit_t.getText().toString());

            }else{
            	t=0;
            }
            ZL=Microstrip.MicroSLZL(h, t, w, Er);
//FehlerhafterTeil in der Microstripline.java
//            if(CBt){
//            	t=Double.valueOf(Edit_t.getText().toString());
//            }else{
//            	t=0;
//            }
//          CBfreq=CB_freq.isChecked();
//        	if (CBfreq) {
//        		Fr=Double.valueOf(Edit_Fr.getText().toString());
//        		ZL=Microstrip.MicroSLZL(h, t, w, Er,Fr);
//        			
//			} else  {
//				ZL=Microstrip.MicroSLZL(h, t, w, Er);
//			}
        	
        	Edit_ZL.setText(Double.toString(ZL));
        	
        	
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
			                    Toast.LENGTH_SHORT).show();
		}
        
      //Schließt das virtuelle Keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        
    }
    
    
    public void Checkbox_listener(View v){
    	CheckBox CB_t= (CheckBox) (findViewById(R.id.checkBox1_t));
    	EditText Edit_t= (EditText) (findViewById(R.id.editTextmicro_t));
 
//    	CheckBox CB_freq= (CheckBox) (findViewById(R.id.checkBox2_freq));
//    	EditText Edit_Fr= (EditText) (findViewById(R.id.editTextmicro_freq));
    	
//    	if(CB_freq.isChecked()){
//    		Edit_Fr.setHint(this.getString(R.string.MHz));
//    	}else{
//    		Edit_Fr.setHint(this.getString(R.string.off));
//    	}
    	
    	if(CB_t.isChecked()){
    		Edit_t.setHint(this.getString(R.string.mm));
    	}else{
    		Edit_t.setHint(this.getString(R.string.off));    		
    	}
    }
    
}