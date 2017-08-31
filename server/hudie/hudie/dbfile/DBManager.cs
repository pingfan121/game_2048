using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections.Concurrent;
using System.Threading;
using GameLib.Database;

namespace hudie.sql
{
   
    public class DBManager
    {
        //数据库属性
        public static string baseaddr = "localhost";
        public static string baseuser = "root";
        public static string basepass = "12345678";
        public static string baseport = "3311";

        public static IDbConnect getBenefitDB()
        {
            DbConfig config = new DbConfig();
            config.Host = baseaddr;
            config.Port = baseport;
            config.Name = "app_benefit";
            config.User = baseuser;
            config.Pwd = basepass;
            config.CharSet = "utf8";

            DbMySql connect = new DbMySql();

            connect.init(config);

            return connect;
        }

    }

}
