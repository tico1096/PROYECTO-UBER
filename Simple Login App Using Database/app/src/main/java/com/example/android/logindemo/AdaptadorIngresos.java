package com.example.android.logindemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorIngresos extends RecyclerView.Adapter<AdaptadorIngresos.IngresoViewHolder> {

    Context context;
    List<Ingreso> listaIngresos;

    public AdaptadorIngresos(Context context, List<Ingreso> listaIngresos) {
        this.context = context;
        this.listaIngresos = listaIngresos;
    }

    @NonNull
    @Override
    public IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_ingreso, viewGroup, false);
        return new IngresoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoViewHolder ingresoViewHolder, int i) {
        ingresoViewHolder.tvMetodo.setText(listaIngresos.get(i).getMetodo());
        ingresoViewHolder.tvIdUsuario.setText(listaIngresos.get(i).getIdUsuario());
        ingresoViewHolder.tvValor.setText(listaIngresos.get(i).getValor());
        ingresoViewHolder.tvFecha.setText(listaIngresos.get(i).getFecha());
    }

    @Override
    public int getItemCount() {
        return listaIngresos.size();
    }

    public class IngresoViewHolder extends RecyclerView.ViewHolder {

        TextView tvMetodo, tvIdUsuario, tvValor, tvFecha;

        public IngresoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMetodo = itemView.findViewById(R.id.tvMetodo);
            tvIdUsuario = itemView.findViewById(R.id.tvIdUsuario);
            tvValor = itemView.findViewById(R.id.tvValor);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }

    public void filtrar(ArrayList<Ingreso> filtroIngresos) {
        this.listaIngresos = filtroIngresos;
        notifyDataSetChanged();
    }
}