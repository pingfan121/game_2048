using System.Collections.Generic;using System.Collections;using GameLib.game;using GameServer.Define.EnumNormal;namespace GameDb.Data{	public class TbDataWneg{		/**		* 祝福值		*/		public int Id;		/**		* 升级概率		*/		public int AddRate;		/**		* 保持不变概率		*/		public int StayRate;		/**		* 下降一点概率		*/		public int ReduceRate;		static public Dictionary<int, TbDataWneg> temples=new Dictionary<int,TbDataWneg>();		static public void initdata(Dictionary<int,Hashtable> table){			foreach(Hashtable tb in table.Values){			try{				TbDataWneg tp=new TbDataWneg();				temples[(int)tb["Id"]] = tp;				tp.Id=(int)tb["Id"];				tp.AddRate=(int)tb["AddRate"];				tp.StayRate=(int)tb["StayRate"];				tp.ReduceRate=(int)tb["ReduceRate"];			}catch(System.Exception ee){				System.Console.WriteLine(ee);			}			}		}	static public TbDataWneg select(int id) {		if (temples.ContainsKey(id)) {			return temples[id];		}		return null;	}	}}