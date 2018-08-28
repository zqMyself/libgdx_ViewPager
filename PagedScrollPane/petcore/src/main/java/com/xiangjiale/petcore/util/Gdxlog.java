package com.xiangjiale.petcore.util;

import com.badlogic.gdx.Gdx;
import com.xiangjiale.petcore.MainGame;

/**
 * Created by zengqiang on 2018/8/7
 * 从入门到放弃
 */
public class Gdxlog {
    private static  boolean isLog= MainGame.isLog;
    public static void  log(String tag,String context){
        if(isLog) {
            Gdx.app.log(tag, context);
        }
    }
}
