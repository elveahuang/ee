import { PageParams, PageResult, R } from '@commons/core/types';
import { isFunction } from 'radash';

export class DataList {
    refreshing: boolean = false;
    loading: boolean = false;
    finished: boolean = false;
    pagination: {
        page: number;
        size: number;
        total: number | string;
    } = {
        page: 0,
        size: 10,
        total: 0,
    };
    items: any[] = [];
}

export const initialize = (pagination: { size: number } = { size: 12 }): DataList => {
    const list: DataList = new DataList();
    if (pagination.size > 0) {
        list.pagination.size = pagination.size;
    }
    return list;
};

export const handleResult = <T>(dataList: DataList, result: R<PageResult<T>>, callback?: Function): DataList => {
    if (result?.data?.content.length) {
        for (const item of result.data.content) {
            if (callback && isFunction(callback)) {
                dataList.items.push(callback(item));
            } else {
                dataList.items.push(item);
            }
        }
    }
    dataList.pagination.page = result?.data?.pageable?.pageNumber + 1;
    dataList.pagination.size = result?.data?.pageable?.pageSize;
    dataList.pagination.total = result?.data?.totalElements;
    dataList.loading = false;
    dataList.refreshing = false;
    dataList.finished = result?.data?.last;
    return dataList;
};

export const handleParams = (dataList: DataList): PageParams => {
    dataList.loading = true;
    if (dataList.refreshing) {
        dataList.items = [];
    }
    return {
        page: dataList.pagination.page + 1,
        size: dataList.pagination.size,
    };
};

export const useDataList = () => {
    return {
        initialize: initialize,
        handleResult: handleResult,
        handleParams: handleParams,
    };
};
