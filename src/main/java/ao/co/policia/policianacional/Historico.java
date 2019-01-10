package ao.co.policia.policianacional;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

public class Historico extends AppCompatActivity {


    private BottomNavigationView nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        nota = (BottomNavigationView) findViewById(R.id.li);
        nota.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String menssage ="";
                if (item.getItemId()==R.id.item){
                    menssage = "chats";
                    Toast.makeText(Historico.this,menssage + "clicou ",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


    }
}
