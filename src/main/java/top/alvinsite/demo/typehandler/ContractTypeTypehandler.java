package top.alvinsite.demo.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.ContractType;
import top.alvinsite.demo.model.enums.HonorGrade;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ContractTypeTypehandler extends BaseTypeHandler<ContractType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int idx, ContractType contractType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(idx, contractType.getId());
    }

    @Override
    public ContractType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String id = resultSet.getString(columnName);
        return ContractType.getEnumById(id);
    }

    @Override
    public ContractType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String id = resultSet.getString(i);
        return ContractType.getEnumById(id);
    }

    @Override
    public ContractType getNullableResult(CallableStatement callableStatement, int idx) throws SQLException {
        String id = callableStatement.getString(idx);
        return ContractType.getEnumById(id);
    }
}
