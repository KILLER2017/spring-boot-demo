/*
package top.alvinsite.demo.typehandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import top.alvinsite.demo.model.enums.BookType;
import top.alvinsite.demo.model.enums.ProjectLevel;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(BookType.class)
public class BookTypeTypehandler extends BaseTypeHandler<BookType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int idx, BookType bookType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(idx, bookType.getValue());
    }

    @Override
    public BookType getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String id = resultSet.getString(columnName);
        return BookType.getEnumById(id);
    }

    @Override
    public BookType getNullableResult(ResultSet resultSet, int idx) throws SQLException {
        String id = resultSet.getString(idx);
        return BookType.getEnumById(id);
    }

    @Override
    public BookType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String id = callableStatement.getString(i);
        return BookType.getEnumById(id);
    }
}
*/
