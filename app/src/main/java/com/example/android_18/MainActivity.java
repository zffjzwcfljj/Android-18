package com.example.android_18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input;
    private TextView output;
    private String name = "test.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                try{
                    FileOutputStream fos = openFileOutput(name, Context.MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
                    osw.write(input.getText().toString());
                    osw.flush();
                    fos.flush();
                    osw.close();
                    fos.close();
                    Toast.makeText(this,"写入完成", Toast.LENGTH_SHORT).show();
                }catch (Exception e) {
                    e.printStackTrace();
                }
                break;
                case R.id.button2:
                    try{
                        FileInputStream fis = openFileInput(name);
                        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
                        char input[] = new char[fis.available()];
                        isr.read(input);
                        isr.close();
                        fis.close();
                        String readed = new String(input);
                        output.setText(readed);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

        }
    }
}