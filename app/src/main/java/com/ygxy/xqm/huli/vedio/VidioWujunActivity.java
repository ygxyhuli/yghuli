package com.ygxy.xqm.huli.vedio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.TipsActivity;
import com.ygxy.xqm.huli.util.OkHttpGetUtil;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by XQM on 2017/7/2.
 */

public class VidioWujunActivity extends Activity {
    @BindView(R.id.videocontroller1)
    JCVideoPlayer videocontroller1;
    @BindView(R.id.image_video1)
    ImageView imageVideo1;
    @BindView(R.id.image_select1)
    ImageView imageSelect1;
    @BindView(R.id.image_video2)
    ImageView imageVideo2;
    @BindView(R.id.image_select2)
    ImageView imageSelect2;

    private String select = "";
    String answer = "";
    int problemsNum;
    String url;
    LinkedList<String> imgRList;
    LinkedList<String> imgLList;
    LinkedList<String> answerList;
    LinkedList<String> problemsList;
    LinkedList<String> titleList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_w_activity);
        RelativeLayout layout=(RelativeLayout)findViewById(R.id.selectArea);
        if(getIntent().getIntExtra("open",0)==1)layout.setVisibility(View.GONE);
        ButterKnife.bind(this);
        showVedio();
    }

    private void showVedio() {
        imgLList=new LinkedList<>();
        imgRList=new LinkedList<>();
        answerList=new LinkedList<>();
        problemsList=new LinkedList<>();
        titleList=new LinkedList<>();

        url = getIntent().getStringExtra("vedioUrl");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String title = getIntent().getStringExtra("title");
        titleList.add(title);
        String imageRUrl = getIntent().getStringExtra("imageRUrl");
        imgRList.add(imageRUrl);
        String imageEUrl = getIntent().getStringExtra("imageEUrl");
        imgLList.add(imageEUrl);
        String problem=getIntent().getStringExtra("problem");
        problemsList.add(problem);
        problemsNum=getIntent().getIntExtra("problemsNum",1);
        answer = getIntent().getStringExtra("answer");
        answerList.add(answer);

        for(int i=1;i<problemsNum;i++){
            imgLList.add(getIntent().getStringExtra("imageLUrl"+i));
            imgRList.add(getIntent().getStringExtra("imageRUrl"+i));
            answerList.add(getIntent().getStringExtra("answer"+i));
            problemsList.add(getIntent().getStringExtra("problem"+i));
            titleList.add(getIntent().getStringExtra("title"+i));
        }
        videocontroller1.setUp(url, imageUrl, title);
        Glide.with(this).load(imageEUrl).into(imageVideo1);
        Glide.with(this).load(imageRUrl).into(imageVideo2);
        TextView t=(TextView)findViewById(R.id.tv_problem_show);
        t.setText(problem);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @OnClick({R.id.image_video1, R.id.image_video2,R.id.vedio_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_video1:
                imageSelect1.setVisibility(View.VISIBLE);
                imageSelect2.setVisibility(View.INVISIBLE);
                select = "0";
                break;
            case R.id.image_video2:
                imageSelect2.setVisibility(View.VISIBLE);
                imageSelect1.setVisibility(View.INVISIBLE);
                select = "1";
                break;
            case R.id.vedio_submit:
                if(getIntent().getIntExtra("toHigherPractice",0)==9){
                    Intent intent=new Intent(this,TipsActivity.class);
                    intent.putExtra("passAll",1);
                    intent.putExtra("from","VidioWujunActivity");
                    intent.putExtra("field",getIntent().getIntExtra("field",0));
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                    startActivity(intent);
                    //finish();
                }
                else if (answer.equals(select)){
                    //Toast.makeText(this,"恭喜少侠答对了",Toast.LENGTH_SHORT).show();
                    if(problemsNum>1){
                        Intent intent2 = new Intent(this, VidioWujunActivity.class);
                        String url = OkHttpGetUtil.vedioW1Url;
                        String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                        String title = "无菌技术操作前准备";
                        intent2.putExtra("vedioUrl",url);
                        for (int i=2;i<problemsNum;i++){
                            intent2.putExtra("imageLUrl"+(i-1),imgLList.get(i));
                            intent2.putExtra("imageRUrl"+(i-1),imgRList.get(i));
                            intent2.putExtra("answer"+(i-1),answerList.get(i));
                            intent2.putExtra("problem"+(i-1),problemsList.get(i));
                            intent2.putExtra("title"+(i-1),titleList.get(i));
                        }
                        intent2.putExtra("imageUrl",imageUrl);
                        intent2.putExtra("title",titleList.get(1));
                        intent2.putExtra("imageEUrl",imgLList.get(1));
                        intent2.putExtra("imageRUrl",imgRList.get(1));
                        intent2.putExtra("problemsNum",problemsNum-1);
                        intent2.putExtra("answer",answerList.get(1));
                        intent2.putExtra("problem",problemsList.get(1));
                        intent2.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0));
                        intent2.putExtra("field",getIntent().getIntExtra("field",0));
                        intent2.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                        startActivity(intent2);
                        finish();
                    }
                    else {
                        Intent intent=new Intent();
                        intent.setClass(this, TipsActivity.class);
                        intent.putExtra("from","VidioWujunActivity");
                        intent.putExtra("toHigherPractice",getIntent().getIntExtra("toHigherPractice",0));
                        intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                        intent.putExtra("field",getIntent().getIntExtra("field",0));
                        startActivity(intent);
                    }
                }else {
                    //Toast.makeText(this,"很遗憾，少侠答错了",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(this, TipsActivity.class);
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0)+1);
                    intent.putExtra("from","failed");
                    if(getIntent().getIntExtra("field",0)==0) intent.putExtra("failedFrom",1);
                    else intent.putExtra("failedFrom",2);
                    intent.putExtra("field",getIntent().getIntExtra("field",0));
                    startActivity(intent);
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.setClass(this,TipsActivity.class);
        intent.putExtra("from","back");
        startActivity(intent);
    }
}
