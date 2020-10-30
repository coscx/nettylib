package pw.hais.netty.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pw.hais.netty.NettyClient;
import pw.hais.netty.NettyEventListener;
import pw.hais.netty.NettyLog;


public class MainActivity extends AppCompatActivity implements NettyEventListener {
    String s = "{\"Method\":\"Login\",\"DateTime\":\"2016-07-05 14:43:01\",\"Param\":{\"AndroidVersionCode\":0,\"UserType\":1,\"UserID\":\"2-2\",\"IosVersionCode\":0}}";
    NettyClient nettyClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nettyClient = new NettyClient("192.168.1.111", 11001,this).connect();

        findViewById(R.id.text_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    nettyClient.send(s);
                } catch (Exception e) {
                    nettyClient.closeAll();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onConnectSuccess() {
        NettyLog.e("--------onConnectSuccess---------");
    }

    @Override
    public void onConnections() {
        NettyLog.e("--------onConnections---------");
    }

    @Override
    public void onConnectError(Exception e) {
        NettyLog.e("--------onConnectError---------"+e);
    }

    @Override
    public void onEventMessage(String s) {
        NettyLog.e("--------onEventMessage---------"+s);
    }
}
