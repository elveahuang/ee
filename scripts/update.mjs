import { resolve } from 'node:path';
import { formatModule, initModule, updateModule } from './utils.mjs';

const root = resolve(process.cwd());
console.log(`Current workspace - ${root}`);
// 更新标准模块依赖
await updateModule(resolve(root));
await updateModule(resolve(root, 'commons/core'));
await updateModule(resolve(root, 'commons/mobile'));
await updateModule(resolve(root, 'commons/webapp'));
await updateModule(resolve(root, 'apps/admin'));
await updateModule(resolve(root, 'apps/mobile'));
await updateModule(resolve(root, 'apps/webapp'));
await updateModule(resolve(root, 'docs'));
// 安装模块依赖
await initModule(resolve(root));
// 格式化代码
await formatModule(resolve(root));
