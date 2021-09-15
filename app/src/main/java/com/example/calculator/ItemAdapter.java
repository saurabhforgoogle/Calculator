package com.example.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<String> inputData;
    private ArrayList<String> outputData;



    public  static  class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = (TextView) itemView.findViewById(R.id.inputtext);
            textView2 = (TextView) itemView.findViewById(R.id.outputtext);

        }

        public TextView getTextView1() {
            return textView1;
        }

        public TextView getTextView2() {
            return textView2;
        }
    }

    public ItemAdapter(ArrayList<String> InputText,ArrayList<String> OutputText){
        inputData=InputText;
        outputData=OutputText;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.info_item,parent,false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView1().setText(inputData.get(position));
        holder.getTextView2().setText("="+ outputData.get(position));
    }

    @Override
    public int getItemCount() {
        return inputData.size();
    }


}
