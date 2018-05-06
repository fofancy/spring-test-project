import './assets/styles/app.css'
import 'bootstrap/dist/css/bootstrap.css'
import angular from 'angular'
import ngRoute from 'angular-route'

import pictures from './js/pictures/PicturesModule'

import routeConfig from './app.routes'

export default angular
    .module('app', [ngRoute, pictures]) 
    .config(routeConfig)
    .name;


