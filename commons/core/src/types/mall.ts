export class PayType {
    id: string | number;
    code: string;
    title: string;
    icon: string;
}

export class VipItem {
    id: string | number;
    code: string;
    title: string;
    icon: string;
    listPrice: number;
    price: number;
}

export class VipType {
    id: string | number;
    code: string;
    title: string;
    icon: string;
    items: VipItem[];
}
