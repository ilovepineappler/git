package com.example.edu.communication_withrasp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//UDP 관련

public class MainActivity extends AppCompatActivity {
    //서버주서
    public static final String sIP = "192.168.219.101";
    //사용할 통신 포트
    public static final int sPORT = 8011;

    //데이터 보낼 클랙스
    public SendData mSendData = null;

    //화면 표시용 TextView
    public TextView txtView = null;
    public EditText edittext=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnHello 버튼을 layout의 버튼과 연결
        Button btnHello = (Button) findViewById(R.id.button);
        //txtView를 layout의 TextView와 연결
        txtView = (TextView) findViewById(R.id.tv);
        edittext=(EditText)findViewById(R.id.et);

        //버튼이 눌렸다면
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SendData 클래스 생성
                mSendData = new SendData();
                Log.d("111111","1");
                //보내기 시작
                mSendData.start();
                Log.d("111111","2");
            }
        });

    }

    //데이터 보내는 쓰레드 클래스
    class SendData extends Thread{
        public void run(){
            try{
                //UDP 통신용 소켓 생성
                DatagramSocket socket = new DatagramSocket();
                //서버 주소 변수
                InetAddress serverAddr = InetAddress.getByName(sIP);
                Log.d("111111","3");
                //보낼 데이터 생성
                byte[] buf = edittext.getText().toString().getBytes();
                Log.d("111111","4");
                //String voice = URLEncoder.encode(edittext.getText().toString(),"UTF-8");
               // byte[] buf = voice.getBytes();



                //패킷으로 변경
                DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, sPORT);
                Log.d("111111","5");
                //패킷 전송!
                socket.send(packet);
                Log.d("111111","6");
                //데이터 수신 대기
                socket.receive(packet);
                Log.d("111111","7");
                //데이터 수신되었다면 문자열로 변환
                String msg = new String(packet.getData());
                Log.d("111111","8");

                //txtView에 표시
                txtView.setText(msg);
            }catch (Exception e){

            }
        }
    }
}