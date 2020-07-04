package cdgy.pqv.mamage.vuedemo3.convert;

import cdgy.pqv.mamage.vuedemo3.util.UserOrderStatusEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderStatusHandel implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(parameter.equals("已付款")){
            ps.setInt(i, UserOrderStatusEnum.PAY.getValue());
        }
        if(parameter.equals("商家已发货")){
            ps.setInt(i,UserOrderStatusEnum.MAN_YES.getValue());
        }
        if( parameter.equals("订单已取消")){
            ps.setInt(i,UserOrderStatusEnum.USER_NO.getValue());
        }
        if(parameter.equals("确认收货")){
            ps.setInt(i,UserOrderStatusEnum.USER_YES.getValue());
        }
    }

    private  String type(int type){
        if(type==1){
            return "已付款";
        }
        if(type==2){
            return "商家已发货";
        }
        if(type==4){
            return "确认收货";
        }
        return "订单已取消";
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String mes = type(rs.getInt(columnName));
        return mes;

    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String mes = type(rs.getInt(columnIndex));
        return mes;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String mes = type(cs.getInt(columnIndex));
        return mes;
    }
}
