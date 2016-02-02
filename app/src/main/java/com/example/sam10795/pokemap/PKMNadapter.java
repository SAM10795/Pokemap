package com.example.sam10795.pokemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by SAM10795 on 17-09-2015.
 */
public class PKMNadapter extends ArrayAdapter {

    private Context mcontext;
    private ArrayList<PKMN> pklist;

    public PKMNadapter(Context context, ArrayList<PKMN> pkmns)
    {
        super(context,R.layout.pkmn,pkmns);
        mcontext = context;
        pklist = pkmns;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(position%2==0)
            {
                convertView = inflater.inflate(R.layout.pkmn,parent,false);
            }
            holder = new ViewHolder();
            holder.name = (TextView)convertView.findViewById(R.id.pkmname);
            holder.level = (TextView)convertView.findViewById(R.id.pkmnlv);
            holder.time = (TextView)convertView.findViewById(R.id.pkmntim);
            holder.pokemon = (ImageView)convertView.findViewById(R.id.pkmnview);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        PKMN pkmn = (PKMN)getItem(position);
        holder.name.setText(pkmn.getName());
        holder.level.setText(pkmn.getLv());
        holder.time.setText(pkmn.getCdt().toString());

        return convertView;
    }

    static class ViewHolder
    {
        TextView name;
        TextView level;
        TextView time;
        ImageView pokemon;
    }
}
