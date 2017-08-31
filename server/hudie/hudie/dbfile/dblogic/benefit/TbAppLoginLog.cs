using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "app_login_log")] 
	 public class TbAppLoginLog:TbLogic
	 { 
		private string _id;
		[Id(Name = "id", Strategy = GenerationType.GUID)]
		public string Id{
			get{ return _id;}
			set
			{
				_id = value;
			}
		}
		private string _user_id;
		[Column(Name = "user_id")]
		public string UserId{
			get{ return _user_id;}
			set
			{
				_user_id = value;
				changedKeys.Add("UserId");
			}
		}
		private string _ip;
		[Column(Name = "ip")]
		public string Ip{
			get{ return _ip;}
			set
			{
				_ip = value;
				changedKeys.Add("Ip");
			}
		}
		private long _create_time;
		[Column(Name = "create_time")]
		public long CreateTime{
			get{ return _create_time;}
			set
			{
				_create_time = value;
				changedKeys.Add("CreateTime");
			}
		}
		public TbAppLoginLog()
		{
			UserId ="";
			Ip ="";
		}
		public TbAppLoginLog copy()
		{
			TbAppLoginLog t = new TbAppLoginLog();

			t.Id = Id;
			t.UserId = UserId;
			t.Ip = Ip;
			t.CreateTime = CreateTime;
			return t;
		}
	 } 
}    

