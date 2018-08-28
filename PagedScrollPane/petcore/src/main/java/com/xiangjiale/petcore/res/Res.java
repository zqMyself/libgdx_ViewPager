package com.xiangjiale.petcore.res;

/**
 * 资源常量
 * 
 * @xietansheng
 */
public interface Res {

    /** 固定世界宽度为 480, 高度根据实际屏幕比例换算 */
     float FIX_WORLD_WIDTH = 480;

    interface  Pets{
    }


    /**
     * 图标数据
     */
    interface  Pngs{
        String PATH_PNG="pngs";//pngs跟目录
        /**宠物选中*/
        String PET_TAB_SELECT_PATH = PATH_PNG+"/ico_pet_tab_select.png";
        /**宠物未选中*/
        String PET_TAB_UNSELECT_PATH = PATH_PNG+"/ico_pet_tab_unselect.png";
        /**小软蛋渐变*/
        String SMALL_SOFT_PATH=PATH_PNG+"/small_soft.png";

    }


    /**
     * 音效
     */
    public static interface Audios {

    }

    /**
     * Preferences 本地存储相关
     */
    public static interface Prefs {

         String PREFS_FILE_NAME = "prefs_flappy_bird";

         String KEY_BEST_SCORE = "best_score";
    }

}




















