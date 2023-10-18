import { resolve } from 'node:path';
import { execTask, updateModule } from './utils.mjs';

const root = resolve(process.cwd());

await updateModule(root);

await execTask(`pnpm install`, root);
