package com.xiangjiale.petcore;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xiangjiale.petcore.res.Res;
import com.xiangjiale.petcore.screen.GameScreen;
import com.xiangjiale.petcore.util.Gdxlog;


/**
 *
 * @xietansheng
 */
public class MainGame extends Game {

    public static final String TAG = "Pets";
    public   static boolean isLog=true;//是否启动log
    /** 世界宽度 */
    private float worldWidth;
    /** 世界高度 */
    private float worldHeight;

    /** 主游戏场景 */
    private GameScreen gameScreen;




    public MainGame(boolean isLog){
        this.isLog=isLog;
    }


    @Override
    public void create() {
        // 为了不压扁或拉长图片, 按实际屏幕比例计算世界宽高
        worldWidth = Res.FIX_WORLD_WIDTH;
        worldHeight = Gdx.graphics.getHeight() * worldWidth / Gdx.graphics.getWidth();
        Gdxlog.log(TAG, "World Size: " + worldWidth + " * " + worldHeight);
        // 创建主游戏场景
         gameScreen = new GameScreen(this);
        // 设置当前场景
        setScreen(gameScreen);

    }



    @Override
    public void render() {
        // 黑色清屏
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // 父类渲染场景
        super.render();

    }


    @Override
    public void dispose() {
        super.dispose();
        // 应用退出时需要手动销毁场景
        if (gameScreen != null) {
            gameScreen.dispose();
        }
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }


    public GameScreen getGameScreen() {
        return gameScreen;
    }



}
















