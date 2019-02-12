package facci.pm.CantosBrayan.taller.Actividades;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import facci.pm.CantosBrayan.taller.Adaptador.AdaptadorProductos;
import facci.pm.CantosBrayan.taller.Modelo.Productos;
import facci.pm.CantosBrayan.taller.R;

public class MainActivity extends AppCompatActivity {

    private static final String URL_P = "http://10.22.24.176:3000/productos";
    private RecyclerView recyclerView;
    private ArrayList<Productos> productosArrayList;
    private ProgressDialog progressDialog;
    private AdaptadorProductos adaptadorProductos;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.RecyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productosArrayList = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        adaptadorProductos = new AdaptadorProductos(productosArrayList);
        button = (Button)findViewById(R.id.BTNIngreso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this, IngresoActivity.class));
            }
        });

        DatosGenerales();

    }

    private void DatosGenerales() {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, URL_P, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //repuesta de que todo esta bien y me trae datos
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Productos productos = new Productos();
                        productos.setName(jsonObject.getString("name"));
                        productos.setDescripcion(jsonObject.getString("descripcion"));
                        productos.setEstado(jsonObject.getString("estado"));
                        productos.setFoto(jsonObject.getString("foto"));
                        productos.setMarca(jsonObject.getString("marca"));
                        productos.setPrecio(jsonObject.getString("precio"));
                        productos.setId(jsonObject.getString("id"));

                        if (productos.getEstado().equals("true")){
                            productosArrayList.add(productos);
                        }
                    }
                    recyclerView.setAdapter(adaptadorProductos);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // repuesta de que ocurrio un error mientras se hacia la petición
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}













