package tarce.testnew.http.bean.responseBean;

import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/8.
 */

public class GetStockInventoryResponse {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":[{"location_name":"库存","name":"20161204成品入库","state":"done","date":"2016-12-31 09:02:39","line_ids":null,"id":9},{"location_name":"库存","name":"20161203成品入库","state":"done","date":"2016-12-21 00:08:15","line_ids":null,"id":8},{"location_name":"库存","name":"20161202成品入库","state":"done","date":"2016-12-13 08:53:37","line_ids":null,"id":7},{"location_name":"库存","name":"20161201库存调整","state":"done","date":"2016-12-12 09:13:54","line_ids":null,"id":6},{"location_name":"库存","name":"当前库存","state":"done","date":"2016-11-28 08:15:47","line_ids":null,"id":2}],"res_msg":"","res_code":1}
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
         * res_data : [{"location_name":"库存","name":"20161204成品入库","state":"done","date":"2016-12-31 09:02:39","line_ids":null,"id":9},{"location_name":"库存","name":"20161203成品入库","state":"done","date":"2016-12-21 00:08:15","line_ids":null,"id":8},{"location_name":"库存","name":"20161202成品入库","state":"done","date":"2016-12-13 08:53:37","line_ids":null,"id":7},{"location_name":"库存","name":"20161201库存调整","state":"done","date":"2016-12-12 09:13:54","line_ids":null,"id":6},{"location_name":"库存","name":"当前库存","state":"done","date":"2016-11-28 08:15:47","line_ids":null,"id":2}]
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
             * location_name : 库存
             * name : 20161204成品入库
             * state : done
             * date : 2016-12-31 09:02:39
             * line_ids : null
             * id : 9
             */

            private String location_name;
            private String name;
            private String state;
            private String date;
            private Object line_ids;
            private int id;

            public String getLocation_name() {
                return location_name;
            }

            public void setLocation_name(String location_name) {
                this.location_name = location_name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Object getLine_ids() {
                return line_ids;
            }

            public void setLine_ids(Object line_ids) {
                this.line_ids = line_ids;
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
