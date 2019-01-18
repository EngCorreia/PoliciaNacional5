package ao.co.policia.policianacional.adpteres;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.Denunciados;
import ao.co.policia.policianacional.modelos.Privados;
import de.hdodenhof.circleimageview.CircleImageView;

public class PrivadoAdapter extends RecyclerView.Adapter<PrivadoAdapter.viewHolher> {

final List<Privados>lista;

    public PrivadoAdapter(List<Privados> lista) {
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
        Picasso.get().load(lista.get(position).getImagem()).into(holder.imagens);
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
