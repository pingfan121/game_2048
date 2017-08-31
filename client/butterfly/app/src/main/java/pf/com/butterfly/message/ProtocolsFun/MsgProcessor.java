package pf.com.butterfly.message.ProtocolsFun;
import pf.com.butterfly.message.MsgBase;
import pf.com.butterfly.message.MsgCodeId;
import pf.com.butterfly.message.MsgMap;
import pf.com.butterfly.message.Protocols.*;

public class MsgProcessor
{
		public static void init()
		{
				 MsgMap.reslutmap.put(MsgCodeId.info_benefit_head_item,new info_benefit_head_item_fun());
				 MsgMap.reslutmap.put(MsgCodeId.info_bored_head_item,new info_bored_head_item_fun());
				 MsgMap.reslutmap.put(MsgCodeId.info_bored_record_item,new info_bored_record_item_fun());
				 MsgMap.reslutmap.put(MsgCodeId.info_user,new info_user_fun());
				 MsgMap.reslutmap.put(MsgCodeId.login_res,new login_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.register_res,new register_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.login_weixin_res,new login_weixin_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.benefit_head_res,new benefit_head_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.benefit_detail_res,new benefit_detail_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.benefit_proof_discuss_res,new benefit_proof_discuss_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.advise_cteate_res,new advise_cteate_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.bored_head_items_res,new bored_head_items_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.bored_record_items_res,new bored_record_items_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.bored_head_item_add_res,new bored_head_item_add_res_fun());
				 MsgMap.reslutmap.put(MsgCodeId.bored_record_item_add_res,new bored_record_item_add_res_fun());




				 MsgMap.msgmap.put(MsgCodeId.info_benefit_head_item, info_benefit_head_item.class);
				 MsgMap.msgmap.put(MsgCodeId.info_bored_head_item, info_bored_head_item.class);
				 MsgMap.msgmap.put(MsgCodeId.info_bored_record_item, info_bored_record_item.class);
				 MsgMap.msgmap.put(MsgCodeId.info_user, info_user.class);
				 MsgMap.msgmap.put(MsgCodeId.login_res, login_res.class);
				 MsgMap.msgmap.put(MsgCodeId.register_res, register_res.class);
				 MsgMap.msgmap.put(MsgCodeId.login_weixin_res, login_weixin_res.class);
				 MsgMap.msgmap.put(MsgCodeId.benefit_head_res, benefit_head_res.class);
				 MsgMap.msgmap.put(MsgCodeId.benefit_detail_res, benefit_detail_res.class);
				 MsgMap.msgmap.put(MsgCodeId.benefit_proof_discuss_res, benefit_proof_discuss_res.class);
				 MsgMap.msgmap.put(MsgCodeId.advise_cteate_res, advise_cteate_res.class);
				 MsgMap.msgmap.put(MsgCodeId.bored_head_items_res, bored_head_items_res.class);
				 MsgMap.msgmap.put(MsgCodeId.bored_record_items_res, bored_record_items_res.class);
				 MsgMap.msgmap.put(MsgCodeId.bored_head_item_add_res, bored_head_item_add_res.class);
				 MsgMap.msgmap.put(MsgCodeId.bored_record_item_add_res, bored_record_item_add_res.class);
		}
}
