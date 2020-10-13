package top.alvinsite.demo.typehandler;

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


@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(ProjectLevel.class)
public class ProjectLevelTypeHandler extends BaseTypeHandler<ProjectLevel> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int idx, ProjectLevel projectLevel, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(idx, projectLevel.getId());
    }

    @Override
    public ProjectLevel getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return ProjectLevel.getEnumById(id);
    }

    @Override
    public ProjectLevel getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return ProjectLevel.getEnumById(id);
    }

    @Override
    public ProjectLevel getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return ProjectLevel.getEnumById(id);
    }
}
