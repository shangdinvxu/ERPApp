package tarce.testnew.http.bean.responseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/15.
 */

public class GetStockMoveResponseBean {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":[{"name":"MO/2016122105124","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105126","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105128","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105130","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105138","product_uom_qty":270,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105140","product_uom_qty":270,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}}],"res_msg":"","res_code":1}
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
         * res_data : [{"name":"MO/2016122105124","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105126","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105128","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105130","product_uom_qty":600,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105138","product_uom_qty":270,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}},{"name":"MO/2016122105140","product_uom_qty":270,"state":"waiting","location":"WH/Stock","location_dest":"Virtual Locations/Production","product_id":{"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}}]
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

        public static class ResDataBean implements Serializable{
            /**
             * name : MO/2016122105124
             * product_uom_qty : 600
             * state : waiting
             * location : WH/Stock
             * location_dest : Virtual Locations/Production
             * product_id : {"product_name":"[46.100000.133] \u2018Made In China\u2019不干胶-EXCLUSIVAS CAMACHO,S.L.","id":53371}
             */

            private String name;
            private int product_uom_qty;
            private String state;
            private String location;
            private String location_dest;
            private ProductIdBean product_id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getProduct_uom_qty() {
                return product_uom_qty;
            }

            public void setProduct_uom_qty(int product_uom_qty) {
                this.product_uom_qty = product_uom_qty;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getLocation_dest() {
                return location_dest;
            }

            public void setLocation_dest(String location_dest) {
                this.location_dest = location_dest;
            }

            public ProductIdBean getProduct_id() {
                return product_id;
            }

            public void setProduct_id(ProductIdBean product_id) {
                this.product_id = product_id;
            }

            public static class ProductIdBean implements Serializable {
                /**
                 * product_name : [46.100000.133] ‘Made In China’不干胶-EXCLUSIVAS CAMACHO,S.L.
                 * id : 53371
                 */

                private String product_name;
                private int id;

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
