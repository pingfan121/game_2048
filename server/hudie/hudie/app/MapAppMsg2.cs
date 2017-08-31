using hudie.net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using GameServer.Define.EnumNormal;

namespace hudie.app
{
	public partial class MapAppMsg
	{
		private void init_class_map(GameApp app)
		{
			class_map.Add("hudie."+"app.module.user", new app.module.user(app));
			class_map.Add("hudie."+"app.module.advice", new app.module.advice(app));
			class_map.Add("hudie."+"app.module.bored", new app.module.bored(app));
			class_map.Add("hudie."+"app.module.game.g2048", new app.module.game.g2048(app));
		}
		private void init_msg_map()
		{
			msg_map.Add("hudie."+"app.module.user.wx_login", ((app.module.user)class_map["hudie.app.module.user"]).wx_login);
			msg_map.Add("hudie."+"app.module.advice.add", ((app.module.advice)class_map["hudie.app.module.advice"]).add);
			msg_map.Add("hudie."+"app.module.bored.add", ((app.module.bored)class_map["hudie.app.module.bored"]).add);
			msg_map.Add("hudie."+"app.module.bored.getlist", ((app.module.bored)class_map["hudie.app.module.bored"]).getlist);
			msg_map.Add("hudie."+"app.module.bored.voicelist", ((app.module.bored)class_map["hudie.app.module.bored"]).voicelist);
			msg_map.Add("hudie."+"app.module.bored.addvoice", ((app.module.bored)class_map["hudie.app.module.bored"]).addvoice);
			msg_map.Add("hudie."+"app.module.game.g2048.rank", ((app.module.game.g2048)class_map["hudie.app.module.game.g2048"]).rank);
		}
		private void init_req_map()
		{
			req_map.Add("hudie.app.module.user.wx_login",new List<string>());
			req_map.Add("hudie.app.module.advice.add",new List<string>());
			req_map.Add("hudie.app.module.bored.add",new List<string>());
			req_map.Add("hudie.app.module.bored.voicelist",new List<string>());
			req_map.Add("hudie.app.module.bored.addvoice",new List<string>());
			req_map.Add("hudie.app.module.game.g2048.rank",new List<string>());
			req_map["hudie.app.module.user.wx_login"].Add("wx_token");
			req_map["hudie.app.module.advice.add"].Add("content");
			req_map["hudie.app.module.bored.add"].Add("content");
			req_map["hudie.app.module.bored.voicelist"].Add("boredid");
			req_map["hudie.app.module.bored.addvoice"].Add("boredid");
			req_map["hudie.app.module.bored.addvoice"].Add("voiceurl");
			req_map["hudie.app.module.bored.addvoice"].Add("voicelen");
			req_map["hudie.app.module.game.g2048.rank"].Add("type");
			req_map["hudie.app.module.game.g2048.rank"].Add("num");
		}
	}
}
