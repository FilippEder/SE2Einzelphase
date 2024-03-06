package com.example.filippeder_einzelbeispiel_seii;



import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyThread extends Thread {

    private final int port = Constants.PORT;
    private final String address = Constants.ADDRESS;
    private final String mtrklNumber;
    private final TextView output;
    private final Handler handler;

    /*
    mtrklNumber - Matrikelnummer that is sent to server
    handler - Handler that handles updating the UI after the thread is done
    output - Text View where the server answer is written
     */
    public MyThread(String mtrklNumber, Handler handler, TextView output){
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
            handler.post(() -> output.setText(result));
        }
    }

    public String connect(String mtrklNumber){
        Log.d("MyTag","Connection starting...");
        String response = "-1";
        try{

            Socket socket = new Socket(address,port); // address = se2-submission.aau.at; port = 20080;
            socket.setSoTimeout(20000); //20 seconds

            Log.d("MyTag","Sending Request:"+mtrklNumber+" to "+address+":"+port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.write(mtrklNumber+'\n');
            out.flush();

            Log.d("MyTag","Waiting for answer...");

            response = in.readLine();
            Log.d("MyTag","Response:"+response);
            Log.d("MyTag","MyThread is done");

            socket.close();
            Log.d("MyTag","Connection closed");
        }catch (IOException e){
            e.printStackTrace();
        }
        return response;
    }
}
