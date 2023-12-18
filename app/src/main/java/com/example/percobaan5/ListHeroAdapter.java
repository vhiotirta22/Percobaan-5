package com.example.percobaan5;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ListViewHolder> {
    private ArrayList<Hero> listHero;

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }
    public ListHeroAdapter(ArrayList<Hero> list){
        this.listHero = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.item_row_hero, parent, false);
        return new ListViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Hero hero = listHero.get(position);
        holder.imgPhoto.setImageResource(hero.getPhoto());
        holder.tvname.setText(hero.getName());
        holder.tvDescription.setText(hero.getDescription());
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "kamu memilih " + listHero.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
        });
        holder.itemView.setOnClickListener(v -> onItemClickCallback.onItemclicked(listHero.get(holder.getAdapterPosition())));
    }
    public interface OnItemClickCallback{
        void onItemclicked(Hero data);
    }

    @Override
    public int getItemCount() {return listHero.size();}
    class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvname, tvDescription;
        ListViewHolder (View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvname = itemView.findViewById(R.id.tv_item_name);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }
}