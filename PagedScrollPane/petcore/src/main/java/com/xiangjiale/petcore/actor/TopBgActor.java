package com.xiangjiale.petcore.actor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xiangjiale.petcore.MainGame;
import com.xiangjiale.petcore.actor.base.BaseGroup;
import com.xiangjiale.petcore.interfaces.TabEventListener;
import com.xiangjiale.petcore.state.SelectTabState;
import com.xiangjiale.petcore.util.Gdxlog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengqiang on 2018/7/30
 * 从入门到放弃
 */
public class TopBgActor extends BaseGroup {
    private int mSelectTab;
    private TextureRegion mNuSelectTab;

    private List<TabImageActor > imageActors=new ArrayList<>();
    private float startX;
    private TabEventListener tabEventListener;
    private int size=0;

    public TopBgActor(MainGame mainGame,int size) {
        super(mainGame);
        this.size=size;
        setSize(mainGame.getWorldWidth(),70);
        init();
        setXY();
    }

    private void init(){

        Gdxlog.log("TopBgActor","size="+size);
        for (int i=0;i<size;i++){
            createItem(i);
        }
        setPosition((getMainGame().getWorldWidth()/2)-(size*20),0);
    }
    public void setCurrent(int position) {
        Gdxlog.log("TopBgActor","position="+position);

        for (int i=0;i<imageActors.size();i++){
            imageActors.get(i).setStates(SelectTabState.NUSELECT_TAB_STATE);
        }
        imageActors.get(position).setStates(SelectTabState.SELECT_TAB_STATE);
        mSelectTab=position;

    }

    public void createItem(int i){
        SelectTabState state;
        if(i==0) {
            state= SelectTabState.SELECT_TAB_STATE;
            mSelectTab=0;
        }else  {
            state= SelectTabState.NUSELECT_TAB_STATE;
        }
        TabImageActor imageActor = new TabImageActor(state, 20, 25);
        imageActor.setCurrentTag(i);
        imageActor.setX(20*(i));
        imageActor.setY(getHeight()/2-10);
        Gdx.app.log("TopBgActor",i+"=getX="+imageActor.getX());
        addActor(imageActor);
        imageActors.add(imageActor);

        imageActor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Actor actor=event.getListenerActor();
                if(actor instanceof TabImageActor ) {
                    Gdx.app.log("TopBgActor","postion="+((TabImageActor) actor).getCurrentTag());

                    setCurrent(((TabImageActor) actor).getCurrentTag());
                    if(tabEventListener!=null){
                        tabEventListener.onTabEvent(((TabImageActor) actor).getCurrentTag());
                    }
                }
            }
        });

    }
    public void addItem(int i){
        createItem(i);
        setXY();
    }

    private void setXY() {

        setPosition(getMainGame().getWorldWidth() / 2 - (( imageActors.size() * 20 ) / 2 ),0);
    }

    public int getSelectTab() {
        return mSelectTab;
    }

    public void setTabEventListener(TabEventListener tabEventListener) {
        this.tabEventListener = tabEventListener;
    }
}
