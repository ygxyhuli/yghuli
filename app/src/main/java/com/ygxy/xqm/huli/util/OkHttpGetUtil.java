package com.ygxy.xqm.huli.util;

import java.util.LinkedList;

/**
 * get请求框架
 * Created by XQM on 2017/3/22.
 */

public class OkHttpGetUtil {
    //视频缓冲出现的图片
    public static final String getVedioW1ImageUrl = "http://onohffsqv.bkt.clouddn.com/wujun1.png";

    //无菌技术视频连接
    public static final String vedioW1Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A0%E6%93%8D%E4%BD%9C%E5%89%8D%E5%87%86%E5%A4%87.mp4";
    public static final String vedioW2Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A1%E9%93%BA%E6%B2%BB%E7%96%97%E5%B7%BE.mp4";
    public static final String vedioW3Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A2%E5%A4%87%E6%97%A0%E8%8F%8C%E5%99%A8%E6%A2%B0%E3%80%81%E5%AE%B9%E5%99%A8%E3%80%81%E6%95%B7%E6%96%99.mp4";
    public static final String vedioW4Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A3%E5%A4%87%E6%97%A0%E8%8F%8C%E6%BA%B6%E6%B6%B2.mp4";
    public static final String vedioW5Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A4%E5%A4%87%E6%93%8D%E4%BD%9C%E9%83%A8%E4%BD%8D&%E7%A9%BF%E8%84%B1%E6%97%A0%E8%8F%8C%E6%89%8B%E5%A5%97.mp4";
    public static final String vedioW6Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A5%E5%AE%A3%E6%95%99%E5%8F%8A%E6%95%B4%E7%90%86.mp4";

    //无菌中级擦车顺序
    public static final String vedioWCE1Image = "http://onohffsqv.bkt.clouddn.com/cache1_e.jpg";
    public static final String vedioWCR1Image = "http://onohffsqv.bkt.clouddn.com/cache1_r.jpg";

    public static final String vedioWTE1Image = "http://onohffsqv.bkt.clouddn.com/nawutie2_e.jpg";
    public static final String vedioWTR1Image = "http://onohffsqv.bkt.clouddn.com/nawutie2_r.jpg";

    public static final String vedioWDE1Image = "http://onohffsqv.bkt.clouddn.com/dakaijin3_e.jpg";
    public static final String vedioWDR1Image = "http://onohffsqv.bkt.clouddn.com/dakaijin3_r.jpg";

    public static final String vedioWPE1Image = "http://onohffsqv.bkt.clouddn.com/pupan4_e.jpg";
    public static final String vedioWPR1Image = "http://onohffsqv.bkt.clouddn.com/pupan4_r.jpg";

    public static final String vedioWKE1Image = "http://onohffsqv.bkt.clouddn.com/dakai_z5_e.jpg";
    public static final String vedioWKR1Image = "http://onohffsqv.bkt.clouddn.com/dakai_z5_r.jpg";

    public static final String vedioWYE1Image = "http://onohffsqv.bkt.clouddn.com/daye7_e.jpg";
    public static final String vedioWYR1Image = "http://onohffsqv.bkt.clouddn.com/daoye7_r.jpg";

    public static final String vedioWFE1Image = "http://onohffsqv.bkt.clouddn.com/fenpan8_e.jpg";
    public static final String vedioWFR1Image = "http://onohffsqv.bkt.clouddn.com/fenpan8_r.jpg";

    public static final String vedioWTaE1Image = "http://onohffsqv.bkt.clouddn.com/ctao9_e.jpg";
    public static final String vedioWTa1Image = "http://onohffsqv.bkt.clouddn.com/ctao9_r.jpg";

    //无菌技术错误视频链接
    public static final String vedioWR1Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A0.mp4";
    public static final String vedioWR2Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A1.mp4";
    public static final String vedioWR3Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A2.mp4";
    public static final String vedioWR4Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A3.mp4";
    public static final String vedioWR5Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A4.mp4";

    //导尿中级视频
    public static final String getVedioD1Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A0%E6%91%86%E4%BD%93%E4%BD%8D.mp4";
    public static final String getVedioD2Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A1%E5%88%9D%E6%AD%A5%E6%B6%88%E6%AF%92.mp4";
    public static final String getVedioD3Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A2%E5%BC%80%E5%8C%85%E9%93%BA%E5%B7%BE.mp4";
    public static final String getVedioD4Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A3%E5%86%8D%E6%AC%A1%E6%B6%88%E6%AF%92%E5%B9%B6%E6%8F%92%E7%AE%A1.mp4";
    public static final String getVedioD5Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A4%E5%9B%BA%E5%AE%9A.mp4";
    public static final String getVedioD6Url = "http://onohffsqv.bkt.clouddn.com/%E2%91%A5%E5%AE%A3%E6%95%99%E4%B8%8E%E6%95%B4%E7%90%86.mp4";

    //导尿中级照片
    public static final String vedioDtE1Image = "http://onohffsqv.bkt.clouddn.com/icon_tiwei1_error.jpg";
    public static final String vedioDtR1Image = "http://onohffsqv.bkt.clouddn.com/icon_tiwei1_right.jpg";

    public static final String vedioDxE1Image = "http://onohffsqv.bkt.clouddn.com/icon_xiaodu2_error.jpg";
    public static final String vedioDxR1Image = "http://onohffsqv.bkt.clouddn.com/icon_xiaodu2_right.jpg";

    public static final String vedioDoE1Image = "http://onohffsqv.bkt.clouddn.com/icon_open_bao3_error.jpg";
    public static final String vedioDoR1Image = "http://onohffsqv.bkt.clouddn.com/icon_open_bao3_right.jpg";

    public static final String vedioDdE1Image = "http://onohffsqv.bkt.clouddn.com/icon_dongjin4_error.jpg";
    public static final String vedioDdR1Image = "http://onohffsqv.bkt.clouddn.com/icon_dongjin4_right.jpg";

    public static final String vedioDsE1Image = "http://onohffsqv.bkt.clouddn.com/icon_shuinang6_error.jpg";
    public static final String vedioDsR1Image = "http://onohffsqv.bkt.clouddn.com/icon_shuinang6_right.jpg";

    public static final String vedioDbE1Image = "http://onohffsqv.bkt.clouddn.com/icon_biaoqian7_error.jpg";
    public static final String vedioDbR1Image = "http://onohffsqv.bkt.clouddn.com/icon_biaoqian7_right.jpg";

    public static final String vedioDstE1Image = "http://onohffsqv.bkt.clouddn.com/icon_shoutao8_error.jpg";
    public static final String vedioDstR1Image = "http://onohffsqv.bkt.clouddn.com/icon_shoutao8_right.jpg";

    //导尿高级视频
    public static final String getVedioDR1Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A0.mp4";
    public static final String getVedioDR2Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A1.mp4";
    public static final String getVedioDR3Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A2.mp4";
    public static final String getVedioDR4Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A3.mp4";
    public static final String getVedioDR5Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A4.mp4";
    public static final String getVedioDR6Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A5.mp4";
    public static final String getVedioDR7Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A6.mp4";
    public static final String getVedioDR8Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A7.mp4";
    public static final String getVedioDR9Url = "http://onohffsqv.bkt.clouddn.com/d%E2%91%A8.mp4";

//    public static void sendOkHttpRequest(String address, Callback callback){
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(address).build();
//        client.newCall(request).enqueue(callback);
//    }
}
