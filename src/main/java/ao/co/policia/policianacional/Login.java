package ao.co.policia.policianacional;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {


    private Toolbar toolbar;
    private TextView email, senha;
    private Button login;
    ProgressDialog progressBar;
    private CardView cardView;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.tLogin);
        mAuth = FirebaseAuth.getInstance();

        progressBar = new ProgressDialog(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("LOGIN");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        email = (TextView) findViewById(R.id.email);
        senha = (TextView) findViewById(R.id.senha);

        cardView = (CardView)findViewById(R.id.lg);




        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = email.getText().toString();
                String password = senha.getText().toString();
                if (!TextUtils.isEmpty(email1) || !TextUtils.isEmpty(password)) {
                    progressBar.setTitle("Fazendo Login");
                    progressBar.setMessage("Por favor Aguarda.....");
                    progressBar.setCanceledOnTouchOutside(false);
                    progressBar.show();
                    loginUser(email1, password);


                }else{
                    Toast.makeText(Login.this,"Por favor Prienche os Campos Vazios",Toast.LENGTH_LONG).show();
                }

            }

            private void loginUser(String email, String passw) {


                mAuth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressBar.dismiss();
                            Intent mainIntent = new Intent(Login.this, MainActivity.class);
                            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();
                        }else{
                            progressBar.hide();
                            Toast.makeText(Login.this,"Erro de Login: Verifica o email ou a Senha",Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
