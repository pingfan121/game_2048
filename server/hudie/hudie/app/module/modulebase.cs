using GameLib.Util;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace hudie.app.module
{
    public class ModuleBase
    {
         protected LogImplement log;

         public GameApp app;

        public ModuleBase() : base()
        {
            log = LogFactory.getLogger(this.GetType());
        }
    }
}
