package facci.pm.CantosBrayan.taller.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import facci.pm.CantosBrayan.taller.R;

public class IngresoActivity extends AppCompatActivity {
    private EditText id, nombre, estado, foto, descripcion, precio, marca;
    private Button ingresar;
    private static final String URL_P_N = "http://10.22.24.176:3000/productos";
    private ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("INGRESANDO PRODUCTO");

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.getText().toString().isEmpty()){
                    id.setError("CAMPO NECESARIO");
                }else if (nombre.getText().toString().isEmpty()){
                    nombre.setError("CAMPO NECESARIO");
                }else if (estado.getText().toString().isEmpty()){
                    estado.setError("CAMPO NECESARIO");
                }else if (foto.getText().toString().isEmpty()){
                    foto.setError("CAMPO NECESARIO");
                }else if (descripcion.getText().toString().isEmpty()){
                    descripcion.setError("CAMPO NECESARIO");
                }else if (precio.getText().toString().isEmpty()){
                    precio.setError("CAMPO NECESARIO");
                }else if (marca.getText().toString().isEmpty()){
                    marca.setError("CAMPO NECESARIO");
                }else {
                    Ingresar();
                }
            }
        });
    }

    private void Ingresar() {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_P_N, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(IngresoActivity.this, "PRODUCTO INGRESADO", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(IngresoActivity.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(IngresoActivity.this, "ERROR: " + error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("id", id.getText().toString().trim());
                map.put("name", nombre.getText().toString().trim());
                map.put("estado", estado.getText().toString().trim());
                map.put("foto", foto.getText().toString().trim());
                map.put("precio", precio.getText().toString().trim());
                map.put("descripcion", descripcion.getText().toString().trim());
                map.put("marca", marca.getText().toString().trim());
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(IngresoActivity.this);
        requestQueue.add(stringRequest);
    }
}
