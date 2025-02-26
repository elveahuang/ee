package cc.elvea.platform.commons.data.mybatis.utils;

import cc.elvea.platform.commons.utils.CollectionUtils;
import cc.elvea.platform.commons.web.request.PageRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * @author elvea
 */
public abstract class MyBatisPlusUtils {

    /**
     * 把MyBatis-Plus的分页对象转换成为Spring-Data的分页对象
     */
    public static <T> org.springframework.data.domain.Page<T> toSpringDataPage(IPage<T> mybatisPlusPage) {
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
        Pageable pageable = org.springframework.data.domain.PageRequest.of(page, size, Sort.by(orderList));
        return new PageImpl<>(mybatisPlusPage.getRecords(), pageable, mybatisPlusPage.getTotal());
    }

    /**
     * 把Spring-Data的分页请求对象转换成为MyBatis-Plus的分页对象
     */
    public static <E> Page<E> getMyBatisPlusPage(Pageable pageable) {
        Page<E> page = new Page<>(pageable.getPageNumber() + 1, pageable.getPageSize());
        List<OrderItem> orders = pageable.getSort().get().map((e) ->
                Sort.Direction.ASC.equals(e.getDirection()) ? OrderItem.asc(e.getProperty()) : OrderItem.desc(e.getProperty())
        ).toList();
        page.setOrders(orders);
        return page;
    }

    /**
     * 把Spring-Data的分页请求对象转换成为MyBatis-Plus的分页对象
     */
    public static <E> Page<E> getMyBatisPlusPage(PageRequest pageRequest) {
        Page<E> page = new Page<>(pageRequest.getPage(), pageRequest.getSize());
        if (!Strings.isNullOrEmpty(pageRequest.getSort())) {
            if ("desc".equalsIgnoreCase(pageRequest.getOrder())) {
                page.setOrders(Collections.singletonList(OrderItem.desc(pageRequest.getSort())));
            } else {
                page.setOrders(Collections.singletonList(OrderItem.asc(pageRequest.getSort())));
            }
        }
        return page;
    }

    /**
     * 获取一个仅查询一条记录的分页对象
     */
    public static <E> Page<E> getLimitPage() {
        return new Page<>(1, 1);
    }

}
