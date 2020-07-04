package cdgy.pqv.mamage.vuedemo3.convert;

import cdgy.pqv.mamage.vuedemo3.util.ShopTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//TypeHandle<JavaBean> String int
public class ShopTypeHandle implements TypeHandler<String> {

    //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线

    String getType(int type){
        if(type== ShopTypeEnum.COMPUTER.Value()){
            return "电脑";
        }
        if(type== ShopTypeEnum.PHONE.Value()){
            return "手机";
        }
        if(type== ShopTypeEnum.FLAT.Value()){
            return "平板";
        }
        if(type== ShopTypeEnum.CHARGER.Value()){
            return "充电器";
        }
        if(type== ShopTypeEnum.DATA_LINE.Value()){
            return "数据线";
        }
        if(type== ShopTypeEnum.JACKET.Value()){
            return "上衣";
        }
        if(type== ShopTypeEnum.TROUSERS.Value()){
            return "裤子";
        }
        if(type== ShopTypeEnum.SHOES.Value()){
            return "鞋子";
        }
        if(type== ShopTypeEnum.FOOD.Value()){
            return "食品";
        }
        if(type== ShopTypeEnum.JEWELRY.Value()){
            return "首饰";
        }
        if(type== ShopTypeEnum.TELEVISION.Value()){
            return "电视机";
        }
        if(type== ShopTypeEnum.HEADSET.Value()){
            return "耳机";
        }
        if(type== ShopTypeEnum.ROUTER.Value()){
            return "路由器";
        }
        /*
        if(type== ShopTypeEnum.PATCH_CORD.Value()){
            return "插排线";
        }*/
        return "插排线";
    }
    //电脑，手机，平板，充电器，数据线，上衣，裤子，鞋子，食品，首饰，电视机，耳机，路由器，插排线
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(parameter.equals("电脑")){
            ps.setInt(i,ShopTypeEnum.COMPUTER.Value());
        }
        if(parameter.equals("手机")){
            ps.setInt(i,ShopTypeEnum.PHONE.Value());
        }
        if(parameter.equals("平板")){
            ps.setInt(i,ShopTypeEnum.FLAT.Value());
        }
        if(parameter.equals("充电器")){
            ps.setInt(i,ShopTypeEnum.CHARGER.Value());
        }
        if(parameter.equals("数据线")){
            ps.setInt(i,ShopTypeEnum.DATA_LINE.Value());
        }
        if(parameter.equals("上衣")){
            ps.setInt(i,ShopTypeEnum.JACKET.Value());
        }
        if(parameter.equals("裤子")){
            ps.setInt(i,ShopTypeEnum.TROUSERS.Value());
        }
        if(parameter.equals("鞋子")){
            ps.setInt(i,ShopTypeEnum.SHOES.Value());
        }
        if(parameter.equals("食品")){
            ps.setInt(i,ShopTypeEnum.FOOD.Value());
        }
        if(parameter.equals("首饰")){
            ps.setInt(i,ShopTypeEnum.JEWELRY.Value());
        }
        if(parameter.equals("电视机")){
            ps.setInt(i,ShopTypeEnum.TELEVISION.Value());
        }
        if(parameter.equals("耳机")){
            ps.setInt(i,ShopTypeEnum.HEADSET.Value());
        }
        if(parameter.equals("路由器")){
            ps.setInt(i,ShopTypeEnum.ROUTER.Value());
        }
        if(parameter.equals("插排线")){
            ps.setInt(i,ShopTypeEnum.PATCH_CORD.Value());
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        int type=rs.getInt(columnName);
        return getType(type);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        int type=rs.getInt(columnIndex);
        return getType(type);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int type=cs.getInt(columnIndex);
        return getType(type);
    }
}
