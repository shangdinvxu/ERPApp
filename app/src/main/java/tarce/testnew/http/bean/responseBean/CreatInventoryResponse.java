package tarce.testnew.http.bean.responseBean;

/**
 * Created by Daniel.Xu on 2017/2/9.
 */

public class CreatInventoryResponse {

    /**
     * jsonrpc : 2.0
     * id : 0
     * result : {"res_msg":"","res_code":1}
     */

    private String jsonrpc;
    private int id;
    private ResultBean result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
         * res_msg :
         * res_code : 1
         */

        private String res_msg;
        private int res_code;

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
    }
}
