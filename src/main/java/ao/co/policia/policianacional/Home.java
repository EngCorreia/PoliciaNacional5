package ao.co.policia.policianacional;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    Button conta, criarConta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        conta = (Button) findViewById(R.id.tenhoConta);
        criarConta = (Button) findViewById(R.id.criarConta);


        criarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent criarConta = new Intent(Home.this, RegistoActivity.class);
                startActivity(criarConta);
            }
        });


        conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);

            }
        });
    }
}
