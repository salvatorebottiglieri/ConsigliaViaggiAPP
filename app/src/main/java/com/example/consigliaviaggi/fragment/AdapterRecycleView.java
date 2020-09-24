package com.example.consigliaviaggi.fragment;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.consigliaviaggi.R;
import com.example.consigliaviaggi.fragment.searchModel.CardStruttura;
import com.example.consigliaviaggi.model.Struttura;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import static java.security.AccessController.getContext;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.StrutturaViewHolder> {

    private ArrayList<CardStruttura> listaStrutture;
    private OnItemClickListener clickListener;

    public interface OnItemClickListener{
        void onIntemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener ){
        clickListener = listener;
    }

    public AdapterRecycleView(ArrayList<CardStruttura> strutture){
        this.listaStrutture = strutture;
    }

    @NonNull
    @Override
    public StrutturaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        return new StrutturaViewHolder(view,clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull StrutturaViewHolder holder, int position) {
        CardStruttura strutturaCorrente = listaStrutture.get(position);


        Picasso.get().load(strutturaCorrente.getImmagine()).into(holder.thumbStruttura);
        holder.nomeStruttura.setText(strutturaCorrente.getNomeStruttura());
        holder.descrizioneStruttura.setText(strutturaCorrente.getDescrizioneStruttura());

    }

    @Override
    public int getItemCount() {
        return listaStrutture.size();
    }

    public static class StrutturaViewHolder extends RecyclerView.ViewHolder{
        public ImageView thumbStruttura;
        public TextView nomeStruttura;
        public TextView descrizioneStruttura;

        public StrutturaViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            thumbStruttura = itemView.findViewById(R.id.thumbnail_struttura);
            nomeStruttura = itemView.findViewById(R.id.nome_struttura);
            descrizioneStruttura = itemView.findViewById(R.id.descrizione_struttura);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onIntemClick(position);
                        }
                    }
                }
            });

        }
    }
}
