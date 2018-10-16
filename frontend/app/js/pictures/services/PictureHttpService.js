export default function pictureHttpService ($http) {
     function PictureHttpService() {

    }

    function post(picture, callbackSuccess, callbackReject) {
        console.log(picture);

        var payload  = new FormData();
        payload.append("image", picture.image);
        payload.append("category", picture.category.toUpperCase());
        payload.append("title", picture.title);
        payload.append("description", picture.description);

        console.log(payload);
        $http({
            method: 'POST',
            url: 'api/pictures/picture', 
            data: payload,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            callbackSuccess,
            callbackReject
        );
    }

    function getPage(requestParams, callbackSuccess, callbackReject) {
        var params = {
            'page-index' : requestParams.pageIndex
        }

        if(requestParams.titleBeginsWith)
            params['title-begins-with'] = requestParams.titleBeginsWith;

        $http({
            method: 'GET',
            url: 'api/pictures/picture',
            contentType: 'application/json', 
            params: params
        }).then(
            callbackSuccess,
            callbackReject
        );
    }

    function deletePicture(requestParams, callbackSuccess, callbackReject) {
        $http({
            method: 'DELETE',
            url: 'api/pictures/picture',
            contentType: 'application/json', 
            params: {'id' : requestParams.id}
        }).then(
            callbackSuccess,
            callbackReject
        );
    }

    function put(picture, callbackSuccess, callbackReject) {
        var payload  = new FormData();
        payload.append("id", picture.id);
        payload.append("image", picture.image);
        payload.append("category", picture.category.toUpperCase());
        payload.append("title", picture.title);
        payload.append("description", picture.description);

        $http({
            method: 'PUT',
            url: 'api/pictures/picture',
            data: payload,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(
            callbackSuccess,
            callbackReject
        );
    }

    PictureHttpService.prototype.post = post;
    PictureHttpService.prototype.getPage = getPage;
    PictureHttpService.prototype.deletePicture = deletePicture;
    PictureHttpService.prototype.put = put;

    return new PictureHttpService();
}