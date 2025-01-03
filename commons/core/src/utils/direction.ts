/**
 * =====================================================================================================================
 * 对齐方式
 * =====================================================================================================================
 */

/**
 * 对齐方式枚举
 */
export enum Direction {
    LTR = 'ltr',
    RTL = 'rtl',
}

/**
 * 对齐方式类型
 */
export type DirectionType = {
    direction: Direction;
    title: string;
    description: string;
};

/**
 * 默认对齐方式
 */
export const defaultDirection: Direction = Direction.LTR;

/**
 * 内置对齐方式
 */
export const directions: Array<DirectionType> = [
    {
        direction: Direction.LTR,
        title: '左对齐',
        description: '左对齐',
    },
    {
        direction: Direction.RTL,
        title: '右对齐',
        description: '右对齐',
    },
];

export default directions;
