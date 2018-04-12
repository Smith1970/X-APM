package github.tornaco.xposedmoduletest.ui.activity.pmh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import dev.nick.tiles.tile.Category;
import github.tornaco.xposedmoduletest.R;
import github.tornaco.xposedmoduletest.model.CommonPackageInfo;
import github.tornaco.xposedmoduletest.ui.AppCustomDashboardFragment;
import github.tornaco.xposedmoduletest.ui.activity.common.CommonPackageInfoListActivity;
import github.tornaco.xposedmoduletest.ui.adapter.common.CommonPackageInfoAdapter;
import github.tornaco.xposedmoduletest.ui.tiles.pmh.PMHHnaldersEnabler;
import github.tornaco.xposedmoduletest.ui.widget.SwitchBar;
import github.tornaco.xposedmoduletest.xposed.app.XAshmanManager;

/**
 * Created by guohao4 on 2017/11/2.
 * Email: Tornaco@163.com
 */

public class PMHDashboardActivity extends CommonPackageInfoListActivity
        implements SwitchBar.OnSwitchChangeListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, PMHDashboardActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.app_list_pmh;
    }

    @Override
    protected void onRequestClearItemsInBackground() {

    }

    @Override
    protected void onInitSwitchBar(SwitchBar switchBar) {
        switchBar.show();
        switchBar.setChecked(XAshmanManager.get().isPushMessageHandleEnabled());
        switchBar.addOnSwitchChangeListener(this);
    }

    @Override
    protected void initView() {
        super.initView();
        fab = findViewById(R.id.fab);
        fab.hide();
        setupViews();
    }


    void setupViews() {
        replaceV4(R.id.container, new Dashboards(), null, false);
    }


    @Override
    protected boolean hasRecyclerView() {
        return false;
    }

    @Override
    protected void onRequestPick() {

    }

    @Override
    protected int getSummaryRes() {
        return R.string.summary_pmh;
    }

    @Override
    protected CommonPackageInfoAdapter onCreateAdapter() {
        return new CommonPackageInfoAdapter(this);
    }

    @Override
    protected List<CommonPackageInfo> performLoading() {
        return new ArrayList<>(0);
    }

    @Override
    public void onSwitchChanged(SwitchCompat switchView, boolean isChecked) {
        XAshmanManager.get().setPushMessageHandleEnabled(isChecked);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pmh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static class Dashboards extends AppCustomDashboardFragment {

        @Override
        protected void onCreateDashCategories(List<Category> categories) {
            super.onCreateDashCategories(categories);

            Category list = new Category();
            list.titleRes = R.string.title_push_message_handlers;
            list.addTile(new PMHHnaldersEnabler(getActivity()));

            Category settings = new Category();
            settings.titleRes = R.string.configure;

            categories.add(list);
        }
    }

}
