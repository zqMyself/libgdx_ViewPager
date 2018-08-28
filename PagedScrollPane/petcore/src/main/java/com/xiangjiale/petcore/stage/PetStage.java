package com.xiangjiale.petcore.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xiangjiale.petcore.MainGame;
import com.xiangjiale.petcore.actor.PagedScrollPane;
import com.xiangjiale.petcore.actor.TopBgActor;
import com.xiangjiale.petcore.actor.framework.ImageActor;
import com.xiangjiale.petcore.interfaces.PagedScrollListener;
import com.xiangjiale.petcore.interfaces.TabEventListener;
import com.xiangjiale.petcore.res.Res;
import com.xiangjiale.petcore.stage.base.BaseStage;


/**
 * Created by zengqiang on 2018/7/31
 * 从入门到放弃
 */
public class PetStage extends BaseStage implements PagedScrollListener, TabEventListener {

    private final PagedScrollPane pagedScrollPane1;
    /** 宠物 */
    private ImageActor gameOverActor;
    private TopBgActor topBgActor;

    public PetStage(MainGame mainGame, Viewport viewport) {
        super(mainGame, viewport);


        pagedScrollPane1=new PagedScrollPane();
        pagedScrollPane1.setFlingTime(0.1f);
        pagedScrollPane1.setPageSpacing(0);//页面间隙
        pagedScrollPane1.setSize(getMainGame().getWorldWidth(),getMainGame().getWorldHeight());
        pagedScrollPane1.setPagedScrollListener(this);

        addActor(pagedScrollPane1);
        init();
    }

    private void init() {
        for ( int i=0;i<5;i++) {
            gameOverActor = new ImageActor(new TextureRegion(new Texture(Gdx.files.internal(Res.Pngs.SMALL_SOFT_PATH)),(int)getMainGame().getWorldWidth(),600));
            pagedScrollPane1.addPage(gameOverActor);
        }
        //底部导航栏
         topBgActor=new TopBgActor(getMainGame(),5);
        topBgActor.setTabEventListener(this);

        addActor(topBgActor);
    }


    @Override
    public void scrollEnd(int position) {

        topBgActor.setCurrent(position);
    }

    @Override
    public void onTabEvent(int position) {
        pagedScrollPane1.setScrollX(position);//使用自定义的滚动方法

    }
}
