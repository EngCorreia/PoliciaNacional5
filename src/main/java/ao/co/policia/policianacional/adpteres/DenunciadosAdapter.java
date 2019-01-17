package ao.co.policia.policianacional.adpteres;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.Denunciados;

public class DenunciadosAdapter extends RecyclerView.Adapter<DenunciadosAdapter.myViewHolher> {

    final List<Denunciados>lista;

    public DenunciadosAdapter(List<Denunciados> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public myViewHolher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_denunciados,parent,false);
        myViewHolher myViewHolher= new myViewHolher(view);
        return myViewHolher;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolher holder, int position) {
        holder.nome.setText(lista.get(position).getNome());
        holder.data.setText(lista.get(position).getDatas());
        holder.tipo.setText(lista.get(position).getTipoDenunca());
        holder.crime.setText(lista.get(position).getCrime());
       /** holder.imagem.setImageResource();*/
        Picasso.get().load(lista.get(position).getImagem()).into(holder.imagem);


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class myViewHolher extends RecyclerView.ViewHolder {

        TextView nome,data,tipo,crime;
        ImageView imagem;

        LinearLayout linearLayout;

        public myViewHolher(@NonNull View itemView) {
            super(itemView);

            //   linearLayout=(LinearLayout)itemView.findViewById(R.id.layoutCrimes);

            nome = (TextView)itemView.findViewById(R.id.statusD);
            data=(TextView)itemView.findViewById(R.id.descricaoD);
            tipo=(TextView)itemView.findViewById(R.id.crimeD);
            crime=(TextView)itemView.findViewById(R.id.datasD);
            imagem =(ImageView)itemView.findViewById(R.id.imgAmigosD);
        }
    }
}
