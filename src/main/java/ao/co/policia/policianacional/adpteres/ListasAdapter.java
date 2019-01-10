package ao.co.policia.policianacional.adpteres;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.Conctatos;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListasAdapter extends RecyclerView.Adapter<ListasAdapter.viewHolher> {

final List<Conctatos>lista;

    public ListasAdapter(List<Conctatos> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public viewHolher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tefones,parent,false);
        viewHolher viewHolher= new viewHolher(view);

        return viewHolher;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolher holder, int position) {

        holder.nome.setText(lista.get(position).getNomes());
        holder.descricao.setText(lista.get(position).getDescricao());
        holder.crime.setText(lista.get(position).getLocalizacao());
        holder.imagens.setImageResource(lista.get(position).getImagem());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class viewHolher extends RecyclerView.ViewHolder {
        CircleImageView imagens;
        TextView nome, descricao,crime,linha;


        public viewHolher(@NonNull View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.status);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            imagens = itemView.findViewById(R.id.imgAmigos);
            crime=(TextView)itemView.findViewById(R.id.crime);
            linha=(TextView)itemView.findViewById(R.id.linha);

        }
    }
}
