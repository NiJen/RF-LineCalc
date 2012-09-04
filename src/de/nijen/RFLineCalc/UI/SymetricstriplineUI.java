package de.nijen.RFLineCalc.UI;



import de.nijen.RFLineCalc.R;
import de.nijen.RFLineCalc.RFLineCalcActivity;
import de.nijen.RFLineCalc.Wires.Symetricstripline;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class SymetricstriplineUI extends Activity {

	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symetricstripline_ui);

       EditText test= (EditText) findViewById(R.id.editTextmicro_w);
       test.requestFocus();
       
       //TEST TODO
       EditText Edit_w= (EditText) (findViewById(R.id.editTextmicro_w));
       EditText Edit_t= (EditText) (findViewById(R.id.editTextmicro_t));
       EditText Edit_h= (EditText) (findViewById(R.id.editTextmicro_h));
       EditText Edit_Er= (EditText) (findViewById(R.id.editTextmicro_Er));

       Edit_w.setText(Double.toString(50));
       Edit_h.setText(Double.toString(63));
       Edit_t.setText(Double.toString(1));
       Edit_Er.setText(Double.toString(4));
       
       //TEST ENDE
     
        
    }
    
    public void BerechnungSymetricZL(View v){
    	EditText Edit_w= (EditText) (findViewById(R.id.editTextmicro_w));
        EditText Edit_t= (EditText) (findViewById(R.id.editTextmicro_t));
        EditText Edit_h= (EditText) (findViewById(R.id.editTextmicro_h));
        EditText Edit_Er= (EditText) (findViewById(R.id.editTextmicro_Er));
        EditText Edit_ZL= (EditText) (findViewById(R.id.editTextmicro_ZL));

        
        double w,t,h,ER,ZL;
        try {
        	w=Double.valueOf(Edit_w.getText().toString());            
            h=Double.valueOf(Edit_h.getText().toString());
            ER=Double.valueOf(Edit_Er.getText().toString());
            t=Double.valueOf(Edit_t.getText().toString());
            
            Symetricstripline Symstrip = new Symetricstripline();
                        
            ZL=Symstrip.SymetricSLZL(h, t, w, ER);
	

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