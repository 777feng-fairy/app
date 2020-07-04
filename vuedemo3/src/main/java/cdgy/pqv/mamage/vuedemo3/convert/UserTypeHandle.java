package cdgy.pqv.mamage.vuedemo3.convert;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTypeHandle implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(parameter.equals("一般会员")){
            ps.setInt(i,0);
        }else {
            ps.setInt(i,1);
        }
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName)==0? "一般会员":"管理员";
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex)==0? "一般会员":"管理员";
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex)==0? "一般会员":"管理员";
    }
}
