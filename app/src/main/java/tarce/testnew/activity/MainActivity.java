package tarce.testnew.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.hugeterry.updatefun.UpdateFunGO;
import cn.hugeterry.updatefun.config.UpdateKey;
import greendao.DaoSession;
import greendao.MenuListBeanDao;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.IntentFactory;
import tarce.testnew.MainFragment.Homefragment;
import tarce.testnew.MainFragment.SecondFragment;
import tarce.testnew.MainFragment.ThreeFragment;
import tarce.testnew.MyApplication;
import tarce.testnew.R;
import tarce.testnew.Utils.AlertAialogUtils;
import tarce.testnew.Utils.MyLog;
import tarce.testnew.ViewUtil.SharePreferenceUtils;
import tarce.testnew.greendao.GreendaoUtils.MenuListBeanUtils;
import tarce.testnew.greendao.greendaoBeans.MenuListBean;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,BottomNavigationBar.OnTabSelectedListener {

    private String TAG = this.getClass().getSimpleName();

    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragments ;
    private DaoSession daoSession;
    private MenuListBeanDao menuListBeanDao;
    private MenuListBeanUtils menuListBeanUtils;
    private int userID;
    private Menu navigationViewmenu;
    private List<MenuListBean> menuByParentIdFirst;
    private ImageView heardImageView;
    private View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Odoo");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        userID = MyApplication.userID;
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationViewmenu = navigationView.getMenu();
        navigationView.setNavigationItemSelectedListener(this);
        initButtomBar();
        daoSession = GreenDaoManager.getInstance().getmDaoSession();
        menuListBeanDao = daoSession.getMenuListBeanDao();
        menuListBeanUtils = new MenuListBeanUtils();
        headerView = navigationView.getHeaderView(0);
        initMenuList();
        initListener();
        checkFirUpdate();
    }

    private void checkFirUpdate() {
        UpdateKey.API_TOKEN = "f2a3d1973878abb5ba921a0176a5c1fb";
        UpdateKey.APP_ID = "589c54dc959d69172400040d";
        UpdateFunGO.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFunGO.onResume(this);
    }
    @Override
    protected void onStop() {
        super.onStop();
        UpdateFunGO.onStop(this);
    }

    private void initListener() {
        /**点击头像处退出,userid 置为-1000*/
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder commonDialog = AlertAialogUtils.getCommonDialog(MainActivity.this, "退出");
                commonDialog
                        .setMessage("确定要退出?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharePreferenceUtils.putInt("user_id", -1000, MainActivity.this);
                        IntentFactory.start_LoginActivity(MainActivity.this);
                    }
                }).show();
            }
        });
    }

    private void initMenuList() {
        menuByParentIdFirst = menuListBeanUtils.getMenuByParentId(userID, 0);
        if (menuByParentIdFirst.size()>0){
            for (int i = 0 ; i<menuByParentIdFirst.size();i++){
                navigationViewmenu.add(i,i,i,menuByParentIdFirst.get(i).getName());
            }
        }

    }


    private void initButtomBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_menu_gallery, "itme1").setActiveColor("#6495ED"))
                .addItem(new BottomNavigationItem(R.drawable.ic_menu_gallery, "item2").setActiveColor("#FFB6C1"))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(this);

    }

    /** * 设置默认的 */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.layFrame, new Homefragment());
        transaction.commit();
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Homefragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThreeFragment());
        return fragments;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int itemId = item.getItemId();
        MyLog.e(TAG,"点击的ItemId"+itemId);

        long id = menuByParentIdFirst.get(itemId).getId();
        Intent intent = new Intent(MainActivity.this,SecondaryMenuActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                    ft.replace(R.id.layFrame, fragment);
                ft.commitAllowingStateLoss();
            }
        }

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


}
