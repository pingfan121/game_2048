package pf.com.butterfly.module.benefit;

import android.view.View;
import android.widget.ListView;

import pf.com.butterfly.R;
import pf.com.butterfly.base.AppBaseControl;
import pf.com.butterfly.base.AppBaseFragment;
import pf.com.butterfly.base.AppBaseViewControl;
import pf.com.butterfly.module.title.TitleModule;

/**
 * Created by admin on 2017/3/3.
 */
public class BenefitHead extends AppBaseViewControl
{
    private static BenefitHead _instance;

    public static BenefitHead getInstance()
    {
        if(_instance==null)
        {
            _instance=new BenefitHead();
        }

        return _instance;
    }

    public void initValue()
    {
        title="蝴蝶";
        layout=R.layout.benefit_head;
    }

    private BenefitHeadItemAdapter adapter;

    @Override
    public void initControl()
    {


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

        adapter=new BenefitHeadItemAdapter();

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
