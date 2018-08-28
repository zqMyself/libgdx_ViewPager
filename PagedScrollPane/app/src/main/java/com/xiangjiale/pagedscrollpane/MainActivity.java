package com.xiangjiale.pagedscrollpane;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.xiangjiale.petcore.MainGame;


public class MainActivity extends AndroidApplication   {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new MainGame(true));
    }


}
