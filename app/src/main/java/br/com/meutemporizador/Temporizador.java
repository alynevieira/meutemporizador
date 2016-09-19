package br.com.meutemporizador;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Temporizador extends MainActivity {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private final long interval = 1 * 1000;
    EditText e;
    Button botao;
    TextView contador;
    int segundosRestantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);
        botao = (Button) findViewById(R.id.botao);
        contador = (TextView) findViewById(R.id.contador);
        int e = getIntent().getIntExtra("Segundos", 0);
        segundosRestantes = e;
        formatarSegundos(e);
        countDownTimer = new MyCountDownTimer(e * 1000, interval);
        countDownTimer.start();
    }

    public void onClick(View v) {
        //COMEÇANDO O COUTDOWNTIMER
        int timeVal = segundosRestantes;
        //FAZER O COUTDOWN EM SEGUNDOS
        timeVal = timeVal * 1000;
        //SE NÃO TIVER INICIADO FAZER ESSAS FUNCOES
        if (!timerHasStarted) {
            //iniciando o metodo
            countDownTimer = new MyCountDownTimer(timeVal, interval);
            //setando o valor em segundos
            String.valueOf(timeVal / 1000);
            //começando o metodo
            countDownTimer.start();
            //setando o timer para true, para fazer a comparação no if.
            timerHasStarted = true;
            //setando o texto do botao para parar
            botao.setText("PARAR");
        } else {
            //pausa o coutdowntimer
            countDownTimer.cancel();
            //coloca o timer pra false, para quando clicar no botao novamente ele entrar no if de cima
            timerHasStarted = false;
            //seta o botao para reiniciar
            botao.setText("REINICIAR");
        }
    }

    //Ele vai atribuir o valor no text view, se for mais de 60, ele faz a conta e atribui ao minutos e/ou horas
    public void formatarSegundos(int s) {
        int horas = 0;
        int minutos = 0;
        int segundos = 0;

        minutos = s / 60;
        horas = minutos / 60;
        segundos = s % 60;

        //contador é o nome do textview 00:00:00, colocando o format e atribuindo valores
        contador.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
    }

    //metodo do temporizador
    class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long timeVal, long interval) {
            super(timeVal, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //cada tique, retorna o tempo q falta
//            resultado.setText("" + millisUntilFinished/1000);
            segundosRestantes = ((int) millisUntilFinished / 1000);
            formatarSegundos(segundosRestantes);
        }

        @Override
        public void onFinish() {
            //quando acabar ele exibe um popup
            AlertDialog.Builder alerta = new AlertDialog.Builder(Temporizador.this);
            alerta.setMessage("Finalizado");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override //aqui finaliza a activity
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alerta.create().show();
        }
    }
}

