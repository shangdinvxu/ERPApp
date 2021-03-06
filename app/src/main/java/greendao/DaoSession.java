package greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import tarce.testnew.greendao.greendaoBeans.ContactsBean;
import tarce.testnew.greendao.greendaoBeans.LoginResponseBean;
import tarce.testnew.greendao.greendaoBeans.MenuListBean;
import tarce.testnew.greendao.greendaoBeans.SaveInventory;
import tarce.testnew.greendao.greendaoBeans.UserLogin;

import greendao.ContactsBeanDao;
import greendao.LoginResponseBeanDao;
import greendao.MenuListBeanDao;
import greendao.SaveInventoryDao;
import greendao.UserLoginDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig contactsBeanDaoConfig;
    private final DaoConfig loginResponseBeanDaoConfig;
    private final DaoConfig menuListBeanDaoConfig;
    private final DaoConfig saveInventoryDaoConfig;
    private final DaoConfig userLoginDaoConfig;

    private final ContactsBeanDao contactsBeanDao;
    private final LoginResponseBeanDao loginResponseBeanDao;
    private final MenuListBeanDao menuListBeanDao;
    private final SaveInventoryDao saveInventoryDao;
    private final UserLoginDao userLoginDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        contactsBeanDaoConfig = daoConfigMap.get(ContactsBeanDao.class).clone();
        contactsBeanDaoConfig.initIdentityScope(type);

        loginResponseBeanDaoConfig = daoConfigMap.get(LoginResponseBeanDao.class).clone();
        loginResponseBeanDaoConfig.initIdentityScope(type);

        menuListBeanDaoConfig = daoConfigMap.get(MenuListBeanDao.class).clone();
        menuListBeanDaoConfig.initIdentityScope(type);

        saveInventoryDaoConfig = daoConfigMap.get(SaveInventoryDao.class).clone();
        saveInventoryDaoConfig.initIdentityScope(type);

        userLoginDaoConfig = daoConfigMap.get(UserLoginDao.class).clone();
        userLoginDaoConfig.initIdentityScope(type);

        contactsBeanDao = new ContactsBeanDao(contactsBeanDaoConfig, this);
        loginResponseBeanDao = new LoginResponseBeanDao(loginResponseBeanDaoConfig, this);
        menuListBeanDao = new MenuListBeanDao(menuListBeanDaoConfig, this);
        saveInventoryDao = new SaveInventoryDao(saveInventoryDaoConfig, this);
        userLoginDao = new UserLoginDao(userLoginDaoConfig, this);

        registerDao(ContactsBean.class, contactsBeanDao);
        registerDao(LoginResponseBean.class, loginResponseBeanDao);
        registerDao(MenuListBean.class, menuListBeanDao);
        registerDao(SaveInventory.class, saveInventoryDao);
        registerDao(UserLogin.class, userLoginDao);
    }
    
    public void clear() {
        contactsBeanDaoConfig.clearIdentityScope();
        loginResponseBeanDaoConfig.clearIdentityScope();
        menuListBeanDaoConfig.clearIdentityScope();
        saveInventoryDaoConfig.clearIdentityScope();
        userLoginDaoConfig.clearIdentityScope();
    }

    public ContactsBeanDao getContactsBeanDao() {
        return contactsBeanDao;
    }

    public LoginResponseBeanDao getLoginResponseBeanDao() {
        return loginResponseBeanDao;
    }

    public MenuListBeanDao getMenuListBeanDao() {
        return menuListBeanDao;
    }

    public SaveInventoryDao getSaveInventoryDao() {
        return saveInventoryDao;
    }

    public UserLoginDao getUserLoginDao() {
        return userLoginDao;
    }

}
