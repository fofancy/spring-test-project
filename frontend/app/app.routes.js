export default function routeConfig($routeProvider, $locationProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "views/input-form.html"
        })
        .when("/data", {
            templateUrl: "views/navigate-data.html"
        })

    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}