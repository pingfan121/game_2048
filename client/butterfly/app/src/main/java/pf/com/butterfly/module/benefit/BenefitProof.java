package pf.com.butterfly.module.benefit;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseControl;
import pf.com.butterfly.base.AppBaseFragment;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.title.TitleModule;

/**
 * Created by admin on 2017/3/10.
 * 证明界面视图
 */

public class BenefitProof extends AppBaseViewControl
{
    private static BenefitProof _instance;

    public static BenefitProof getInstance()
    {
        if(_instance==null)
        {
            _instance=new BenefitProof();
        }

        return _instance;
    }

    protected void initValue()
    {
        title="证明";
        layout=R.layout.benefit_proof;
    }


    private BenefitProofItemAdapter adapter;
    @Override
    public void initControl()
    {
        View listshow=view.findViewById(R.id.listView);

        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnAddInfo();
            }
        });

        view.findViewById(R.id.btn_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnSubInfo();
            }
        });

        ListView listView=(ListView)view.findViewById(R.id.listView);

        adapter=new BenefitProofItemAdapter();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(adapter);
    }

    public void OnAddInfo()
    {

        adapter.AddOneItem("111");
    }

    public void OnSubInfo()
    {
        adapter.SubOneItem("111");
    }
}
