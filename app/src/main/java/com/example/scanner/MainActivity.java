package com.example.scanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {
    private Button scanButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.scan_button);
        resultTextView = findViewById(R.id.result_textView);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator( MainActivity.this);
                integrator.setPrompt("Escanea un codigo de barras o qr");
                integrator.initiateScan();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (result != null){
            String scanContent = result.getContents();
            resultTextView.setText("Resultado del escaneo: "+scanContent);

            if (isValidUrl(scanContent)){
                Intent browserIntent = new Intent(Intent.ACTION_VIEW), Uri.parse(scanContent);
                startActivity(browserIntent);
            }

        }
    }

    private boolean isValidUrl(String url){
        return url !=null &&(url.startsWith("https://")|| url.startsWith("https://"))
    }
}