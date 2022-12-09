package com.app.teamproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private ArrayList<String> myStations;
    private Context context;

    public BookmarkAdapter(ArrayList<String> stations, Context context) {
        this.myStations = stations;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.onBind(favstation.get(position));
        //holder.name.setText(favstation.get(position).getName());
        holder.name.setText(myStations.get(position));
    }

    public ArrayList<String> getFavStationList(){
        return myStations;
    }

    @Override
    public int getItemCount() {
        return myStations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView fav, temp;
        TextView name;
        ImageButton btn_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //this.fav = (ImageView) itemView.findViewById(R.id.imageview);
            this.name = (TextView) itemView.findViewById(R.id.textview);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem edit = menu.add(Menu.NONE, 1001, 1, "상세정보");
            MenuItem delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
        }

        public void onBind(BookmarkStation s) {
            fav.setImageResource(s.getResourceId());
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    // 상세정보
                    case 1001:
//                        Intent intent = new Intent(context, StationActivity.class);
//                        intent.putExtra("station", String.valueOf(name));
//                        Log.v("putextra", String.valueOf(name));
//                        context.startActivity(intent);

                        String s = name.getText().toString();
                        Intent intent = new Intent(context, StationActivity.class);
                        intent.putExtra("station", s);
                        context.startActivity(intent);
                        break;

                    // 삭제
                    case 1002:
                        myStations.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), myStations.size());
                        break;
                }
                return true;
            }
        };

    }
}
