using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "rank")] 
	 public class TbRank:TbLogic
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
		private int _four_max_score;
		[Column(Name = "four_max_score")]
		public int FourMaxScore{
			get{ return _four_max_score;}
			set
			{
				_four_max_score = value;
				changedKeys.Add("FourMaxScore");
			}
		}
		private int _six_max_score;
		[Column(Name = "six_max_score")]
		public int SixMaxScore{
			get{ return _six_max_score;}
			set
			{
				_six_max_score = value;
				changedKeys.Add("SixMaxScore");
			}
		}
		public TbRank()
		{
			Name ="";
			Face ="";
			DeviceId ="";
		}
		public TbRank copy()
		{
			TbRank t = new TbRank();

			t.Id = Id;
			t.Name = Name;
			t.Face = Face;
			t.DeviceId = DeviceId;
			t.FourMaxScore = FourMaxScore;
			t.SixMaxScore = SixMaxScore;
			return t;
		}
	 } 
}    

