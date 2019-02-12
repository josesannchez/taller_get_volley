package facci.pm.CantosBrayan.taller.Actividades;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import facci.pm.CantosBrayan.taller.R;

public class DetalleActivity extends AppCompatActivity {
    private static final String URL_D = "http://10.22.29.171:3000/producto/";
    private ImageView imageView;
    private TextView nombre, precio, descripcion;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String id = getIntent().getStringExtra("Pilozo");
        imageView = (ImageView)findViewById(R.id.ImagenPD);
        nombre = (TextView)findViewById(R.id.LBLNombreD);
        precio = (TextView)findViewById(R.id.LBLPrecioPD);
        descripcion = (TextView)findViewById(R.id.LBLDescripcionPD);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        Detalle(id);
    }

    private void Detalle(String id) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, URL_D+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    nombre.setText(jsonObject.getString("name"));
                    precio.setText("$"+ jsonObject.getString("precio"));
                    descripcion.setText(jsonObject.getString("descripcion"));
                    Picasso.get().load(jsonObject.getString("foto")).into(imageView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetalleActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
