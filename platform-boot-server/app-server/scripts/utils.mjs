import { exec } from 'node:child_process';
import { chdir } from 'node:process';
import { resolve } from 'node:path';
import { existsSync } from 'node:fs';
import { rm } from 'fs/promises';
import gulpFileSync from 'gulp-file-sync';
import gulp from 'gulp';
import { deleteAsync } from 'del';

export const root = resolve(process.cwd());

export const paths = {
    resources: {
        nmp: resolve(root, 'node_modules'),
        public: resolve(root, 'src/main/resources/public'),
        static: resolve(root, 'src/main/resources/static'),
        templates: resolve(root, 'src/main/resources/templates'),
        libs: resolve(root, 'src/main/resources/public/static'),
    },
    dist: {
        public: resolve(root, 'build/resources/main/public'),
        static: resolve(root, 'build/resources/main/static'),
        templates: resolve(root, 'build/resources/main/templates'),
    },
};

export const syncStatic = async () => {
    console.log('sync static...');
    await gulpFileSync(paths.resources.static, paths.dist.static, { recursive: true });
    console.log('sync static done.');
};

export const syncPublic = async () => {
    console.log('sync public...');
    await gulpFileSync(paths.resources.public, paths.dist.public, { recursive: true });
    console.log('sync public done.');
};

export const syncTemplate = async () => {
    console.log('sync template...');
    await gulpFileSync(paths.resources.templates, paths.dist.templates, { recursive: true });
    console.log('sync template done.');
};

export const sync = async () => {
    console.log('sync...');
    await syncStatic();
    await syncPublic();
    await syncTemplate();
    console.log('sync done.');
};

export const dev = async () => {
    console.log('dev...');
    gulp.watch([paths.resources.static], gulp.series(syncStatic));
    gulp.watch([paths.resources.public], gulp.series(syncPublic));
    gulp.watch([paths.resources.templates], gulp.series(syncTemplate));
};

export const libs = async () => {
    console.log('sync libs...');
    await deleteAsync([paths.resources.libs]);
    // jQuery
    await gulp.src(paths.resources.nmp + '/jquery/dist/jquery.min.js').pipe(gulp.dest(paths.resources.libs + '/jquery'));
    await gulp.src(paths.resources.nmp + '/jquery/dist/jquery.min.map').pipe(gulp.dest(paths.resources.libs + '/jquery'));
};

export const updateModule = async (path) => {
    console.log(`Update module [${path}]...`);
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
