package pf.com.butterfly.module.bored;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pf.com.butterfly.R;
import pf.com.butterfly.adapter.AdapterItemData;
import pf.com.butterfly.adapter.ListViewAdapter;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.manager.DataManager;
import pf.com.butterfly.okhttp.IMsgback;
import pf.com.butterfly.infofile.res_bored_add;
import pf.com.butterfly.infofile.res_bored_getlist;
import pf.com.butterfly.okhttp.OkHttpUtils;
import pf.com.butterfly.util.HDLog;
import pf.com.butterfly.util.MyGson;

/**
 * Created by admin on 2017/3/3.
 */
public class BoredHead extends AppBaseViewControl
{
    private static BoredHead _instance;

    public static BoredHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new BoredHead();
        }

        return _instance;
    }

    public void initValue()
    {
        title="无聊";
        layout=R.layout.bored_head;
    }

    private ListViewAdapter adapter;

    private EditText et_text;

    private ListView listView;

    private SwipeRefreshLayout srl;

    @Override
    public void initControl()
    {
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnTiJiao();
            }
        });


        //下拉刷新布局

        srl=(SwipeRefreshLayout)view.findViewById(R.id.sr_update);

        //设置卷内的颜色
        srl.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //设置下拉刷新监听
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
               updateData(true);
            }
        });



        listView=(ListView)view.findViewById(R.id.listView);

        adapter=new ListViewAdapter(R.layout.bored_head_item, BoredItemView.class.getName());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Object obj=view.getTag();

                BoredHeadItemData itemdata=(BoredHeadItemData)adapter.getItem(position);
              //点击了列表
                BoredVoice.getInstance().ShowView(itemdata.id,itemdata.text);

            }
        });

        et_text=(EditText)view.findViewById(R.id.et_text);
    }

    @Override
    public void reShow()
    {
        srl.setRefreshing(false);

        updateData(false);
    }

    public void OnTiJiao()
    {

        if(DataManager.checkLogin()==false)
        {
            return ;
        }
        //检测文本是否合法

        String str=et_text.getText().toString();

        if(str.equals(""))
        {
            HDLog.Toast("请输入您的文本");
            return ;
        }

        if(str.length()>30)
        {
            HDLog.Toast("文本长度过长,请重新输入");
            return ;
        }

        Map<String,String> param=new HashMap<>();

        param.put("content",str);

        OkHttpUtils.getInstance().sendAppMsg("bored/add",param,add_result);


    }

    public void updateData(boolean flag)
    {
        if(flag==false)
        {
            if (adapter.datas.size() != 0)
                return;

            srl.setProgressViewOffset(false, 0, 30);
            srl.setRefreshing(true);
        }


       // MsgManager.sendMsg2("bored","getlist",null,update_result);
        OkHttpUtils.getInstance().sendAppMsg("bored/getlist",null,update_result);
    }

    //-----------------网络返回处理-------------------

    private IMsgback add_result=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {

            if(err!=0)
            {
                HDLog.Toast(result);
                return ;
            }
            HDLog.Toast("提交成功");

            res_bored_add res= MyGson.parseJson(result, res_bored_add.class);

            BoredHeadItemData data=new BoredHeadItemData();
            data.id=res.info.id;
            data.icon=res.info.userface;
            data.name=res.info.username;
            data.text=res.info.content;
            data.num=res.info.rownum;

            adapter.addOneItem(data);
        }
    };


    private IMsgback update_result=new IMsgback()
    {
        @Override
        public void onMsgDispose(int err, String result, Object userToken)
        {
            srl.setRefreshing(false);

            if(err!=0)
            {
                HDLog.Toast("无聊列表出现问题:"+err);
                return;
            }

            res_bored_getlist res=MyGson.parseJson(result, res_bored_getlist.class);

            List<AdapterItemData> datas=new ArrayList<AdapterItemData>();

            for(int i=0;i<res.list.length;i++)
            {
                BoredHeadItemData data=new BoredHeadItemData();
                data.id=res.list[i].id;
                data.icon=res.list[i].userface;
                data.name=res.list[i].username;
                data.text=res.list[i].content;
                data.num=res.list[i].rownum;

                datas.add(data);
            }

            adapter.datas=datas;
            adapter.notifyDataSetChanged();

            HDLog.Toast("已刷新");
        }
    };
}
