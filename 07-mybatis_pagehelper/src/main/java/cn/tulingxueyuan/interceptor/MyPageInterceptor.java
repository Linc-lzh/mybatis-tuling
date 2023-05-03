package cn.tulingxueyuan.interceptor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;


/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 自定义分页插件实现的简易版分页插件
 */
@Intercepts({
        @Signature(type = Executor.class,method = "query" ,args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class} ), // 需要代理的对象和方法
        @Signature(type = Executor.class,method = "query" ,args ={MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class} ) // 需要代理的对象和方法
})
public class MyPageInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("简易版的分页插件：逻辑分页改成物理分页");

        // 修改sql 拼接Limit 0,10
        Object[] args = invocation.getArgs();
        // MappedStatement 对mapper映射文件里面元素的封装
        MappedStatement ms= (MappedStatement) args[0];
        // BoundSql 对sql和参数的封装
        Object parameterObject=args[1];
        BoundSql boundSql = ms.getBoundSql(parameterObject);
        // RowBounds 封装了逻辑分页的参数 ：当前页offset，一页数limit
        RowBounds rowBounds= (RowBounds) args[2];

        // 拿到原来的sql语句
        String sql = boundSql.getSql();
        String limitSql=sql+ " limit "+rowBounds.getOffset()+","+ rowBounds.getLimit();

        //将分页sql重新封装一个BoundSql 进行后续执行
        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), limitSql, boundSql.getParameterMappings(), parameterObject);

        // 被代理的对象
        Executor executor= (Executor) invocation.getTarget();
        CacheKey cacheKey = executor.createCacheKey(ms, parameterObject, rowBounds, pageBoundSql);
        // 调用修改过后的sql继续执行查询
        return  executor.query(ms,parameterObject,rowBounds, (ResultHandler) args[3],cacheKey,pageBoundSql);
    }
}
