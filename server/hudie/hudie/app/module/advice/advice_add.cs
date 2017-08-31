using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
using GameDb.Util;
using GameDb.Logic;
using GameLib.Util;
using GameLib.Database;
using hudie.app.info;
using hudie.cache;

namespace hudie.app.module
{
    public partial class advice
    {
        public void add(HttpInfo reqinfo)
        {
            Log.warn("收到了建议请求");

            //请求数据库数据......

            TbAppAdvice jianyi = new TbAppAdvice();
            jianyi.Id = ObjectId.NewObjectId().ToString();
            jianyi.CreateTime = DateUtil.NowToToUnixTime2();
            jianyi.Content =reqinfo.req_params["content"];

            if(reqinfo.req_params.ContainsKey("token"))
            {
                string token=reqinfo.req_params["token"];

                TbAppUser user = TokenCache.getUserData(token);

                if(user != null)
                {
                    jianyi.Userid = user.Id;
                }
            }
            else
            {
                jianyi.Userid = "";
            }
           

            sql_struct sql = new sql_struct();

            sql.cmd = new DbInsert<TbAppAdvice>(null, jianyi, null);

            app.db_Insert(sql);

            //通知前端.....

            res_advice_add res = new res_advice_add();

            res.state = 0;

            //处理结束
            app.sendMsg(reqinfo, res);

            Log.warn("建议请求处理完成");
        }
    }
}