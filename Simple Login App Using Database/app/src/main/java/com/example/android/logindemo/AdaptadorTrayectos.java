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

public class AdaptadorTrayectos extends RecyclerView.Adapter<AdaptadorTrayectos.TrayectoViewHolder> {

    Context context;
    List<Trayecto> listaTrayectos;

    public AdaptadorTrayectos(Context context, List<Trayecto> listaTrayectos) {
        this.context = context;
        this.listaTrayectos = listaTrayectos;
    }

    @NonNull
    @Override
    public AdaptadorTrayectos.TrayectoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_trayecto, viewGroup, false);
        return new AdaptadorTrayectos.TrayectoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorTrayectos.TrayectoViewHolder trayectoViewHolder, int i) {
        trayectoViewHolder.tvIdUsuario.setText(listaTrayectos.get(i).getIdUsuario());
        trayectoViewHolder.tvConductor.setText(listaTrayectos.get(i).getNombre());
        trayectoViewHolder.tvPasajero.setText(listaTrayectos.get(i).getNombrepas());
        trayectoViewHolder.tvInicio.setText(listaTrayectos.get(i).getFechainicio());
        trayectoViewHolder.tvFin.setText(listaTrayectos.get(i).getFechafin());
        trayectoViewHolder.tvValor.setText(listaTrayectos.get(i).getValor());

    }

    @Override
    public int getItemCount() {
        return listaTrayectos.size();
    }

    public class TrayectoViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdUsuario, tvConductor, tvPasajero,tvInicio,tvFin ,tvValor;

        public TrayectoViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdUsuario = itemView.findViewById(R.id.tvIdUsuario);
            tvConductor = itemView.findViewById(R.id.tvConductor);
            tvPasajero = itemView.findViewById(R.id.tvPasajero);
            tvInicio = itemView.findViewById(R.id.tvInicio);
            tvFin = itemView.findViewById(R.id.tvFin);
            tvValor = itemView.findViewById(R.id.tvValor);
        }
    }

    public void filtrar(ArrayList<Trayecto> filtroTrayectos) {
        this.listaTrayectos = filtroTrayectos;
        notifyDataSetChanged();
    }
}