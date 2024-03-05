package com.example.filippeder_einzelbeispiel_seii;



import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyThread extends Thread {

    private final int port = 20080;
    private final int mtrklNumber;

    public MyThread(int mtrklNumber){
        this.mtrklNumber = mtrklNumber;
    }

    @Override
    public void run(){
        connect(mtrklNumber);
    }

    public void connect(int mtrklNumber){
        Log.d("MyTag","Connection starting...");
        try{
            Socket socket = new Socket("se2-submission.aau.at",port);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(mtrklNumber);
            socket.close();
            Log.d("MyTag","Connection closed");
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
