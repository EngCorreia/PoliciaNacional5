package ao.co.policia.policianacional.adpteres;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ao.co.policia.policianacional.fragmentos.Fragmento_foragidos;
import ao.co.policia.policianacional.fragmentos.Fragmento_denunciadoss;
import ao.co.policia.policianacional.fragmentos.Fragmeto_privado;

public class FragamentoAdapter extends FragmentPagerAdapter {


    public FragamentoAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Fragmento_foragidos fragmentoAmigos = new Fragmento_foragidos();
                return fragmentoAmigos;
            case 1:
                Fragmeto_privado fragmetoChat = new Fragmeto_privado();
                return  fragmetoChat;
            case 2:
                Fragmento_denunciadoss fragmentoPedido= new Fragmento_denunciadoss();
                return fragmentoPedido;
                default:
                    return null;

                    }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "FORAGIDO";

            case 1:
                return "PRIVADO";
            case 2:
                return "DENUNCIA";
            default:
                return null;
        }

    }
}
