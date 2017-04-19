package tarce.testnew.http.bean.responseBean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/16.
 */

public class OrderDetailResponse {


    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":{"origin":false,"order_id":19208,"qty_produced":0,"cur_location":null,"stock_move_lines":[{"quantity_ready":0,"virtual_available":-523,"product_id":"[22.0G0000.003] RF-300C-11440电机(P320/P350) ","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49504,"order_id":19208,"quantity_available":0,"qty_available":-522,"quantity_done":0,"id":17,"over_picking_qty":0},{"quantity_ready":0,"virtual_available":-34511,"product_id":"[39.1PXX00.002] PXX0-AXIS(小飞机马达轴套)","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49421,"order_id":19208,"quantity_available":1,"qty_available":26416,"quantity_done":0,"id":18,"over_picking_qty":0}],"date_planned_start":"2017-02-17 10:14:10","bom_name":"[28.0P3200.001] 电机线材组合[P320、P350]","product_qty":1,"display_name":"MO/2017021719165","user_id":"Administrator","production_order_type":"ordering","state":"prepare_material_ing","product_name":"[28.0P3200.001] 电机线材组合[P320、P350]"},"res_msg":"","res_code":1}
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
         * res_data : {"origin":false,"order_id":19208,"qty_produced":0,"cur_location":null,"stock_move_lines":[{"quantity_ready":0,"virtual_available":-523,"product_id":"[22.0G0000.003] RF-300C-11440电机(P320/P350) ","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49504,"order_id":19208,"quantity_available":0,"qty_available":-522,"quantity_done":0,"id":17,"over_picking_qty":0},{"quantity_ready":0,"virtual_available":-34511,"product_id":"[39.1PXX00.002] PXX0-AXIS(小飞机马达轴套)","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49421,"order_id":19208,"quantity_available":1,"qty_available":26416,"quantity_done":0,"id":18,"over_picking_qty":0}],"date_planned_start":"2017-02-17 10:14:10","bom_name":"[28.0P3200.001] 电机线材组合[P320、P350]","product_qty":1,"display_name":"MO/2017021719165","user_id":"Administrator","production_order_type":"ordering","state":"prepare_material_ing","product_name":"[28.0P3200.001] 电机线材组合[P320、P350]"}
         * res_msg :
         * res_code : 1
         */

        private ResDataBean res_data;
        private String res_msg;
        private int res_code;

        public ResDataBean getRes_data() {
            return res_data;
        }

        public void setRes_data(ResDataBean res_data) {
            this.res_data = res_data;
        }

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

        public static class ResDataBean  implements Serializable{
            /**
             * origin : false
             * order_id : 19208
             * qty_produced : 0
             * cur_location : null
             * stock_move_lines : [{"quantity_ready":0,"virtual_available":-523,"product_id":"[22.0G0000.003] RF-300C-11440电机(P320/P350) ","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49504,"order_id":19208,"quantity_available":0,"qty_available":-522,"quantity_done":0,"id":17,"over_picking_qty":0},{"quantity_ready":0,"virtual_available":-34511,"product_id":"[39.1PXX00.002] PXX0-AXIS(小飞机马达轴套)","suggest_qty":1.03,"product_uom_qty":1,"return_qty":0,"product_tmpl_id":49421,"order_id":19208,"quantity_available":1,"qty_available":26416,"quantity_done":0,"id":18,"over_picking_qty":0}]
             * date_planned_start : 2017-02-17 10:14:10
             * bom_name : [28.0P3200.001] 电机线材组合[P320、P350]
             * product_qty : 1
             * display_name : MO/2017021719165
             * user_id : Administrator
             * production_order_type : ordering
             * state : prepare_material_ing
             * product_name : [28.0P3200.001] 电机线材组合[P320、P350]
             */

            private boolean origin;
            private int order_id;
            private double qty_produced;
            private Object cur_location;
            private String date_planned_start;
            private String bom_name;
            private int product_qty;
            private String display_name;
            private String user_id;
            private String production_order_type;
            private String state;
            private String product_name;
            private List<StockMoveLinesBean> stock_move_lines;

            public String getError() {
                return error;
            }

            public void setError(String error) {
                this.error = error;
            }

            private String error ;

            public boolean isOrigin() {
                return origin;
            }

            public void setOrigin(boolean origin) {
                this.origin = origin;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public double getQty_produced() {
                return qty_produced;
            }

            public void setQty_produced(double qty_produced) {
                this.qty_produced = qty_produced;
            }

            public Object getCur_location() {
                return cur_location;
            }

            public void setCur_location(Object cur_location) {
                this.cur_location = cur_location;
            }

            public String getDate_planned_start() {
                return date_planned_start;
            }

            public void setDate_planned_start(String date_planned_start) {
                this.date_planned_start = date_planned_start;
            }

            public String getBom_name() {
                return bom_name;
            }

            public void setBom_name(String bom_name) {
                this.bom_name = bom_name;
            }

            public int getProduct_qty() {
                return product_qty;
            }

            public void setProduct_qty(int product_qty) {
                this.product_qty = product_qty;
            }

            public String getDisplay_name() {
                return display_name;
            }

            public void setDisplay_name(String display_name) {
                this.display_name = display_name;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getProduction_order_type() {
                return production_order_type;
            }

            public void setProduction_order_type(String production_order_type) {
                this.production_order_type = production_order_type;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public List<StockMoveLinesBean> getStock_move_lines() {
                return stock_move_lines;
            }

            public void setStock_move_lines(List<StockMoveLinesBean> stock_move_lines) {
                this.stock_move_lines = stock_move_lines;
            }

            public static class StockMoveLinesBean  implements Serializable{
                /**
                 * quantity_ready : 0
                 * virtual_available : -523
                 * product_id : [22.0G0000.003] RF-300C-11440电机(P320/P350)
                 * suggest_qty : 1.03
                 * product_uom_qty : 1
                 * return_qty : 0
                 * product_tmpl_id : 49504
                 * order_id : 19208
                 * quantity_available : 0
                 * qty_available : -522
                 * quantity_done : 0
                 * id : 17
                 * over_picking_qty : 0
                 */

                private Double quantity_ready;
                private Double virtual_available;
                private String product_id;
                private double suggest_qty;
                private Double product_uom_qty;
                private int return_qty;
                private int product_tmpl_id;
                private int order_id;
                private Double quantity_available;
                private Double qty_available;
                private Double quantity_done;
                private int stock_move_lines_id;
                private int id ;
                private Double over_picking_qty;

                public String getError() {
                    return error;
                }

                public void setError(String error) {
                    this.error = error;
                }

                private String error;
                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Double getQuantity_ready() {
                    return quantity_ready;
                }

                public void setQuantity_ready(Double quantity_ready) {
                    this.quantity_ready = quantity_ready;
                }

                public Double getVirtual_available() {
                    return virtual_available;
                }

                public void setVirtual_available(Double virtual_available) {
                    this.virtual_available = virtual_available;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }

                public double getSuggest_qty() {
                    return suggest_qty;
                }

                public void setSuggest_qty(double suggest_qty) {
                    this.suggest_qty = suggest_qty;
                }

                public Double getProduct_uom_qty() {
                    return product_uom_qty;
                }

                public void setProduct_uom_qty(Double product_uom_qty) {
                    this.product_uom_qty = product_uom_qty;
                }

                public int getReturn_qty() {
                    return return_qty;
                }

                public void setReturn_qty(int return_qty) {
                    this.return_qty = return_qty;
                }

                public int getProduct_tmpl_id() {
                    return product_tmpl_id;
                }

                public void setProduct_tmpl_id(int product_tmpl_id) {
                    this.product_tmpl_id = product_tmpl_id;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }

                public Double getQuantity_available() {
                    return quantity_available;
                }

                public void setQuantity_available(Double quantity_available) {
                    this.quantity_available = quantity_available;
                }

                public Double getQty_available() {
                    return qty_available;
                }

                public void setQty_available(Double qty_available) {
                    this.qty_available = qty_available;
                }

                public Double getQuantity_done() {
                    return quantity_done;
                }

                public void setQuantity_done(Double quantity_done) {
                    this.quantity_done = quantity_done;
                }

                public int getStock_move_lines_id() {
                    return stock_move_lines_id;
                }

                public void setStock_move_lines_id(int stock_move_lines_id) {
                    this.stock_move_lines_id = stock_move_lines_id;
                }



                public Double getOver_picking_qty() {
                    return over_picking_qty;
                }

                public void setOver_picking_qty(Double over_picking_qty) {
                    this.over_picking_qty = over_picking_qty;
                }
            }
        }
    }
}
