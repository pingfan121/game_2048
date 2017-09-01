using Enum;
using GameDb.Logic;
using GameDb.Util;
using GameLib.Database;
using GameLib.Util;
using hudie.app;
using hudie.net;
using hudie.sql;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace hudie
{
    public class GameApp
    {
        private IDbConnect connect;

        public BackMsg backmsg;

        private MapAppMsg msg_map = new MapAppMsg();

        public ConcurrentQueue<HttpInfo> msg_data = new ConcurrentQueue<HttpInfo>();

        public ConcurrentQueue<sql_struct> sql_read_data = new ConcurrentQueue<sql_struct>();
       
        public ConcurrentQueue<sql_struct> sql_write_data = new ConcurrentQueue<sql_struct>();

        public ConcurrentQueue<sql_struct> sql_data_back = new ConcurrentQueue<sql_struct>();


        public void init()
        {
            backmsg = new BackMsg();

            connect = DBManager.getBenefitDB();

            Thread thread = new Thread(Run);
            thread.IsBackground = true;
            thread.Start();

            //消息映射
            msg_map.init(this);

            //数据库读取线程
            Thread sql_read_thread = new Thread(sql_read);
            sql_read_thread.IsBackground = true;
            sql_read_thread.Start();

            //数据库写入线程
            Thread sql_write_thread = new Thread(sql_write);
            sql_write_thread.IsBackground = true;
            sql_write_thread.Start();
        }

        public void addMsg(HttpInfo info)
        {
            msg_data.Enqueue(info);
        }
       

        public void Run()
        {

            while (true)
            {
                HttpInfo msg = null;

                if (msg_data.TryDequeue(out msg)==true)
                {
                   
                  //  if (msg_map.class_map.ContainsKey(msg.modpath)==true)
                  //  {
                        if(msg_map.msg_map.ContainsKey(msg.funpath) == true)
                        {
                            try
                            {
                                if(msg_map.req_map.ContainsKey(msg.funpath))
                                {
                                    if(msg.req_params==null)
                                    {
                                        sendErrorMsg(msg, EnumMsgState.param_err);
                                        continue;
                                    }

                                    int flag = 0;
                                    foreach(var item in msg_map.req_map[msg.funpath])
                                    {
                                        if(msg.req_params.ContainsKey(item) == false)
                                        {
                                            flag = 1;
                                            break;
                                        }
                                    }

                                    if(flag==1)
                                    {
                                        sendErrorMsg(msg, EnumMsgState.param_err);
                                        continue;
                                    }
                                }
                                
                                msg_map.msg_map[msg.funpath](msg);
                            }
                            catch(Exception ex)
                            {
                                Log.error("处理" + msg.funpath + "出现异常");
                                Log.error(ex.Message);
                                Log.error(ex.StackTrace);

                                sendErrorMsg(msg, EnumMsgState.fun_err);
                            }
                        }
                        else
                        {
                            sendErrorMsg(msg, EnumMsgState.fun_err);
                        }
//                     }
//                     else
//                     {
//                         sendErrorMsg(msg, EnumMsgState.module_err);
//                     }
                }

                sql_struct sql = null;

                int a = 0;

                if(sql_data_back.TryDequeue(out sql) == true)
                {
                    if(sql.fun != null)
                    {
                        sql.fun(sql);
                    }
                   
                }
                else
                {
                    Thread.Sleep(20);

                    a++;
                }


                if(a > 100000)
                {
                    a = 0;

                    ((DbMySql)connect).Ping();
                }
            }
        }

        private void sql_read()
        {

            while(true)
            {
                sql_struct sql = null;

                if(sql_read_data.TryDequeue(out sql) == true)
                {
                    sql.cmd.DbConnect = this.connect;
                    sql.cmd.processRequest();

                    sql_data_back.Enqueue(sql);
                }
                else
                {
                    Thread.Sleep(20);
                }
            }
        }

        private void sql_write()
        {

            while(true)
            {
                sql_struct sql = null;

                if(sql_write_data.TryDequeue(out sql) == true)
                {
                    if(sql.cmd != null)
                    {
                        sql.cmd.DbConnect = this.connect;
                        sql.cmd.processRequest();
                    }
                    else
                    {
                        connect.executeNonQuery(sql.sqlstr, null);
                    }
                   

                    sql_data_back.Enqueue(sql);
                }
                else
                {
                    Thread.Sleep(20);
                }
            }
        }

        //返回消息
      
        public void sendMsg(HttpInfo reqinfo, Object msg)
        {
            try
            {


                HttpListenerResponse reponse = reqinfo.context.Response;
                StreamWriter writer = new StreamWriter(reponse.OutputStream);

                reponse.ContentType = "text/html; charset=utf-8";

                backmsg.error = 0;
                backmsg.msg = msg;

                byte[] bb = Encoding.UTF8.GetBytes(JSON.Encode(backmsg));

                string str = Encoding.UTF8.GetString(bb);

                Console.WriteLine(str);

                Console.WriteLine(writer.Encoding);

                writer.Write(JSON.Encode(backmsg));

                writer.Close();
                reponse.Close();
            }
            catch
            {

            }
        }
        public void sendErrorMsg(HttpInfo reqinfo,EnumMsgState err)
        {
            try
            {
                HttpListenerResponse reponse = reqinfo.context.Response;
                StreamWriter writer = new StreamWriter(reponse.OutputStream);

                backmsg.error = (int)err;
                backmsg.msg = new object();

                writer.Write(JSON.Encode(backmsg));

                writer.Close();
                reponse.Close();
            }
            catch(Exception ex)
            {
                Log.error(ex);
            }
           
        }

        //----------数据库读取---------
        public void db_Select(sql_struct sql)
        {
          //  sql.cmd.DbConnect = this.connect;
            sql_read_data.Enqueue(sql);
        }

        //数据库插入
        public void db_Insert(sql_struct sql)
        {
         //   sql.cmd.DbConnect = this.connect;
            sql_write_data.Enqueue(sql);
        }

        //数据库更新
        public void db_Update(sql_struct sql)
        {
           
          //  sql.cmd.DbConnect = this.connect;
            sql_write_data.Enqueue(sql);
        }
    }

}
