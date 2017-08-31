using GameLib.Database;
using hudie.sql;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hudie.dbfile
{
    class DbRead
    {
        private IDbConnect connect;

        public ConcurrentQueue<int> temp_chats = new ConcurrentQueue<int>(); 
        public void init()
        {
            connect = DBManager.getBenefitDB();

            //启动线程
        }
    }
}
