package programacao.mobile.lista03exerciciosintroducaoaandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText altura;
    private EditText peso;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa as variaveis
        this.altura = findViewById(R.id.altura);
        this.peso = findViewById(R.id.peso);
        this.bt = findViewById(R.id.button);

        // seta desativado o botão
        bt.setEnabled(false);

        // QUANDO CLICKAR AQUI CHAMA ATUALIZAENABLEBOTAO();
        this.altura.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                atualizaEnableBotao();
                return false;
            }
        });

        this.peso.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                atualizaEnableBotao();
                return false;
            }
        });
        this.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticar();
            }
        });
        //autenticar();
        //limparTela();
    }

    // Calcuala IMC
    public double calculaIMC(double altura, double peso){
        double imc = 0;
        imc = (peso / Math.pow(altura,2));
        return imc;
    }

    // Atauliza a ativação do botão após estarem preenchidos
    public void atualizaEnableBotao(){
        String valorAltura = altura.getText().toString().trim();
        String valorPeso = peso.getText().toString().trim();
        boolean habilitaBotao = !valorAltura.isEmpty() && !valorPeso.isEmpty();
        this.bt.setEnabled(habilitaBotao);
    }


    public void autenticar(){
        String alturaTxt = this.altura.getText().toString();
        String pesoTxt = this.peso.getText().toString();
        String menssagem = "";
        int color = 0;

        double a = Double.valueOf(alturaTxt);
        double b = Double.valueOf(pesoTxt);
        double resposta = calculaIMC(a,b);
        if (resposta < 20){
            menssagem = "Abaixo do peso! IMC = "+ resposta;
            color = Color.rgb(200,255,200);

        }else {
            if (resposta > 25){
                menssagem = "Acima do peso! IMC = "+ resposta;
                color = Color.rgb(255,0,0);
            }else {
                menssagem = "Pesoo OK! IMC = "+ resposta;
                color = Color.rgb(0,0,255);
            }
        }

        TextView tv = findViewById(R.id.resultado);
        tv.setTextSize(30);
        tv.setText(menssagem);
        tv.setTextColor(color);
    }

    public void limparTela(){
        this.altura.setText("");
        this.peso.setText("");
        atualizaEnableBotao();
    }

}