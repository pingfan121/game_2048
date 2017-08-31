using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "app_advice")] 
	 public class TbAppAdvice:TbLogic
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
		private string _userid;
		[Column(Name = "userid")]
		public string Userid{
			get{ return _userid;}
			set
			{
				_userid = value;
				changedKeys.Add("Userid");
			}
		}
		private string _content;
		[Column(Name = "content")]
		public string Content{
			get{ return _content;}
			set
			{
				_content = value;
				changedKeys.Add("Content");
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
		public TbAppAdvice()
		{
			Userid ="";
			Content ="";
		}
		public TbAppAdvice copy()
		{
			TbAppAdvice t = new TbAppAdvice();

			t.Id = Id;
			t.Userid = Userid;
			t.Content = Content;
			t.CreateTime = CreateTime;
			return t;
		}
	 } 
}    

