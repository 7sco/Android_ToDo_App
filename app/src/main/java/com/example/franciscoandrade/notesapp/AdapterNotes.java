package com.example.franciscoandrade.notesapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import static android.app.LauncherActivity.*;

/**
 * Created by franciscoandrade on 12/13/17.
 */

public class AdapterNotes extends RecyclerView.Adapter<AdapterNotes.ViewHolderNotes> {
    List<ModelClass> listNotes;

    Context context;


    private int previousPosition = 0;

    public AdapterNotes(List<ModelClass> listNotes, Context context) {
        this.listNotes = listNotes;
        this.context = context;
    }

    @Override
    public ViewHolderNotes onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);


        return new ViewHolderNotes(view);
    }



    @Override
    public void onBindViewHolder(ViewHolderNotes holder, final int position) {

        holder.textNote.setText(listNotes.get(position).getText());
        holder.titleNote.setText(listNotes.get(position).getTitle());

        int red= listNotes.get(position).getRed();
        int green = listNotes.get(position).getGreen();
        int blue= listNotes.get(position).getBlue();


        //holder.background.setBackgroundColor(Color.rgb(red, green, blue));


        holder.titleNote.setTextColor(Color.rgb(red, green, blue));
//        holder.background.setBackgroundColor(Color.rgb(red, green, blue));


        holder.recyclerItemHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item #: "+position, Toast.LENGTH_SHORT).show();
            }
        });


        previousPosition = position;




        final int currentPosition = position;
        final ModelClass infoData = listNotes.get(position);



    }

    private void removeItem(ModelClass infoData) {

        int position= listNotes.indexOf(infoData);
        listNotes.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();

    }
    public void swipeDelete(int pos)
    {
        listNotes.remove(pos);
        this.notifyItemRemoved(pos);
    }



    private int generateRandom() {
        return new Random().nextInt(256);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class ViewHolderNotes extends RecyclerView.ViewHolder {
        TextView textNote, titleNote;
        CardView recyclerItemHolder;

        LinearLayout background;

        public ViewHolderNotes(View itemView) {
            super(itemView);

            textNote=(TextView)itemView.findViewById(R.id.textNote);
            titleNote=(TextView)itemView.findViewById(R.id.titleNote);
            recyclerItemHolder=(CardView)itemView.findViewById(R.id.recyclerItemHolder);
            //background=(LinearLayout)itemView.findViewById(R.id.background);

            int red= generateRandom();
            int green = generateRandom();
            int blue= generateRandom();

//            background.setBackgroundColor(Color.rgb(red, green, blue));

            titleNote.setTextColor(Color.rgb(red, green, blue));



        }
    }
}
