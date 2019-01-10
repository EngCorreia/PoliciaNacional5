package ao.co.policia.policianacional.adpteres;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ao.co.policia.policianacional.Apagar;
import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.Foragido;
import de.hdodenhof.circleimageview.CircleImageView;

public class ForagidoAdapter extends RecyclerView.Adapter<ForagidoAdapter.viewHolher> {
    final List<Foragido> listas;
    int id;
    List<Foragido> foragidos;

    public ForagidoAdapter(List<Foragido> listas) {
        this.listas = listas;
    }


    @NonNull
    @Override
    public viewHolher onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_foragido, parent, false);
        final viewHolher viewHolher = new viewHolher(view);
        foragidos = new ArrayList<>();
        viewHolher.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = viewHolher.getAdapterPosition();

                String codigoF = listas.get(id).getCodigo();
                String nomeF = listas.get(id).getNome();
                String descricaoF = listas.get(id).getDescricao();
                String crimeF = listas.get(id).getCrime();
                String dataF = listas.get(id).getDatas();


                Intent novo = new Intent(parent.getContext(), Apagar.class);
                novo.putExtra("codigoF", codigoF);
                novo.putExtra("nomeF", nomeF);
                novo.putExtra("descricaoF", descricaoF);
                novo.putExtra("crimeF", crimeF);
                novo.putExtra("dataF", dataF);

                parent.getContext().startActivity(novo);
            }
        });
        return viewHolher;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolher holder, int position) {


        holder.nome.setText("Nome: " + listas.get(position).getNome());
        holder.descricao.setText("Perfil:" + listas.get(position).getDescricao());
        holder.imagens.setImageResource(listas.get(position).getImagem());
        holder.crime.setText("Local Crime: " + listas.get(position).getCrime());
        holder.datas.setText("Data do Crime: " + listas.get(position).getDatas());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }


    public class viewHolher extends RecyclerView.ViewHolder {
        CircleImageView imagens;
        TextView nome, descricao, crime, datas, linha;
        LinearLayout linearLayout;


        public viewHolher(@NonNull View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.status);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            imagens = itemView.findViewById(R.id.imgAmigos);
            crime = (TextView) itemView.findViewById(R.id.crime);
            datas = (TextView) itemView.findViewById(R.id.datas);
            linha = (TextView) itemView.findViewById(R.id.linha);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.forag);

        }
    }
}

