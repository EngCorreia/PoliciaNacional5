package ao.co.policia.policianacional;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class RegistoActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TextInputLayout usuario;
    private TextView email, senha;
    private CardView cardView;
    DatabaseReference myRef;


    ProgressDialog dialog;

    /**
     * Declaração da base de dados de autenticação
     */
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo);


        //Instançiar o Objecto mAuth
        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        toolbar = findViewById(R.id.tCriaConta);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CRIAR CONTA DE USUARIO");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * declaraçao das minhas views
         *
         * */

        cardView=(CardView)findViewById(R.id.conta);
        email = (TextView) findViewById(R.id.nomeEmail);
        senha = (TextView) findViewById(R.id.senha);



        /**
         * atribuir os valores das views numa variavel do tipo string
         *
         * */


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user_name = usuario.getEditText().getText().toString();
                String email_user = email.getText().toString();
                String password_user = senha.getText().toString();

                if (!TextUtils.isEmpty(user_name) || !TextUtils.isEmpty(email_user) || !TextUtils.isEmpty(password_user)) {
                    dialog.setTitle("Criando a sua Conta");
                    dialog.setMessage("Por favor aguarda.....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    registos(user_name, email_user, password_user);
                } else {
                    Toast.makeText(RegistoActivity.this, "Por favor Prienche todos Campos", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void registos(final String user_name, String email_user, String password_user) {

        mAuth.createUserWithEmailAndPassword(email_user, password_user)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser currente = FirebaseAuth.getInstance().getCurrentUser();
                            String uId = currente.getUid();
                            myRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(uId);
                            HashMap<String, String> usermap = new HashMap<>();
                            usermap.put("nome", user_name);
                            usermap.put("status", "Ola estou usando o App Policia");
                            usermap.put("imagem", "default");
                            usermap.put("imagem_tam", "default");
                            myRef.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        dialog.dismiss();
                                        Intent registo = new Intent(RegistoActivity.this, MainActivity.class);
                                        registo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(registo);
                                        finish();
                                    }
                                }
                            });


                        } else {
                            // If sign in fails, display a message to the user.
                            dialog.hide();
                            Toast.makeText(RegistoActivity.this, "Erro de Coneção", Toast.LENGTH_LONG).show();

                        }

                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}