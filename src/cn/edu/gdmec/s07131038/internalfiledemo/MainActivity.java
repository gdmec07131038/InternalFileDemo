package cn.edu.gdmec.s07131038.internalfiledemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	private TextView tv,tv1;
	private EditText et;
	private CheckBox cb;
	private final String NAME="internal";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.textView1);
        tv1=(TextView) findViewById(R.id.textView2);
        et=(EditText) findViewById(R.id.editText1);
        cb=(CheckBox) findViewById(R.id.checkBox1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void Read(View v){
    	FileInputStream fis=null;
    	String str="";
    	try {
			if(fis.available()==0){
				return;
			}
			byte buffer[]=new byte[fis.available()];
			while(fis.read(buffer)!=-1){
				str+=new String(buffer);
			}
			tv.setText(str);
			tv1.setText("文件读取成功，文件长度"+str.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally{
    		try {
    				if(fis!=null){		
					fis.close();
    				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    	}
    }
    
    public void Write(View v){
    	FileOutputStream fos=null;
    	
    		try {
    			if(cb.isChecked()){
    				fos=openFileOutput(NAME, Context.MODE_APPEND);
    			}else{
    				fos=openFileOutput(NAME, Context.MODE_PRIVATE);
    			}
    			String str=et.getText().toString();
    			fos.write(str.getBytes());
    			tv1.setText("文件写入成功,写入长度"+str.length());
    			et.setText("");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		finally{
    			try{
    				if(fos!=null){
    					fos.flush();
    					fos.close();
    				}
    			}catch(IOException e){
    				e.printStackTrace();
    			}
    		}
    	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
