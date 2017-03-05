package com.pacheco.saul.pickmyrandomteam;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class Main extends AppCompatActivity {

    Button btPickMyTeam;
    ListView lvPokemon;
    BDUtils bdUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdUtils = new BDUtils();
        bdUtils.setDb(openOrCreateDatabase("poke.db", Context.MODE_PRIVATE, null));
        confBtPickMyTeam();
    }

    private void confBtPickMyTeam() {
        getBtPickMyTeam().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> pokemon = bdUtils.getPokemon();
                getLvPokemon().setAdapter(new PokemonAdapter(getBaseContext(), pokemon));
            }
        });
    }

    public Button getBtPickMyTeam() {
        if (btPickMyTeam == null) {
            btPickMyTeam = (Button) findViewById(R.id.btPickMyTeam);
        }
        return btPickMyTeam;
    }

    public ListView getLvPokemon() {
        if (lvPokemon == null) {
            lvPokemon = (ListView) findViewById(R.id.lvPokemon);
        }
        return lvPokemon;
    }
}
