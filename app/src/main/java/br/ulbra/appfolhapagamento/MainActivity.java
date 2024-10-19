package br.ulbra.appfolhapagamento;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
                Calcular();
            }
        });

    }

    public void Calcular() {
        double salariobruto, inss = 0, ir = 0, salariofinal, nfilhos = 0;
        String generofinal = "", nome;

        try {
            salariobruto = Double.parseDouble(edtSalarioBruto.getText().toString());
            nome = edtNome.getText().toString();

            if (nome.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, insira seu nome.", Toast.LENGTH_SHORT).show();
                return; // Encerrar o método se o nome não for fornecido
            }

            try {
                String nFilhosText = edtNFilhos.getText().toString();
                if (!nFilhosText.isEmpty()) {
                    nfilhos = Double.parseDouble(nFilhosText);
                }
            } catch (NumberFormatException e) {
                nfilhos = 0;
            }

            int genero = rgSexo.getCheckedRadioButtonId();
            if (genero == R.id.rbMasculino) {
                generofinal = "Sr.";
            } else if (genero == R.id.rbFeminino) {
                generofinal = "Sra.";
            } else {
                Toast.makeText(MainActivity.this, "Por favor, insira um genero.", Toast.LENGTH_SHORT).show();
                return;
            }


            if (salariobruto <= 1212.00) {
                inss = salariobruto * 0.075;
            } else if (salariobruto >= 1212.01 && salariobruto <= 2427.35) {
                inss = salariobruto * 0.09;
            } else if (salariobruto >= 2427.36 && salariobruto <= 3641.03) {
                inss = salariobruto * 0.12;
            } else if (salariobruto >= 3641.04 && salariobruto <= 7087.22) {
                inss = salariobruto * 0.14;
            }

            double salarioCominss = salariobruto - inss;

            if (salarioCominss <= 1903.98) {
                ir = 0;
            } else if (salarioCominss >= 1903.99 && salarioCominss <= 2826.65) {
                ir = (salarioCominss - 1903.98) * 0.075;
            } else if (salarioCominss >= 2826.66 && salarioCominss <= 3751.05) {
                ir = (salarioCominss - 2826.65) * 0.15 + (2826.65 - 1903.98) * 0.075;
            } else if (salarioCominss >= 3751.06 && salarioCominss <= 4664.68) {
                ir = (salarioCominss - 3751.05) * 0.225 + (3751.05 - 2826.65) * 0.15 + (2826.65 - 1903.98) * 0.075;
            }

            salariofinal = salarioCominss - ir;

            if (salariobruto <= 1212.00 && nfilhos >= 0) {
                for (int i = 0; i < nfilhos; i++) {
                    salariofinal = salariofinal + 56.47;
                }
            }

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle(generofinal + " " + nome);
            dialog.setMessage(String.format("Seu novo salário: R$%.2f", salariofinal));
            dialog.setNeutralButton("OK", null);
            dialog.show();

        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Por favor, insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }

}