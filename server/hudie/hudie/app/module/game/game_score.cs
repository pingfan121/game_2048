using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;
 using GameDb.Util;
using GameLib.Database;
using GameDb.Logic;
using GameLib.Util;
using hudie.app.info;

namespace hudie.app.module
{
	public partial class game
	{
		public void score(HttpInfo reqinfo)
		{
            //取出分数记录

            sql_struct sql = new sql_struct();

            DbSelect<TbRank> select = new DbSelect<TbRank>(null, "select * from rank where device_id='" + reqinfo.req_params["device_id"] + "';", null);

            sql.httpinfo = reqinfo;
            sql.cmd = select;
            sql.fun = score_back;

            app.db_Select(sql);
		}

        private void score_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;

            DbSelect<TbRank> select = sql.cmd as DbSelect<TbRank>;

            if(select.ListRecord == null || select.ListRecord.Count == 0)
            {
                DbSelect<TbUser> select2 = new DbSelect<TbUser>(null, "select * from user where device_id='" + reqinfo.req_params["device_id"] + "';", null);

                sql.cmd = select2;
                sql.fun = score2_back;

                app.db_Select(sql);
            }
            else
            {
                TbRank rank = select.ListRecord[0];

                if(reqinfo.req_params["type"]=="4")
                {
                    rank.FourMaxScore= int.Parse(reqinfo.req_params["score"]);
                }
                else
                {
                     rank.SixMaxScore= int.Parse(reqinfo.req_params["score"]);
                }


                DbUpdate<TbRank> dbupdate = new DbUpdate<TbRank>(null, rank, null);

                sql.httpinfo = reqinfo;
                sql.cmd = dbupdate;
                sql.fun = score3_back;

                app.db_Select(sql);

            }
        }

        private void score2_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;

            DbSelect<TbUser> tb_user = sql.cmd as DbSelect<TbUser>;

            TbRank rank = new TbRank();

            rank.Id = ObjectId.NewObjectId().ToString();

            rank.DeviceId = reqinfo.req_params["device_id"];

            if(reqinfo.req_params["type"] == "4")
            {
                rank.FourMaxScore = int.Parse(reqinfo.req_params["score"]);
                rank.SixMaxScore = 0;
            }
            else
            {
                rank.FourMaxScore = 0;
                rank.SixMaxScore = int.Parse(reqinfo.req_params["score"]);
            }



            if(tb_user.ListRecord == null || tb_user.ListRecord.Count == 0)
            {
               
            }
            else
            {
                rank.Name=tb_user.ListRecord[0].Name;
                rank.Face = tb_user.ListRecord[0].Face;
            }


            DbInsert<TbRank> dbinsert = new DbInsert<TbRank>(null, rank, null);

            sql.cmd = dbinsert;
            sql.fun = score3_back;

            app.db_Insert(sql);
        }

        private void score3_back(sql_struct sql)
        {
            HttpInfo reqinfo = sql.httpinfo;

            //做一些其他事情....

            res_game_score res = new res_game_score();

            app.sendMsg(reqinfo, res);
        }
	}
}
