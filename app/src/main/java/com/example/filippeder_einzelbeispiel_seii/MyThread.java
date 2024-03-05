package com.example.filippeder_einzelbeispiel_seii;



import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyThread extends Thread {

    private final int port = 20080;
    private final int mtrklNumber;
    private final TextView output;
    private final Handler handler;

    /*
    mtrklNumber - Matrikelnummer that is sent to server
    handler - Handler that handles updating the UI after the thread is done
    output - Text View where the server answer is written
     */
    public MyThread(int mtrklNumber, Handler handler, TextView output){
        this.mtrklNumber = mtrklNumber;
        this.handler = handler;
        this.output = output;
    }

    @Override
    public void run(){
        String result = String.valueOf(connect(mtrklNumber));
        if(result.equals("-1")){
            Log.e("MyTag","Thread has not reached the server");
            handler.post(()-> output.setText("Connection to server failed"));
        } else {
            handler.post(() -> output.setText("Answer from Server: " + result));
        }
    }

    public int connect(int mtrklNumber){
        Log.d("MyTag","Connection starting...");
        int response = -1;
        try{

            Socket socket = new Socket("se2-submission.aau.at",port);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(mtrklNumber);
            socket.close();
            Log.d("MyTag","Connection closed");
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
