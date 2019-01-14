package ao.co.policia.policianacional.fragmentos;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.Sobre;
import ao.co.policia.policianacional.modelos.Foragido;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento_amigos extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference referencia;
    List<Foragido> mlista;
    private ProgressDialog progressDialog;


    public Fragmento_amigos() {

    }


    public void modelo1() {
        progressDialog= new ProgressDialog(getContext());
        progressDialog.setTitle("Foragidos");
        progressDialog.setMessage("Por Favor Aguarde....");
        progressDialog.show();
        String cod=UUID.randomUUID().toString();
        referencia = FirebaseDatabase.getInstance().getReference().child("foragidos");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    mlista.add(new Foragido(dados.child("codigo").getValue().toString(), dados.child("nome").getValue(String.class), dados.child("descricao").getValue(String.class), dados.child("crime").getValue(String.class), dados.child("data").getValue(String.class), dados.child("imagem").getValue(String.class)));

                }
                progressDialog.dismiss();
                Sobre.ForagidoAdapter fora = new Sobre.ForagidoAdapter(mlista);

                recyclerView.setAdapter(fora);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fragment_fragmento_amigos, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.foraRec);
        mlista = new ArrayList<>();



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        modelo1();

        return view;
    }


}
