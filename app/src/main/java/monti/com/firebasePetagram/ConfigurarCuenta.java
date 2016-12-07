package monti.com.firebasePetagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ConfigurarCuenta extends AppCompatActivity {
    private EditText tvCuenta;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        tvCuenta = (EditText) findViewById(R.id.tvCuenta);


        toolbar = (Toolbar) findViewById(R.id.toolbarConfigurar);

        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Cuenta");
        }


        Button guardar = (Button) findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
              //  finish();

            }
        });
    }
    public void guardar(){
        String usuario = tvCuenta.getText().toString();

        try{

            FileOutputStream fos = openFileOutput("usuario.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);

            // Escribimos el String en el archivo
            osw.write(usuario);
            osw.flush();
            osw.close();

            // Mostramos que se ha guardado
            Toast.makeText(getBaseContext(), "Guardado", Toast.LENGTH_SHORT).show();

            tvCuenta.setText("");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }
    @Override
    public void finish() {
/*
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("cuenta", tvCuenta.getText().toString());
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        */
        super.finish();
    }
}
