using GameDb.Logic;
using GameLib.Database;
using hudie.net;
using hudie.sql;
using messages;
using messages.Protocols;
using System;

namespace messages
{
  public partial class MsgHandle
  {
      static public void handle_benefit_theme_req(MsgBase msg)
      {
//           benefit_head_req req = msg as benefit_head_req;
// 
//           //请求数据库数据......
// 
//         //  string str = String.Format("select * from benefit_theme limit {0},{1};", req.curr, 20);
// 
//           string str = String.Format("select * from benefit_head while over ='0';");
// 
//           DbSelect<TbBenefitHead> dbselect = new DbSelect<TbBenefitHead>(msg.db, str, null);
//           dbselect.processRequest();
// 
//           //请求数据库数据结束......
// 
// 
//           //逻辑计算.......
// 
//           //逻辑计算结束.......
// 
//           
//           //通知前端.....
// 
// 
// 
//           benefit_head_res res = new benefit_head_res();
// 
//           if (dbselect.ListRecord != null)
//           {
//               res.infos = new info_benefit_head_item[dbselect.ListRecord.Count];
// 
//               int count = 0;
//               foreach (TbBenefitHead theme in dbselect.ListRecord)
//               {
//                   res.infos[count] = new info_benefit_head_item();
// 
//                   res.infos[count].itemid = theme.Id;
//                   res.infos[count].name = theme.Name;
//                   res.infos[count].addr = theme.Addr;
//                   res.infos[count].needmoney = theme.Needmoney;
//                   res.infos[count].nowmoney = theme.Nowmoney;
//               }
//           }
//           else
//           {
//               res.infos = new info_benefit_head_item[0];
//           }

          //处理结束
        //   msg.app.sendMsg(req.context, res);

         
      }
  }
}
