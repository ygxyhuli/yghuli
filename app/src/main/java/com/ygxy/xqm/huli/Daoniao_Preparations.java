package com.ygxy.xqm.huli;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxy.xqm.huli.util.OkHttpPostUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 准备药物阶段
 * Created by XQM on 2017/4/24.
 */

public class Daoniao_Preparations extends AppCompatActivity implements AbsListView.MultiChoiceModeListener {
    @BindView(R.id.gridView_preparations)GridView gridView;
//    @BindView(R.id.intermediate_rl)RelativeLayout intermediate_rl;
//    @BindView(R.id.intermediate_right)LinearLayout intermediate_right;
    @BindView(R.id.text)TextView text;
    private GridView mGridView;
    private GridAdapter mGridAdapter;
    private TextView mActionText;
    private Dialog dialog;
    private AlertDialog.Builder builder;
    private static final int MENU_SELECT_ALL = 0;
    private static final int MENU_UNSELECT_ALL = MENU_SELECT_ALL + 1;
    private Map<Integer, Boolean> mSelectMap = new HashMap<Integer, Boolean>();
    private int[] mImgIds = new int[] { R.drawable.pic_bainpenjin, R.drawable.pic_bianpen,
            R.drawable.pic_biezhen,R.drawable.pic_daoniaobao,R.drawable.pic_dnmianqian,R.drawable.pic_dnmianqiu,
            R.drawable.pic_dnpingfeng,R.drawable.pic_dnqixiehe,R.drawable.pic_dnshouxiye,
            R.drawable.pic_dnxiaoduye,R.drawable.pic_dnzhiliaoche,R.drawable.pic_dnzhiliaojin}; // 定义并初始化保存图片id的数组
    private int count = 0;
    private OkHttpPostUtil okHttpPostUtil;
    private String header;
    private SharedPreferences sharedPreferences;
    private UserLoginActivity userLoginActivity;

    private int record[]=new int[12];
    private int answer[]={1,1,1,1,0,0,1,0,1,0,1,1};

    //    final String addGoldCount = "0";
    public static final String ADD_URL = "http://139.199.220.49:8080/gold/add/";//增加金币
    @OnClick(R.id.prepare_submit)void prepare_submit(){
        /*if (gridView.getCheckedItemCount() == mImgIds.length){
            builder.setMessage("大侠，恭喜你通过导尿技术初级的挑战，获得金币一枚");
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    addGold("1");
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.show();
        }else {
            builder.setMessage("大侠，你没有通过本次的挑战，希望你再接再厉");
            builder.setPositiveButton("我知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    for (i = 0; i < mGridView.getCount(); i++) {
                        mGridView.setItemChecked(i, false);
                        mSelectMap.clear();
                    }
                    dialog.dismiss();
                }
            });
            builder.setCancelable(false);
            dialog = builder.create();
            dialog.show();
        }*/

        for(int i=0;i<12;i++) {
            if (record[i] != answer[i]) {
                Intent intent = new Intent();
                intent.putExtra("from", "Daoniao_Preparations");
                intent.putExtra("pass", 0);
                intent.setClass(this, TipsActivity.class);
                startActivity(intent);
                return;
            }
        }
        addGold("1");

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
            bufferedWriter.write(p[0]+","+2);
            bufferedWriter.close();
            fileWriter.close();
        }
        catch (Exception e){Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();}

        Intent intent=new Intent();
        intent.putExtra("from","Daoniao_Preparations");
        intent.putExtra("pass",1);
        intent.setClass(this, TipsActivity.class);
        startActivity(intent);
        ((Button)findViewById(R.id.prepare_submit)).setVisibility(View.INVISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pharmaceutical_preparations);
        ButterKnife.bind(this);
        text.setText("请在所有备选物品中选出“导尿技术”所需药品");
        builder = new AlertDialog.Builder(this);
        okHttpPostUtil = new OkHttpPostUtil();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userLoginActivity = new UserLoginActivity();
        mGridView = (GridView) findViewById(R.id.gridView_preparations);
        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        mGridAdapter = new GridAdapter(this);
        mGridView.setAdapter(mGridAdapter);
        mGridView.setMultiChoiceModeListener(this);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(record[position]==0){
                    view.setAlpha(0.5f);
                    record[position]=1;
                }
                else {
                    view.setAlpha(1);
                    record[position]=0;
                }
            }
        });

    }

    /**
     * 增加金币数量
     */
    private void addGold(String addGoldCount) {
        header = userLoginActivity.returnToken(sharedPreferences);
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
            Toast.makeText(this,"添加金币失败，请重新登录",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        View v = LayoutInflater.from(this).inflate(R.layout.actionbar_layout,
                null);
        mActionText = (TextView) v.findViewById(R.id.action_text);
        mActionText.setText(formatString(mGridView.getCheckedItemCount()));
        mode.setCustomView(v);
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        // TODO Auto-generated method stub
        menu.getItem(MENU_SELECT_ALL).setEnabled(
                mGridView.getCheckedItemCount() != mGridView.getCount());
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        // TODO Auto-generated method stub

        switch (item.getItemId()) {
            case R.id.menu_select:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    mGridView.setItemChecked(i, true);
                    mSelectMap.put(i, true);
                }
                break;
            case R.id.menu_unselect:
                for (int i = 0; i < mGridView.getCount(); i++) {
                    mGridView.setItemChecked(i, false);
                    mSelectMap.clear();
                }
                break;
        }
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        // TODO Auto-generated method stub
        mGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position,
                                          long id, boolean checked) {
        // TODO Auto-generated method stub
        mActionText.setText(formatString(mGridView.getCheckedItemCount()));
        mSelectMap.put(position, checked);
        mode.invalidate();
        count = mGridView.getCheckedItemCount();
    }

    private String formatString(int count) {
        return String.format(getString(R.string.selection), count);
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent();
        intent.setClass(this,TipsActivity.class);
        intent.putExtra("from","back");
        startActivity(intent);
    }

    private class GridAdapter extends BaseAdapter {

        private Context mContext;

        public GridAdapter(Context ctx) {
            mContext = ctx;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mImgIds.length;
        }

        @Override
        public Integer getItem(int position) {
            // TODO Auto-generated method stub
            return Integer.valueOf(mImgIds[position]);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridItem item;
            if (convertView == null) {
                item = new GridItem(mContext);
                item.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.FILL_PARENT,
                        AbsListView.LayoutParams.FILL_PARENT));
            } else {
                item = (GridItem) convertView;
            }
            item.setImgResId(getItem(position));
            item.setChecked(mSelectMap.get(position) == null ? false
                    : mSelectMap.get(position));
            return item;
        }


    }
}
