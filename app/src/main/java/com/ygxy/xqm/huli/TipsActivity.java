package com.ygxy.xqm.huli;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TipsActivity extends Activity {

    Intent intent;
    TextView tvShow;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        tvShow=(TextView)findViewById(R.id.tvShow);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });

        Deliver();
    }

    private void Deliver(){
        intent=new Intent();

        String lastActivity=getIntent().getStringExtra("from");

        if(lastActivity.equals("PracticePrimaryRangeFragment")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,WujunAssessActivity.class);
                tvShow.setText("少侠，恭喜你一次通过初级训练场排序的考验,点击下一步进入进行评估");
            }
            else {
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                tvShow.setText("少侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }

        else if(lastActivity.equals("Pharmaceutical_Preparations")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,IntermediateActivity.class);
                tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,点击下一步进入进入中级场");
            }
            else {
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }
    }
}
