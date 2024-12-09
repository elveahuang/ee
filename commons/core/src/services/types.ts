export class WeWorkFileObject {}
export class WeChatFileObject {}
export class FileObject {
    public id: string;
    public object: WeChatFileObject | WeWorkFileObject;
}
