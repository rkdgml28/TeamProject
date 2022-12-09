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
import android.widget.Toast;

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

    public ArrayList<String> getStationList(){
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
            MenuItem start = menu.add(Menu.NONE, 1003, 3, "출발지 설정");
            MenuItem finish = menu.add(Menu.NONE, 1004, 4, "도착지 설정");
            edit.setOnMenuItemClickListener(onEditMenu);
            delete.setOnMenuItemClickListener(onEditMenu);
            start.setOnMenuItemClickListener(onEditMenu);
            finish.setOnMenuItemClickListener(onEditMenu);
        }

        public void onBind(BookmarkStation s) {
            fav.setImageResource(s.getResourceId());
        }

        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String s = "";
                Intent intent;
                switch (item.getItemId()) {
                    // 상세정보
                    case 1001:
                        s = name.getText().toString();
                        intent = new Intent(context, StationActivity.class);
                        intent.putExtra("station", s);
                        context.startActivity(intent);
                        break;

                    // 삭제
                    case 1002:
                        myStations.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), myStations.size());
                        break;

                    case 1003:
                        s = name.getText().toString();
                        intent = new Intent(context, SearchActivity.class);
                        intent.putExtra("start_station", s);
                        context.startActivity(intent);
                        break;

                    case 1004:
                        s = name.getText().toString();
                        intent = new Intent(context, SearchActivity.class);
                        intent.putExtra("finish_station", s);
                        context.startActivity(intent);
                        break;
                }
                return true;
            }
        };

    }
}
