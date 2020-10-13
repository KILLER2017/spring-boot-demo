package top.alvinsite.demo.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.HonorGrade;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HonorGradeTypehandler extends BaseTypeHandler<HonorGrade> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int idx, HonorGrade honorGrade, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(idx, honorGrade.getId());
    }

    @Override
    public HonorGrade getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String id = resultSet.getString(columnName);
        return HonorGrade.getEnumById(id);
    }

    @Override
    public HonorGrade getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String id = resultSet.getString(i);
        return HonorGrade.getEnumById(id);
    }

    @Override
    public HonorGrade getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String id = callableStatement.getString(i);
        return HonorGrade.getEnumById(id);
    }
}
