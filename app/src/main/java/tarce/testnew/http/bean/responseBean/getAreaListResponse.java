package tarce.testnew.http.bean.responseBean;

import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/10.
 */

public class GetAreaListResponse {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":[{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:08","description":false,"__last_update":"2017-02-10 00:50:08","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:08","display_name":"位置1","id":1,"name":"位置1"},{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:15","description":false,"__last_update":"2017-02-10 00:50:15","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:15","display_name":"位置2","id":2,"name":"位置2"},{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:21","description":false,"__last_update":"2017-02-10 00:50:21","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:21","display_name":"位置3","id":3,"name":"位置3"}],"res_msg":"","res_code":1}
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
         * res_data : [{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:08","description":false,"__last_update":"2017-02-10 00:50:08","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:08","display_name":"位置1","id":1,"name":"位置1"},{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:15","description":false,"__last_update":"2017-02-10 00:50:15","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:15","display_name":"位置2","id":2,"name":"位置2"},{"create_uid":[1,"Administrator"],"create_date":"2017-02-10 00:50:21","description":false,"__last_update":"2017-02-10 00:50:21","write_uid":[1,"Administrator"],"write_date":"2017-02-10 00:50:21","display_name":"位置3","id":3,"name":"位置3"}]
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
             * create_uid : [1,"Administrator"]
             * create_date : 2017-02-10 00:50:08
             * description : false
             * __last_update : 2017-02-10 00:50:08
             * write_uid : [1,"Administrator"]
             * write_date : 2017-02-10 00:50:08
             * display_name : 位置1
             * id : 1
             * name : 位置1
             */

            private String create_date;
            private boolean description;
            private String __last_update;
            private String write_date;
            private String display_name;
            private int id;
            private String name;
            private List<Object> create_uid;
            private List<Object> write_uid;

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public boolean isDescription() {
                return description;
            }

            public void setDescription(boolean description) {
                this.description = description;
            }

            public String get__last_update() {
                return __last_update;
            }

            public void set__last_update(String __last_update) {
                this.__last_update = __last_update;
            }

            public String getWrite_date() {
                return write_date;
            }

            public void setWrite_date(String write_date) {
                this.write_date = write_date;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Object> getCreate_uid() {
                return create_uid;
            }

            public void setCreate_uid(List<Object> create_uid) {
                this.create_uid = create_uid;
            }

            public List<Object> getWrite_uid() {
                return write_uid;
            }

            public void setWrite_uid(List<Object> write_uid) {
                this.write_uid = write_uid;
            }
        }
    }
}
