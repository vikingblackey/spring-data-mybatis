package org.springframework.data.mybatis.repository.dialect;

import java.sql.Types;

import org.springframework.data.mybatis.repository.dialect.identity.IdentityColumnSupport;
import org.springframework.data.mybatis.repository.dialect.identity.SQLiteIdentityColumnSupport;
import org.springframework.data.mybatis.repository.dialect.pagination.AbstractLimitHandler;
import org.springframework.data.mybatis.repository.dialect.pagination.LimitHandler;

public class SQLiteDialect extends Dialect {
	
    private static final AbstractLimitHandler LIMIT_HANDLER = new AbstractLimitHandler() {
        @Override
        public boolean supportsLimit() {
            return true;
        }

        @Override
        public boolean bindLimitParametersInReverseOrder() {
            return true;
        }


        @Override
        public String processSql(boolean hasFirstRow, String columns, String from, String condition, String sorts) {
            String sql = "select " + columns + from + condition + sorts;
            return sql + (hasFirstRow ? " limit #{pageSize} offset #{offset}" : " limit #{pageSize}");
        }
    };
	
    public SQLiteDialect() {
        registerColumnType(Types.BIT, "integer");
        registerColumnType(Types.TINYINT, "tinyint");
        registerColumnType(Types.SMALLINT, "smallint");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.FLOAT, "float");
        registerColumnType(Types.REAL, "real");
        registerColumnType(Types.DOUBLE, "double");
        registerColumnType(Types.NUMERIC, "numeric");
        registerColumnType(Types.DECIMAL, "decimal");
        registerColumnType(Types.CHAR, "char");
        registerColumnType(Types.VARCHAR, "varchar");
        registerColumnType(Types.LONGVARCHAR, "longvarchar");
        registerColumnType(Types.DATE, "date");
        registerColumnType(Types.TIME, "time");
        registerColumnType(Types.TIMESTAMP, "timestamp");
        registerColumnType(Types.BINARY, "blob");
        registerColumnType(Types.VARBINARY, "blob");
        registerColumnType(Types.LONGVARBINARY, "blob");
        // registerColumnType(Types.NULL, "null");
        registerColumnType(Types.BLOB, "blob");
        registerColumnType(Types.CLOB, "clob");
        registerColumnType(Types.BOOLEAN, "integer");
    }

    @Override
    public String getDatabaseId() {
        return "sqlite";
    }

    @Override
    public LimitHandler getLimitHandler() {
        return LIMIT_HANDLER;
    }

    @Override
    public char closeQuote() {
        return '`';
    }

    @Override
    public char openQuote() {
        return '`';
    }

    @Override
    public IdentityColumnSupport getIdentityColumnSupport() {
        return new SQLiteIdentityColumnSupport();
    }

}
