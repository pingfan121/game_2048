using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "app_user")] 
	 public class TbAppUser:TbLogic
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
		private string _mobile;
		[Column(Name = "mobile")]
		public string Mobile{
			get{ return _mobile;}
			set
			{
				_mobile = value;
				changedKeys.Add("Mobile");
			}
		}
		private string _mail;
		[Column(Name = "mail")]
		public string Mail{
			get{ return _mail;}
			set
			{
				_mail = value;
				changedKeys.Add("Mail");
			}
		}
		private string _pass;
		[Column(Name = "pass")]
		public string Pass{
			get{ return _pass;}
			set
			{
				_pass = value;
				changedKeys.Add("Pass");
			}
		}
		private string _wx_id;
		[Column(Name = "wx_id")]
		public string WxId{
			get{ return _wx_id;}
			set
			{
				_wx_id = value;
				changedKeys.Add("WxId");
			}
		}
		private string _wb_id;
		[Column(Name = "wb_id")]
		public string WbId{
			get{ return _wb_id;}
			set
			{
				_wb_id = value;
				changedKeys.Add("WbId");
			}
		}
		private string _name;
		[Column(Name = "name")]
		public string Name{
			get{ return _name;}
			set
			{
				_name = value;
				changedKeys.Add("Name");
			}
		}
		private string _head;
		[Column(Name = "head")]
		public string Head{
			get{ return _head;}
			set
			{
				_head = value;
				changedKeys.Add("Head");
			}
		}
		private int _sex;
		[Column(Name = "sex")]
		public int Sex{
			get{ return _sex;}
			set
			{
				_sex = value;
				changedKeys.Add("Sex");
			}
		}
		private long _createtime;
		[Column(Name = "createtime")]
		public long Createtime{
			get{ return _createtime;}
			set
			{
				_createtime = value;
				changedKeys.Add("Createtime");
			}
		}
		public TbAppUser()
		{
			Mobile ="";
			Mail ="";
			Pass ="";
			WxId ="";
			WbId ="";
			Name ="";
			Head ="";
		}
		public TbAppUser copy()
		{
			TbAppUser t = new TbAppUser();

			t.Id = Id;
			t.Mobile = Mobile;
			t.Mail = Mail;
			t.Pass = Pass;
			t.WxId = WxId;
			t.WbId = WbId;
			t.Name = Name;
			t.Head = Head;
			t.Sex = Sex;
			t.Createtime = Createtime;
			return t;
		}
	 } 
}    

