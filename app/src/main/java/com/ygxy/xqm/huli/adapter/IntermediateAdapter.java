package com.ygxy.xqm.huli.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.bean.Intermediate;

import java.util.List;

/**
 * Created by XQM on 2017/4/28.
 */

public class IntermediateAdapter extends RecyclerView.Adapter<IntermediateAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context mContext;
    private List<Intermediate> list;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String answer = null;
    private String answer0 = null;
    private String answer1 = null;
    public IntermediateAdapter(Context context,List<Intermediate>list){
        inflater = LayoutInflater.from(context);
        mContext = context;
        this.list = list;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
        if (TextUtils.isEmpty(sharedPreferences.getString("answer",null))){
            for (Intermediate intermediate : list){
                intermediate.isSelect0 = false;
                intermediate.isSelect1 = false;
            }
            answer = null;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = inflater.inflate(R.layout.intermediate_once_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        final Intermediate intermediate = list.get(position);
        holder.textView.setText(list.get(position).getNumber());
        Glide.with(mContext).load(list.get(position).getUrl1()).into(holder.imageView1);
        Glide.with(mContext).load(list.get(position).getUrl2()).into(holder.imageView2);
        if (TextUtils.isEmpty(sharedPreferences.getString("answer",null))){
            for (Intermediate intermediate : list){
                intermediate.isSelect0 = false;
                intermediate.isSelect1 = false;
            }
        }
        if (list.get(position).isSelect0){
            holder.select0.setVisibility(View.VISIBLE);
        }else {
            holder.select0.setVisibility(View.GONE);
        }
        if (list.get(position).isSelect1){
            holder.select1.setVisibility(View.VISIBLE);

        }else {
            holder.select1.setVisibility(View.GONE);
        }
//        if (!TextUtils.isEmpty(sharedPreferences.getString("answer",null))){
//            Log.e("answer",sharedPreferences.getString("answer",null));
//        }
        editor.putString("answer",answer);
        editor.apply();
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                for (Intermediate intermediate1:list){
//                    intermediate1.isSelect = false;
//                }
                list.get(position).isSelect0 = true;
                list.get(position).isSelect1 = false;
                answer0 = holder.select0.getText().toString();
                answer = answer + answer0;
                notifyDataSetChanged();
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).isSelect1 = true;
                list.get(position).isSelect0 = false;
                answer1 = holder.select1.getText().toString();
                answer = answer + answer1;
                notifyDataSetChanged();
            }
        });
    }

    public static String returnAnswer(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("answer",null);
    }

    public void setNull(){
        answer = null;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView textView;
        ImageView imageView1, imageView2;
        Button select0,select1;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            textView = (TextView) itemView.findViewById(R.id.intermediate_first_text);
            imageView1 = (ImageView) itemView.findViewById(R.id.intermediate_first_answer);
            imageView2 = (ImageView) itemView.findViewById(R.id.intermediate_first_respondence);
            select0 = (Button) itemView.findViewById(R.id.select0);
            select1 = (Button) itemView.findViewById(R.id.select1);
        }
    }
}
