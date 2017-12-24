package com.ygxy.xqm.huli.vedio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.TipsActivity;
import com.ygxy.xqm.huli.util.OkHttpGetUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by XQM on 2017/7/2.
 */

public class VidioHDaoNiaoActivity extends Activity {
    @BindView(R.id.videocontroller2)
    JCVideoPlayer videocontroller1;
    @BindView(R.id.videocontroller3)
    JCVideoPlayer videocontroller2;
    @BindView(R.id.tv_error_a)
    TextView tvErrorA;
    @BindView(R.id.tv_error_b)
    TextView tvErrorB;
    @BindView(R.id.tv_error_c)
    TextView tvErrorC;
    @BindView(R.id.tv_error_d)
    TextView tvErrorD;
    @BindView(R.id.tv_error_e)
    TextView tvErrorE;
    private AlertDialog.Builder builder = null;

    boolean flag1 = false,flag0 = false,flag2 = false,flag3 = false
            ,flag4 = false,flag5 = false,flag6 = false;
    private String select0 = "1",select1 = "1",select2 = "1",select3 = "1",
            select4 = "1",select5="1",select6="1";
    String answer = "0001011";
    private String select ="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_whdaoniao_activity);
        ButterKnife.bind(this);
        showVedio();

        Button btnP1=(Button)findViewById(R.id.btnP1);
        Button btnP2=(Button)findViewById(R.id.btnP2);
        Button btnP3=(Button)findViewById(R.id.btnP3);
        btnP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://onohffsqv.bkt.clouddn.com/%E5%AF%BC%E5%B0%BF%E8%A7%86%E9%A2%91%E4%B8%80.mp4";
                String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                String title = "完整版正确视频";
                videocontroller1.release();
                videocontroller1.setUp(url, imageUrl, title);
            }
        });

        btnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="http://onohffsqv.bkt.clouddn.com/%E5%AF%BC%E5%B0%BF%E8%A7%86%E9%A2%91%E4%BA%8C.mp4";
                String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                String title = "完整版正确视频";
                videocontroller1.release();
                videocontroller1.setUp(url, imageUrl, title);
            }
        });

        btnP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://onohffsqv.bkt.clouddn.com/%E5%AF%BC%E5%B0%BF%E8%A7%86%E9%A2%91%E4%B8%89.mp4";
                String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
                String title = "完整版正确视频";
                videocontroller1.release();
                videocontroller1.setUp(url, imageUrl, title);
            }
        });
    }

    private void showVedio() {
        String url = "http://onohffsqv.bkt.clouddn.com/%E5%AF%BC%E5%B0%BF%E8%A7%86%E9%A2%91%E4%B8%80.mp4";
        String url1 = OkHttpGetUtil.getVedioDR1Url;
        String imageUrl = OkHttpGetUtil.getVedioW1ImageUrl;
        String title = "完整版正确视频";
        videocontroller1.setUp(url, imageUrl, title);
        videocontroller2.setUp(url1, imageUrl, "");

        if (builder == null){
            builder = new AlertDialog.Builder(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @OnClick({R.id.tv_error_a, R.id.tv_error_b, R.id.tv_error_c, R.id.tv_error_d,
            R.id.tv_error_e, R.id.vedio_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_error_a:
                if (!flag0){
                    if (!TextUtils.isEmpty(tvErrorA.getText())){
                        tvErrorA.setTextColor(getResources().getColor(R.color.blue_6db));
                        select0 = "0";
                    }

                    flag0 = true;
                }else {
                    if (!TextUtils.isEmpty(tvErrorA.getText())){
                        tvErrorA.setTextColor(getResources().getColor(R.color.biz_audio_progress_second));
                        select0 = "1";
                    }

                    flag0 = false;
                }
                break;
            case R.id.tv_error_b:
                if (!flag1){
                    if (!TextUtils.isEmpty(tvErrorB.getText())){
                        tvErrorB.setTextColor(getResources().getColor(R.color.blue_6db));
                        select1 = "0";
                    }

                    flag1 = true;
                }else {
                    if (!TextUtils.isEmpty(tvErrorB.getText())){
                        tvErrorB.setTextColor(getResources().getColor(R.color.biz_audio_progress_second));
                        select1 = "1";
                    }

                    flag1 = false;
                }
                break;
            case R.id.tv_error_c:
                if (!flag2){
                    if (!TextUtils.isEmpty(tvErrorC.getText())){
                        tvErrorC.setTextColor(getResources().getColor(R.color.blue_6db));
                        select2 = "0";
                    }

                    flag2 = true;
                }else {
                    if (!TextUtils.isEmpty(tvErrorC.getText())){
                        tvErrorC.setTextColor(getResources().getColor(R.color.biz_audio_progress_second));
                        select2 = "1";
                    }

                    flag2 = false;
                }
                break;
            case R.id.tv_error_d:
                if (!flag3){
                    if (!TextUtils.isEmpty(tvErrorD.getText())){
                        tvErrorD.setTextColor(getResources().getColor(R.color.blue_6db));
                        select3 = "0";
                    }

                    flag3 = true;
                }else {
                    if (!TextUtils.isEmpty(tvErrorD.getText())){
                        tvErrorD.setTextColor(getResources().getColor(R.color.biz_audio_progress_second));
                        select3 = "1";
                    }

                    flag3 = false;
                }
                break;
            case R.id.tv_error_e:
                if (!flag4){
                    if (!TextUtils.isEmpty(tvErrorE.getText())){
                        tvErrorE.setTextColor(getResources().getColor(R.color.blue_6db));
                        select4 = "0";
                    }

                    flag4 = true;
                }else {
                    if (!TextUtils.isEmpty(tvErrorE.getText())){
                        tvErrorE.setTextColor(getResources().getColor(R.color.biz_audio_progress_second));
                        select4 = "1";
                    }
                    flag4 = false;
                }
                break;
            case R.id.vedio_submit:
                select = select0+select1+select2+select3+select4+select5+select6;
                if (select.equals(answer)) {
                    ((LinearLayout)findViewById(R.id.answerArea)).setVisibility(View.GONE);
                    //Toast.makeText(this, "恭喜少侠答对了", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, TipsActivity.class);
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0));
                    intent.putExtra("from","VideoHigher");
                    intent.putExtra("n",5);
                    startActivity(intent);
                    finish();
                } else {
                    //Toast.makeText(this, "很遗憾，少侠答错了", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(this, TipsActivity.class);
                    intent.putExtra("from","failed");
                    intent.putExtra("answer","ABCE");
                    intent.putExtra("failedNum",getIntent().getIntExtra("failedNum",0)+1);
                    intent.putExtra("failedFrom",3);
                    startActivity(intent);
                }
                break;
            /*case R.id.tv_expland:
                builder.setTitle("查看解析")
                        .setMessage("正确答案："+"\n"+"ABCDE")
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                break;*/
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
