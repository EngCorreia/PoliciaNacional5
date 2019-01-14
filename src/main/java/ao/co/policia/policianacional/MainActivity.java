package ao.co.policia.policianacional;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ao.co.policia.policianacional.adpteres.FragamentoAdapter;
import ao.co.policia.policianacional.modelos.Foragido;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    private FirebaseAuth mAuth;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private DatabaseReference referencia;
    List<Foragido> mlista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlista= new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.tMenu);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        recyclerView = (RecyclerView)findViewById(R.id.foraRec);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu Inicial");
        //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setIcon(R.drawable.poli);


        fragmentPagerAdapter = new FragamentoAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_amigo);
      //  tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_chat);
      //  tabLayout.getTabAt(2).setIcon(R.drawable.ic_action_telefone);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.mene4);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // usuario fez a busca
                String rs = query;

                reference = FirebaseDatabase.getInstance().getReference();

                Query query1;
                query1 = reference.child("foragidos").orderByChild("nome").startAt(rs).endAt(rs + "\uf8ff");
                query1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dados : dataSnapshot.getChildren()) {

                            mlista.add(new Foragido(dados.child("codigo").getValue().toString(), dados.child("nome").getValue(String.class), dados.child("descricao").getValue(String.class), dados.child("crime").getValue(String.class), dados.child("data").getValue(String.class), dados.child("imagem").getValue(String.class)));

                        }
                       // progressDialog.dismiss();
                        Sobre.ForagidoAdapter fora = new Sobre.ForagidoAdapter(mlista);

                        recyclerView.setAdapter(fora);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(MainActivity.this, "Digitou: " + query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //usuario mudou o texto digitado
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == R.id.menu2) {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == android.R.id.home) {
            //   finish();
        } else if (item.getItemId() == R.id.item5) {
            FirebaseAuth.getInstance().signOut();
            menuInicial();
        } else if (item.getItemId() == R.id.menu1) {
            Intent conta = new Intent(MainActivity.this, Usuario.class);
            startActivity(conta);
        } else if (item.getItemId() == R.id.mene3) {
            Intent conta1 = new Intent(MainActivity.this, Sobre.class);
            startActivity(conta1);
        } else if (item.getItemId() == R.id.fora) {
            Intent conta2 = new Intent(MainActivity.this, Foragidos.class);
            startActivity(conta2);
        }else if (item.getItemId()==R.id.me){
            Intent conta4 = new Intent(MainActivity.this, Historico.class);
            startActivity(conta4);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            menuInicial();


        }

    }

    public void menuInicial() {

        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);

    }

}
