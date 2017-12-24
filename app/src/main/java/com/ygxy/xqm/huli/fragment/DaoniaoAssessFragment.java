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

import com.ygxy.xqm.huli.DanNiaoPrimaryActivity;
import com.ygxy.xqm.huli.Daoniao_Preparations;
import com.ygxy.xqm.huli.PracticeItemActivity;
import com.ygxy.xqm.huli.R;
import com.ygxy.xqm.huli.myview.RecordButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 导尿技术评估
 * Created by XQM on 2017/5/4.
 */

public class DaoniaoAssessFragment extends Fragment{
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
                        Intent intent = new Intent(getActivity(), DanNiaoPrimaryActivity.class);
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
        mTvcontent.setText("评估参考内容是：\n"+"        XX您好！我是您的管床护士XX，请问您叫什么名字啊？我可以看看您的手腕带吗？ XX您现在小便情况怎么样啊？（按压腹部/叩诊）因为XXX，根据医嘱要给您插根尿管到膀胱内，帮您排出尿液，减缓您腹胀腹痛的情况，可以吗？现在我想看看您的会阴部皮肤，可以吗？（保护隐私）\n" +
                "        请问您有对什么消毒液过敏吗？插尿管需要一段时间，请问您需要大便吗？\n" +
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
