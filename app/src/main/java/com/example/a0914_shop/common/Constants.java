package com.example.a0914_shop.common;





import com.example.a0914_shop.app.MyApp;

import java.io.File;
public class Constants {
    public static final String Base_MallUrl = "http://cdwan.cn/api/";


    //网络缓存的地址
    public static final String PATH_DATA = MyApp.app.getCacheDir().getPath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/Shop";



}
