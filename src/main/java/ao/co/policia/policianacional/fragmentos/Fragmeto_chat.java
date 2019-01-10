package ao.co.policia.policianacional.fragmentos;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.adpteres.ListasAdapter;
import ao.co.policia.policianacional.modelos.Conctatos;
import ao.co.policia.policianacional.modelos.Foragido;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmeto_chat extends Fragment {


    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private DatabaseReference referencia;
    private List<Conctatos> mlista;

    public Fragmeto_chat() {
        // Required empty public constructor
    }


    public void modelo1() {

        mlista.add(new Conctatos("Policia Nacional: Tel - 113", "Ministerio do Interior", "Municipio da Maianga", R.drawable.poli));
        mlista.add(new Conctatos("Policia Nacional: Tel - 115", "Ministerio do Interior", "Municipio da Maianga", R.drawable.poli));
        mlista.add(new Conctatos("Policia Nacional: Tel - 113", "Ministerio do Interior", "Municipio da Maianga", R.drawable.poli));
        mlista.add(new Conctatos("Policia Nacional: Tel - 113", "Ministerio do Interior", "Municipio da Maianga", R.drawable.poli1));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmeto_chat, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyTelefone);
        mlista = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        modelo1();
        ListasAdapter listasAdapter = new ListasAdapter(mlista);
        recyclerView.setAdapter(listasAdapter);
        return view;
    }

}
