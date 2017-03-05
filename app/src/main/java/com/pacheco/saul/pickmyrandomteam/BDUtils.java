package com.pacheco.saul.pickmyrandomteam;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pacheco.saul.pickmyrandomteam.constant.CommonConstant;
import com.pacheco.saul.pickmyrandomteam.constant.PokemonTableConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by cloud on 04/03/2017.
 */

public class BDUtils {
    SQLiteDatabase db;


    public boolean hayRegistrosPokemon() {
        String consulta = "SELECT * FROM ".concat(PokemonTableConstant.POKEMON).concat(" LIMIT 1");
        Cursor c = getCursor(consulta);
        return c.moveToNext();
    }

    private Cursor getCursor(String consulta) {
        Cursor c;
        c = getDb().rawQuery(consulta, null);
        return c;
    }

    private void createPokemonTable() {
        String table = "CREATE TABLE IF NOT EXISTS ";
        table = table.concat(PokemonTableConstant.POKEMON);
        table = table.concat(" ( id INTEGER NOT NULL, ");
        table = table.concat(PokemonTableConstant.POKEMON_NOMBRE);
        table = table.concat(" VARCHAR(79) NOT NULL, ");
        table = table.concat(PokemonTableConstant.POKEMON_NUMERO);
        table = table.concat("INTEGER, ");
        table = table.concat("height INTEGER NOT NULL, ");
        table = table.concat("weight INTEGER NOT NULL, ");
        table = table.concat("base_experience INTEGER NOT NULL, ");
        table = table.concat("orden INTEGER NOT NULL, ");
        table = table.concat("is_default BOOLEAN NOT NULL, ");
        table = table.concat("PRIMARY KEY (id))");
        getDb().execSQL(table);
    }

    private void instertPokemon() {
        getDb().execSQL(PokemonUtils.getStringPokemon());
    }

    public List<String> getPokemon() {
        List<String> aux = new ArrayList<>();
        String consulta = "SELECT ".concat(PokemonTableConstant.POKEMON_NOMBRE);
        consulta = consulta.concat(", ").concat(PokemonTableConstant.POKEMON_NUMERO);
        consulta = consulta.concat(" FROM ").concat(PokemonTableConstant.POKEMON);
        consulta = consulta.concat(" WHERE ").concat(PokemonTableConstant.POKEMON_NUMERO);
        consulta = consulta.concat(" IN (").concat(getSixNumberRandoms(",")).concat(")");
        Cursor c = getCursor(consulta);
        while (c.moveToNext()) {
            String nombre = c.getString(c.getColumnIndex(PokemonTableConstant.POKEMON_NOMBRE));
            String numero = Integer.toString(c.getInt(c.getColumnIndex(PokemonTableConstant.POKEMON_NUMERO)));
            String pokemonNombreNumero = "";
            pokemonNombreNumero = pokemonNombreNumero.concat(nombre);
            pokemonNombreNumero = pokemonNombreNumero.concat(":");
            pokemonNombreNumero = pokemonNombreNumero.concat(numero);
            aux.add(pokemonNombreNumero);
        }
        return aux;
    }

    private String getSixNumberRandoms(String separator) {
        String res;
        Random ran = new Random();
        res = Integer.toString(ran.nextInt(CommonConstant.CANTIDAD_ACTUAL) + 1);
        for (int i = 0; i < 5; i++) {
            res = res.concat(separator);
            res = res.concat(Integer.toString(ran.nextInt(CommonConstant.CANTIDAD_ACTUAL) + 1));
        }
        return res;
    }


    private void createDb() {

    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
        createPokemonTable();
        if (!hayRegistrosPokemon()) {
            createDb();
            instertPokemon();
        }
    }
}
