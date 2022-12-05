package com.app.teamproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private ArrayList<BookmarkStation> favstation;

    interface OnItemClickListener{
        void onRemoveClick(View v, int position);
    }
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(favstation.get(position));
    }

    public void setFavStationList(ArrayList<BookmarkStation> list){
        this.favstation = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return favstation.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fav;
        TextView name;
        ImageButton btn_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fav = (ImageView) itemView.findViewById(R.id.imageview);
            name = (TextView) itemView.findViewById(R.id.textview);
            btn_remove=(ImageButton) itemView.findViewById(R.id.imageButton);


            btn_remove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    int position = getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        if(mListener!=null){
                            mListener.onRemoveClick(view,position);
                        }
                    }
                }
            });
        }

        public void onBind(BookmarkStation s) {
            fav.setImageResource(s.getResourceId());
            name.setText(s.getName());
            btn_remove.setImageResource(s.getB_ResourceId());
        }
    }
}
