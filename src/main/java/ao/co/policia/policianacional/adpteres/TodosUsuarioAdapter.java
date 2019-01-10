package ao.co.policia.policianacional.adpteres;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.modelos.ModeloUsuarios;
import de.hdodenhof.circleimageview.CircleImageView;

public class TodosUsuarioAdapter extends RecyclerView.Adapter<TodosUsuarioAdapter.viewHolher> {

    final List<ModeloUsuarios> listas;

    public TodosUsuarioAdapter(List<ModeloUsuarios> listas) {
        this.listas = listas;
    }

    @NonNull
    @Override
    public viewHolher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_amigos_layout, parent, false);
        viewHolher viewHolher = new viewHolher(view);

        return viewHolher;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolher holder, int position) {

        holder.nome.setText(listas.get(position).getNome());
        holder.descricao.setText(listas.get(position).getStatus());
        holder.imagens.setImageResource(listas.get(position).getImagem());
    }

    @Override
    public int getItemCount() {
        return listas.size();
    }

    public class viewHolher extends RecyclerView.ViewHolder {
        CircleImageView imagens;
        TextView nome, descricao;


        public viewHolher(@NonNull View itemView) {
            super(itemView);

            nome = (TextView) itemView.findViewById(R.id.status);
            descricao = (TextView) itemView.findViewById(R.id.descricao);
            imagens = itemView.findViewById(R.id.imgAmigos);

        }
    }
}
