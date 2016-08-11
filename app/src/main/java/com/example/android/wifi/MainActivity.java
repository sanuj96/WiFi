package com.example.android.wifi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Mymsg";
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        final WifiManager wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        wifiManager.getScanResults();

// The above is an async call and will results are available System will broadcast SCAN_RESULTS_AVAILABLE intent and you need to set a `BroadCastReceiver` for it.

// And when you catch the intent, get the results using

        List<ScanResult> results = wifiManager.getScanResults();


        List<String> l=new ArrayList<String>();

        Iterator iter=results.iterator();

        while (iter.hasNext()) {
            ScanResult result= (ScanResult) iter.next();
            l.add(result.SSID);
            
            //Toast.makeText(getApplicationContext(), result.SSID,Toast.LENGTH_SHORT).show();
            //Log.i(TAG, result.SSID);
            //str[index]=result.SSID;

            //Log.i(TAG,str[index]);
        }
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,R.layout.itemlayout,l);
        ListView list=(ListView)findViewById(R.id.listView);
        list.setAdapter(adp);





        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                final String item = ((TextView) view).getText().toString();

                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("Connect to "+ item/*R.string.dialog_message*/)
                        .setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.connect, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    //////////////////////////////////////////////////////////////////////
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        // Get the layout inflater
                        LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                        // Inflate and set the layout for the dialog
                        // Pass null as the parent view because its going in the dialog layout
                        builder1.setView(inflater.inflate(R.layout.dialog_signin, null))
                                // Add action buttons
                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(MainActivity.this, "Working on it....", Toast.LENGTH_LONG).show();
                                        EditText Ped = (EditText) findViewById(R.id.pd);
                                        Toast.makeText(MainActivity.this, "gggg", Toast.LENGTH_LONG).show();
                                        /*String networkPass = "gfhgf";//Ped.getText().toString();
                                        String networkSSID = item;
                                        Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();*/

                                        /*WifiConfiguration wc=new WifiConfiguration();
                                        wc.SSID="\"my_ssid\"";
                                        wc.preSharedKey = "\"my_password\"";
                                        wc.status = WifiConfiguration.Status.ENABLED;
                                        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                                        wc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                                        wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                                        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                                        wc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                                        wc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                                        wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

                                        int netId=wifiManager.addNetwork(wc);
                                        wifiManager.enableNetwork(netId, true);
                                        wifiManager.reconnect();*/
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                        AlertDialog dialog1=builder1.create();
                        dialog1.show();
                    /////////////////////////////////////////////////////////////////////////////

                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }





}