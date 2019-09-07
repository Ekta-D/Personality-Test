package com.android.personalitytest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.personalitytest.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.ViewHolder> {

    private RadioButton lastSelected = null;
    private int lastSelectedPos = 0;
    private String mQues = "";
    ArrayList<String> optionsArrayList;
    Context context;

    public OptionAdapter(Context context, ArrayList<String> optionsArrayList, String mQues) {
        this.context = context;
        this.optionsArrayList = optionsArrayList;
        this.mQues = mQues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.option_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(optionsArrayList.get(position));
        holder.radioButton.setTag(new Integer(position));

        if (position == 0 && holder.radioButton.isChecked()) {
            lastSelected = holder.radioButton;
            lastSelectedPos = 0;
        }
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                RadioButton radioButton = (RadioButton) buttonView;
                int clickedPos = ((Integer) radioButton.getTag()).intValue();

                if (radioButton.isChecked()) {
                    if (lastSelected != null) {
                        lastSelected.setChecked(false);
                    }

                    lastSelected = radioButton;
                    lastSelectedPos = clickedPos;
                } else
                    lastSelected = null;
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public RadioButton radioButton;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.option_name);
            this.radioButton = (RadioButton) itemView.findViewById(R.id.option_selection);
        }
    }
}


