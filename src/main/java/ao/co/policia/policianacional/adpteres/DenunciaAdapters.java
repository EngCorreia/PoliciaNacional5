package ao.co.policia.policianacional.adpteres;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ao.co.policia.policianacional.Denucia;
import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.Denuncias;
import de.hdodenhof.circleimageview.CircleImageView;

public class DenunciaAdapters extends RecyclerView.Adapter<DenunciaAdapters.myViewHolher> {

    final List<Denuncias> listas;

    public DenunciaAdapters(List<Denuncias> listas) {
        this.listas = listas;
    }


    @NonNull
    @Override
    public myViewHolher onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recicleviews_denuncia, parent, false);
        final myViewHolher viewHolhe = new myViewHolher(view);


        viewHolhe.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = String.valueOf(viewHolhe.getAdapterPosition());
                if (!nome.equals("")) {

                    Intent intents = new Intent(parent.getContext(), Denucia.class);
                    parent.getContext().startActivity(intents);

                }
            }
        });
        return viewHolhe;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolher holder, int position) {

        holder.nome.setText(listas.get(position).getNome());
        holder.descricao.setText(listas.get(position).getDescricao());
        Picasso.get().load(listas.get(position).getImagem()).into(holder.imagem);


    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public class myViewHolher extends RecyclerView.ViewHolder {

        TextView nome, descricao;
        CircleImageView imagem;
        CardView cardView;
        LinearLayout linearLayout;

        public myViewHolher(@NonNull View itemView) {
            super(itemView);

            //   linearLayout=(LinearLayout)itemView.findViewById(R.id.layoutCrimes);
            cardView = itemView.findViewById(R.id.card);
            nome = itemView.findViewById(R.id.descr);
            descricao = itemView.findViewById(R.id.descr1);
            imagem = itemView.findViewById(R.id.imagem);
        }
    }
}
