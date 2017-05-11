package com.ygxy.xqm.huli.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.UserLoginActivity;
import com.ygxy.xqm.huli.adapter.IntermediateAdapter;
import com.ygxy.xqm.huli.bean.Intermediate;
import com.ygxy.xqm.huli.util.OkHttpPostUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 中级练习场
 * Created by XQM on 2017/4/28.
 */
public class IntermediateFragment extends Fragment implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    @BindView(R.id.intermediate_first_right)
    VideoView videoView;
    @BindView(R.id.once_pass_listView)RecyclerView recyclerView;
    private ArrayList<Intermediate> mDatas = new ArrayList<>();
    private List<Intermediate> list = new ArrayList<>();
    private IntermediateAdapter adapter;
    private MediaController mediaController;
    private SharedPreferences.Editor editor;
    private String rightAnswer = "null01000001";
    private AlertDialog.Builder builder;
    private Dialog dialog;
    private OkHttpPostUtil okHttpPostUtil;
    private String header;
    private SharedPreferences sharedPreferences;
    private UserLoginActivity userLoginActivity;
    public static final String ADD_URL = "http://139.199.220.49:8080/gold/add/";//增加金币

    private Intermediate[] intermediates = {
            new Intermediate(false,false,"0","1","第一题：擦车顺序",R.drawable.wujun_cache_r1,R.drawable
    .wujun_cache_x1),
            new Intermediate(false,false,"0","1","第二题：拿取无菌持物钳",R.drawable.wujun_chitie_x2,R.drawable
            .wujun_chitie_r2),
            new Intermediate(false,false,"0","1","第三题：打开无菌治疗巾手法",R.drawable.wujun_openjin_r3,R.drawable
            .wujun_openjin_x3),
            new Intermediate(false,false,"0","1","第四题：铺盘",R.drawable.wujun_pupan_r4,R.drawable
            .wujun_pupan_x4),
            new Intermediate(false,false,"0","1","第五题：打开无菌盅",R.drawable.wujun_openz_r5,R.drawable
            .wujun_openz_x5),
            new Intermediate(false,false,"0","1","第六题：倒无菌溶液",R.drawable.wujun_daoye_r7,R.drawable
            .wujun_daoye_x7),
            new Intermediate(false,false,"0","1","第七题：封盘",R.drawable.wujun_daoye_r8,R.drawable
            .wujun_fengpan_x8),
            new Intermediate(false,false,"0","1","第八题：穿无菌手套",R.drawable.wujun_tao_x9,R.drawable
                    .wujun_tao_r9)
            };

    @OnClick(R.id.intermediate_btn_submit)void intermediate_btn_submit(){
        String answer = IntermediateAdapter.returnAnswer(sharedPreferences);
        if (!TextUtils.isEmpty(answer)){
            if (rightAnswer.equals(answer)){
                Log.e("an",answer);
                builder.setMessage("少侠，恭喜你通过中级训练场练习的重重考验，获得两枚" +
                        "金币，接下来进入高级训练场吧");
                builder.setCancelable(false);
                builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addGold("2");
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }else {
                builder.setMessage("少侠，很遗憾你没有通过中级训练场的考验，块进行第二次尝试吧");
                builder.setCancelable(false);
                builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
            adapter.setNull();
            editor.putString("answer",null);
            editor.apply();
        }
//        Intent intent = new Intent(getActivity(), AdvancedActivity.class);
//        startActivity(intent);
//        getActivity().finish();
    }
    @OnClick(R.id.intermediate_first_right)void intermediate_first_right(){
//        videoView.start();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.intermediate_once_pass,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initData();
    }

    /**
     * 增加金币数量
     */
    private void addGold(String addGoldCount) {
        header = userLoginActivity.returnToken(sharedPreferences);
//        Log.d("header",header);
        if (header != null){
            okHttpPostUtil.postHeader(ADD_URL+addGoldCount, header, new Callback() {
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
        }else {
            Toast.makeText(getActivity(),"添加金币失败，请重新登录",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化相关对象
     */
    private void init() {
        mDatas.add(new Intermediate(false,false,"0","1","第一题：擦车顺序",R.drawable.wujun_cache_r1,R.drawable
                .wujun_cache_x1));
        mDatas.add(new Intermediate(false,false,"0","1","第二题：拿取无菌持物钳",R.drawable.wujun_chitie_x2,R.drawable
                .wujun_chitie_r2));
        mDatas.add(new Intermediate(false,false,"0","1","第三题：打开无菌治疗巾手法",R.drawable.wujun_openjin_r3,R.drawable
                .wujun_openjin_x3));
        mDatas.add(new Intermediate(false,false,"0","1","第四题：铺盘",R.drawable.wujun_pupan_r4,R.drawable
                .wujun_pupan_x4));
        mDatas.add(new Intermediate(false,false,"0","1","第五题：打开无菌盅",R.drawable.wujun_openz_r5,R.drawable
                .wujun_openz_x5));
        mDatas.add(new Intermediate(false,false,"0","1","第六题：倒无菌溶液",R.drawable.wujun_daoye_r7,R.drawable
                .wujun_daoye_x7));
        mDatas.add(new Intermediate(false,false,"0","1","第七题：封盘",R.drawable.wujun_daoye_r8,R.drawable
                .wujun_fengpan_x8));
        mDatas.add(new Intermediate(false,false,"0","1","第八题：穿无菌手套",R.drawable.wujun_tao_r9,R.drawable
                .wujun_tao_x9));
    }
    /**
     * 初始化数据
     */
    private void initData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
        okHttpPostUtil = new OkHttpPostUtil();
        userLoginActivity = new UserLoginActivity();
        builder = new AlertDialog.Builder(getActivity());
        mediaController = new MediaController(getActivity());
        //设置控制栏
        videoView.setMediaController(mediaController);
        //获取焦点
        videoView.requestFocus();
        Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/v1.mp4");
        videoView.setVideoURI(videoUri);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        list.clear();
//        for (int i = 0;i < intermediates.length;i++){
////            Random random = new Random();
////            int index = random.nextInt(intermediates.length);
//            list.add(intermediates[i]);
//        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new IntermediateAdapter(getActivity(),mDatas);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Toast.makeText(getActivity(),"获取视频失败",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        videoView.start();
    }

//    /**
//     * 回调内部类
//     */
//    private class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//            Toast.makeText( getActivity(), "播放完成了", Toast.LENGTH_SHORT).show();
//            mp.start();
//        }
//    }
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        //横屏
    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        //竖屏
    }
}
}
