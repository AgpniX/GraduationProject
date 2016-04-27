package com.example.xz.graduationproject;

import android.app.Activity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xz.control.ServiceControl;


public class MainActivity extends Activity {
    private ListView lv;
    private Switch _switch;
    private TextView _textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.lv_view);

        final String [] holders = new String[]{getString(R.string.textViewCusText),getString(R.string.textViewFitText),getString(R.string.textViewAutoText)};
        ListAdapter adapter = new BaseAdapter() {
            LinearLayout linearLayout;
            @Override
            public int getCount() {
                return holders.length;
            }

            @Override
            public String getItem(int position) {
                return holders[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView!=null){
                    linearLayout = (LinearLayout)convertView;
                }else{
                    linearLayout =  (LinearLayout)LayoutInflater.from(MainActivity.this).inflate(R.layout.lv_item,null);
                }
                _switch = (Switch)linearLayout.findViewById(R.id._switch);
                _textView = (TextView)linearLayout.findViewById(R.id._textView);
                _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String result;
                        if(isChecked){
                            //按钮选择
                            //Toast.makeText(MainActivity.this,"checked",Toast.LENGTH_LONG).show();
                            result =  ServiceControl.StartService(MainActivity.this,position);
                            if (result.equals("success")){
                                Toast.makeText(MainActivity.this,"<"+getItem(position)+">服务开启成功",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"<"+getItem(position)+">服务开启失败",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            //取消选择
                           // Toast.makeText(MainActivity.this,"unchecked",Toast.LENGTH_LONG).show();
                            result =  ServiceControl.StopService(MainActivity.this,position);
                            if (result.equals("success")){
                                Toast.makeText(MainActivity.this,"<"+getItem(position)+">服务停止成功",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(MainActivity.this,"<"+getItem(position)+">服务停止失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                       // Toast.makeText(MainActivity.this,"text："+getItem(position) +" position: "+position+" switch :"+_switch.isSelected()+"",Toast.LENGTH_LONG).show();
                    }
                });
                _textView.setText(getItem(position));
                return linearLayout;
            }
        };
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //List选项
  /* class ViewHolder {
        public Switch mSwitch;
        public TextView mTv;
    }*/

}
