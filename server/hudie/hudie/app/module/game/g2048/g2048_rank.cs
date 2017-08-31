using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using GameDb.Logic;
using GameLib.Database;
using hudie.app.info;

namespace hudie.app.module.game
{
	public partial class g2048
	{
		public void rank(HttpInfo reqinfo)
		{

            //请求数据库数据......

            string str = String.Format("select name,six_max_score from game_2048_rank order by six_max_score desc limit 20;");
             

            DbSelect<TbGame2048Rank> dbselect = new DbSelect<TbGame2048Rank>(null, str, null);
            //请求数据库数据结束......

            sql_struct sql = new sql_struct();

            sql.httpinfo = reqinfo;
            sql.cmd = dbselect;
            sql.fun = getlist_back;

            app.db_Select(sql);
		}

        private void getlist_back(sql_struct sql)
        {
            DbSelect<TbGame2048Rank> dbselect=sql.cmd as DbSelect<TbGame2048Rank>;

            res_g2048_rank res = new res_g2048_rank();

            if (dbselect.ListRecord != null)
            {
                res.list = new info_2048_rank[dbselect.ListRecord.Count];

                int count = 0;
                foreach (TbGame2048Rank theme in dbselect.ListRecord)
                {
                    res.list[count] = new info_2048_rank();

                    res.list[count].name = theme.Name;
                    res.list[count].score = theme.SixMaxScore;
                    count++;
                }
            }
            else
            {
                res.list = new info_2048_rank[0];
            }

            app.sendMsg(sql.httpinfo, res);
        }
	}
}
