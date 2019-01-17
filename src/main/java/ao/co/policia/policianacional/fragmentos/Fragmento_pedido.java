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

import com.coremedia.iso.boxes.DataEntryUrlBox;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ao.co.policia.policianacional.R;
import ao.co.policia.policianacional.adpteres.DenunciadosAdapter;
import ao.co.policia.policianacional.modelos.Denunciados;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragmento_pedido extends Fragment {

    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    DatabaseReference referencia;
    List<Denunciados>mlista;


    public Fragmento_pedido() {
        // Required empty public constructor
    }




    public void modelo1() {
        progressDialog= new ProgressDialog(getContext());
        progressDialog.setTitle("Foragidos");
        progressDialog.setMessage("Por Favor Aguarde....");
        progressDialog.show();
        String cod=UUID.randomUUID().toString();
        referencia = FirebaseDatabase.getInstance().getReference().child("denuncias");
        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    mlista.add(new Denunciados(dados.child("codigo").getValue().toString(), dados.child("nome").getValue(String.class), dados.child("data").getValue(String.class), dados.child("crime").getValue(String.class), dados.child("denuncia").getValue(String.class), dados.child("imagem").getValue(String.class)));

                }
                progressDialog.dismiss();
                DenunciadosAdapter fora = new DenunciadosAdapter(mlista);

                recyclerView.setAdapter(fora);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento_pedido, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.denunciados);
        mlista= new ArrayList<>();
        modelo1();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }

}
