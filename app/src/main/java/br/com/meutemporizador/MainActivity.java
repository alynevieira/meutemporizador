package br.com.meutemporizador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        e = (EditText)findViewById(R.id.segundos);
        int segundos = Integer.parseInt(e.getText().toString());
        Intent transicao = new Intent(this, Temporizador.class);
        transicao.putExtra("Segundos", segundos);
        startActivity(transicao);
    }

}
