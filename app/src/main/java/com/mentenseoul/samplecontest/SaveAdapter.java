package com.mentenseoul.samplecontest;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.SaveViewHodler>{

    List<Data4> saveData = new ArrayList();
    public static class SaveViewHodler extends RecyclerView.ViewHolder{

        TextView modelText;
        TextView nameText;
        TextView rankText;
        TextView countText;


        public SaveViewHodler(@NonNull View itemView) {
            super(itemView);

            modelText = itemView.findViewById(R.id.modelText);
            nameText = itemView.findViewById(R.id.nameText);
            rankText = itemView.findViewById(R.id.rankText);
            countText = itemView.findViewById(R.id.countText);
        }
    }

    public SaveAdapter (List<Data4> saveData) {this.saveData = saveData; }


    @NonNull
    @Override
    public SaveViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.save_item, parent, false);

        SaveViewHodler vh = new SaveViewHodler(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SaveViewHodler holder, int position) {
        holder.modelText.setText(saveData.get(position).getModel());
//        holder.timeText.setText(saveData.get(position).getTime());
        holder.nameText.setText(saveData.get(position).getKinds());
        holder.rankText.setText(saveData.get(position).getGrade().toString());
        holder.countText.setText(saveData.get(position).getOrderCount().toString() + "개");
        if(saveData.get(position).getGrade().toString().equals("1")){
            holder.rankText.setTextColor(Color.parseColor("#9fe6a0"));
        } else if(saveData.get(position).getGrade().toString().equals("2")){
            holder.rankText.setTextColor(Color.parseColor("#ffe268"));
        } else if (saveData.get(position).getGrade().toString().equals("3")){
            holder.rankText.setTextColor(Color.parseColor("#ffdf91"));
        } else if (saveData.get(position).getGrade().toString().equals("4")) {
            holder.rankText.setTextColor(Color.parseColor("#8fd6e1"));
        } else {
            holder.rankText.setTextColor(Color.parseColor("#d44000"));
        }
    }

    @Override
    public int getItemCount() {
        return saveData.size();
    }

}
