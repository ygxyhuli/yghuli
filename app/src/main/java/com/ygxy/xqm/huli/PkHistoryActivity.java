package com.ygxy.xqm.huli;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ygxy.xqm.huli.adapter.PkHistoryAdapter;
import com.ygxy.xqm.huli.bean.History;
import com.ygxy.xqm.huli.util.OkHttpPostUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by XQM on 2017/3/30.
 */

public class PkHistoryActivity extends AppCompatActivity{
    @BindView(R.id.pk_heroes_toolbar)Toolbar toolbar;
    @BindView(R.id.pk_history_refresh)SwipeRefreshLayout refreshLayout;
    @BindView(R.id.history_recyclerView)RecyclerView recyclerView;
    @BindView(R.id.pk_heroes_hit)TextView mTvNot;

    private List<History> historyList = new ArrayList<>();
    private PkHistoryAdapter historyAdapter;
    private History history;
    private UserLoginActivity loginActivity;
    private SharedPreferences sharedPreferences;
//    private History[] histories = {new History("李思思","张三","李四","100分","98分","赢"),
//            new History("李思思","张三","李四","100分","98分","赢"),
//            new History("李思思","张三","李四","100分","98分","赢")};
    private String PK_HISTORY_URL = "http://139.199.155.77:8080/NursingAppServer/GetRecord?id=";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pk_history);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mTvNot.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loginActivity = new UserLoginActivity();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        historyList.clear();
//        for (int i = 0;i<histories.length;i++){
//            Random random = new Random();
//            int index = random.nextInt(histories.length);
//            historyList.add(histories[index]);
//        }
        historyAdapter = new PkHistoryAdapter(this,getHeroesList(PK_HISTORY_URL+loginActivity.returnId(sharedPreferences)));
        recyclerView.setAdapter(historyAdapter);
        refreshLayout.setColorSchemeResources(R.color.colorPrimary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRange();
            }
        });
    }

    /**
     * 返回HeroesList集合
     * @param url
     * @return
     */
    private List<History> getHeroesList(String url){
        final List<History> heroesList = new ArrayList<>();
        OkHttpPostUtil.postPkTable(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PkHistoryActivity.this,"访问服务器出现异常",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
//                Log.e("re",result);
                if (result.equals("[]")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshLayout.setVisibility(View.GONE);
                            mTvNot.setVisibility(View.VISIBLE);
                        }
                    });
                }else {
                    try {
                        JSONArray jsonArray = new JSONArray(result);
                        JSONObject jsonObject = null;
                        for (int i = 0;i < jsonArray.length();i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            int myResult = jsonObject.getInt("Result");
                            history = new History();
                            history.setHistory_name(jsonObject.getString("Competitor"));
                            history.setHistory_grade1(jsonObject.getInt("Score1"));
                            history.setHistory_grade2(jsonObject.getInt("Score2"));
                            history.setHistory_referee1(jsonObject.getString("Referee1"));
                            history.setHistory_referee1(jsonObject.getString("Referee2"));
                            if (myResult == -1){
                                history.setHistory_result("输");
                            } else if (myResult == 0) {
                                history.setHistory_result("平");
                            }else {
                                history.setHistory_result("赢");
                            }
//                            Log.e(TAG, String.valueOf(jsonObject.getInt("Rank")));
                            historyList.add(history);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return heroesList;
    }

    private void refreshRange() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PkHistoryActivity.this,"刷新成功",Toast.LENGTH_SHORT).show();
                        historyAdapter.notifyDataSetChanged();
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}
