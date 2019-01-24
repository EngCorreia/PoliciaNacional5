package ao.co.policia.policianacional;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

   private Button cardView1;
   private Button cardView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        cardView1 = findViewById(R.id.conta);
        cardView2 = findViewById(R.id.conta1);


        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent criarConta = new Intent(Home.this, RegistoActivity.class);
                startActivity(criarConta);
            }
        });


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Login.class);
                startActivity(intent);

            }
        });
    }
}
