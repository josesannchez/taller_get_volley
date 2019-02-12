package facci.pm.CantosBrayan.taller.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import facci.pm.CantosBrayan.taller.R;

public class IngresoActivity extends AppCompatActivity {
    private EditText id, nombre, estado, foto, descripcion, precio, marca;
    private Button ingresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);
        id = (EditText)findViewById(R.id.TXTIdIngresar);
        nombre = (EditText)findViewById(R.id.TXTNombreIngresar);
        estado = (EditText)findViewById(R.id.TXTEstadoIngresar);
        foto = (EditText)findViewById(R.id.TXTFotoIngresar);
        descripcion = (EditText)findViewById(R.id.TXTDescripcionIngresar);
        precio = (EditText)findViewById(R.id.TXTPrecioIngresar);
        marca = (EditText)findViewById(R.id.TXTMarcaIngresar);
        ingresar = (Button)findViewById(R.id.BTNIngresarProducto);
    }
}
