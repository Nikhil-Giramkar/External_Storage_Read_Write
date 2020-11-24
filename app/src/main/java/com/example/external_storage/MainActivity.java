package com.example.external_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME="myFile.txt";
    private static final String FILEPATH="MyFileDir";
    TextView res, textread;
    EditText text1;
    Button send, read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = (EditText)findViewById(R.id.text1);
        send = (Button)findViewById(R.id.send);
        textread = (TextView)findViewById(R.id.textread);
        read = (Button)findViewById(R.id.read);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile(text1.getText().toString());
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromFile(new File(Environment.getExternalStorageDirectory() + "//myFile.txt"));

            }
        });
    }

    void writeToFile(String data)
    {
        try{

            File myExternalFile = new File(getExternalFilesDir(FILEPATH),FILENAME);
            FileOutputStream fos = null;
            fos = new FileOutputStream(myExternalFile);
            fos.write(data.getBytes());
            Toast.makeText(this,"File is Written",Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    void readFromFile(File file){

           File myExternalFile = new File(getExternalFilesDir(FILEPATH),FILENAME);
           FileReader fr =null;

            StringBuilder stringBuilder = new StringBuilder();
            try{
                fr = new FileReader(myExternalFile);
                BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line !=null){
                stringBuilder.append(line).append('\n');
                line = br.readLine();
            }
            textread.setText(stringBuilder.toString());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
                String fileContents = "File Contents \n" + stringBuilder.toString();
                textread.setText(fileContents);
            }
    }
}