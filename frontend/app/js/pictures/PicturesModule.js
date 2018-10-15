import angular from 'angular'

import inputFormController from './components/input/InputFormController'
import gridController from './components/grid/GridController'

import pictureHttpService from './services/PictureHttpService'

import fileModelDir from './directives/FileModelDir'

export default angular
    .module('pictures', [])
    .controller('InputFormController', inputFormController)
    .controller('GridController', gridController)
    .factory('PictureHttpService', pictureHttpService)
    .directive('fileModel', fileModelDir)
    .name;
