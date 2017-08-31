using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hudie.app
{
    public delegate void msg_dispose(HttpInfo info);
    public partial class MapAppMsg
    {
        public Dictionary<string,Object> class_map=new Dictionary<string,object>();

        public Dictionary<string, msg_dispose> msg_map = new Dictionary<string, msg_dispose>();

        public Dictionary<string, List<string>> req_map = new Dictionary<string, List<string>>();

        public void init(GameApp app)
        {
            init_class_map(app);

            init_msg_map();

            init_req_map();
        }

    }
}
