package br.edu.ifsp.scl.sdm.bingo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random gerarNumero;
    private List<Integer> numerosSorteados = new ArrayList<>();
    private List<Integer> cartela = new ArrayList<>();
    private List<Integer> cartelaAcertos = new ArrayList<>();

    private Button sortearButton;
    private Button novoJogoButton;
    private TextView cartelaTextView;
    private TextView numeroSorteadoTextView;
    private TextView numerosSorteadosTextView;
    private TextView cartelaAcertosTextView;
    private TextView mensagemTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gerarNumero = new Random();

        sortearButton = findViewById(R.id.sortearButton);
        novoJogoButton = findViewById(R.id.novoJogoButton);
        cartelaTextView = findViewById(R.id.cartelaTextView);
        numeroSorteadoTextView = findViewById(R.id.numeroSorteadoTextView);
        numerosSorteadosTextView = findViewById(R.id.numerosSorteadosTextView);
        cartelaAcertosTextView = findViewById(R.id.cartelaAcertosTextView);
        mensagemTextView = findViewById(R.id.mensagemTextView);

        criarCartela();
    }

    private void criarCartela() {
        cartelaTextView.setText("");

        int numero = 1;
        cartela.add(numero);
        cartelaTextView.setText(cartelaTextView.getText().toString() + "[" + numero + "]");

        numero = 7;
        cartela.add(numero);
        cartelaTextView.setText(cartelaTextView.getText().toString() + "[" + numero + "]");

        numero = 24;
        cartela.add(numero);
        cartelaTextView.setText(cartelaTextView.getText().toString() + "[" + numero + "]");

        numero = 48;
        cartela.add(numero);
        cartelaTextView.setText(cartelaTextView.getText().toString() + "[" + numero + "]");

        numero = 98;
        cartela.add(numero);
        cartelaTextView.setText(cartelaTextView.getText().toString() + "[" + numero + "]");
    }

    public void novoJogoClick(View view) {
        if (view.getId() == R.id.novoJogoButton) {
            numeroSorteadoTextView.setText("");
            numerosSorteadosTextView.setText("");
            cartelaAcertosTextView.setText("");
            mensagemTextView.setText("");

            numerosSorteados.clear();
            cartelaAcertos.clear();
            sortearButton.setEnabled(true);
        }
    }

    public void sortearClick(View view) {
        if (view.getId() == R.id.sortearButton) {
            if (cartelaAcertos.size() < cartela.size()) {
                boolean numeroExistente = false;

                int numeroSorteado = 0;
                while (numeroExistente == false) {
                    numeroSorteado = gerarNumero.nextInt(99) + 1;
                    if (!numerosSorteados.contains(numeroSorteado)) {
                        numeroExistente = true;
                    }
                }

                numeroSorteadoTextView.setText("" + numeroSorteado);
                numerosSorteados.add(numeroSorteado);

                if (numerosSorteadosTextView.getText().toString() == "") {
                    numerosSorteadosTextView.setText("[" + numeroSorteado + "]");
                } else {
                    numerosSorteadosTextView.setText(numerosSorteadosTextView.getText().toString() + "[" + numeroSorteado + "]");
                }

                if (cartela.contains(numeroSorteado)) {
                    if (cartelaAcertosTextView.getText().toString() == "") {
                        cartelaAcertosTextView.setText("[" + numeroSorteado + "]");
                    } else {
                        cartelaAcertosTextView.setText(cartelaAcertosTextView.getText().toString() + "[" + numeroSorteado + "]");
                    }

                    cartelaAcertos.add(numeroSorteado);
                }

                if (cartela.size() == cartelaAcertos.size()) {
                    mensagemTextView.setText("Bingo!");
                    sortearButton.setEnabled(false);
                }
            }
        }
    }
}
