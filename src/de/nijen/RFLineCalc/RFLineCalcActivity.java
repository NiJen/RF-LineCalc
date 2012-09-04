package de.nijen.RFLineCalc;



import de.nijen.RFLineCalc.R;
import de.nijen.RFLineCalc.UI.CoaxUI;
import de.nijen.RFLineCalc.UI.SymetricstriplineUI;
import de.nijen.RFLineCalc.UI.WireembeddedstriplineUI;
import de.nijen.RFLineCalc.UI.WirestriplineUI;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class RFLineCalcActivity extends ListActivity {
	public static boolean Werbung =true;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String[] rfLines = getResources().getStringArray(R.array.rfLines_array);
        setListAdapter(new ArrayAdapter<String>(this, R.layout.main, rfLines));
        
        final ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
              // When clicked, show a toast with the TextView text
//              Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
//                  Toast.LENGTH_SHORT).show();
              
              //TODO Position nach Namen filtern
              if(position==0){
            	  final Intent intent = new Intent(RFLineCalcActivity.this, CoaxUI.class);
            	  startActivity(intent);
        	  }
              else if(position==1){
            	  Toast.makeText(getApplicationContext(), RFLineCalcActivity.this.getString(R.string.ErrorFormula),
                        Toast.LENGTH_SHORT).show();
//            	  final Intent intent = new Intent(RFLineCalcActivity.this, MicrostriplineUI.class);
//            	  startActivity(intent);
        	  }
              else if(position==2){
            	  final Intent intent = new Intent(RFLineCalcActivity.this, WirestriplineUI.class);
            	  startActivity(intent);
        	  }
              else if(position==3){
            	  final Intent intent = new Intent(RFLineCalcActivity.this, WireembeddedstriplineUI.class);
            	  startActivity(intent);
        	  }
              else if(position==4){
            	  final Intent intent = new Intent(RFLineCalcActivity.this, SymetricstriplineUI.class);
            	  startActivity(intent);
        	  }
              else{
            	  Toast.makeText(getApplicationContext(), RFLineCalcActivity.this.getString(R.string.ERRError),
                          Toast.LENGTH_SHORT).show();
              }
            }
            
          });}

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.rflinealc_options, menu);
            return true;
        }
        
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle item selection
            switch (item.getItemId()) {
            case R.id.WWW:
            	String www=this.getString(R.string.www);
            	Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(www));
            	startActivity(viewIntent);  
                return true;
            case R.id.feedback:
            	/* Create the Intent */
            	final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            	/* Fill it with Data */
            	emailIntent.setType("plain/text");
            	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{this.getString(R.string.email)});
            	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.getString(R.string.feedback)+" "+this.getString(R.string.app_name));
//            	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");
            	startActivity(emailIntent);  
//            case R.id.help:
//                //showHelp();
//                return true;
//            case R.id.options:
//            	return true;
            default:
                return super.onOptionsItemSelected(item);
            }
        }

}