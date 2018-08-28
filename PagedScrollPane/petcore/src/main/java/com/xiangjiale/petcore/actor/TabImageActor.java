package com.xiangjiale.petcore.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xiangjiale.petcore.actor.framework.ImageActor;
import com.xiangjiale.petcore.res.Res;
import com.xiangjiale.petcore.state.SelectTabState;

/**
 * Created by zengqiang on 2018/8/6
 * 从入门到放弃
 */
public class TabImageActor extends ImageActor {

    private final float width;
    private final float height;
    private TextureRegion mSelectTab;
    private TextureRegion mNuSelectTab;
    private SelectTabState states;
    private int mCurrentTag;

    public void setStates (SelectTabState states){
        this.states=states;
        if(SelectTabState.NUSELECT_TAB_STATE==states) {
            setRegion(mNuSelectTab,width, height);
        }else  {
            setRegion(mSelectTab,width, height);
        }
    }

    public TabImageActor(SelectTabState states, float width, float height) {
        this.states=states;
        this.width=width;
        this.height=height;
        mSelectTab= new TextureRegion(new Texture(Gdx.files.internal(Res.Pngs.PET_TAB_SELECT_PATH)));
        mNuSelectTab=new TextureRegion(new Texture(Gdx.files.internal(Res.Pngs.PET_TAB_UNSELECT_PATH)));
        if(SelectTabState.NUSELECT_TAB_STATE==states) {
            setRegion(mNuSelectTab,width, height);
        }else  {
            setRegion(mSelectTab,width, height);
        }
    }

    public int getCurrentTag() {
        return mCurrentTag;
    }

    public void setCurrentTag(int mCurrentTag) {
        this.mCurrentTag = mCurrentTag;
    }
}
