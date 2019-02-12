package facci.pm.CantosBrayan.taller.Adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import facci.pm.CantosBrayan.taller.Actividades.DetalleActivity;
import facci.pm.CantosBrayan.taller.Modelo.Productos;
import facci.pm.CantosBrayan.taller.R;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.MyViewHolder>{

    private ArrayList<Productos> productosArrayList;

    public AdaptadorProductos(ArrayList<Productos> productosArrayList) {
        this.productosArrayList = productosArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.vista_item_productos, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        final Productos productos = productosArrayList.get(position);
        myViewHolder.name.setText(productos.getName());
        myViewHolder.estado.setText(productos.getEstado());
        myViewHolder.precio.setText("$"+productos.getPrecio());
        Picasso.get().load(productos.getFoto()).into(myViewHolder.imageView);

        myViewHolder.view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetalleActivity.class);
                intent.putExtra("Pilozo", productos.getId());
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return productosArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private View view1;
        private ImageView imageView;
        private TextView name, estado, precio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            view1 = itemView;
            imageView = (ImageView)view1.findViewById(R.id.ImagenPLista);
            name = (TextView)view1.findViewById(R.id.LBLNombrePLista);
            estado = (TextView)view1.findViewById(R.id.LBLEstadoPLista);
            precio = (TextView)view1.findViewById(R.id.LBLPrecioPLista);

        }
    }
}








