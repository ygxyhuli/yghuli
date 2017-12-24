package com.ygxy.xqm.huli;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxy.xqm.huli.fragment.DaoniaoPrimaryRangeFragment;
import com.ygxy.xqm.huli.util.OkHttpGetUtil;
import com.ygxy.xqm.huli.util.OkHttpPostUtil;
import com.ygxy.xqm.huli.vedio.VideoHn2WujunActivity;
import com.ygxy.xqm.huli.vedio.VideoHn3WujunActivity;
import com.ygxy.xqm.huli.vedio.VideoHn4WujunActivity;
import com.ygxy.xqm.huli.vedio.VidioHDaoNiaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHWujunActivity;
import com.ygxy.xqm.huli.vedio.VidioHn1DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn2DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn3DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn4DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn5DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn6DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn7DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHn8DaoniaoActivity;
import com.ygxy.xqm.huli.vedio.VidioHnWujunActivity;
import com.ygxy.xqm.huli.vedio.VidioWujunActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TipsActivity extends Activity {

    Intent intent;
    TextView tvShow;
    Button btnNext;
    Button btnCancel;
    boolean toNewActivity=true;
    int myGold;

    private OkHttpPostUtil postUtil;

    LinkedList<ImageView> imgList=new LinkedList<>();

    private OkHttpPostUtil okHttpPostUtil;
    private String header;
    private SharedPreferences sharedPreferences;
    private UserLoginActivity userLoginActivity;

    public static final String REDUCE_URL = "http://139.199.220.49:8080/gold/reduce/";//减少金币
    public static final String ADD_URL = "http://139.199.220.49:8080/gold/add/";//增加金币

    ImageView bg;

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

        okHttpPostUtil = new OkHttpPostUtil();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLoginActivity = new UserLoginActivity();

        bg=(ImageView)findViewById(R.id.imgTouch);

        Deliver();

        //Toast.makeText(this,getIntent().getIntExtra("failedNum",0)+"",Toast.LENGTH_SHORT).show();
    }

    private void Deliver(){
        intent=new Intent();

        String lastActivity=getIntent().getStringExtra("from");

        if(lastActivity.equals("PracticePrimaryRangeFragment")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                intent.putExtra("toHigherPractice",1);  /**返回到排序页面**/
                //tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,是否直接进入中级场？");
                tvShow.setText("点击下一步继续");
                btnNext.setText("下一步");
                //btnCancel.setText("暂不");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else if(getIntent().getIntExtra("toHigherPractice",0)>=1){
                switch(getIntent().getIntExtra("toHigherPractice",0)){
                    case 1:intent.setClass(this,WujunAssessActivity.class);break;
                    case 2:intent.setClass(this,Pharmaceutical_Preparations.class);
                        intent.putExtra("field",0);break;
                    case 3:intent.setClass(this,VidioWujunActivity.class);
                        String url = OkHttpGetUtil.vedioW1Url;
                        String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title = "擦车顺序";
                        intent.putExtra("vedioUrl",url);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("title",title);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWCE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWCR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("field",0);
                        intent.putExtra("problem","操作前擦净治疗盘治疗台治疗车的正确顺序是？");
                        break;
                    case 4:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url1 = OkHttpGetUtil.vedioW2Url;
                        String imageUrl1 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title1 = "拿取无菌持物钳";
                        intent.putExtra("vedioUrl",url1);
                        intent.putExtra("imageUrl",imageUrl1);
                        intent.putExtra("title","拿取无菌持物钳");
                        intent.putExtra("title1","打开无菌治疗巾手法");
                        intent.putExtra("title2","铺盘");
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWTE1Image);
                        intent.putExtra("imageRUrl1",OkHttpGetUtil.vedioWDE1Image);
                        intent.putExtra("imageRUrl2",OkHttpGetUtil.vedioWPE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWTR1Image);
                        intent.putExtra("imageLUrl1",OkHttpGetUtil.vedioWDR1Image);
                        intent.putExtra("imageLUrl2",OkHttpGetUtil.vedioWPR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("answer1","0");
                        intent.putExtra("answer2","0");
                        intent.putExtra("problemsNum",3);
                        intent.putExtra("problem","拿取无菌持物钳的正确手法是？");
                        intent.putExtra("problem1","打开无菌治疗巾的正确手法是？");
                        intent.putExtra("problem2","下列哪个铺盘是正确的？");
                        intent.putExtra("field",0);
                        break;
                    case 5:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url2 = OkHttpGetUtil.vedioW3Url;
                        String imageUrl2 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title2 = "打开无菌盅";
                        intent.putExtra("vedioUrl",url2);
                        intent.putExtra("imageUrl",imageUrl2);
                        intent.putExtra("title",title2);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWKE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWKR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("problem","打开无菌盅的正确方法是？");
                        intent.putExtra("field",0);
                        break;
                    case 6:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url3 = OkHttpGetUtil.vedioW4Url;
                        String imageUrl3 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title3 = "备无菌溶液";
                        intent.putExtra("vedioUrl",url3);
                        intent.putExtra("imageUrl",imageUrl3);
                        intent.putExtra("title","倒无菌溶液");
                        intent.putExtra("title1","封盘");
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWYE1Image);
                        intent.putExtra("imageLUrl1",OkHttpGetUtil.vedioWFE1Image);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWYR1Image);
                        intent.putExtra("imageRUrl1",OkHttpGetUtil.vedioWFR1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("answer1","1");
                        intent.putExtra("problemsNum",2);
                        intent.putExtra("problem","该如何倒取无菌溶液？");
                        intent.putExtra("problem1","封盘方式正确的是？");
                        intent.putExtra("field",0);
                        break;
                    case 7:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url4 = OkHttpGetUtil.vedioW5Url;
                        String imageUrl4 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title4 = "穿无菌手套";
                        intent.putExtra("vedioUrl",url4);
                        intent.putExtra("imageUrl",imageUrl4);
                        intent.putExtra("title",title4);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWTaE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWTa1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("problem","下列穿戴无菌手套的方法中错误的是");
                        intent.putExtra("field",0);
                        break;
                    case 8:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url5 = OkHttpGetUtil.vedioW6Url;
                        String imageUrl5 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title5 = "整理并记录";
                        intent.putExtra("vedioUrl",url5);
                        intent.putExtra("imageUrl",imageUrl5);
                        intent.putExtra("title",title5);
                        intent.putExtra("imageEUrl","");
                        intent.putExtra("imageRUrl","");
                        intent.putExtra("answer","1");
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("open",1);
                        intent.putExtra("problem"," ");
                        intent.putExtra("field",0);
                        break;
                    default:break;
                }
                intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0)+1);
                tvShow.setText("大侠，请按提示点击");
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setVisibility(View.VISIBLE);
                bg.setOnClickListener(new View.OnClickListener() {
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
                tvShow.setText("少侠，你没有通过本次的挑战，希望你再接再厉,点击下一步返回主页");
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }

        else if(lastActivity.equals("Pharmaceutical_Preparations")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,PracticeItemActivity.class);
                intent.putExtra("toHigherPractice",3);  /**返回到排序页面**/
                tvShow.setText("大侠，恭喜你通过无菌技术初级的挑战，获得金币一枚,点击下一步进入中级场");
                //btnNext.setText("好的");
                //btnCancel.setText("暂不");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步返回主页");
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }

        else if(lastActivity.equals("VidioWujunActivity")){
            if(getIntent().getIntExtra("field",0)==0){
                if(getIntent().getIntExtra("passAll",0)==1){
                    intent.setClass(this,VidioHWujunActivity.class);
                    if(getIntent().getIntExtra("failedNum",0)==0){
                        tvShow.setText("大侠，恭喜你一次通过无菌技术中级的挑战，获得金币两枚,是否直接进入高级场？");
                        addGold("2");
                    }
                    else{
                        tvShow.setText("大侠，恭喜你通过无菌技术中级的挑战，获得金币一枚,是否直接进入高级场？");
                        addGold("1");
                    }

                    try{
                        File file=new File(getFilesDir().getPath()+"/progress.txt");
                        if(!file.exists()){
                            file.createNewFile();
                            FileWriter fileWriter=new FileWriter(file);
                            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                            bufferedWriter.write("1,1");
                            bufferedWriter.close();
                            fileWriter.close();
                        }
                        FileReader fileReader=new FileReader(file);
                        BufferedReader reader=new BufferedReader(fileReader);
                        String[] p=reader.readLine().split(",");
                        reader.close();
                        fileReader.close();
                        //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
                        FileWriter fileWriter=new FileWriter(file);
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        bufferedWriter.write(3+","+p[1]);
                        bufferedWriter.close();
                        fileWriter.close();
                    }
                    catch (Exception e){Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();}
                }
                else {
                    intent.setClass(this,PracticeItemActivity.class);
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                    intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0));
                    tvShow.setText("大侠，点击下一步继续");
                }
            }
            else if(getIntent().getIntExtra("field",0)==1){
                if(getIntent().getIntExtra("passAll",0)==1){
                    intent.setClass(this,VidioHDaoNiaoActivity.class);
                    if(getIntent().getIntExtra("failedNum",0)==0){
                        tvShow.setText("大侠，恭喜你一次通过导尿中级的挑战，获得金币两枚,是否直接进入高级场？");
                        addGold("2");
                    }
                    else {
                        tvShow.setText("大侠，恭喜你通过导尿中级的挑战，获得金币一枚,是否直接进入高级场？");
                        addGold("1");
                    }

                    try{
                        File file=new File(getFilesDir().getPath()+"/progress.txt");
                        if(!file.exists()){
                            file.createNewFile();
                            FileWriter fileWriter=new FileWriter(file);
                            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                            bufferedWriter.write("1,1");
                            bufferedWriter.close();
                            fileWriter.close();
                        }
                        FileReader fileReader=new FileReader(file);
                        BufferedReader reader=new BufferedReader(fileReader);
                        String[] p=reader.readLine().split(",");
                        reader.close();
                        fileReader.close();
                        //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
                        FileWriter fileWriter=new FileWriter(file);
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        bufferedWriter.write(p[0]+","+3);
                        bufferedWriter.close();
                        fileWriter.close();
                    }
                    catch (Exception e){Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();}
                }
                else {
                    intent.setClass(this,DanNiaoPrimaryActivity.class);
                    intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0));
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                    tvShow.setText("大侠，点击下一步继续");
                }
            }
        }

        else if(lastActivity.equals("DaoniaoPrimaryRangeFragment")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,DanNiaoPrimaryActivity.class);
                intent.putExtra("toHigherPractice",1);  /**返回到排序页面**/
                tvShow.setText("大侠，点击下一步继续");
                btnNext.setText("下一步");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else if(getIntent().getIntExtra("toHigherPractice",0)>=1){
                switch(getIntent().getIntExtra("toHigherPractice",0)){
                    case 1:intent.setClass(TipsActivity.this,DaoniaoAssessActivity.class);break;
                    case 2:intent.setClass(this,Daoniao_Preparations.class);
                        intent.putExtra("field",1);break;
                    case 3:intent.setClass(this,VidioWujunActivity.class);
                        String url = OkHttpGetUtil.getVedioD1Url;
                        String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title = "体位";
                        intent.putExtra("vedioUrl",url);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("title",title);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDtE1Image);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDtR1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("problem","导尿前该怎么安置患者体位？");
                        intent.putExtra("field",1);
                        break;
                    case 4:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url1 = OkHttpGetUtil.getVedioD2Url;
                        String imageUrl1 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title1 = "初步消毒";
                        intent.putExtra("vedioUrl",url1);
                        intent.putExtra("imageUrl",imageUrl1);
                        intent.putExtra("title",title1);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDxE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDxR1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("problem","初步消毒时哪个手法是错误的？");
                        intent.putExtra("field",1);
                        break;
                    case 5:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url2 = OkHttpGetUtil.getVedioD3Url;
                        String imageUrl2 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title2 = "开包铺巾";
                        intent.putExtra("vedioUrl",url2);
                        intent.putExtra("imageUrl",imageUrl2);
                        intent.putExtra("title","打开导尿包");
                        intent.putExtra("title1","铺洞巾");
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDoE1Image);
                        intent.putExtra("imageLUrl1",OkHttpGetUtil.vedioDdE1Image);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDoR1Image);
                        intent.putExtra("imageRUrl1",OkHttpGetUtil.vedioDdR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("answer1","1");
                        intent.putExtra("problemsNum",2);
                        intent.putExtra("problem","如何正确打开导尿包？");
                        intent.putExtra("problem1","铺洞巾的正确手法是？");
                        intent.putExtra("field",1);
                        break;
                    case 6:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url3 = OkHttpGetUtil.getVedioD4Url;
                        String imageUrl3 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title3 = "打水囊";
                        intent.putExtra("vedioUrl",url3);
                        intent.putExtra("imageUrl",imageUrl3);
                        intent.putExtra("title",title3);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDsE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDsR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("problem","打水囊需要注入多少注射用水？");
                        intent.putExtra("field",1);
                        break;
                    case 7:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url4 = OkHttpGetUtil.getVedioD5Url;
                        String imageUrl4 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title4 = "固定";
                        intent.putExtra("vedioUrl",url4);
                        intent.putExtra("imageUrl",imageUrl4);
                        intent.putExtra("title","尿管标签");
                        intent.putExtra("title1","脱无菌手套");
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDbE1Image);
                        intent.putExtra("imageLUrl1",OkHttpGetUtil.vedioDstE1Image);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDbR1Image);
                        intent.putExtra("imageRUrl1",OkHttpGetUtil.vedioDstR1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("answer1","1");
                        intent.putExtra("problemsNum",2);
                        intent.putExtra("problem","尿管插入后该做什么？");
                        intent.putExtra("problem1","脱无菌手套的正确方法是？");
                        intent.putExtra("field",1);
                        break;
                    case 8:
                        intent.setClass(this, VidioWujunActivity.class);
                        String url5 = OkHttpGetUtil.getVedioD6Url;
                        String imageUrl5 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title5 = "整理并记录";
                        intent.putExtra("vedioUrl",url5);
                        intent.putExtra("imageUrl",imageUrl5);
                        intent.putExtra("title",title5);
                        intent.putExtra("imageEUrl","");
                        intent.putExtra("imageRUrl","");
                        intent.putExtra("answer","1");
                        intent.putExtra("open",1);
                        intent.putExtra("problemsNum",1);
                        intent.putExtra("problem"," ");
                        intent.putExtra("field",1);
                        break;
                    default:break;
                }
                intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0)+1);
                tvShow.setText("大侠，请按提示点击");
                imgList.get(getIntent().getIntExtra("toHigherPractice",0)-1).setVisibility(View.VISIBLE);
                bg.setOnClickListener(new View.OnClickListener() {
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
                tvShow.setText("少侠，你没有通过本次的挑战，希望你再接再厉,点击下一步返回主页");
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }

        else if(lastActivity.equals("Daoniao_Preparations")){
            if(getIntent().getIntExtra("pass",0)==1){
                intent.setClass(TipsActivity.this,DanNiaoPrimaryActivity.class);
                intent.putExtra("toHigherPractice",3);  /**返回到排序页面**/
                tvShow.setText("大侠，恭喜你通过导尿初级的挑战，获得金币一枚,点击下一步进入进入中级场");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步返回主页");
                btnNext.setText("下一步");
                btnCancel.setVisibility(View.INVISIBLE);
            }
        }

        else if(lastActivity.equals("WujunTips")){
            toNewActivity=false;
            tvShow.setText(R.string.tv_range_first_hit);
            btnNext.setText("我知道了");
            btnCancel.setVisibility(View.INVISIBLE);
        }

        else if(lastActivity.equals("DaoniaoTips")){
            toNewActivity=false;
            tvShow.setText("少侠，请按照导尿技术的操作步骤进行排序，点击步骤添加到方框里对应的位置");
            btnNext.setText("我知道了");
            btnCancel.setVisibility(View.INVISIBLE);
        }

        else if(lastActivity.equals("back")){
            intent.setClass(TipsActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            tvShow.setText("少侠，是否退出练习？");
            btnNext.setText("是的");
            btnCancel.setText("取消");
        }

        else if(lastActivity.equals("VidioHn8DaoniaoActivity")){
            intent.setClass(TipsActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(getIntent().getIntExtra("failedNum",0)==0){
                tvShow.setText("大侠，恭喜你一次通过导尿技术高级的挑战，获得金币三枚！");
                addGold("3");
            }
            else{
                tvShow.setText("大侠，恭喜你通过导尿技术高级的挑战，获得金币一枚！");
                addGold("1");
            }
            btnNext.setText("好的");
            //btnCancel.setVisibility(View.INVISIBLE);
        }

        else if(lastActivity.equals("VideoHn4WujunActivity")){
            intent.setClass(TipsActivity.this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            if(getIntent().getIntExtra("failedNum",0)==0){
                tvShow.setText("大侠，恭喜你一次通过无菌技术高级的挑战，获得金币三枚！");
                addGold("3");
            }
            else{
                tvShow.setText("大侠，恭喜你通过无菌技术高级的挑战，获得金币一枚！");
                addGold("1");
            }
            btnNext.setText("好的");
            btnCancel.setVisibility(View.INVISIBLE);
        }

        else if(lastActivity.equals("VideoHigher")){
            intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
            switch (getIntent().getIntExtra("n",0)){
                case 0:intent.setClass(this,VidioHnWujunActivity.class);break;
                case 1:intent.setClass(this,VideoHn2WujunActivity.class);break;
                case 2:intent.setClass(this,VideoHn3WujunActivity.class);break;
                case 3:intent.setClass(this,VideoHn4WujunActivity.class);break;
                case 4:intent.setClass(this,VidioHDaoNiaoActivity.class);break;
                case 5:intent.setClass(this,VidioHn1DaoniaoActivity.class);break;
                case 6:intent.setClass(this,VidioHn2DaoniaoActivity.class);break;
                case 7:intent.setClass(this,VidioHn3DaoniaoActivity.class);break;
                case 8:intent.setClass(this,VidioHn4DaoniaoActivity.class);break;
                case 9:intent.setClass(this,VidioHn5DaoniaoActivity.class);break;
                case 10:intent.setClass(this,VidioHn6DaoniaoActivity.class);break;
                case 11:intent.setClass(this,VidioHn7DaoniaoActivity.class);break;
                case 12:intent.setClass(this,VidioHn8DaoniaoActivity.class);break;
            }
            tvShow.setText("是否进入下一题？");
            btnNext.setText("好的");
            btnCancel.setText("再看一下");
        }

        /**
         * 答题错误处理
         */
        else if(lastActivity.equals("failed")){
            String s="";
            try{
                if(!getIntent().getStringExtra("answer").isEmpty())s="正确答案是"+getIntent().getStringExtra("answer")+",";
            }
            catch (Exception e){}
            if(getIntent().getIntExtra("failedNum",0)==2){
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,"+s+"是否花费一枚金币留在本场？否则将从初级场开始");
                btnNext.setText("是");
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        postHeaderToBackgound();
                    }
                });
                btnCancel.setText("否");
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent);
                        try{
                            File file=new File(getFilesDir().getPath()+"/progress.txt");
                            if(!file.exists()){
                                file.createNewFile();
                            }
                            FileReader fileReader=new FileReader(file);
                            BufferedReader reader=new BufferedReader(fileReader);
                            String[] p=reader.readLine().split(",");
                            reader.close();
                            fileReader.close();
                            //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
                            FileWriter fileWriter=new FileWriter(file);
                            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                            if(getIntent().getIntExtra("field",0)==1)bufferedWriter.write(p[0]+","+1);
                            else bufferedWriter.write(1+","+p[1]);
                            bufferedWriter.close();
                            fileWriter.close();
                            //Toast.makeText(TipsActivity.this,"返回",Toast.LENGTH_SHORT).show();
                        }
                        catch (Exception e){Toast.makeText(TipsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();}
                    }
                });

            }
            else if(getIntent().getIntExtra("failedNum",0)==1){
                String answer="";
                try{
                    if(!getIntent().getStringExtra("answer").isEmpty())s="正确答案是："+getIntent().getStringExtra("answer")+"，";
                }
                catch (Exception e){}
                tvShow.setText("大侠，你没有通过本次的挑战，"+s+"你还有一次机会，点击下一步重新开始");
                switch (getIntent().getIntExtra("failedFrom",0)){
                    case 1:intent.setClass(this,VidioWujunActivity.class);
                        String url = OkHttpGetUtil.vedioW1Url;
                        String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title = "擦车顺序";
                        intent.putExtra("vedioUrl",url);
                        intent.putExtra("imageUrl",imageUrl);
                        intent.putExtra("title",title);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWCE1Image);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWCR1Image);
                        intent.putExtra("answer","1");
                        intent.putExtra("field",0);
                        intent.putExtra("problem","操作前擦净治疗盘治疗台治疗车的正确顺序是？");
                        intent.putExtra("failedNum",1);
                        intent.putExtra("toHigherPractice",4);
                        break;
                    case 2:intent.setClass(this,VidioWujunActivity.class);
                        String url1 = OkHttpGetUtil.getVedioD1Url;
                        String imageUrl1 = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title1 = "体位";
                        intent.putExtra("vedioUrl",url1);
                        intent.putExtra("imageUrl",imageUrl1);
                        intent.putExtra("title",title1);
                        intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDtE1Image);
                        intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDtR1Image);
                        intent.putExtra("answer","0");
                        intent.putExtra("problem","导尿前该怎么安置患者体位？");
                        intent.putExtra("field",1);
                        intent.putExtra("failedNum",1);
                        intent.putExtra("toHigherPractice",4);
                        break;
                    case 3:intent.setClass(this,VidioHWujunActivity.class);
                        intent.putExtra("failedNum",1);
                        break;
                    case 4:intent.setClass(this,VidioHDaoNiaoActivity.class);
                        intent.putExtra("failedNum",1);
                        break;
                    default:break;
                }
                btnNext.setText("下一步");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            else {
                intent.setClass(TipsActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                tvShow.setText("大侠，你没有通过本次的挑战，希望你再接再厉,点击下一步回到主页");
                btnNext.setText("下一步");
                btnCancel.setVisibility(View.INVISIBLE);
            }
            /*btnNext.setText("下一步");
            btnCancel.setVisibility(View.INVISIBLE);*/
        }
    }

    @Override
    public void onBackPressed() {}

    private void addGold(String addGoldCount) {
        header = userLoginActivity.returnToken(sharedPreferences);
        if (header != null) {
            okHttpPostUtil.postHeader(ADD_URL + addGoldCount, header, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.getMessage();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String addGold = jsonObject.optString("addGold");
//                        Log.d("addGold",addGold);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Toast.makeText(this, "添加金币失败，请重新登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void reduceGold() {
        header = userLoginActivity.returnToken(sharedPreferences);
        final String reduceGoldCount = "1";
//        Log.d("header",header);
        if (header != null) {
            okHttpPostUtil.postHeader(REDUCE_URL + reduceGoldCount, header, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    /*tvShow.setText("金币不足，将返回主页");
                    btnCancel.setText("好的");
                    btnNext.setVisibility(View.INVISIBLE);
                    try{
                        File file=new File(getFilesDir().getPath()+"/progress.txt");
                        if(!file.exists()){
                            file.createNewFile();
                        }
                        FileReader fileReader=new FileReader(file);
                        BufferedReader reader=new BufferedReader(fileReader);
                        String[] p=reader.readLine().split(",");
                        reader.close();
                        fileReader.close();
                        //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
                        FileWriter fileWriter=new FileWriter(file);
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        if(getIntent().getIntExtra("field",0)==1)bufferedWriter.write(p[0]+","+1);
                        else bufferedWriter.write(1+","+p[1]);
                        bufferedWriter.close();
                        fileWriter.close();
                        Toast.makeText(TipsActivity.this,"返回",Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception ex){Toast.makeText(TipsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();}*/
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    Log.d("result", result);
                    Log.d("请求头", REDUCE_URL + reduceGoldCount);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        String reduceGold = jsonObject.optString("reduceGold");
                        //Toast.makeText(TipsActivity.this,result,Toast.LENGTH_LONG).show();
//                    Log.d("addGold",reduceGold);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this, "操作失败，请重新登录", Toast.LENGTH_SHORT).show();
        }
    }

    private void postHeaderToBackgound() {
        header = userLoginActivity.returnToken(sharedPreferences);
        if (header != null){
            String url = "http://139.199.220.49:8080/gold/findGold";
            postUtil.postHeader(url,header, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(TipsActivity.this,"访问服务器异常，请检查网络",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        final int gold = jsonObject.optInt("金币数:");
                        final String myGold = String.valueOf(gold);
//                        Log.d("金币数目：", String.valueOf(gold));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (gold <= 0){
                                    tvShow.setText("金币不足，将返回主页");
                                    btnCancel.setText("好的");
                                    btnNext.setVisibility(View.INVISIBLE);
                                    try{
                                        File file=new File(getFilesDir().getPath()+"/progress.txt");
                                        if(!file.exists()){
                                            file.createNewFile();
                                        }
                                        FileReader fileReader=new FileReader(file);
                                        BufferedReader reader=new BufferedReader(fileReader);
                                        String[] p=reader.readLine().split(",");
                                        reader.close();
                                        fileReader.close();
                                        //Toast.makeText(this,p[0]+p[1],Toast.LENGTH_SHORT).show();
                                        FileWriter fileWriter=new FileWriter(file);
                                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                                        if(getIntent().getIntExtra("field",0)==1)bufferedWriter.write(p[0]+","+1);
                                        else bufferedWriter.write(1+","+p[1]);
                                        bufferedWriter.close();
                                        fileWriter.close();
                                        Toast.makeText(TipsActivity.this,"返回",Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception ex){Toast.makeText(TipsActivity.this,ex.getMessage(),Toast.LENGTH_SHORT).show();}
                                }else {
                                    reduceGold();
                                    switch (getIntent().getIntExtra("failedFrom",0)){
                                        case 1:intent.setClass(TipsActivity.this,VidioWujunActivity.class);
                                            String url = OkHttpGetUtil.vedioW1Url;
                                            String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                                            String title = "擦车顺序";
                                            intent.putExtra("vedioUrl",url);
                                            intent.putExtra("imageUrl",imageUrl);
                                            intent.putExtra("title",title);
                                            intent.putExtra("imageEUrl",OkHttpGetUtil.vedioWCE1Image);
                                            intent.putExtra("imageRUrl",OkHttpGetUtil.vedioWCR1Image);
                                            intent.putExtra("answer","1");
                                            intent.putExtra("field",0);
                                            intent.putExtra("problem","操作前擦净治疗盘治疗台治疗车的正确顺序是？");
                                            intent.putExtra("toHigherPractice",4);
                                            break;
                                        case 2:intent.setClass(TipsActivity.this,VidioWujunActivity.class);
                                            String url1 = OkHttpGetUtil.getVedioD1Url;
                                            String imageUrl1 = OkHttpGetUtil.getVedioW1ImageUrl;
                                            String title1 = "体位";
                                            intent.putExtra("vedioUrl",url1);
                                            intent.putExtra("imageUrl",imageUrl1);
                                            intent.putExtra("title",title1);
                                            intent.putExtra("imageRUrl",OkHttpGetUtil.vedioDtE1Image);
                                            intent.putExtra("imageEUrl",OkHttpGetUtil.vedioDtR1Image);
                                            intent.putExtra("answer","0");
                                            intent.putExtra("problem","导尿前该怎么安置患者体位？");
                                            intent.putExtra("field",1);
                                            intent.putExtra("toHigherPractice",4);
                                            break;
                                        case 3:intent.setClass(TipsActivity.this,VidioHWujunActivity.class);
                                            break;
                                        case 4:intent.setClass(TipsActivity.this,VidioHDaoNiaoActivity.class);
                                            break;
                                        default:break;
                                    }
                                    startActivity(intent);
                                }
                            }
                        });
                    } catch (JSONException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TipsActivity.this,"请重新登录",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });
        }
    }
}

