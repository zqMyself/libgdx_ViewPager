package com.xiangjiale.petcore.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.xiangjiale.petcore.MainGame;
import com.xiangjiale.petcore.stage.PetStage;


/**
 * 主游戏场景
 * 
 * @xietansheng
 */
public class GameScreen extends ScreenAdapter {

    private MainGame mainGame;
    /** 主游戏舞台 */
    private PetStage petStage;

    public GameScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        init();
    }

    private void init() {
         petStage=new PetStage(getMainGame(),new FillViewport(mainGame.getWorldWidth(),mainGame.getWorldHeight()));
        // 将输入处理设置到主游戏舞台
        Gdx.input.setInputProcessor(petStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        // 更新并绘制舞台（主游戏舞台优先处理）
        if (petStage.isVisible()) {
            petStage.act();
            petStage.draw();
        }


    }

    @Override
    public void dispose() {
        super.dispose();
        // 场景销毁时, 同时销毁所有的舞台
        if (petStage != null) {
            petStage.dispose();
        }
    }
    public MainGame getMainGame() {
        return mainGame;
    }
}


























