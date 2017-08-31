package pf.com.butterfly.module;

/**
 * Created by admin on 2017/5/21.
 */

//同层级互斥

public class ControlLayer
{
    public static int main_layers =-100;  //首页层

    public static int module_layers =0;  //模块层
    public static int module_view1 =1;  //模块层内的视图
    public static int module_view2 =2;  //模块层内的视图的视图
    public static int module_view3 =3;  //模块层内的视图的视图的视图
    public static int module_view4 =4;  //模块层内的视图的视图的视图的视图

    public static int module_login=99;  //登录模块
    public static int input_layers =100;  //输入层


    public static int rule_head=200;//规则模块

    public static int debug_layer=9999;//调试输出层
}
