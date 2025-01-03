import { EChartsOption } from 'echarts';
import { BarChart, LineChart, PieChart } from 'echarts/charts';
import { GridComponent, LegendComponent, TitleComponent, TooltipComponent } from 'echarts/components';
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';

export const getEChartsDefaultOption = (): EChartsOption => {
    return {
        series: [],
    };
};

/**
 * 用于加载默认组件
 */
export const useECharts = (): void => {
    use([
        CanvasRenderer,
        PieChart,
        LineChart,
        TitleComponent,
        TooltipComponent,
        LegendComponent,
        CanvasRenderer,
        BarChart,
        TitleComponent,
        TooltipComponent,
        LegendComponent,
        GridComponent,
    ]);
};
