package tarce.testnew.greendao.GreendaoUtils;

import java.util.List;

import greendao.UserLoginDao;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.greendao.greendaoBeans.UserLogin;

/**
 * Created by Daniel.Xu on 2017/2/10.
 */

public class UserLoginUtils {
    private UserLoginDao userLoginDao ;
    public UserLoginUtils() {
        this.userLoginDao = GreenDaoManager.getInstance().getmDaoSession().getUserLoginDao();
    }

    public void insertUser(UserLogin userLogin){
        userLoginDao.insertOrReplace(userLogin);
    }

    public List<UserLogin> searchAll(){
        List<UserLogin> userLogins = userLoginDao.loadAll();
        return userLogins;
    }

}
