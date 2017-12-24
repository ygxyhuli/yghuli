package com.ygxy.xqm.huli.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxy.xqm.huli.DanNiaoPrimaryActivity;
import com.ygxy.xqm.huli.MyApplication;
import com.ygxy.xqm.huli.PracticeItemActivity;
import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.TipsActivity;
import com.ygxy.xqm.huli.adapter.PracticeAdapter;
import com.ygxy.xqm.huli.bean.Practice;
import com.ygxy.xqm.huli.myview.MyTopbar;
import com.ygxy.xqm.huli.util.OkHttpGetUtil;
import com.ygxy.xqm.huli.vedio.VidioHDaoNiaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHWujunActivity;
import com.ygxy.xqm.huli.vedio.VidioWujunActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by XQM on 2017/3/16.
 */

public class PracticeRangeFragment extends Fragment {
    private List<Practice> practiceList = new ArrayList<>();
    private PracticeAdapter adapter;
    private Practice[] practices = {new Practice("我的天", "中", R.drawable.ic_launcher),
            new Practice("我的天", "难", R.drawable.ic_launcher),
            new Practice("我的天", "难", R.drawable.ic_launcher)};
    @BindView(R.id.practice_bar)
    MyTopbar myTopbar;

    String[] p={"1","1"};
    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.practice_range, container, false);
        ButterKnife.bind(this, view);

        try{
            File file=new File(getActivity().getFilesDir().getPath()+"/progress.txt");
            if(!file.exists()){
                file.createNewFile();
                FileWriter fileWriter=new FileWriter(file);
                BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                bufferedWriter.write("1,1");
                bufferedWriter.close();
                fileWriter.close();
            }

        }
        catch (Exception e){Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();}

        try{
            File file=new File(getActivity().getFilesDir().getPath()+"/progress.txt");
            FileReader fileReader=new FileReader(file);
            BufferedReader reader=new BufferedReader(fileReader);
            p=reader.readLine().split(",");
            reader.close();
            fileReader.close();
            //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();}

        TextView t1=(TextView)view.findViewById(R.id.practice_name_level);
        TextView t2=(TextView)view.findViewById(R.id.practice_daoniao_level0);

        if(p[0].equals("1"))t1.setText("初");
        else if(p[0].equals("2"))t1.setText("中");
        else t1.setText("高");
        if(p[1].equals("1"))t2.setText("初");
        else if(p[1].equals("2"))t2.setText("中");
        else t2.setText("高");

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myTopbar.setMainTitle("练习场");
        adapter = new PracticeAdapter(MyApplication.getContext(), practiceList);
        initPractices();

    }

    @Override
    public void onResume(){
        super.onResume();
        try{
            File file=new File(getActivity().getFilesDir().getPath()+"/progress.txt");
            FileReader fileReader=new FileReader(file);
            BufferedReader reader=new BufferedReader(fileReader);
            p=reader.readLine().split(",");
            reader.close();
            fileReader.close();
            //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();}

        TextView t1=(TextView)view.findViewById(R.id.practice_name_level);
        TextView t2=(TextView)view.findViewById(R.id.practice_daoniao_level0);

        if(p[0].equals("1"))t1.setText("初");
        else if(p[0].equals("2"))t1.setText("中");
        else t1.setText("高");
        if(p[1].equals("1"))t2.setText("初");
        else if(p[1].equals("2"))t2.setText("中");
        else t2.setText("高");
    }

    private void initPractices() {
        practiceList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(practices.length);
            practiceList.add(practices[index]);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.ll_practice_wujun_chu, R.id.ll_practice_daoniao_chu, R.id.practice_demo,
            R.id.practice_high_demo,})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_practice_wujun_chu:
                Intent intent = new Intent();
                if(p[0].equals("1")){
                    intent.setClass(getActivity(),PracticeItemActivity.class);
                }
                else if(p[0].equals("2")){
                    intent.setClass(getActivity(),PracticeItemActivity.class);
                    intent.putExtra("toHigherPractice",3);
                }
                else intent.setClass(getActivity(),VidioHWujunActivity.class);
                //Intent intent = new Intent(getActivity(), PracticeItemActivity.class);
                startActivity(intent);

                break;
            case R.id.ll_practice_daoniao_chu:
                Intent intent1 = new Intent();
                if(p[1].equals("1")){
                    intent1.setClass(getActivity(),DanNiaoPrimaryActivity.class);
                }
                else if(p[1].equals("2")){
                    intent1.setClass(getActivity(),DanNiaoPrimaryActivity.class);
                    intent1.putExtra("toHigherPractice",3);
                }
                else intent1.setClass(getActivity(),VidioHDaoNiaoActivity.class);
                //Intent intent1 = new Intent(getActivity(), DanNiaoPrimaryActivity.class);
                startActivity(intent1);
                break;
            case R.id.practice_demo:
                //Intent intent2 = new Intent(getActivity(), VidioHDaoNiaoActivity.class);
                //startActivity(intent2);
                Intent intent2=new Intent();
                intent2.putExtra("from","Pharmaceutical_Preparations");
                intent2.putExtra("pass",1);
                intent2.setClass(getActivity(), TipsActivity.class);
                intent2.putExtra("toHigherPractice",3);
                startActivity(intent2);
                break;
            case R.id.practice_high_demo:
                Intent intent3=new Intent();
                intent3.putExtra("from","Daoniao_Preparations");
                intent3.putExtra("pass",1);
                intent3.setClass(getActivity(), TipsActivity.class);
                intent3.putExtra("toHigherPractice",3);
                startActivity(intent3);
        }
    }
}
