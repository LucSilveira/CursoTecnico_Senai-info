package com.example.acer.mundocelular;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SobreActivity.this, MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
