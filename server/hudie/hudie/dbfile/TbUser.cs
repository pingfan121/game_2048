using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "user")] 
	 public class TbUser:TbLogic
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
		private string _device_id;
		[Column(Name = "device_id")]
		public string DeviceId{
			get{ return _device_id;}
			set
			{
				_device_id = value;
				changedKeys.Add("DeviceId");
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
		public TbUser()
		{
			WxId ="";
			Name ="";
			Face ="";
			DeviceId ="";
		}
		public TbUser copy()
		{
			TbUser t = new TbUser();

			t.Id = Id;
			t.WxId = WxId;
			t.Name = Name;
			t.Face = Face;
			t.DeviceId = DeviceId;
			t.Createtime = Createtime;
			return t;
		}
	 } 
}    

