package com.xiangjiale.petcore.actor;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.xiangjiale.petcore.interfaces.PagedScrollListener;
import com.xiangjiale.petcore.util.Gdxlog;

/**
 * Created by zengqiang on 2018/8/21
 * 从入门到放弃
 */
public class PagedScrollPane extends ScrollPane {
    private float pickAmountX;
    private float pickDifferenceX = -1;

    private boolean wasPanDragFling = false;

    private float scrollToPageSpeed = 1000;

    private float pageSpacing;

    private Table content;

    private PagedScrollListener pagedScrollListener;

    private int mCurrent=0;
    private int mTemporary=0;

    private int mScrollDistance=0;

    public PagedScrollPane () {
        super(null);
        content = new Table();
        content.defaults().space(50);
        setWidget(content);
    }

    public void setPagedScrollListener(PagedScrollListener pagedScrollListener) {
        this.pagedScrollListener = pagedScrollListener;
    }

    //    public PagedScrollPane1 (Skin skin) {
//        super(null, skin);
//        content = new Table();
//        content.defaults().space(50);
//        setWidget(content);
//    }
//
//    public PagedScrollPane1 (Skin skin, String styleName) {
//        super(null, skin, styleName);
//        content = new Table();
//        content.defaults().space(50);
//        setWidget(content);
//    }

    public PagedScrollPane (Actor widget, ScrollPaneStyle style) {
        super(null, style);
        content = new Table();
        content.defaults().space(50);
        setWidget(content);
    }

    public void addPages (Actor... pages) {
        for (Actor page : pages) {
            content.add(page).expandY().fillY();
        }
    }

    public void addPage (Actor page) {
        content.add(page).expandY().fillY();
    }

    public Actor getPage (int page) {
        if(content.getCells().size>0){
            return content.getCells().get(page).getActor();
        }
        return  null;
    }



    @Override
    public void act (float delta) {
        super.act(delta);
        if (wasPanDragFling && !isPanning() && !isDragging() && !isFlinging()) {
            wasPanDragFling = false;
            scrollToPage();
        } else {
            if (isPanning() || isDragging() || isFlinging()) {
                wasPanDragFling = true;
            }
        }
    }

    @Override
    public void setWidth (float width) {
        super.setWidth(width);
        if (content != null) {
            for (Cell cell : content.getCells()) {
                cell.width(width);
            }
            content.invalidate();
        }
    }

    public void setPageSpacing (float pageSpacing) {
        if (content != null) {
            content.defaults().space(pageSpacing);
            for (Cell cell : content.getCells()) {
                cell.space(pageSpacing);
            }
            content.invalidate();
        }
    }

    public void setScrollX(int position){
        mCurrent = position;
        mTemporary = position;
        mScrollDistance=Math.round(position * getWidth());
        setScrollX(position * getWidth());
//        if(mTemporary!=mCurrent) { //如何当前下标和临时下标不相等 表示为 页面滑动
//            Gdxlog.log("scrollToPage", "setScrollX=" + mCurrent);
//
//        }
    }

    private synchronized  void scrollToPage () {
        final float width = getWidth();
        final float scrollX = getScrollX();
        final float maxX = getMaxX();

        if (scrollX >= maxX || scrollX <= 0) return;

        Array<Actor> pages = content.getChildren();
        float pageX = 0;
        float pageWidth = 0;
        if (pages.size > 0) {
            for (Actor a : pages) {
                pageX = a.getX();
                pageWidth = a.getWidth();
                if (scrollX < (pageX + pageWidth * 0.4)) {
                    break;
                }
            }

            float maths=MathUtils.clamp(pageX - (width - pageWidth) / 2, 0, maxX);

            setScrollX(maths);

            int size=pages.size;



            if(Math.round(maths)>mScrollDistance){//右滑
                if(maths>=((mCurrent%size)*pageWidth)){
                    mScrollDistance=Math.round(maths);
                    mCurrent++;
                }
            }else  if(Math.round(maths)<mScrollDistance){//左滑
                if(maths>=(((mCurrent-1)%size)*pageWidth)){
                    mScrollDistance=Math.round(maths);
                    mCurrent--;
                }
            }


            if(mTemporary!=mCurrent){ //如何当前下标和临时下标不相等 表示为 页面滑动
                pagedScrollListener.scrollEnd(mCurrent);
                mTemporary=mCurrent;
                Gdxlog.log("scrollToPage","mCurrent="+mCurrent);

            }
        }
    }
}
