/**
 * 饼图
 */
export abstract class PieData {
    name?: string = '';
    value?: number = 0;
}

/**
 * 折线图
 */
export abstract class LineData {
    name?: string = '';
    type?: string = '';
    data?: Array<number> = [];
}

export abstract class ChartVo {
    // 通用属性
    legendList?: Array<string> = [];
    xaxisDataList?: Array<string> = [];
    // 饼图数据
    pieDataList?: Array<PieData> = [];
    // 折线图数据
    lineDataList?: Array<LineData> = [];
    // 柱状图数据
    barDataList?: Array<Array<string>> = [];
}
