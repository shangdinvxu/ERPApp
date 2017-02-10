package tarce.testnew.http.bean.responseBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel.Xu on 2017/2/9.
 */

public class GetStockInventoryDetailResponse {

    /**
     * jsonrpc : 2.0
     * id : null
     * result : {"res_data":{"location_name":"库存","name":"20161203成品入库","state":"done","date":"2016-12-21 00:08:15","line_ids":[{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=46804","id":45137,"product_name":"[98.BA501S.100] BA501S-裱纸成品(皇家胜利号)-RT-ENG"},"product_qty":380,"theoretical_qty":330,"id":11263},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47033","id":45366,"product_name":"[65.0D2100.001] D210-MOTOR（D210行走马达）"},"product_qty":6487,"theoretical_qty":5431,"id":11267},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47084","id":45417,"product_name":"[60.0D2100.001] D210 控制盒"},"product_qty":11058,"theoretical_qty":10357,"id":11266},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47352","id":45685,"product_name":"[98.0DV180.100] DV180成品(别墅组合-厨房家具)-RT-ENG"},"product_qty":194,"theoretical_qty":180,"id":11235},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47367","id":45700,"product_name":"[98.0DV181.100] DV181成品(别墅组合-浴室家具)-RT-ENG"},"product_qty":264,"theoretical_qty":260,"id":11236},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47382","id":45715,"product_name":"[98.0DV182.100] DV182成品(别墅组合-客厅家具)-RT-ENG"},"product_qty":443,"theoretical_qty":366,"id":11238},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47413","id":45746,"product_name":"[98.0DV184.100] DV184成品(别墅组合-卧室家具)-RT-ENG"},"product_qty":120,"theoretical_qty":104,"id":11237},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47776","id":46109,"product_name":"[98.0F1260.100] F126-成品(法国花屋)-彩盒装-RT-ENG"},"product_qty":589,"theoretical_qty":572,"id":11234},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47787","id":46120,"product_name":"[98.0F1270.100] F127-成品(法国旅馆)-彩盒装-RT-ENG"},"product_qty":462,"theoretical_qty":418,"id":11252},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47849","id":46182,"product_name":"[98.0F1320.100] F132-成品(英国乐器店)-彩盒装-RT-ENG"},"product_qty":565,"theoretical_qty":562,"id":11248},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47875","id":46208,"product_name":"[98.0F1340.100] F134-成品(英国服装店)-彩盒装-RT-ENG"},"product_qty":572,"theoretical_qty":520,"id":11247},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47902","id":46235,"product_name":"[98.0F1360.100] F136-成品(美国汽车旅馆)-彩盒装-RT-ENG"},"product_qty":674,"theoretical_qty":666,"id":11245},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48109","id":46442,"product_name":"[98.0F302S.100] F302S简装成品(农家小院)-RT-ENG"},"product_qty":50,"theoretical_qty":0,"id":11259},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48122","id":46455,"product_name":"[98.0F401S.100] F401S-成品简装裱纸(温馨小屋)-RT-ENG"},"product_qty":56,"theoretical_qty":0,"id":11249},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48137","id":46470,"product_name":"[98.0F402S.100] F402S-成品简装裱纸(欧式小洋楼)-RT-ENG"},"product_qty":85,"theoretical_qty":30,"id":11265},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48149","id":46482,"product_name":"[98.0F403S.100] F403S-成品简装裱纸(飞屋)-RT-ENG"},"product_qty":109,"theoretical_qty":70,"id":11264},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48246","id":46579,"product_name":"[98.0JP109.100] JP109-成品(摩托车)-简装-RT-ENG"},"product_qty":279,"theoretical_qty":120,"id":11242},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48292","id":46625,"product_name":"[98.0JP150.100] JP150-成品(跑车)-简装-RT-ENG "},"product_qty":494,"theoretical_qty":434,"id":11246},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48329","id":46662,"product_name":"[98.0JP202.100] JP202-成品(螳螂)-简装-RT-ENG"},"product_qty":303,"theoretical_qty":291,"id":11250},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48438","id":46771,"product_name":"[98.0JP208.100] JP208-成品(信天翁飞机)-简装-RT-ENG"},"product_qty":308,"theoretical_qty":300,"id":11261},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48489","id":46822,"product_name":"[98.0JP209.100] JP209-成品(水上飞机)-简装-RT-ENG"},"product_qty":324,"theoretical_qty":300,"id":11251},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48554","id":46887,"product_name":"[98.0JP215.122] JP215-成品(大象)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11270},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48639","id":46972,"product_name":"[98.0JP219.100] JP219-成品(雷龙)-简装-RT-ENG"},"product_qty":64,"theoretical_qty":0,"id":11241},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48671","id":47004,"product_name":"[98.0JP222.122] JP222-成品(企鹅)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11268},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48568","id":46901,"product_name":"[98.0JP225.122] JP225-成品(狮子)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11272},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48552","id":46885,"product_name":"[98.0JP227.122] JP227-成品(鳄鱼)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11271},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48560","id":46893,"product_name":"[98.0JP228.122] JP228-成品(孔雀)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11269},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48913","id":47246,"product_name":"[98.0JP235.100] JP235-成品(法拉利)-简装-RT-ENG"},"product_qty":3,"theoretical_qty":0,"id":11258},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48960","id":47293,"product_name":"[98.0JP238.100] JP238-成品(伦敦巴士)-简装-RT-ENG"},"product_qty":378,"theoretical_qty":360,"id":11260},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48555","id":46888,"product_name":"[98.0JP243.122] JP243-成品(长颈鹿)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11273},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49271","id":47604,"product_name":"[98.0JP295.100] JP295-成品(海龟)-简装-RT-ENG"},"product_qty":169,"theoretical_qty":120,"id":11257},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49517","id":47850,"product_name":"[98.JPD656.100] JPD656-成品(伦敦塔桥)-盒装-RT-ENG"},"product_qty":111,"theoretical_qty":100,"id":11240},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49555","id":47888,"product_name":"[47.1JP866.100] JPD866-彩盒(大本钟)-RT-ENG"},"product_qty":185,"theoretical_qty":170,"id":11239},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49815","id":48148,"product_name":"[98.0JZ802.100] JZ802-成品(巴黎圣母院)-RT-ENG"},"product_qty":6,"theoretical_qty":0,"id":11262},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50114","id":48447,"product_name":"[98.0MJ202.100] MJ202-成品(海港大桥)-RT-ENG"},"product_qty":213,"theoretical_qty":180,"id":11243},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50129","id":48462,"product_name":"[98.0MJ206.100] MJ206-成品(玛雅金字塔)-RT-ENG"},"product_qty":79,"theoretical_qty":60,"id":11255},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50139","id":48472,"product_name":"[98.0MJ208.100] MJ208-成品(荷兰风车)-RT-ENG"},"product_qty":155,"theoretical_qty":132,"id":11253},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50221","id":48554,"product_name":"[98.0MJ210.100] MJ210-成品(东方明珠)-RT-ENG"},"product_qty":41,"theoretical_qty":0,"id":11244},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50326","id":48659,"product_name":"[98.0MJ403.100] MJ403-成品(泰姬陵)-RT-ENG"},"product_qty":133,"theoretical_qty":125,"id":11256},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50333","id":48666,"product_name":"[98.0MJ404.100] MJ404-成品(巴黎圣母院)-RT-ENG"},"product_qty":147,"theoretical_qty":137,"id":11254}],"id":8},"res_msg":"","res_code":1}
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
         * res_data : {"location_name":"库存","name":"20161203成品入库","state":"done","date":"2016-12-21 00:08:15","line_ids":[{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=46804","id":45137,"product_name":"[98.BA501S.100] BA501S-裱纸成品(皇家胜利号)-RT-ENG"},"product_qty":380,"theoretical_qty":330,"id":11263},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47033","id":45366,"product_name":"[65.0D2100.001] D210-MOTOR（D210行走马达）"},"product_qty":6487,"theoretical_qty":5431,"id":11267},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47084","id":45417,"product_name":"[60.0D2100.001] D210 控制盒"},"product_qty":11058,"theoretical_qty":10357,"id":11266},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47352","id":45685,"product_name":"[98.0DV180.100] DV180成品(别墅组合-厨房家具)-RT-ENG"},"product_qty":194,"theoretical_qty":180,"id":11235},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47367","id":45700,"product_name":"[98.0DV181.100] DV181成品(别墅组合-浴室家具)-RT-ENG"},"product_qty":264,"theoretical_qty":260,"id":11236},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47382","id":45715,"product_name":"[98.0DV182.100] DV182成品(别墅组合-客厅家具)-RT-ENG"},"product_qty":443,"theoretical_qty":366,"id":11238},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47413","id":45746,"product_name":"[98.0DV184.100] DV184成品(别墅组合-卧室家具)-RT-ENG"},"product_qty":120,"theoretical_qty":104,"id":11237},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47776","id":46109,"product_name":"[98.0F1260.100] F126-成品(法国花屋)-彩盒装-RT-ENG"},"product_qty":589,"theoretical_qty":572,"id":11234},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47787","id":46120,"product_name":"[98.0F1270.100] F127-成品(法国旅馆)-彩盒装-RT-ENG"},"product_qty":462,"theoretical_qty":418,"id":11252},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47849","id":46182,"product_name":"[98.0F1320.100] F132-成品(英国乐器店)-彩盒装-RT-ENG"},"product_qty":565,"theoretical_qty":562,"id":11248},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47875","id":46208,"product_name":"[98.0F1340.100] F134-成品(英国服装店)-彩盒装-RT-ENG"},"product_qty":572,"theoretical_qty":520,"id":11247},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47902","id":46235,"product_name":"[98.0F1360.100] F136-成品(美国汽车旅馆)-彩盒装-RT-ENG"},"product_qty":674,"theoretical_qty":666,"id":11245},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48109","id":46442,"product_name":"[98.0F302S.100] F302S简装成品(农家小院)-RT-ENG"},"product_qty":50,"theoretical_qty":0,"id":11259},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48122","id":46455,"product_name":"[98.0F401S.100] F401S-成品简装裱纸(温馨小屋)-RT-ENG"},"product_qty":56,"theoretical_qty":0,"id":11249},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48137","id":46470,"product_name":"[98.0F402S.100] F402S-成品简装裱纸(欧式小洋楼)-RT-ENG"},"product_qty":85,"theoretical_qty":30,"id":11265},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48149","id":46482,"product_name":"[98.0F403S.100] F403S-成品简装裱纸(飞屋)-RT-ENG"},"product_qty":109,"theoretical_qty":70,"id":11264},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48246","id":46579,"product_name":"[98.0JP109.100] JP109-成品(摩托车)-简装-RT-ENG"},"product_qty":279,"theoretical_qty":120,"id":11242},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48292","id":46625,"product_name":"[98.0JP150.100] JP150-成品(跑车)-简装-RT-ENG "},"product_qty":494,"theoretical_qty":434,"id":11246},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48329","id":46662,"product_name":"[98.0JP202.100] JP202-成品(螳螂)-简装-RT-ENG"},"product_qty":303,"theoretical_qty":291,"id":11250},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48438","id":46771,"product_name":"[98.0JP208.100] JP208-成品(信天翁飞机)-简装-RT-ENG"},"product_qty":308,"theoretical_qty":300,"id":11261},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48489","id":46822,"product_name":"[98.0JP209.100] JP209-成品(水上飞机)-简装-RT-ENG"},"product_qty":324,"theoretical_qty":300,"id":11251},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48554","id":46887,"product_name":"[98.0JP215.122] JP215-成品(大象)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11270},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48639","id":46972,"product_name":"[98.0JP219.100] JP219-成品(雷龙)-简装-RT-ENG"},"product_qty":64,"theoretical_qty":0,"id":11241},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48671","id":47004,"product_name":"[98.0JP222.122] JP222-成品(企鹅)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11268},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48568","id":46901,"product_name":"[98.0JP225.122] JP225-成品(狮子)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11272},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48552","id":46885,"product_name":"[98.0JP227.122] JP227-成品(鳄鱼)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11271},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48560","id":46893,"product_name":"[98.0JP228.122] JP228-成品(孔雀)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11269},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48913","id":47246,"product_name":"[98.0JP235.100] JP235-成品(法拉利)-简装-RT-ENG"},"product_qty":3,"theoretical_qty":0,"id":11258},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48960","id":47293,"product_name":"[98.0JP238.100] JP238-成品(伦敦巴士)-简装-RT-ENG"},"product_qty":378,"theoretical_qty":360,"id":11260},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48555","id":46888,"product_name":"[98.0JP243.122] JP243-成品(长颈鹿)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11273},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49271","id":47604,"product_name":"[98.0JP295.100] JP295-成品(海龟)-简装-RT-ENG"},"product_qty":169,"theoretical_qty":120,"id":11257},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49517","id":47850,"product_name":"[98.JPD656.100] JPD656-成品(伦敦塔桥)-盒装-RT-ENG"},"product_qty":111,"theoretical_qty":100,"id":11240},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49555","id":47888,"product_name":"[47.1JP866.100] JPD866-彩盒(大本钟)-RT-ENG"},"product_qty":185,"theoretical_qty":170,"id":11239},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49815","id":48148,"product_name":"[98.0JZ802.100] JZ802-成品(巴黎圣母院)-RT-ENG"},"product_qty":6,"theoretical_qty":0,"id":11262},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50114","id":48447,"product_name":"[98.0MJ202.100] MJ202-成品(海港大桥)-RT-ENG"},"product_qty":213,"theoretical_qty":180,"id":11243},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50129","id":48462,"product_name":"[98.0MJ206.100] MJ206-成品(玛雅金字塔)-RT-ENG"},"product_qty":79,"theoretical_qty":60,"id":11255},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50139","id":48472,"product_name":"[98.0MJ208.100] MJ208-成品(荷兰风车)-RT-ENG"},"product_qty":155,"theoretical_qty":132,"id":11253},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50221","id":48554,"product_name":"[98.0MJ210.100] MJ210-成品(东方明珠)-RT-ENG"},"product_qty":41,"theoretical_qty":0,"id":11244},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50326","id":48659,"product_name":"[98.0MJ403.100] MJ403-成品(泰姬陵)-RT-ENG"},"product_qty":133,"theoretical_qty":125,"id":11256},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50333","id":48666,"product_name":"[98.0MJ404.100] MJ404-成品(巴黎圣母院)-RT-ENG"},"product_qty":147,"theoretical_qty":137,"id":11254}],"id":8}
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

        public static class ResDataBean {
            /**
             * location_name : 库存
             * name : 20161203成品入库
             * state : done
             * date : 2016-12-21 00:08:15
             * line_ids : [{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=46804","id":45137,"product_name":"[98.BA501S.100] BA501S-裱纸成品(皇家胜利号)-RT-ENG"},"product_qty":380,"theoretical_qty":330,"id":11263},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47033","id":45366,"product_name":"[65.0D2100.001] D210-MOTOR（D210行走马达）"},"product_qty":6487,"theoretical_qty":5431,"id":11267},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47084","id":45417,"product_name":"[60.0D2100.001] D210 控制盒"},"product_qty":11058,"theoretical_qty":10357,"id":11266},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47352","id":45685,"product_name":"[98.0DV180.100] DV180成品(别墅组合-厨房家具)-RT-ENG"},"product_qty":194,"theoretical_qty":180,"id":11235},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47367","id":45700,"product_name":"[98.0DV181.100] DV181成品(别墅组合-浴室家具)-RT-ENG"},"product_qty":264,"theoretical_qty":260,"id":11236},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47382","id":45715,"product_name":"[98.0DV182.100] DV182成品(别墅组合-客厅家具)-RT-ENG"},"product_qty":443,"theoretical_qty":366,"id":11238},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47413","id":45746,"product_name":"[98.0DV184.100] DV184成品(别墅组合-卧室家具)-RT-ENG"},"product_qty":120,"theoretical_qty":104,"id":11237},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47776","id":46109,"product_name":"[98.0F1260.100] F126-成品(法国花屋)-彩盒装-RT-ENG"},"product_qty":589,"theoretical_qty":572,"id":11234},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47787","id":46120,"product_name":"[98.0F1270.100] F127-成品(法国旅馆)-彩盒装-RT-ENG"},"product_qty":462,"theoretical_qty":418,"id":11252},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47849","id":46182,"product_name":"[98.0F1320.100] F132-成品(英国乐器店)-彩盒装-RT-ENG"},"product_qty":565,"theoretical_qty":562,"id":11248},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47875","id":46208,"product_name":"[98.0F1340.100] F134-成品(英国服装店)-彩盒装-RT-ENG"},"product_qty":572,"theoretical_qty":520,"id":11247},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=47902","id":46235,"product_name":"[98.0F1360.100] F136-成品(美国汽车旅馆)-彩盒装-RT-ENG"},"product_qty":674,"theoretical_qty":666,"id":11245},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48109","id":46442,"product_name":"[98.0F302S.100] F302S简装成品(农家小院)-RT-ENG"},"product_qty":50,"theoretical_qty":0,"id":11259},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48122","id":46455,"product_name":"[98.0F401S.100] F401S-成品简装裱纸(温馨小屋)-RT-ENG"},"product_qty":56,"theoretical_qty":0,"id":11249},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48137","id":46470,"product_name":"[98.0F402S.100] F402S-成品简装裱纸(欧式小洋楼)-RT-ENG"},"product_qty":85,"theoretical_qty":30,"id":11265},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48149","id":46482,"product_name":"[98.0F403S.100] F403S-成品简装裱纸(飞屋)-RT-ENG"},"product_qty":109,"theoretical_qty":70,"id":11264},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48246","id":46579,"product_name":"[98.0JP109.100] JP109-成品(摩托车)-简装-RT-ENG"},"product_qty":279,"theoretical_qty":120,"id":11242},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48292","id":46625,"product_name":"[98.0JP150.100] JP150-成品(跑车)-简装-RT-ENG "},"product_qty":494,"theoretical_qty":434,"id":11246},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48329","id":46662,"product_name":"[98.0JP202.100] JP202-成品(螳螂)-简装-RT-ENG"},"product_qty":303,"theoretical_qty":291,"id":11250},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48438","id":46771,"product_name":"[98.0JP208.100] JP208-成品(信天翁飞机)-简装-RT-ENG"},"product_qty":308,"theoretical_qty":300,"id":11261},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48489","id":46822,"product_name":"[98.0JP209.100] JP209-成品(水上飞机)-简装-RT-ENG"},"product_qty":324,"theoretical_qty":300,"id":11251},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48554","id":46887,"product_name":"[98.0JP215.122] JP215-成品(大象)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11270},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48639","id":46972,"product_name":"[98.0JP219.100] JP219-成品(雷龙)-简装-RT-ENG"},"product_qty":64,"theoretical_qty":0,"id":11241},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48671","id":47004,"product_name":"[98.0JP222.122] JP222-成品(企鹅)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11268},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48568","id":46901,"product_name":"[98.0JP225.122] JP225-成品(狮子)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11272},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48552","id":46885,"product_name":"[98.0JP227.122] JP227-成品(鳄鱼)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11271},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48560","id":46893,"product_name":"[98.0JP228.122] JP228-成品(孔雀)-美国Toysmith "},"product_qty":1600,"theoretical_qty":0,"id":11269},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48913","id":47246,"product_name":"[98.0JP235.100] JP235-成品(法拉利)-简装-RT-ENG"},"product_qty":3,"theoretical_qty":0,"id":11258},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48960","id":47293,"product_name":"[98.0JP238.100] JP238-成品(伦敦巴士)-简装-RT-ENG"},"product_qty":378,"theoretical_qty":360,"id":11260},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=48555","id":46888,"product_name":"[98.0JP243.122] JP243-成品(长颈鹿)-美国Toysmith"},"product_qty":1600,"theoretical_qty":0,"id":11273},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49271","id":47604,"product_name":"[98.0JP295.100] JP295-成品(海龟)-简装-RT-ENG"},"product_qty":169,"theoretical_qty":120,"id":11257},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49517","id":47850,"product_name":"[98.JPD656.100] JPD656-成品(伦敦塔桥)-盒装-RT-ENG"},"product_qty":111,"theoretical_qty":100,"id":11240},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49555","id":47888,"product_name":"[47.1JP866.100] JPD866-彩盒(大本钟)-RT-ENG"},"product_qty":185,"theoretical_qty":170,"id":11239},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=49815","id":48148,"product_name":"[98.0JZ802.100] JZ802-成品(巴黎圣母院)-RT-ENG"},"product_qty":6,"theoretical_qty":0,"id":11262},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50114","id":48447,"product_name":"[98.0MJ202.100] MJ202-成品(海港大桥)-RT-ENG"},"product_qty":213,"theoretical_qty":180,"id":11243},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50129","id":48462,"product_name":"[98.0MJ206.100] MJ206-成品(玛雅金字塔)-RT-ENG"},"product_qty":79,"theoretical_qty":60,"id":11255},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50139","id":48472,"product_name":"[98.0MJ208.100] MJ208-成品(荷兰风车)-RT-ENG"},"product_qty":155,"theoretical_qty":132,"id":11253},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50221","id":48554,"product_name":"[98.0MJ210.100] MJ210-成品(东方明珠)-RT-ENG"},"product_qty":41,"theoretical_qty":0,"id":11244},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50326","id":48659,"product_name":"[98.0MJ403.100] MJ403-成品(泰姬陵)-RT-ENG"},"product_qty":133,"theoretical_qty":125,"id":11256},{"product":{"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=50333","id":48666,"product_name":"[98.0MJ404.100] MJ404-成品(巴黎圣母院)-RT-ENG"},"product_qty":147,"theoretical_qty":137,"id":11254}]
             * id : 8
             */

            private String location_name;
            private String name;
            private String state;
            private String date;
            private int id;
            private List<LineIdsBean> line_ids;

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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public List<LineIdsBean> getLine_ids() {
                return line_ids;
            }

            public void setLine_ids(List<LineIdsBean> line_ids) {
                this.line_ids = line_ids;
            }

            public static class LineIdsBean implements Serializable {
                /**
                 * product : {"area":{"area_name":false,"id":false},"image_medium":"http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=46804","id":45137,"product_name":"[98.BA501S.100] BA501S-裱纸成品(皇家胜利号)-RT-ENG"}
                 * product_qty : 380
                 * theoretical_qty : 330
                 * id : 11263
                 */

                private ProductBean product;
                private int product_qty;
                private int theoretical_qty;
                private int id;

                public ProductBean getProduct() {
                    return product;
                }

                public void setProduct(ProductBean product) {
                    this.product = product;
                }

                public int getProduct_qty() {
                    return product_qty;
                }

                public void setProduct_qty(int product_qty) {
                    this.product_qty = product_qty;
                }

                public int getTheoretical_qty() {
                    return theoretical_qty;
                }

                public void setTheoretical_qty(int theoretical_qty) {
                    this.theoretical_qty = theoretical_qty;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public static class ProductBean {
                    /**
                     * area : {"area_name":false,"id":false}
                     * image_medium : http://192.168.2.111:8069/linkloving_app_api/get_product_image?product_id=46804
                     * id : 45137
                     * product_name : [98.BA501S.100] BA501S-裱纸成品(皇家胜利号)-RT-ENG
                     */

                    private AreaBean area;
                    private String image_medium;
                    private int id;
                    private String product_name;

                    public AreaBean getArea() {
                        return area;
                    }

                    public void setArea(AreaBean area) {
                        this.area = area;
                    }

                    public String getImage_medium() {
                        return image_medium;
                    }

                    public void setImage_medium(String image_medium) {
                        this.image_medium = image_medium;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getProduct_name() {
                        return product_name;
                    }

                    public void setProduct_name(String product_name) {
                        this.product_name = product_name;
                    }

                    public static class AreaBean {
                        /**
                         * area_name : false
                         * id : false
                         */

                        private boolean area_name;
                        private boolean id;

                        public boolean isArea_name() {
                            return area_name;
                        }

                        public void setArea_name(boolean area_name) {
                            this.area_name = area_name;
                        }

                        public boolean isId() {
                            return id;
                        }

                        public void setId(boolean id) {
                            this.id = id;
                        }
                    }
                }
            }
        }
    }
}
