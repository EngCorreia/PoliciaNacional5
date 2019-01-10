package ao.co.policia.policianacional.adpteres;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ao.co.policia.policianacional.fragmentos.Fragmento_amigos;
import ao.co.policia.policianacional.fragmentos.Fragmento_pedido;
import ao.co.policia.policianacional.fragmentos.Fragmeto_chat;

public class FragamentoAdapter extends FragmentPagerAdapter {


    public FragamentoAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Fragmento_amigos fragmentoAmigos = new Fragmento_amigos();
                return fragmentoAmigos;
            case 1:
                Fragmeto_chat fragmetoChat = new Fragmeto_chat();
                return  fragmetoChat;
            case 2:
                Fragmento_pedido fragmentoPedido= new Fragmento_pedido();
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
                return "Foragidos";

            case 1:
                return "Conctatos";
            case 2:
                return "Denunciados";
            default:
                return null;
        }

    }
}
