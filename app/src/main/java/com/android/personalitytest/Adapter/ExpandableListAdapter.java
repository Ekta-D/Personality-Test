package com.android.personalitytest.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.android.personalitytest.Model.Questions;
import com.android.personalitytest.R;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<String> mCategories;
    private HashMap<String, ArrayList<Questions>> mQues;

    public ExpandableListAdapter(Context mContext, ArrayList<String> mCategories, HashMap<String, ArrayList<Questions>> mQues) {
        this.mContext = mContext;
        this.mCategories = mCategories;
        this.mQues = mQues;
    }

    @Override
    public int getGroupCount() {
        return this.mCategories.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mQues.get(this.mCategories.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.mCategories.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.mQues.get(this.mCategories.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.header_item, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.header_title);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        if (headerTitle.equals("hard_fact"))
            headerTitle = "Hard Facts";
        else if (headerTitle.equals("lifestyle"))
            headerTitle = "Lifestyle";
        else if (headerTitle.equals("introversion"))
            headerTitle = "Introversion";
        else
            headerTitle = "Passion";
        lblListHeader.setText(headerTitle);

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        ArrayList<Questions> questions = mQues.get(headerTitle);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_item, null);
        }

        RecyclerView optionsList = (RecyclerView) convertView.findViewById(R.id.options_list);
        optionsList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, true));
        OptionAdapter adapter = new OptionAdapter(mContext, questions.get(childPosition).getmOptions(),questions.get(childPosition).getmQues());
        optionsList.setAdapter(adapter);

        TextView txtListChild = (TextView) convertView.findViewById(R.id.ques_textview);
        txtListChild.setText(questions.get(childPosition).getmQues());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}

