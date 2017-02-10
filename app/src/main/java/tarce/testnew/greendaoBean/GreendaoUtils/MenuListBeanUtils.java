package tarce.testnew.greendaoBean.GreendaoUtils;

import android.content.Context;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import greendao.MenuListBeanDao;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.MyApplication;
import tarce.testnew.greendaoBean.MenuListBean;

/**
 * Created by Daniel.Xu on 2017/2/8.
 */

public class MenuListBeanUtils {
    private  MenuListBeanDao menuListBeanDao ;
    public MenuListBeanUtils() {
        this.menuListBeanDao = GreenDaoManager.getInstance().getmDaoSession().getMenuListBeanDao();
    }

    public List<MenuListBean> getMenuByParentId(int userId, int parentId){
        Query<MenuListBean> build = menuListBeanDao.queryBuilder()
                .where(MenuListBeanDao.Properties.User_id.eq(userId), MenuListBeanDao.Properties.Parent_id.eq(parentId))
                .orderAsc(MenuListBeanDao.Properties.Id)
                .build();
        List<MenuListBean> list = build.list();
        return list ;
    }

    public List<MenuListBean> getMenuById(int userId,int id){
        Query<MenuListBean> build = menuListBeanDao.queryBuilder()
                .where(MenuListBeanDao.Properties.User_id.eq(userId), MenuListBeanDao.Properties.Id.eq(id))
                .build();
        List<MenuListBean> list = build.list();
        return list ;
    }
}
