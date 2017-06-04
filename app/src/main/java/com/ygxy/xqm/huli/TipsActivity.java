package com.ygxy.xqm.huli;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ygxy.xqm.huli.fragment.DaoniaoPrimaryRangeFragment;

import java.util.LinkedList;

public class TipsActivity extends Activity {

    Intent intent;
    TextView tvShow;
    Button btnNext;
    Button btnCancel;
    boolean toNewActivity=true;
    LinkedList<ImageView> imgList=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        imgList.add((ImageView)findViewById(R.id.img0));
        imgList.add((ImageView)findViewById(R.id.img1));
        imgList.add((ImageView)findViewById(R.id.img2));
        imgList.add((ImageView)findViewById(R.id.img3));
        imgList.add((ImageView)findViewById(R.id.img4));
        imgList.add((ImageView)findViewById(R.id.img5));
        imgList.add((ImageView)findViewById(R.id.img6));
        imgList.add((ImageView)findViewById(R.id.img7));

        tvShow=(TextView)findViewById(R.id.tvShow);
        btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toNewActivity)startActivity(intent);
                finish();
            }
        });
        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                intent.putExtra("toHigherPractice",1);  /**返回到排序页面**/
                tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,是否直接进入高级场？");
                btnNext.setText("好的");
                btnCancel.setText("暂不");
            }
            else if(getIntent().getIntExtra("toHigherPractice",0)>=1){
                switch(getIntent().getIntExtra("toHigherPractice",0)){
                    case 1:intent.setClass(TipsActivity.this,WujunAssessActivity.class);break;
                    case 2:intent.setClass(this,Pharmaceutical_Preparations.class);break;
                    default:break;
                }
                intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0)+1);
                tvShow.setText("大侠，请按提示点击");
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setVisibility(View.VISIBLE);
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });
                btnNext.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("少侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }

        else if(lastActivity.equals("Pharmaceutical_Preparations")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                intent.putExtra("toHigherPractice",1);  /**返回到排序页面**/
                tvShow.setText("大侠，点击下一步继续");
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }

        else if(lastActivity.equals("DaoniaoPrimaryRangeFragment")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,DanNiaoPrimaryActivity.class);
                intent.putExtra("toHigherPractice",1);  /**返回到排序页面**/
                tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,是否直接进入高级场？");
                btnNext.setText("好的");
                btnCancel.setText("暂不");
            }
            else if(getIntent().getIntExtra("toHigherPractice",0)>=1){
                switch(getIntent().getIntExtra("toHigherPractice",0)){
                    case 1:intent.setClass(TipsActivity.this,DaoniaoAssessActivity.class);break;
                    case 2:intent.setClass(this,Daoniao_Preparations.class);break;
                    default:break;
                }
                intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0)+1);
                tvShow.setText("大侠，请按提示点击");
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setVisibility(View.VISIBLE);
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                    }
                });
                btnNext.setVisibility(View.INVISIBLE);
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("少侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }

        else if(lastActivity.equals("Daoniao_Preparations")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,DaoniaoIntermediateActivity.class);
                tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,点击下一步进入进入中级场");
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步重新开始");
            }
        }

        else if(lastActivity.equals("WujunTips")){
            toNewActivity=false;
            tvShow.setText(R.string.tv_range_first_hit);
            btnNext.setText("我知道了");
        }

        else if(lastActivity.equals("DaoniaoTips")){
            toNewActivity=false;
            tvShow.setText("少侠，请按照导尿技术的操作步骤进行排序，点击步骤添加到方框里对应的位置");
            btnNext.setText("我知道了");
        }

        else if(lastActivity.equals("back")){
            intent.setClass(TipsActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            tvShow.setText("少侠，是否退出练习？");
            btnNext.setText("是的");
            btnCancel.setText("取消");
        }

    }
}
