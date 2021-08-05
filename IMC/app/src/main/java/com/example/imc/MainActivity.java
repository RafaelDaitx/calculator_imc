package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText altura, peso;
    TextView resultado;
    Button calcular;
    double resultadoIMC;
    String categoria;
    ImageView imagem;
    Bitmap myBitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        altura = findViewById(R.id.inputAltura);
        peso = findViewById(R.id.inputPeso);
        calcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.resultado);
        imagem = findViewById(R.id.imagem);


        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarInputs()){
                    calcularImc();
                    categoriaIMC();
                    resultado.setText(categoria);
                }else{
                    Toast.makeText(MainActivity.this, "Dados incorretos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean verificarInputs(){

        if(altura.length()>0 && peso.length()>0)
            return true;
        else
            return false;
    }

    private void calcularImc(){
        double a = Double.parseDouble(altura.getText().toString());
        double p = Double.parseDouble(peso.getText().toString());

        resultadoIMC = p/(a*a);
    }

    private void categoriaIMC(){

        if(resultadoIMC > 30)
            categoria = "Obeso";
        else if(resultadoIMC > 25)
            categoria = "Acima do Peso ideal";
            else if(resultadoIMC > 18.5)
                categoria = "No peso ideal";
                else  if(resultadoIMC > 16)
                    categoria = "Abaixo do Peso Ideal";
                    else
                        categoria = "Desnutrição";

    }

    private void alterarImagem(){
        File imgMagro = new File("/app/src/main/res/mipmap-xhdpi/magro.png");
        File imgMedio = new File("/app/src/main/res/mipmap-xhdpi/medio.png");
        File imgGordo = new File("/app/src/main/res/mipmap-xhdpi/gordo.png");
        File imgObeso = new File("/app/src/main/res/mipmap-xhdpi/obeso.png");
        File imgPalito = new File("/app/src/main/res/mipmap-xhdpi/palito.png");


        if (categoria.equals("Obeso")) {
            myBitmap = BitmapFactory.decodeFile(imgObeso.getAbsolutePath());
            imagem.setImageBitmap(myBitmap);
        }  else if (categoria.equals("Acima do Peso ideal")) {
            myBitmap = BitmapFactory.decodeFile(imgGordo.getAbsolutePath());
            imagem.setImageBitmap(myBitmap);
            }  else if (categoria.equals("No peso ideal")) {
                myBitmap = BitmapFactory.decodeFile(imgMedio.getAbsolutePath());
                imagem.setImageBitmap(myBitmap);
                } else if (categoria.equals("Abaixo do Peso Ideal")) {
                    myBitmap = BitmapFactory.decodeFile(imgGordo.getAbsolutePath());
                    imagem.setImageBitmap(myBitmap);
                    } else if (categoria.equals("Desnutrição")) {
                        myBitmap = BitmapFactory.decodeFile(imgPalito.getAbsolutePath());
                        imagem.setImageBitmap(myBitmap);
                        }
    }
}