using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using GameLib.Util;
using GameDb.Logic;
using GameLib.Database;
using hudie.app.info;

namespace hudie.app.module
{
	public partial class chat
	{

        long curr = 0;
        List<TbChat> lists = null;
		public void list(HttpInfo reqinfo)
		{
            //请求数据库数据......
            long vaildtime = DateUtil.ToUnixTime2(DateTime.Now.AddDays(-1).Date);

            if(vaildtime == curr && lists!=null)
            {
                sendListMsg(reqinfo);
                return;
            }

            curr = vaildtime;

            string str = String.Format("select * from chat where valid_time={0};", vaildtime);

            DbSelect<TbChat> dbselect = new DbSelect<TbChat>(null, str, null);

            sql_struct sql = new sql_struct();

            sql.httpinfo = reqinfo;
            sql.cmd = dbselect;
            sql.fun = list_back;

            app.db_Select(sql);
        }

        private void list_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;

            DbSelect<TbChat> dbselect = sql.cmd as DbSelect<TbChat>;


            if(dbselect.ListRecord != null)
            {
                lists = dbselect.ListRecord;
            }

            sendListMsg(reqinfo);
        }

        private void sendListMsg(HttpInfo reqinfo)
        {
            res_chat_list res = new res_chat_list();

            if(lists != null)
            {
                res.list = new info_chat[lists.Count];

                int index = 0;

                foreach(var temp in lists)
                {
                    res.list[index] = new info_chat();
                    res.list[index].face = temp.Face;
                    res.list[index].name = temp.Name;
                    res.list[index].content = temp.Content;
                    index++;
                }
            }
            else
            {
                res.list = new info_chat[0];
            }
          

            app.sendMsg(reqinfo, res);

        }
	}
}
