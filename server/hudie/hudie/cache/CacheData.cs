using GameLib.Util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hudie.cache
{
    public class CacheData : IObject
    {
        public long lasttime;   //最后操作时间
        public Object data;    //数据

        public void OnRecycle()
        {
            lasttime = 0;
            data = null;
        }

        public void setData(Object data)
        {
            this.data = data;

            updateTime();
        }

        public Object getData()
        {
            return data;
        }

        public T getData<T>() where T:class
        {
            return data as T;
        }

        public void updateTime()
        {
            lasttime = DateUtil.NowToToUnixTime2();
        }
    }
}
