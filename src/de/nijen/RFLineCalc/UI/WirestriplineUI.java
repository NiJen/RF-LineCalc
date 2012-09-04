package de.nijen.RFLineCalc.UI;


import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import de.nijen.RFLineCalc.R;
import de.nijen.RFLineCalc.RFLineCalcActivity;
import de.nijen.RFLineCalc.Wires.Wirestripline;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class WirestriplineUI extends Activity {
	private AdView adView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wirestripline_ui);

       EditText test= (EditText) findViewById(R.id.editTextmicro_d);
       test.requestFocus();
        
    }
    
    public void BerechnungWirestripZL(View v){
    	EditText Edit_d= (EditText) (findViewById(R.id.editTextmicro_d));
        EditText Edit_h= (EditText) (findViewById(R.id.editTextmicro_h));
        EditText Edit_Er= (EditText) (findViewById(R.id.editTextmicro_Er));
        EditText Edit_ZL= (EditText) (findViewById(R.id.editTextmicro_ZL));

        
        double d,h,ER,ZL;
        try {
        	d=Double.valueOf(Edit_d.getText().toString());            
            h=Double.valueOf(Edit_h.getText().toString());
            ER=Double.valueOf(Edit_Er.getText().toString());

            
            Wirestripline Wirestrip = new Wirestripline();
                        
            ZL=Wirestrip.MicrowireZL(h, d, ER);
	

        	Edit_ZL.setText(Double.toString(ZL));
        	
        	
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), this.getString(R.string.ErrZahlen),
			                    Toast.LENGTH_SHORT).show();
		}
        
      //Schließt das virtuelle Keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        
    }
    
    
}