using GameDb.Logic;
using GameLib.Util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hudie.cache
{
    public class TokenCache :CacheData
    {

        public static long cleantime = DateUtil.NowToToUnixTime2();
        public static int cleaninterval = 24 * 60 * 60; //清理间隔 1天
        public static int cleansustain = 24 * 60 * 60;  //持续时间 7天

        public static Dictionary<string, CacheData> tokens = new Dictionary<string, CacheData>();


        //----------------------token的操作--------------------------
        //添加
        public static void AddToken(string token, TbAppUser user)
        {
            CacheData temp = ObjectPool.getObject<CacheData>();
            temp.setData(user);

            lock(tokens)
            {
                tokens[token] = temp;
            }
        }

        //更新
        public static void updateToken(string token)
        {
            lock(tokens)
            {
                if(tokens.ContainsKey(token))
                {
                    tokens[token].updateTime();
                }
            }
        }

        //删除
        public static void removeToken(string token)
        {
            lock(tokens)
            {
                if(tokens.ContainsKey(token))
                {
                    ObjectPool.recycle(tokens[token]);
                    tokens.Remove(token);
                }
            }

        }

        //获取
        public static TbAppUser getUserData(string token)
        {
            lock(tokens)
            {
                 bool flag = verifyToken(token);

                 if(flag == false)
                 {
                     if(tokens.ContainsKey(token))
                     {
                         ObjectPool.recycle(tokens[token]);
                         tokens.Remove(token);
                     }

                     return null;
                 }

                if(tokens.ContainsKey(token))
                {
                    return tokens[token].getData<TbAppUser>();
                }
                return null;
            }
        }

        //验证
        public static bool verifyToken(string token)
        {
            if(tokens.ContainsKey(token))
            {
                return true;
            }
            return false;
        }



        //清理
        private static List<string> list = new List<string>();
        public static void update(long currtime)
        {
            //清理token

//             if(currtime - TokenCache.cleantime > TokenCache.cleaninterval)
//             {
// 
//                 list.Clear();
// 
//                 lock(tokens)
//                 {
//                     foreach(var temp in tokens)
//                     {
//                         if(currtime - temp.Value.lasttime > TokenCache.cleansustain)
//                         {
//                             list.Add(temp.Key);
//                         }
//                     }
// 
//                     foreach(var key in list)
//                     {
//                         ObjectPool.recycle(tokens[key]);
// 
//                         tokens.Remove(key);
//                     }
// 
//                 }
//             }
        }

    }
}
