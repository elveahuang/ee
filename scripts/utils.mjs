import { cp, mkdir, rm } from 'fs/promises';
import { exec } from 'node:child_process';
import { existsSync } from 'node:fs';
import { resolve } from 'node:path';
import { chdir } from 'node:process';

export const packageManager = 'pnpm';

/**
 * 格式化模块模块
 */
export const formatModule = async (path, manager = packageManager) => {
    console.log(`formatModule [${path}] start.`);
    chdir(path);
    // 格式化代码
    await execTask(`${manager} run prettier`, path);
};

/**
 * 初始化模块
 */
export const initModule = async (path, manager = packageManager) => {
    console.log(`initModule [${path}] start.`);
    chdir(path);
    // 安装模块依赖
    await execTask(`${manager} install`, path);
};

/**
 * 更新模块
 */
export const updateModule = async (path) => {
    console.log(`updateModule [${path}] start.`);
    chdir(path);
    if (existsSync(resolve(path, 'package-lock.json'))) {
        await rm(resolve(path, 'package-lock.json'));
    }
    if (existsSync(resolve(path, 'pnpm-lock.yaml'))) {
        await rm(resolve(path, 'pnpm-lock.yaml'));
    }
    if (existsSync(resolve(path, 'node_modules'))) {
        await rm(resolve(path, 'node_modules'), { recursive: true });
    }
    await execTask(`ncu -u`, path);
};

/**
 * 构建模块
 */
export const buildModule = async (module, profile, manager = packageManager) => {
    console.log(`buildModule [module - ${module}, profile - ${profile}] start.`);
    await execTask(`${manager} run build:${module}:${profile}`);
};

/**
 * 执行任务
 */
export const execTask = async (task, path) => {
    console.log(`execTask [${task}] start.`);
    return new Promise((resolve, reject) => {
        exec(task, { cwd: path }, (e) => {
            if (e) {
                console.log(e);
                reject();
            } else {
                console.log(`execTask [${task}] done.`);
                resolve();
            }
        });
    });
};

/**
 * 清空指定目录
 */
export const clearPath = async (path) => {
    console.log(`clearPath [${path}] start.`);
    if (existsSync(path)) {
        await rm(path, { recursive: true });
    }
    await mkdir(path, { recursive: true });
};

/**
 * 复制指定目录
 */
export const copyPath = async (source, target) => {
    console.log(`copyPath [${source}] to [${target}] start.`);
    await cp(source, target, { recursive: true });
};
