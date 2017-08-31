using System;
using System.Collections.Generic; 
using System.Linq;  
using System.Text;  
using Easy4net.CustomAttributes;  
namespace GameDb.Logic  
{  
	 [Table(Name = "game_2048_rank")] 
	 public class TbGame2048Rank:TbLogic
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
		public TbGame2048Rank()
		{
			Name ="";
		}
		public TbGame2048Rank copy()
		{
			TbGame2048Rank t = new TbGame2048Rank();

			t.Id = Id;
			t.Name = Name;
			t.FourMaxScore = FourMaxScore;
			t.SixMaxScore = SixMaxScore;
			return t;
		}
	 } 
}    

