using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using GameLib.Database;
using GameDb.Logic;
using Enum;
using GameLib.Util;
using hudie.app.info;

namespace hudie.app.module
{
	public partial class chat
	{
		public void add(HttpInfo reqinfo)
		{
            sql_struct sql = new sql_struct();

            DbSelect<TbUser> tb_user = new DbSelect<TbUser>(null, "select * from user where device_id='" + reqinfo.req_params["device_id"] + "';", null);

            sql.cmd = tb_user;
            sql.fun = add_user_back;

            app.db_Select(sql);
        }

        private void add_user_back(sql_struct sql)
        {
             HttpInfo reqinfo = sql.httpinfo;
            DbSelect<TbUser> tb_user = sql.cmd as  DbSelect<TbUser>;

            if(tb_user.ListRecord == null || tb_user.ListRecord.Count==0)
            {
                //没人登陆
                app.sendErrorMsg(reqinfo, EnumMsgState.login_invalid);
                return;
            }
            else
            {
                TbChat chat=new TbChat();
                chat.Id=ObjectId.NewObjectId().ToString();
                chat.Name=tb_user.ListRecord[0].Name;
                chat.Face=tb_user.ListRecord[0].Face;
                chat.Content=reqinfo.req_params["content"];
                chat.CreateTime=DateUtil.NowToToUnixTime2();
                chat.ValidTime=DateUtil.ToUnixTime2(DateTime.Now.Date);

                DbInsert<TbChat> tb_chat = new DbInsert<TbChat>(null, chat, null);

                sql.cmd = tb_chat;
                sql.fun = add_insert_back;
            }
        }

        private void add_insert_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;

            res_chat_add res = new res_chat_add();

            app.sendMsg(sql.httpinfo, res);
        }
	}
}
