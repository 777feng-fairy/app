package cdgy.pqv.mamage.vuedemo3.convert;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShopStatusHandle implements TypeHandler<String> {



    //赋值操作
    /**
     *
     * @param ps jdbc sql参数话类
     * @param i  对应数据库字段的下标
     * @param parameter  传入的参数
     * @param jdbcType  数据库类型
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if(parameter.equals("上架")){
            ps.setInt(i,1);
        }
        if(parameter.equals("下架")){
            ps.setInt(i,0);
        }
    }

    //取值

    /**
     *
     * @param rs  jdbc 结果集
     * @param columnName 数据库字段名
     * @return
     * @throws SQLException
     */
    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {

        int value=rs.getInt(columnName);

        return value==0? "下架":"上架";
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        int value=rs.getInt(columnIndex);

        return value==0? "下架":"上架";
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        int value=cs.getInt(columnIndex);
        return value==0? "下架":"上架";
    }
}
