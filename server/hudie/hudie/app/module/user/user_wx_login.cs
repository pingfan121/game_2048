using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using System.Net;
using Enum;
using GameLib.Util;
using GameDb.Logic;
using hudie.app.info;
using GameLib.Database;

namespace hudie.app.module
{
    public class weixin_info
    {
        public String nickName;
        public int sex;
        public String province;
        public String city;
        public String country;
        public String headimgurl;
        public String[] privilege;
        public String unionid;
    }

	public partial class user
	{
        private static String wx_app_id = "wx075681f7f4975e1a";

        private static HashSet<string> login_reqing = new HashSet<string>();

		public void wx_login(HttpInfo reqinfo)
		{
		      Log.warn("收到了微信登陆消息");

              string wx_token = reqinfo.req_params["wx_token"];

              if(login_reqing.Contains(wx_token) == true)
              {
                  app.sendErrorMsg(reqinfo, EnumMsgState.error);
                  return;
              }

              //直接去微信请求
              WebClient wb = new WebClient();

              wb.DownloadDataCompleted += weixin_back;

              wb.DownloadDataAsync(new Uri(String.Format("https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}", wx_token, wx_app_id)), reqinfo);
		}

        public void weixin_back(object sender, DownloadDataCompletedEventArgs e)
        {
              HttpInfo reqinfo=(HttpInfo)e.UserState;

            try
            {
                string ss = Encoding.UTF8.GetString(e.Result);
                weixin_info info = JSON.Decode<weixin_info>(ss);

                if(info.nickName==null)
                {
                    //请求微信数据有错误...
                    app.sendErrorMsg(reqinfo, EnumMsgState.login_wx_error);
                    return;
                }
                else
                {
                    //没有错误...

                    sql_struct sql = new sql_struct();

                    DbSelect<TbUser> select = new DbSelect<TbUser>(null, "select * from user where device_id='" + reqinfo.req_params["device_id"] + "';", null);

                    sql.httpinfo = reqinfo;
                    sql.data1 = info;
                    sql.cmd = select;
                    sql.fun = wx_login_sql_back;

                    app.db_Select(sql);
                }
            }
            catch(Exception ex)
            {
                log.error(ex);
                app.sendErrorMsg(reqinfo, EnumMsgState.login_wx_error);
                return;
            }
        }

        private void wx_login_sql_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;
            weixin_info info = sql.data1 as weixin_info;

            DbSelect<TbUser> user_select = sql.cmd as DbSelect<TbUser>;

            TbUser user;

            if(user_select.ListRecord == null || user_select.ListRecord.Count == 0)
            {
                    //没有查询到数据  插入一条
                    user = new TbUser();
                    user.Id = ObjectId.NewObjectId().ToString();
                    user.WxId = info.unionid;
                    user.Name = info.nickName;
                    user.DeviceId = reqinfo.req_params["device_id"];

                    user.Createtime = DateUtil.ToUnixTime2(DateTime.Now);

                    sql = new sql_struct();

                    sql.cmd = new DbInsert<TbUser>(null, user, null);

                    app.db_Insert(sql);

                    Log.warn("插入一条微信登陆数据..wx_id=" + info.unionid + "  昵称:" + info.nickName);

            }
            else
            {
                user = user_select.ListRecord[0];
            }

            //更新排行名字
            string sql_str = String.Format("update rank set name='{0}',set face='{1}' where device_id ='{2}';", info.nickName, info.headimgurl,reqinfo.req_params["device_id"]);

            DbSelect<TbRank> select = new DbSelect<TbRank>(null, sql_str, null);
            sql.cmd = select;
            app.db_Select(sql);

            //做一些其他事情....

            res_user_wx_login res = new res_user_wx_login();

            res.name = info.nickName;
            res.face = info.headimgurl;
            res.token = user.Id;

            app.sendMsg(reqinfo, res);
        }
	}
}
