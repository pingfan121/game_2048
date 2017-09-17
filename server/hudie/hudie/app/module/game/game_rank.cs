using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using GameLib.Database;
using GameDb.Logic;
using hudie.app.info;

namespace hudie.app.module
{
	public partial class game
	{
        public void rank(HttpInfo reqinfo)
        {

            //请求数据库数据......

            string type="";

            if(reqinfo.req_params["type"]=="4")
            {
                type="four_max_score";
            }
            else
            {
                 type="six_max_score";
            }

            string str = String.Format("select * from rank order by {0} desc limit 20;",type);


            DbSelect<TbRank> dbselect = new DbSelect<TbRank>(null, str, null);
            //请求数据库数据结束......

            sql_struct sql = new sql_struct();

            sql.httpinfo = reqinfo;
            sql.cmd = dbselect;
            sql.fun = rank_back;

            app.db_Select(sql);
        }

        private void rank_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;


            DbSelect<TbRank> dbselect = sql.cmd as DbSelect<TbRank>;

            res_game_rank res = new res_game_rank();

            if(dbselect.ListRecord != null)
            {
                res.list = new info_rank[dbselect.ListRecord.Count];

                int count = 0;
                foreach(TbRank theme in dbselect.ListRecord)
                {
                    res.list[count] = new info_rank();
                    res.list[count].face = theme.Face;
                    res.list[count].name = theme.Name;
                    res.list[count].rank = count+1;

                    if(reqinfo.req_params["type"] == "4")
                    {
                        res.list[count].score = theme.FourMaxScore;
                    }
                    else
                    {
                        res.list[count].score = theme.SixMaxScore;
                    }
                   
                    count++;
                }
            }
            else
            {
                res.list = new info_rank[0];
            }

            app.sendMsg(sql.httpinfo, res);
        }
	}
}
