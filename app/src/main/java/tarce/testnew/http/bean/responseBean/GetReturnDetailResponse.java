package tarce.testnew.http.bean.responseBean;

import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/20.
 */

public class GetReturnDetailResponse {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":[{"product_tmpl_id":46611,"product_id":"[43.1JP147.000] JP147-彩纸(警车)-RT-CN","return_qty":0},{"product_tmpl_id":59489,"product_id":"671.123","return_qty":0},{"product_tmpl_id":59491,"product_id":"68.123","return_qty":0}],"res_msg":"","res_code":1}
     */

    private String jsonrpc;
    private Object id;
    private ResultBean result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * res_data : [{"product_tmpl_id":46611,"product_id":"[43.1JP147.000] JP147-彩纸(警车)-RT-CN","return_qty":0},{"product_tmpl_id":59489,"product_id":"671.123","return_qty":0},{"product_tmpl_id":59491,"product_id":"68.123","return_qty":0}]
         * res_msg :
         * res_code : 1
         */

        private String res_msg;
        private int res_code;
        private List<ResDataBean> res_data;

        public String getRes_msg() {
            return res_msg;
        }

        public void setRes_msg(String res_msg) {
            this.res_msg = res_msg;
        }

        public int getRes_code() {
            return res_code;
        }

        public void setRes_code(int res_code) {
            this.res_code = res_code;
        }

        public List<ResDataBean> getRes_data() {
            return res_data;
        }

        public void setRes_data(List<ResDataBean> res_data) {
            this.res_data = res_data;
        }

        public static class ResDataBean {
            /**
             * product_tmpl_id : 46611
             * product_id : [43.1JP147.000] JP147-彩纸(警车)-RT-CN
             * return_qty : 0
             */

            private int product_tmpl_id;
            private String product_id;
            private int return_qty;

            public int getProduct_tmpl_id() {
                return product_tmpl_id;
            }

            public void setProduct_tmpl_id(int product_tmpl_id) {
                this.product_tmpl_id = product_tmpl_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public int getReturn_qty() {
                return return_qty;
            }

            public void setReturn_qty(int return_qty) {
                this.return_qty = return_qty;
            }
        }
    }
}
