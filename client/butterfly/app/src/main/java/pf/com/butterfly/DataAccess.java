package pf.com.butterfly;

import android.content.SharedPreferences;

/**
 * Created by admin on 2017/8/27.
 */
//数据存取
public class DataAccess
{


    //存取2048游戏数据--读
    public static String get2048data(int type)
    {
        SharedPreferences sp =MainActivity.main.getSharedPreferences("game2048", MainActivity.MODE_PRIVATE);

        return sp.getString(type+"","");
    }

    //存取2048游戏数据--写
    public static void set2048data(int type,String data)
    {
        SharedPreferences sp =MainActivity.main.getSharedPreferences("game2048", MainActivity.MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();
        editor.putString(type+"", data);
        editor.commit();
    }

    //存取2048游戏数据--读
    public static String get2048maxscore(int type)
    {
        SharedPreferences sp =MainActivity.main.getSharedPreferences("game2048", MainActivity.MODE_PRIVATE);

        return sp.getString(type+"maxscore","");
    }

    //存取2048游戏最大分数
    public static void set2048maxscore(int type,int maxscore)
    {
        SharedPreferences sp =MainActivity.main.getSharedPreferences("game2048", MainActivity.MODE_PRIVATE);

        SharedPreferences.Editor editor=sp.edit();
        editor.putString(type+"maxscore", maxscore+"");
        editor.commit();
    }
}
