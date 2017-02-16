package tarce.testnew.greendao.GreendaoUtils;

import org.greenrobot.greendao.query.Query;

import java.util.List;

import greendao.SaveInventoryDao;
import tarce.testnew.GreenDaoManager;
import tarce.testnew.greendao.greendaoBeans.SaveInventory;

/**
 * Created by Daniel.Xu on 2017/2/8.
 */

public class SaveInventroyUtils {
    private SaveInventoryDao saveInventoryDao;

    public SaveInventroyUtils() {
        this.saveInventoryDao = GreenDaoManager.getInstance().getmDaoSession().getSaveInventoryDao();
    }

    public void insertSaveInventroy(SaveInventory saveInventory) {
        saveInventoryDao.insertOrReplace(saveInventory);

    }

    public List<SaveInventory> getEntityByProductionName(String productionName) {
        Query<SaveInventory> build = saveInventoryDao.queryBuilder().where(SaveInventoryDao.Properties.Product_name.eq(productionName)).build();
        List<SaveInventory> list = build.list();
        return list;
    }

    public void deleteAllDate() {
        saveInventoryDao.deleteAll();
    }

    public void deleteByName(String name){
        Query<SaveInventory> build = saveInventoryDao.queryBuilder().where(SaveInventoryDao.Properties.Product_name.eq(name)).build();
        saveInventoryDao.delete(build.list().get(0));
    }

    public List<SaveInventory> searchALL() {
        List<SaveInventory> saveInventories = saveInventoryDao.loadAll();
        return saveInventories;
    }

}
