import gulp from 'gulp';
import gulpFileSync from 'gulp-file-sync';
import { dirname, resolve } from 'path';
import { deleteAsync } from 'del';
import { fileURLToPath } from 'url';

// =====================================================================================================================
// Commons
// =====================================================================================================================

const getFilename = (metaUrl) => {
    return fileURLToPath(metaUrl);
};

const getDirname = (metaUrl) => {
    return dirname(getFilename(metaUrl));
};

const __filename = getFilename(import.meta.url);

const __dirname = getDirname(import.meta.url);

console.log(`Current Script - ${__filename}`);

console.log(`Current Workspace - ${__dirname}`);

// =====================================================================================================================
// Paths
// =====================================================================================================================

const paths = {
    resources: {
        nmp: resolve(__dirname, 'node_modules'),
        public: resolve(__dirname, 'src/main/resources/public'),
        static: resolve(__dirname, 'src/main/resources/static'),
        templates: resolve(__dirname, 'src/main/resources/templates'),
        libs: resolve(__dirname, 'src/main/resources/public/static'),
    },
    dist: {
        public: resolve(__dirname, 'build/resources/main/public'),
        static: resolve(__dirname, 'build/resources/main/static'),
        templates: resolve(__dirname, 'build/resources/main/templates'),
    },
};

// =====================================================================================================================
// Tasks
// =====================================================================================================================

const showEnv = async () => {
    console.log(`Current workspace - ${__dirname}`);
};

const syncLibs = async () => {
    console.log('sync libs...');
    await deleteAsync([paths.resources.libs]);
    // jQuery
    await gulp.src(paths.resources.nmp + '/jquery/dist/jquery.min.js').pipe(gulp.dest(paths.resources.libs + '/jquery'));
    await gulp.src(paths.resources.nmp + '/jquery/dist/jquery.min.map').pipe(gulp.dest(paths.resources.libs + '/jquery'));
    // Layui
    await gulp.src(paths.resources.nmp + '/layui/dist/**').pipe(gulp.dest(paths.resources.libs + '/layui'));
};

const syncStatic = async () => {
    console.log('sync static...');
    await gulpFileSync(paths.resources.static, paths.dist.static, { recursive: true });
    console.log('sync static done.');
};

const syncPublic = async () => {
    console.log('sync public...');
    await gulpFileSync(paths.resources.public, paths.dist.public, { recursive: true });
    console.log('sync public done.');
};

const syncTemplate = async () => {
    console.log('sync template...');
    await gulpFileSync(paths.resources.templates, paths.dist.templates, { recursive: true });
    console.log('sync template done.');
};

const sync = async () => {
    console.log('sync...');
    await syncStatic();
    await syncPublic();
    await syncTemplate();
    console.log('sync done.');
};

const dev = async () => {
    console.log('dev...');
    gulp.watch([paths.resources.static], gulp.series(syncStatic));
    gulp.watch([paths.resources.public], gulp.series(syncPublic));
    gulp.watch([paths.resources.templates], gulp.series(syncTemplate));
};

const libs = async () => {
    console.log('libs...');
    await showEnv();
    await syncLibs();
};

const clean = async () => {
    console.log('clean...');
    await deleteAsync([resolve(__dirname, paths.resources.static)]);
    console.log('clean done.');
};

export { clean, sync, dev, libs };

export default dev;
