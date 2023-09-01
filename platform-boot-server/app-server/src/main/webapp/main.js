import 'bootstrap';
import jQuery from 'jquery';
//
import '@/main.scss';

Object.assign(window, { $: jQuery, jQuery });

$(() => {
    console.log('Application has been started.');
});
