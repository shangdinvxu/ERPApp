package tarce.testnew.http.bean.responseBean;

import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/17.
 */

public class GetGroupSecondResponse {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":[{"state":"assigned","state_count":115,"__domain":["&",["state","=","assigned"],["picking_type_id","=",1]]},{"state":"cancel","state_count":89,"__domain":["&",["state","=","cancel"],["picking_type_id","=",1]]},{"state":"confirmed","state_count":1,"__domain":["&",["state","=","confirmed"],["picking_type_id","=",1]]},{"state":"done","state_count":1033,"__domain":["&",["state","=","done"],["picking_type_id","=",1]]},{"state":"draft","state_count":123,"__domain":["&",["state","=","draft"],["picking_type_id","=",1]]},{"state":"qc_check","state_count":3,"__domain":["&",["state","=","qc_check"],["picking_type_id","=",1]]}],"res_msg":"","res_code":1}
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
         * res_data : [{"state":"assigned","state_count":115,"__domain":["&",["state","=","assigned"],["picking_type_id","=",1]]},{"state":"cancel","state_count":89,"__domain":["&",["state","=","cancel"],["picking_type_id","=",1]]},{"state":"confirmed","state_count":1,"__domain":["&",["state","=","confirmed"],["picking_type_id","=",1]]},{"state":"done","state_count":1033,"__domain":["&",["state","=","done"],["picking_type_id","=",1]]},{"state":"draft","state_count":123,"__domain":["&",["state","=","draft"],["picking_type_id","=",1]]},{"state":"qc_check","state_count":3,"__domain":["&",["state","=","qc_check"],["picking_type_id","=",1]]}]
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
             * state : assigned
             * state_count : 115
             * __domain : ["&",["state","=","assigned"],["picking_type_id","=",1]]
             */

            private String state;
            private int state_count;
            private List<String> __domain;

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getState_count() {
                return state_count;
            }

            public void setState_count(int state_count) {
                this.state_count = state_count;
            }

            public List<String> get__domain() {
                return __domain;
            }

            public void set__domain(List<String> __domain) {
                this.__domain = __domain;
            }
        }
    }
}
