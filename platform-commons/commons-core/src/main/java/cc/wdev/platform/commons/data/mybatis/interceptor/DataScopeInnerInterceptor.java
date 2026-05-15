package cc.wdev.platform.commons.data.mybatis.interceptor;

import cc.wdev.platform.commons.utils.SecurityUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Slf4j
public class DataScopeInnerInterceptor implements InnerInterceptor {

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        //数据权限校验
        if (SecurityUtils.getDataScopes().contains("ALL")) {
            return;
        }
        //获取sql并且只处理 select
        String sql = boundSql.getSql();
        if (!sql.trim().toLowerCase().startsWith("select")) {
            return;
        }
        try {
            //只处理 zp_project 和单表查询
            Select select = (Select) CCJSqlParserUtil.parse(sql);
            PlainSelect plainSelect = select.getPlainSelect();
            if (plainSelect == null) {
                return;
            }
            //简单判断单表
            if (!(plainSelect.getFromItem() instanceof Table table)) {
                return;
            }
            //存在联表跳过
            if (plainSelect.getJoins() != null && !plainSelect.getJoins().isEmpty()) {
                return;
            }
            //只判断zp_project表
            if (!"zp_project".equalsIgnoreCase(table.getName())) {
                return;
            }
            log.info("数据权限处理前sql{}", sql);

            //增加where语句
            EqualsTo condition = new EqualsTo();
            condition.setLeftExpression(new Column("created_by"));
            condition.setRightExpression(new LongValue(SecurityUtils.getUser().getId()));
            Expression where = plainSelect.getWhere();
            if (where == null) {
                plainSelect.setWhere(condition);
            } else {
                plainSelect.setWhere(new AndExpression(where, condition));
            }
            String newSql = select.toString();
            log.info("数据权限处理后sql{}", newSql);

            //覆盖sql
            MetaObject metaObject = SystemMetaObject.forObject(boundSql);
            metaObject.setValue("sql", newSql);
        } catch (Exception e) {
            log.error("DataScope parse error", e);
        }
    }
}
