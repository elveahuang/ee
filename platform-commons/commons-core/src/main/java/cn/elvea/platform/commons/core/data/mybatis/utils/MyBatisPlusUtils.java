package cn.elvea.platform.commons.core.data.mybatis.utils;

import cn.elvea.platform.commons.core.utils.CollectionUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.google.common.collect.Lists;
import org.springframework.data.domain.*;

import java.util.List;

/**
 * @author elvea
 * @see MyBatisPlusUtils
 * @since 0.0.1
 */
public abstract class MyBatisPlusUtils {

    /**
     * 把MyBatis-Plus的分页对象转换成为Spring-Data的分页对象
     */
    public static <T> Page<T> toSpringDataPage(IPage<T> mybatisPlusPage) {
        // Spring Data Pageable 目前只支持 int 类型的 page 和 size
        int page = Math.toIntExact(mybatisPlusPage.getCurrent() - 1);
        int size = Math.toIntExact(mybatisPlusPage.getSize());
        List<Sort.Order> orderList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(mybatisPlusPage.orders())) {
            for (OrderItem item : mybatisPlusPage.orders()) {
                if (item.isAsc()) {
                    orderList.add(Sort.Order.asc(item.getColumn()));
                } else {
                    orderList.add(Sort.Order.desc(item.getColumn()));
                }
            }
        }
        // 转换分页对象
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderList));
        return new PageImpl<>(mybatisPlusPage.getRecords(), pageable, mybatisPlusPage.getTotal());
    }

    /**
     * 把Spring-Data的分页请求对象转换成为MyBatis-Plus的分页对象
     */
    public static <E> com.baomidou.mybatisplus.extension.plugins.pagination.Page<E> getMyBatisPlusPage(Pageable pageable) {
        return new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(pageable.getPageNumber(), pageable.getPageSize());
    }

}
