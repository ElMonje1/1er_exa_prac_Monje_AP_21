package com.example.exa_monje_21_ap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {//Inicia main
    private EditText matricula, nombre, carrera, calificacion1, calificacion2;
    private Button btnAlta, btnConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {//Inicia Metodo oncreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Integracion de XML a Java
        btnAlta = findViewById(R.id.btnAlta);
        btnConsulta = findViewById(R.id.btnConsulta);
        matricula = findViewById(R.id.etMatricula);
        nombre = findViewById(R.id.etNombre);
        carrera = findViewById(R.id.etCarrera);
        calificacion1 = findViewById(R.id.etCal1);
        calificacion2 = findViewById(R.id.etCal2);

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirConsulta = new Intent(getApplicationContext(), Consulta.class);
                startActivity(abrirConsulta);
            }
        });
    }//Termina metodo oncreate

    public void altaEstudiante(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String matri = matricula.getText().toString();
        String nomb = nombre.getText().toString();
        String carre = carrera.getText().toString();
        String cali1 = calificacion1.getText().toString();
        String cali2 = calificacion2.getText().toString();
        //Calculamos el promedio
        double promedio = (Double.parseDouble(cali1)+Double.parseDouble(cali2))/2;
        //Lo convertimos en string para guardarlo en la base de datos
        String prome = String.valueOf(promedio);

        //Se crea contenedor
        ContentValues registro = new ContentValues();
        //Se integran al registro
        registro.put("matr", matri);
        registro.put("nom", nomb);
        registro.put("carrer", carre);
        registro.put("cal1", cali1);
        registro.put("cal2", cali2);
        registro.put("prom", prome);
        //Insertamos a la tabla
        bd.insert("alumno", null, registro);
        //Cerramos BD
        bd.close();
        //Limpiamos campos de texto
        matricula.setText(null);
        nombre.setText(null);
        carrera.setText(null);
        calificacion1.setText(null);
        calificacion2.setText(null);

        //Imprimimos
        Toast.makeText(this, "exito al ingresar el registro\n\n Matricula:" +matri+ "\nNombre: "+ nomb+"\nCarrera: "+carre+"\nClificacion1: "+cali1+"\nClificacion2: "+cali2+"\nPromedio: "+prome, Toast.LENGTH_SHORT).show();
    }


}//Termina main