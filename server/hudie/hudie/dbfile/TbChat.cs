using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "chat")] 
	 public class TbChat:TbLogic
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
		private string _face;
		[Column(Name = "face")]
		public string Face{
			get{ return _face;}
			set
			{
				_face = value;
				changedKeys.Add("Face");
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
		private long _valid_time;
		[Column(Name = "valid_time")]
		public long ValidTime{
			get{ return _valid_time;}
			set
			{
				_valid_time = value;
				changedKeys.Add("ValidTime");
			}
		}
		public TbChat()
		{
			Name ="";
			Face ="";
			Content ="";
		}
		public TbChat copy()
		{
			TbChat t = new TbChat();

			t.Id = Id;
			t.Name = Name;
			t.Face = Face;
			t.Content = Content;
			t.CreateTime = CreateTime;
			t.ValidTime = ValidTime;
			return t;
		}
	 } 
}    

