package com.s2u2m.slancer.core.enumhandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeReference;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Xia
 * @date 2018/4/18
 */
public class IntEnumTypeHandler<ET extends Enum<ET> & IIntEnum>
        implements TypeHandler<ET> {

    private final Map<Integer, ET> enumMap = new HashMap<>();

	public IntEnumTypeHandler(Class<ET> type) {
        for (ET et : type.getEnumConstants()) {
	        enumMap.put(et.getValue(), et);
        }
	}

	private ET int2Enum(int value) {
	    return enumMap.get(value);
    }

    @Override
    public void setParameter(
            PreparedStatement preparedStatement,
            int i, ET et, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, et.getValue());
    }

    @Override
    public ET getResult(ResultSet resultSet, String s) throws SQLException {
        int value = resultSet.getInt(s);
        return int2Enum(value);
    }

    @Override
    public ET getResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        return int2Enum(value);
    }

    @Override
    public ET getResult(CallableStatement callableStatement, int i) throws SQLException {
	    int value = callableStatement.getInt(i);
	    return int2Enum(value);
    }
}
