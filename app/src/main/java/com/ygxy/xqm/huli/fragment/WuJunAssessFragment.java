package com.ygxy.xqm.huli.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ygxy.xqm.huli.Pharmaceutical_Preparations;
import com.ygxy.xqm.huli.PracticeItemActivity;
import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.TipsActivity;
import com.ygxy.xqm.huli.myview.RecordButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 无菌技术评估
 * Created by XQM on 2017/5/4.
 */

public class WuJunAssessFragment extends Fragment{
    private Dialog dialog;
    private AlertDialog.Builder builder;
    private MediaRecorder mediaRecorder ;
    @BindView(R.id.assess_ll)RelativeLayout assess_ll;
    @BindView(R.id.assess_rl)RelativeLayout assess_rl;
    @BindView(R.id.assess_reference_content)TextView mTvcontent;
    @OnClick(R.id.start_assess)void start_assess(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.start_assess,null);
        final RecordButton mBtnStart = (RecordButton) view.findViewById(R.id.start_recording);
        Button mBtnCancel = (Button) view.findViewById(R.id.assess_cancel);
        Button mBtnStop = (Button) view.findViewById(R.id.assess_stop);
        Button mBtnFinish = (Button) view.findViewById(R.id.assess_finish);
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath();
        path += "/mmmm.amr";
        mBtnStart.setSavePath(path);
        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button ref=(Button) getActivity().findViewById(R.id.assess_reference);
                ref.setVisibility(View.VISIBLE);
                Button next=(Button)getActivity().findViewById(R.id.assess_next);
                next.setVisibility(View.VISIBLE);
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PracticeItemActivity.class);
                        intent.putExtra("toHigherPractice",2);     /**回到排序页面，下一个中级场练习部分**/
                        startActivity(intent);
                    }
                });

                dialog.dismiss();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mediaRecorder.release();
                mBtnStart.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {
                            @Override
                            public void onFinishedRecord(String audioPath) {
                                Log.i("RECORD!!!", "finished!!!!!!!!!! save to "
                                        + audioPath);
                            }
                        });
                dialog.dismiss();
            }
        });
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBtnStart.setOnFinishedRecordListener(new RecordButton.OnFinishedRecordListener() {

                            @Override
                            public void onFinishedRecord(String audioPath) {
                                Log.i("RECORD!!!", "finished!!!!!!!!!! save to "
                                        + audioPath);
                            }
                        });
            }
        });
        builder.setCancelable(false);
        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }
    @OnClick(R.id.assess_reference)void assess_reference(){
        assess_ll.setVisibility(View.VISIBLE);
        assess_rl.setVisibility(View.GONE);
        mTvcontent.setText("评估参考内容是：\n"+"        XX您好！我是您的管床护士XX，请问您叫什么名字啊？我可以看看您的手腕带吗？XX我看看你的伤口好吗？您的伤口XXX，根据医嘱今天要给您换个药。您有对什么药物/消毒液过敏吗？\n" +
                "        换药需要一点时间，请问您需要先上个洗手间吗？\n" +
                "        病房光线充足，无人打扫等，（有床帘/屏风），适宜操作。");
    }
    @OnClick(R.id.assess_close)void assess_close(){
        assess_ll.setVisibility(View.GONE);
        assess_rl.setVisibility(View.VISIBLE);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assess,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initData() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(Environment.getExternalStorageDirectory().getAbsolutePath()+"/huli.3pg");
        builder = new AlertDialog.Builder(getActivity());
        assess_ll.setVisibility(View.GONE);
        assess_rl.setVisibility(View.VISIBLE);
    }

}
