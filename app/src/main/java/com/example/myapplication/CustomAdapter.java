package com.example.myapplication;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<HoaDon> hoaDons;
    private List<HoaDon> searchResults;

    public CustomAdapter(List<HoaDon> hoaDons) {
        this.hoaDons = new ArrayList<>(hoaDons);
        this.searchResults=new ArrayList<>(hoaDons);
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void search(double x){
        if(x==0){
            searchResults=new ArrayList<>(hoaDons);
            return;
        }
        searchResults.clear();
        hoaDons.forEach(h->{
            if(Double.parseDouble(h.getTongTien())>x){
                searchResults.add(h);
            }
        });

    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        HoaDon hd=searchResults.get(position);
        holder.tv1.setText(hd.getTen());
        holder.tv2.setText("Phòng: "+hd.getSoPhong());
        holder.tv3.setText(hd.getTongTien());
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int d=0;
                for(HoaDon h:hoaDons){
                    if(Double.parseDouble(h.getTongTien())>Double.parseDouble(hd.getTongTien())){
                        d++;
                    }
                }

                Toast.makeText(v.getContext(),"Bui The Cuong "+d,Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }



    @Override
    public int getItemCount() {
        return searchResults.size();
    }





    public void sort(){
        searchResults.sort(Comparator.comparing(HoaDon::getSoPhong).reversed());


    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder  {
        TextView tv1,tv2,tv3;
        LinearLayout layout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
            layout=itemView.findViewById(R.id.layout);

        }


    }
}
