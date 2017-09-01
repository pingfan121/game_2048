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
			class_map.Add("hudie."+"app.module.game", new app.module.game(app));
			class_map.Add("hudie."+"app.module.chat", new app.module.chat(app));
		}
		private void init_msg_map()
		{
			msg_map.Add("hudie."+"app.module.user.wx_login", ((app.module.user)class_map["hudie.app.module.user"]).wx_login);
			msg_map.Add("hudie."+"app.module.game.rank", ((app.module.game)class_map["hudie.app.module.game"]).rank);
			msg_map.Add("hudie."+"app.module.game.score", ((app.module.game)class_map["hudie.app.module.game"]).score);
			msg_map.Add("hudie."+"app.module.chat.list", ((app.module.chat)class_map["hudie.app.module.chat"]).list);
			msg_map.Add("hudie."+"app.module.chat.add", ((app.module.chat)class_map["hudie.app.module.chat"]).add);
		}
		private void init_req_map()
		{
			req_map.Add("hudie.app.module.user.wx_login",new List<string>());
			req_map.Add("hudie.app.module.game.rank",new List<string>());
			req_map.Add("hudie.app.module.game.score",new List<string>());
			req_map.Add("hudie.app.module.chat.add",new List<string>());
			req_map["hudie.app.module.user.wx_login"].Add("wx_token");
			req_map["hudie.app.module.game.rank"].Add("type");
			req_map["hudie.app.module.game.score"].Add("token");
			req_map["hudie.app.module.game.score"].Add("device");
			req_map["hudie.app.module.game.score"].Add("type");
			req_map["hudie.app.module.game.score"].Add("score");
			req_map["hudie.app.module.chat.add"].Add("content");
		}
	}
}
