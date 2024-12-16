import { Key, PageParams, PageResult, R } from '@commons/core/types/common';
import { isEmpty, isFunction } from 'radash';

export class DataTableState<T = any> {
    tableSelectedRowKeys: Key[];
    tableSelectedRows: T[];
}

export class DataTableColumn {
    dataKey: string;
    title: string;
    label: string;
}

export class DataTable<T = any> {
    loading: boolean = false;
    finished: boolean = false;
    pagination: {
        page: number;
        size: number;
        total: number | string;
    } = {
        page: 1,
        size: 10,
        total: 0,
    };
    sort: {
        property?: string;
        order?: string;
    } = {
        property: '',
        order: '',
    };
    columns: any[] = [];
    items: T[] = [];
}

export class DataTableOptions {
    columns?: any[] = [];
    pagination?: {
        page: number;
        size: number;
    } = {
        page: 1,
        size: 10,
    };
    sort?: {
        property?: string;
        order?: string;
    } = {
        property: '',
        order: '',
    };
}

export const defaultDataTableOptions: DataTableOptions = {
    columns: [],
    pagination: {
        page: 1,
        size: 10,
    },
    sort: {
        property: '',
        order: '',
    },
};

export const initialize = (options: DataTableOptions): DataTable => {
    options = { ...defaultDataTableOptions, ...options };
    console.log(`DataTable.initialize...`);
    console.log(options);
    const table: DataTable = new DataTable();
    if (!isEmpty(options.columns)) {
        table.columns = options.columns;
    }
    if (options.pagination.page > 0) {
        table.pagination.page = options.pagination.page;
    }
    if (options.pagination.size > 0) {
        table.pagination.size = options.pagination.size;
    }
    return table;
};

export const handleResult = <T>(dataTable: DataTable, result: R<PageResult<T>>, callback?: Function): DataTable => {
    console.log(`DataTable.handleResult...`);
    console.log(dataTable);
    const items: T[] = [];
    if (result?.data?.content.length) {
        for (const item of result.data.content) {
            if (callback && isFunction(callback)) {
                items.push(callback(item));
            } else {
                items.push(item);
            }
        }
    }
    Object.assign(dataTable, {
        items: items,
        pagination: {
            page: result?.data?.pageable?.pageNumber + 1,
            size: result?.data?.pageable?.pageSize,
            total: result?.data?.totalElements,
        },
        loading: false,
        finished: result?.data?.last,
    });
    return dataTable;
};

export const handleParams = (dataTable: DataTable): PageParams => {
    console.log(`DataTable.handleParams...`);
    console.log(dataTable);
    return {
        page: dataTable.pagination.page <= 1 ? 1 : dataTable.pagination.page,
        size: dataTable.pagination.size,
        sort: dataTable.sort.property,
        order: dataTable.sort.order,
    };
};

export const setLoadingStatus = (dataTable: DataTable, loading: boolean = false): DataTable => {
    return {
        ...dataTable,
        ...{
            loading: loading,
        },
    };
};

export const useDataTable = (): {
    initialize: (options: DataTableOptions) => DataTable;
    setLoadingStatus: (dataTable: DataTable, loading: boolean) => DataTable;
    handleResult: <T>(dataTable: DataTable, result: R<PageResult<T>>, callback?: Function) => DataTable;
    handleParams: (dataTable: DataTable) => PageParams;
} => {
    return {
        initialize: initialize,
        setLoadingStatus: setLoadingStatus,
        handleResult: handleResult,
        handleParams: handleParams,
    };
};
