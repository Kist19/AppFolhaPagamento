package br.ulbra.appfolhapagamento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView edtNome, edtSalarioBruto, edtNFilhos;
    RadioGroup rgSexo;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtNome = findViewById(R.id.edtNome);
        edtSalarioBruto = findViewById(R.id.edtSalarioBruto);
        edtNFilhos = findViewById(R.id.edtNFilhos);
        rgSexo = findViewById(R.id.rgSexo);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calcularinss();
                Calcularir();
            }
        });

    }

    public void Calcularinss() {
        double salariobruto, salariofinal = 0;
        try {
            salariobruto = Double.parseDouble(edtSalarioBruto.getText().toString());
            if (salariobruto <= 1212.00) {
                salariofinal = salariofinal + (salariobruto * 0.075);
            } else if (salariobruto >= 1212.01 && salariobruto <= 2427.35) {
                salariofinal = salariofinal + (salariobruto * 0.09);
            } else if (salariobruto >= 2427.36 && salariobruto <= 3641.03) {
                salariofinal = salariofinal + (salariobruto * 0.12);
            } else {
                salariofinal = salariofinal + (salariobruto * 0.14);
            }
            if (salariobruto >= 1903.99 && salariobruto <= 2826.65) {
                salariofinal = salariofinal + (salariobruto * 0.075);
            } else if (salariobruto >= 2826.66 && salariobruto <= 3751.05) {
                salariofinal = salariofinal + (salariobruto * 0.15);
            } else {
                salariofinal = salariofinal + (salariobruto * 0.225);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Por favor, insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }

    public void Calcularir() {
        double salariobruto, salariofinal = 0;
        try {
            salariobruto = Double.parseDouble(edtSalarioBruto.getText().toString());
            if (salariobruto >= 1903.99 && salariobruto <= 2826.65) {
                salariofinal = salariofinal + (salariobruto * 0.075);
            } else if (salariobruto >= 2826.66 && salariobruto <= 3751.05) {
                salariofinal = salariofinal + (salariobruto * 0.15);
            } else {
                salariofinal = salariofinal + (salariobruto * 0.225);
            }
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Por favor, insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }
}