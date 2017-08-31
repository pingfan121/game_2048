using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "bored_voice")] 
	 public class TbBoredVoice:TbLogic
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
		private string _bored_id;
		[Column(Name = "bored_id")]
		public string BoredId{
			get{ return _bored_id;}
			set
			{
				_bored_id = value;
				changedKeys.Add("BoredId");
			}
		}
		private string _useid;
		[Column(Name = "useid")]
		public string Useid{
			get{ return _useid;}
			set
			{
				_useid = value;
				changedKeys.Add("Useid");
			}
		}
		private string _user_name;
		[Column(Name = "user_name")]
		public string UserName{
			get{ return _user_name;}
			set
			{
				_user_name = value;
				changedKeys.Add("UserName");
			}
		}
		private string _user_face;
		[Column(Name = "user_face")]
		public string UserFace{
			get{ return _user_face;}
			set
			{
				_user_face = value;
				changedKeys.Add("UserFace");
			}
		}
		private int _record_len;
		[Column(Name = "record_len")]
		public int RecordLen{
			get{ return _record_len;}
			set
			{
				_record_len = value;
				changedKeys.Add("RecordLen");
			}
		}
		private string _record_url;
		[Column(Name = "record_url")]
		public string RecordUrl{
			get{ return _record_url;}
			set
			{
				_record_url = value;
				changedKeys.Add("RecordUrl");
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
		public TbBoredVoice()
		{
			BoredId ="";
			Useid ="";
			UserName ="";
			UserFace ="";
			RecordUrl ="";
		}
		public TbBoredVoice copy()
		{
			TbBoredVoice t = new TbBoredVoice();

			t.Id = Id;
			t.BoredId = BoredId;
			t.Useid = Useid;
			t.UserName = UserName;
			t.UserFace = UserFace;
			t.RecordLen = RecordLen;
			t.RecordUrl = RecordUrl;
			t.CreateTime = CreateTime;
			return t;
		}
	 } 
}    

