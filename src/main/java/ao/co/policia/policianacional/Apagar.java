package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Apagar extends AppCompatActivity {


    private EditText nome, desc,datas,crimes;
    Foragidos foragidos;
    Button apager;
    DatabaseReference reference;
    Toolbar toolbar;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar);
        progressDialog= new ProgressDialog(this);

        toolbar=(Toolbar)findViewById(R.id.apagados);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String codigo = getIntent().getStringExtra("codigoF");
        final String nomeF = getIntent().getStringExtra("nomeF");
        final String descricaoF = getIntent().getStringExtra("descricaoF");
        final String crimeF = getIntent().getStringExtra("crimeF");
        final String dataF = getIntent().getStringExtra("dataF");
        getSupportActionBar().setTitle(nomeF);



        nome = (EditText) findViewById(R.id.nome4);
        desc = (EditText) findViewById(R.id.d);
        crimes=(EditText)findViewById(R.id.crimes);
        datas = (EditText)findViewById(R.id.datas);
        apager = (Button) findViewById(R.id.btnApagar);


        nome.setText(nomeF);
        desc.setText(descricaoF);
        crimes.setText(crimeF);
        datas.setText(dataF);


        apager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Por favor Aguarde....");
                progressDialog.setTitle("Apagando : " + nomeF);
                progressDialog.show();
                reference = FirebaseDatabase.getInstance().getReference().child("foragidos").child(codigo);
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Intent no = new Intent(Apagar.this, MainActivity.class);
                            startActivity(no);
                            finish();
                        }
                    }
                });

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
