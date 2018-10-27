package com.example.acer.macandip;

import android.annotation.SuppressLint;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvMAC,tvIP;
    Button btnGet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMAC=findViewById(R.id.tvMAC);
        tvIP=findViewById(R.id.tvIP);

        //Make Sure Your Application have an WIFI access permission

        btnGet=findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                assert wifiManager != null;
                if (wifiManager.isWifiEnabled()){
                    getMAC();
                    getIP();
                   }
                else {
                    Toast.makeText(MainActivity.this, "Wifi is Disable", Toast.LENGTH_SHORT).show();
                    tvMAC.setText(R.string.wifi_disable);
                    tvIP.setText(R.string.wifi_disable);
                }
            }

            private void getMAC() {
                    WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                    assert wifiManager != null;
                    WifiInfo wInfo = wifiManager.getConnectionInfo();
                    @SuppressLint("HardwareIds") String macAddress = wInfo.getMacAddress();
                    tvMAC.setText(String.format("MAC Address : %s", macAddress));
            }

            private void getIP() {
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                assert wifiManager != null;
                String ip = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
                tvIP.setText(String.format("IP Address : %s", ip));
            }
        });
    }
}

