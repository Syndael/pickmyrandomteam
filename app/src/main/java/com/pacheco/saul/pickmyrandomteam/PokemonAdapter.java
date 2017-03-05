package com.pacheco.saul.pickmyrandomteam;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pacheco.saul.pickmyrandomteam.constant.CommonConstant;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

public class PokemonAdapter extends BaseAdapter {
    List<String> listaPokemon;
    Context context;
    static Resources resources;
    private static Bitmap pokemonImg;
    private static LayoutInflater inflater = null;

    public PokemonAdapter(Context context, List<String> imgenesTexto) {
        listaPokemon = imgenesTexto;
        setContext(context);
        resources = getContext().getResources();
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaPokemon.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        TextView tv;
        ImageView iv;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        String[] auxStr;
        final Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_listview_pokemon, null);
        holder.tv = (TextView) rowView.findViewById(R.id.tvPokemon);
        holder.iv = (ImageView) rowView.findViewById(R.id.ivPokemon);
        auxStr = listaPokemon.get(position).split(":");
        holder.tv.setText(getStringNombre(auxStr[0]));
        Integer x, y, x1, y1;
        x = getX(Integer.parseInt(auxStr[1]));
        y = getY(Integer.parseInt(auxStr[1]));
        x1 = (x + getWidth()) / Integer.parseInt(auxStr[1]);
        y1 = y + getHeight();
        Bitmap img = Bitmap.createBitmap(getPokemonImg(), x, y, x1, y1);
        holder.iv.setImageBitmap(img);
        return rowView;
    }

    private Integer getX(Integer i) {
        i--;
        int x = (i * getWidth());
        if (x <= 0)
            x = x + 1;
        return x;
    }

    private int getWidth() {
        return (getPokemonImg().getWidth() / CommonConstant.IMG_COLUMNAS);
    }

    private Integer getY(Integer i) {
        Long aux = i.longValue() / CommonConstant.IMG_FILAS;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setRoundingMode(RoundingMode.DOWN);
        i = Integer.parseInt(numberFormat.format(aux)) + 1;
        int y = i * getHeight() - getHeight();
        if (y <= 0)
            y = y + 1;
        return y;
    }

    private int getHeight() {
        return (getPokemonImg().getHeight() / CommonConstant.IMG_FILAS);
    }

    private String getStringNombre(String s) {
        if (s.length() > 1)
            s = s.substring(0, 1).toUpperCase().concat(s.substring(1));
        return s;
    }

    public static Bitmap getPokemonImg() {
        if (pokemonImg == null) {
            setPokemonImg(ImgUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.pokes, 100, 100));
        }
        return pokemonImg;
    }

    public static void setPokemonImg(Bitmap pokemonImg) {
        PokemonAdapter.pokemonImg = pokemonImg;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Resources getResources() {
        return resources;
    }
}